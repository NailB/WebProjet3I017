/**
 * 
 */
package tools;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

/**
 * @author LAOUER Walid
 *
 */
public class MessageTools {


	/**
	 * @param key
	 * @param text
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */


	public static JSONObject postTwist(int id_user ,String text, MongoCollection<Document> message_collection) throws SQLException, JSONException, InstantiationException, IllegalAccessException{
		Document query = new Document();

		System.out.println("user connected!");
		query.append("login", UserTools.getLogin(id_user));
		query.append("date", new Date());
		query.append("content", text);
		List<String> d = new ArrayList<String>();
		query.append("comments", d);

		message_collection.insertOne(query);

		FindIterable<Document> f = message_collection.find(query);
		MongoCursor<Document> cur = f.iterator();

		boolean res = false;
		while(cur.hasNext()) {
			cur.next();
			res = true;
		}
		if (res) {
			return ServiceTools.serviceAccepted().put(UserTools.getLogin(id_user)+" twist has been posted successfully", 1);
		}
		return ServiceTools.serviceRefused(Data.MESSAGE_ERROR_JSON, Data.CODE_ERROR_JSON);
	}

	/**
	 * @param key
	 * @param idMessage
	 * @param m
	 * @return
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	public static JSONObject RemoveTwist(int id_user, String id_message,Connection conn, MongoCollection<Document> m) throws JSONException, SQLException {
		Document query = new Document();
		query.append("_id",new ObjectId(id_message));
		FindIterable<Document> fi = m.find(query);
		MongoCursor<Document> c = fi.iterator();
		boolean message_exist = false;
		while(c.hasNext()) {
			System.out.println(c.next());
			System.out.println("twist exist");
			message_exist = true;
		}
		if(checkAuthor(id_user, id_message, conn, m)) {
			m.deleteOne(query);
		}
		if(message_exist)
			return ServiceTools.serviceAccepted().put("Message" , "twist has been removed successfully");
		else
			return ServiceTools.serviceAccepted().put("Message", "You are not allowed to remove this twist");

	}

	// A comprendre?!
	public static boolean checkAuthor(int userID, String id_message, Connection conn, MongoCollection<Document> message_collection) throws SQLException {

		Document query = new Document();
		query.append("login", UserTools.getLogin(userID));
		query.append("_id", new ObjectId(id_message));

		message_collection.find(query);

		FindIterable<Document> f = message_collection.find(query);
		MongoCursor<Document> cur = f.iterator();

		boolean res = false;
		while(cur.hasNext()) {

			cur.next();

			res = true;
		}

		return res;

	}

	public static JSONObject postComment(int id_user, String id_message, String text, MongoCollection<Document> message_collection, Connection co) throws JSONException, SQLException {
		// TODO Auto-generated method stub
		Document comments = new Document();
		ObjectId objectId = genererObjectId(message_collection);
		comments.append("id_comment", objectId);
		GregorianCalendar c = new GregorianCalendar();

		comments.put("date", c.getTime());

		//Document auteur = new Document();
		//auteur.append("login", UserTools.getLogin(id_user));
		comments.append("author",UserTools.getLogin(id_user));
		comments.append("content", text);
		comments.append("idMessage", id_message);

		Document content = new Document("comments", comments);
		Document push = new Document("$push", content);
		Document filter = new Document("_id", new ObjectId(id_message));

		message_collection.updateOne(filter, push);
		comments.append("idMessage", id_message);
		//		Bson filter = new Document("_id", new ObjectId(id_message));
		//		Bson Q = new Document(UserTools.getLogin(UserTools.getIdFromKey(key, co)), text);
		//		Bson query = new Document("comments", Q);
		//		Bson updateOperationDocument = new Document("$push", query);
		//		message_collection.updateOne(filter, updateOperationDocument);
		return ServiceTools.serviceAccepted().put("Comment has been added", 1);
	}



	public static JSONObject removeComment(String id_message, String id_comment, MongoCollection<Document> message_collection) throws JSONException {
		Bson filter = new Document("id_comment", new ObjectId(id_comment));
		Bson query = new Document("comments", filter);
		Document msg_query = new Document("$pull", query);
		System.out.println(msg_query.toString());
		Bson updateOperationDocument = new Document("_id", new ObjectId(id_message));
		message_collection.updateOne(updateOperationDocument, msg_query);
		//		if(found != null)
		//			System.out.println("element found");
		//		Bson updateOperationDocument = new Document("$pull", query);
		//		message_collection.updateOne(filter, updateOperationDocument);
		//MongoCursor<Document> c = message_collection.find().iterator();

		//		while(c.hasNext())
		//			System.out.println("test");
		return ServiceTools.serviceAccepted().put("Comment deleted", 1);
	}

	public static ObjectId genererObjectId(MongoCollection<Document> message_collection)
	{

		ObjectId id;
		while(true)
		{
			id = new ObjectId();
			Document query = new Document();
			/*
			 * When <boolean> is true, $exists matches the documents that contain the field,
			 * including documents where the field value is null. If <boolean> is false, 
			 * the query returns only the documents that do not contain the field.
			 * This query will select all documents in the message collection where the id_comment field value
			 * does not equal to id*/
			query.append("id_comment", new Document("$exists", true).append("$ne", id));
			//Document f = message_collection.find(query).first();
			//System.out.println(query.toString());
			//FindIterable<Document> cursor = message_collection.find(query);
			//if (((Document) cursor).size() != 0)
			break;
		}
		return id;
	}

	public static JSONArray listTwist(String login ,int nbMessage, boolean orderAsc, MongoCollection<Document> message_collection) 
	{
		Document query = new Document();
		query.append("login", login);
		FindIterable<Document> fi = message_collection.find(query);

		if (!orderAsc)
			fi.sort(new Document("date", -1));
		else
			fi.sort(new Document("date", 1));

		MongoCursor<Document> cur = fi.iterator();
		JSONArray userMessages = new JSONArray();
		try
		{
			int cpt = 0;
			while( (cpt < nbMessage) && (cur.hasNext()))
			{
				JSONObject json = new JSONObject();
				Document document = cur.next();

				json.put("login", document.get("login"));
				json.put("content", document.get("content"));
				json.put("date", document.get("date"));
				json.put("author", document.get("author"));
				json.put("id", document.get("_id"));
				json.put("comments", listComment(document.get("_id").toString(), message_collection));

				userMessages.put(json);

				cpt++;
			}
			//JSONObject ajout = new JSONObject();
			//			if(!cur.hasNext())
			//				ajout.put("end", "no");
			//			else
			//				ajout.put("end", "yes");

			//userMessages.put(ajout);

			return userMessages;
		}
		catch(Exception e)
		{
			System.err.println("listMessage : " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static JSONArray listComment(String id_twist, MongoCollection<Document> message_collection) throws JSONException {
		Document filter = new Document("_id", new ObjectId(id_twist));
		FindIterable<Document> fi = message_collection.find(filter);
		MongoCursor<Document> cursor = fi.iterator();
		Document twist = cursor.next();
		ArrayList<Document> list_comment= (ArrayList<Document>) twist.get("comments");
		Iterator<Document> cur = list_comment.iterator();
		//List<JSONObject> l = new ArrayList<JSONObject>();

		JSONArray userComments = new JSONArray();
		while(cur.hasNext())
		{	
			JSONObject json = new JSONObject();
			Document comment = cur.next();
			//System.out.print("heeere");

			json.put("id_comments", comment.get("_id"));
			json.put("date", comment.get("date"));
			json.put("author", comment.get("author"));
			json.put("content", comment.get("content"));
			//while(cursor.next()) {
			userComments.put(json);
			//l.add(json);
		}
		//System.out.print("heeere");
		//JSONObject res = new JSONObject();
		//res.put("Comments",l);
		return userComments;
	}

	public static JSONArray getTwistByDate(String login, String from, String to, MongoCollection<Document> m, Connection co) throws InstantiationException, IllegalAccessException, SQLException, JSONException {
		Document query = new Document();
		JSONArray list_Date = new JSONArray();
		JSONObject listfollowers = tools.FriendTools.listAbonnement(tools.UserTools.getIdUser(login, co), co);
		for (Iterator iterator = ((JSONObject) listfollowers.get("Followers")).keys(); iterator.hasNext();) {
			Object o = iterator.next();
			JSONObject json = new JSONObject();
			query.append("login", o.toString()); // on récupere le login de tous les amis de l'utilisateur et on le rajoute dans la requete 
			query.put("date", BasicDBObjectBuilder.start("$gte", from).add("$lte", to).get()); // on ajout dans notre requete les dates
			FindIterable<Document> fi = m.find(query);
			MongoCursor<Document> cur = fi.iterator();
			
			Document document = cur.next();
			json.put("login", document.get("login"));
			json.put("content", document.get("content"));
			json.put("date", document.get("date"));
			json.put("author", document.get("author"));
			json.put("id", document.get("_id"));
			json.put("comments", listComment(document.get("_id").toString(), m));
			// une fois les twists de cette personne récupérer on la met dans jsonArray
			list_Date.put(json);
		}


		return list_Date;
	}

}



/**
 * 
 */
package db;

/**
 * @author LAOUER Walid
 *
 */

	public class DBStatic {
  		
		public final static String mysql_host = "localhost:3306";//"sql7.freemysqlhosting.net:3306";
		public final static String mysql_db = "bd";//"sql7279445";
		public final static String mysql_username ="root" ;//"sql7279445";
		public final static String mysql_password ="";//"4rjgZKRwbq";
//		public final static boolean is_pooling = false;	
//		public final static String mysql_host = "sql7.freemysqlhosting.net:3306";
//		public final static String mysql_db = "sql7279445";
//		public final static String mysql_username ="sql7279445";
//		public final static String mysql_password ="4rjgZKRwbq";
		public final static boolean is_pooling = true;
	//	*/    
		
		
	/*
	 * Un pooling c'est comme une boite , il ne ferme pas vraiment pour eviter qu'à
	 * chaqiue fois on doit ouvrir/fermer la connexion//
	 */
}

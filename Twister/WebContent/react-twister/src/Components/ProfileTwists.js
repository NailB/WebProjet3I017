import React from 'react'
import {Button, ButtonGroup, Container, Dropdown, Media} from "react-bootstrap";
import Avatar from "@material-ui/core/Avatar";
import CommentList from "./CommentList";
import Cookies from "js-cookie";
import axios from "axios";
import Form from "react-bootstrap/Form";
class ProfileTwists extends React.Component{
    constructor(props){
        super(props)
        this.state={
            order:false,
            nbTwist:11,
            listTwists:[],
            Refresh:false
        }
        this.changeState=this.changeState.bind(this)
        //Don't need to bind remove twist using function this way
    }


    changeState(){
        this.setState(prevStat => {
            return {Refresh: !prevStat.Refresh}
        })
    }


    componentDidMount() {
        const params ={
            key:Cookies.get('key'),
            orderAsc:this.state.order,
            nbTwist: this.state.nbTwist,
        }
        console.log(params.key)
        console.log(params.orderAsc)
        console.log(params.nbTwist)
        axios.post(`http://localhost:8080/Twister/ListTwist`, null, {params}).then(res => {
            console.log(res);
            console.log(res.data);
            if(res.data.ErrorCode===1000) { //user not connected
                alert(res.data.Message)
            }else if(res.data.ErrorCode===-1){
                alert(res.data.Message)
            }
            else{
                this.setState({listTwists: res.data.My_twists})
            }

        })

    }


    componentWillUpdate(PrevState) {
        if(this.state.Refresh){
         const params ={
            key:Cookies.get('key'),
            orderAsc:this.state.order,
            nbTwist: this.state.nbTwist,
        }
        console.log(params.key)
        console.log(params.orderAsc)
        console.log(params.nbTwist)
        axios.post(`http://localhost:8080/Twister/ListTwist`, null, {params}).then(res => {
            console.log(res);
            console.log(res.data);
            if(res.data.ErrorCode===1000) { //user not connected
                alert(res.data.Message)
            }else if(res.data.ErrorCode===-1){
                alert(res.data.Message)
            }
            else{
                this.setState({listTwists: res.data.My_twists})
            }

        })

    }}


    RemoveTwist = (id) => (e) => {
        e.preventDefault();
        const params={
            key:Cookies.get('key'),
            id_message:id,
        }
        axios.post(`http://localhost:8080/Twister/RemoveTwist`,null, {params}).then(res => {
            console.log(res);
            console.log(res.data);
            console.log(params.key)
            console.log(params.id_message)
            if(res.data.ErrorCode===-1){// Missing params
                alert("Missing params:")
            }
            else if(res.data.ErrorCode===10000) //User not connected
                alert(res.data.Message)
            else //success
            {this.changeState()}
            {this.changeState()}
        })
    }
    changeOrder = (e)=>{
        this.setState({order:!this.state.order})
        e.preventDefault();
        const params ={
            key:Cookies.get('key'),
            nbTwist: this.state.nbTwist,
        }
        console.log(params.key)
        console.log(params.orderAsc)
        console.log(params.nbTwist)
        axios.post(`http://localhost:8080/Twister/ListTwist`, null, {params}).then(res => {
            console.log(res);
            console.log(res.data);
            if(res.data.ErrorCode===1000) { //user not connected
                alert(res.data.Message)
            }else if(res.data.ErrorCode===-1){
                alert(res.data.Message)
            }
            else{
                this.setState({listTwists: res.data.My_twists})
            }
            {this.changeState()}
            {this.changeState()}
        })


    }

    render() {
        const avatarStyle = {
            margin: '0px 10px',
            color: 'blue',
            backgroundColor: '',

        }
        const dateStyle = {
            color: 'grey',
            padding: '2px',
            fontSize: '65%'
        };
        const data = this.state.listTwists.map((twist, index) =>
            <div key={index} className="twister-div-media">
                <div style={{'paddingBottom': '0px', position: 'inherit'}}>

                    <Media className="Media">

                        <div style={{
                            padding: '16px',
                            'fontWeight': '500',
                            'boxSizing': 'border-box',
                            position: 'relative',
                            'whiteSpace': 'nowrap',
                            'maxWidth':'600px'
                        }}>

                            <div>
                                <div style={{'marginRight': '500px'}}>
                                    <div>
                                        <Avatar style={avatarStyle}>
                                            {twist.login.toUpperCase().substr(0,1)}
                                        </Avatar>
                                    </div>
                                </div>
                                <div style={{
                                    display: "inline-block",
                                    verticalAlign: 'top',
                                    whiteSpace: 'normal',
                                    paddingRight: "90px"
                                }}>
                                    <span style={{
                                        color: 'rgba(0, 0, 0, 0.87)',
                                        display: 'block',
                                        fontSize: '20px',
                                        marginLeft: '5px'
                                    }}>{twist.login}</span><br></br>
                                    <span style={{
                                        color: 'rgba(0, 0, 0, 0.54)',
                                        display: 'block',
                                        'fontSize': '14px',
                                        'marginTop': '-20px'
                                    }}>{twist.date}</span>
                                </div>
                            </div>

                            <div style={{position: 'absolute', right: '18px', top: '8px'}}>
                                <div style={{
                                    display: 'block',
                                    position: 'absolute',
                                    top: '0px',
                                    right: '4px',
                                    zIndex: '0'
                                }}>
                                    <div>
                                        <div className="edit-twist-button-toggle">
                                            <Dropdown as={ButtonGroup}>
                                                <Dropdown.Toggle split variant="info" id="dropdown-split-basic"/>
                                                <Dropdown.Menu>
                                                    <Dropdown.Item hred="#/action-2">Edit</Dropdown.Item>
                                                    <Dropdown.Item hred="#/action-2" onClick={this.RemoveTwist(twist.id)}>Remove twist</Dropdown.Item>
                                                </Dropdown.Menu>
                                            </Dropdown>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style={{
                                display: 'inline-block',
                                'verticalAlign': 'none',
                                'whiteSpace': 'normal',
                                'paddingRight': '90px'
                            }}>
                                <p style={{
                                    padding: '10px',
                                    'fontSize': '14px',
                                    color: 'rgba(0, 0, 0, 0.87)',
                                    'overflowWrap': 'break-word',
                                    maxWidth: '650px'
                                }}>
                                    <span style={{'fontSize': '20px'}}>
                                        {twist.content}
                                    </span>
                                </p>
                            </div>
                            <div className="list-comment-button">
                                <CommentList  comments={twist.comments} id_message={twist.id} changeState={()=>this.changeState()}/>
                            </div>
                        </div>
                    </Media>

                </div>
            </div>)
        return (
            <div>
                <Form>
                    <div style={{fontStyle:'italic', fontWeight:'25px',fontSize:'20px'}} key={`custom-checkbox`} className="mb-3">
                        <Form.Check
                            custom
                            type="checkbox"
                            id={`custom-checkbox`}
                            label={`Sort twists by date`}
                            onClick={this.changeOrder}
                        />
                    </div>
                </Form>
                <Container>
                    {this.props.user? data:''}
                </Container>
            </div>
        );
    }
}

export default ProfileTwists
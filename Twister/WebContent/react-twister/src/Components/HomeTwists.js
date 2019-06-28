import React from 'react'
import {Button, ButtonGroup, Container, Dropdown, Media} from "react-bootstrap";
import Avatar from "@material-ui/core/Avatar";
import CommentList from "./CommentList";
import Cookies from "js-cookie";
import axios from "axios";
import Form from "react-bootstrap/Form";
import Grid from "@material-ui/core/Grid";

export default class HomeTwists extends React.Component{
    constructor(props){
        super(props)
        this.state={
            user:this.props.user,
            ord: false,
            nb_twist:11,
            TwistFollowings:[],
            Refresh:false,
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
            login:"neel",
            ord:this.state.ord,
            nb_twist: this.state.nb_twist,
        }
        console.log(params.login)
        console.log(params.ord)
        console.log(params.nb_twist)
        axios.post(`http://localhost:8080/Twister/TwistFollowings`, null, {params}).then(res => {
            console.log(res);
            console.log(res.data);
            console.log(res.data[0]);
            if(res.data.ErrorCode===1000) { //user not connected
                alert(res.data.Message)
            }else if(res.data.ErrorCode===-1){
                alert(res.data.Message)
            }
            else{
                console.log(this.props.user)
                this.setState({TwistFollowings: res.data})
                console.log(this.state.TwistFollowings)
            }
            {this.changeState()}
            {this.changeState()}
        })
    }

    componentWillUpdate(PrevState) {
        if(this.state.Refresh){
            const params ={
                login:this.props.user,
                ord:this.state.ord,
                nb_twist: this.state.nb_twist,
            }
            console.log(params.login);
            console.log(params.ord);
            console.log(params.nb_twist);
            axios.post(`http://localhost:8080/Twister/TwistFollowings`, null, {params}).then(res => {
                console.log(res);
                console.log(res.data);
                console.log(res.data[0]);
                if(res.data.ErrorCode===1000) { //user not connected
                    alert(res.data.Message)
                }else if(res.data.ErrorCode===-1){
                    alert(res.data.Message)
                }
                else{
                    console.log(this.props.user);
                    this.setState({TwistFollowings: res.data});
                    console.log(this.state.TwistFollowings)
                }
            })

        }}

    changeOrder = (e)=>{
        this.setState({ord:!this.state.ord})
        e.preventDefault();
        const params ={
            login:this.props.user,
            ord:this.state.ord,
            nb_twist: this.state.nb_twist,
        }
        console.log(params.login)
        console.log(params.ord)
        console.log(params.nb_twist)
        axios.post(`http://localhost:8080/Twister/TwistFollowings`, null, {params}).then(res => {
            console.log(res);
            console.log(res.data);
            if(res.data.ErrorCode===1000) { //user not connected
                alert(res.data.Message)
            }else if(res.data.ErrorCode===-1){
                alert(res.data.Message)
            }
            else{
                console.log(this.props.user)
                this.setState({TwistFollowings: res.data})
                console.log(this.state.TwistFollowings)
            }
        })
    };


    HideTwist() {
        alert("Can't perform this for now")
    }
    render(){
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
        const publicTwists = this.state.TwistFollowings.map((array) => array.map( (tweet, index) =>
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
                                        <Grid container justify="flex-start" alignItems="center">
                                            <Avatar style={avatarStyle}>
                                                {tweet.login.toUpperCase().substr(0,1)}
                                            </Avatar>
                                        </Grid>
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
                                    }}>{tweet.login}</span><br></br>
                                        <span style={{
                                            color: 'rgba(0, 0, 0, 0.54)',
                                            display: 'block',
                                            'fontSize': '14px',
                                            'marginTop': '-20px'
                                        }}>{tweet.date}</span>
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
                                                        <Dropdown.Item hred="#/action-2" onClick={()=>this.HideTwist()}>Hide twist</Dropdown.Item>
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
                                        {tweet.content}
                                    </span>
                                    </p>
                                </div>
                                <div className="list-comment-button">
                                    {
                                        <CommentList  comments={tweet.comments} id_message={tweet.id} changeState={()=>this.changeState()}/>
                                    }

                                </div>
                            </div>
                        </Media>
                    </div>
                </div>))
        return(
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
                    {publicTwists}
                </Container>
            </div>
        )
    }


}
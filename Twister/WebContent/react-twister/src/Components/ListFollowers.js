import React, { Component } from 'react';
import {Image, Navbar, Nav, Button, Row, Col} from "react-bootstrap";

import {Form, FormGroup, Input, Label, Modal, ModalBody, ModalFooter, ModalHeader} from "reactstrap";
import Cookies from "js-cookie";
import axios from "axios";
import Avatar from "@material-ui/core/Avatar";
import Grid from "@material-ui/core/Grid";

class ListFollowers extends Component {

        constructor(props){
            super(props)
            this.state = {
            modal: false,
            backdrop: true,
            followers:[]
        };

        this.toggle = this.toggle.bind(this);
        this.changeBackdrop = this.changeBackdrop.bind(this);
    }

    toggle() {
        this.setState(prevState => ({
            modal: !prevState.modal
        }));
    }

    showFollowers=event=>{
        event.preventDefault();
        this.setState(prevState => ({
            modal: !prevState.modal
        }));
        const params ={
            key:Cookies.get('key'),
        }
        console.log(params.key)
        axios.post(`http://localhost:8080/Twister/ListFollowers`, null, {params}).then(res => {
            console.log(res);
            console.log(res.data);
            if(res.data.ErrorCode===-1){
                console.log(res.data.Message)
            }else if(res.data.ErrorCode===1000) { //user not connected
                alert(res.data.Message)
            }
            else{
                this.setState({
                    followers:res.data.Followers
                })
            }

        })
    }

    Unfollow= (id)=>(event)=>{
        event.preventDefault();
        this.setState(prevState => ({
            modal: !prevState.modal
        }));
        const params ={
            key:Cookies.get('key'),
            id_friend:id
        }
        console.log(params.key)
        console.log(params.id_friend)
        axios.post(`http://localhost:8080/Twister/RemoveFriend`, null, {params}).then(res => {
            console.log(res);
            console.log(res.data);
            if(res.data.ErrorCode===-1){
                console.log(res.data.Message)
            }else if(res.data.ErrorCode===1000) { //user not connected
                alert(res.data.Message)
            }
            else{
                alert("You just unfollowed a user")
            }

        })
    }

    changeBackdrop(e) {
        let value = e.target.value;
        if (value !== 'static') {
            value = JSON.parse(value);
        }
        this.setState({ backdrop: value });
    }
    render() {
        const avatarStyle = {
            margin: '0px 10px',
            color: 'blue',
            backgroundColor: '',

        }
            const data = this.state.followers.map((user,index)=>
                <div key={index} style={{'marginTop':'10px'}}>
                    <Grid container justify="flex-start" alignItems="center" >
                        <Avatar>
                        {user.user.toUpperCase().substr(0,1)}
                        </Avatar>
                    {'    '}
                    {user.user}
                        <Button variant="secondary" style={{marginLeft:'50px'}} size='small' onClick={this.Unfollow(user.id)}> Unfollow</Button>
                </Grid>
            </div>)
        return (
            <div>
                <Form inline onSubmit={(e) => e.preventDefault()}>
                    <FormGroup style={{top:'100px'}} >
                        <Label for="backdrop">Find follower by login here !</Label>{' '}
                        <Input type="text" placeholder="tape here !"/>
                    </FormGroup>
                    {'   '}
                    <Button variant="info" color="danger" onClick={this.showFollowers}>Show follower's list</Button>
                </Form>
                <Modal isOpen={this.state.modal} toggle={this.toggle} className={this.props.className} backdrop={this.state.backdrop}>
                    <ModalHeader toggle={this.toggle}>Modal title</ModalHeader>
                    <ModalBody>
                        {data}
                    </ModalBody>
                    <ModalFooter>
                        <Button color="secondary" onClick={this.toggle}>Cancel</Button>
                    </ModalFooter>
                </Modal>
            </div>
        );

    }
}

export default ListFollowers;
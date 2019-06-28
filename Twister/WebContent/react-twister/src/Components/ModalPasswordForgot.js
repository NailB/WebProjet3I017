import {Button, FormControl, Modal} from "react-bootstrap";
import React from 'react'
import axios from "axios";



export default class ModalPasswordForgot extends React.Component {
    constructor(props) {
        super(props);

        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);

        this.state = {
            show: props.show,
            mail:""
        };
        this.handleChange=this.handleChange.bind(this)
    }
    handleSubmit = event => {
        event.preventDefault();
        const params={
            mail:this.state.mail,

        }
        axios.post(`http://localhost:8080/Twister/PasswordRecovery`,null, {params}).then(res => {
            console.log(res);
            console.log(res.data);
            console.log(res.data.mail)
            if(res.data.ErrorCode===-1){
                alert("Missing params:")
            }
            else if(res.data.ErrorCode===1002){ //Mail does not exist.
                alert(res.data.Message)
            }
            else{
                alert("Check your email to recover your password!")
            }
        })
    }

    handleChange(){
        this.setState({
            mail:this.mail.value,
        })
    }
    handleClose() {
        this.setState({ show: false });
    }

    handleShow() {
        this.setState({ show: true });
    }

    render() {
        return (
            <div>
                <Modal show={this.state.show} onHide={this.handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Password forgot</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <form>
                            <span style={{'fontStyle':'italic', color:'cadetblue'}}>Merci d'indiquer l'adresse avec laquelle vous vous êtes inscrit. Un Email vous sera renvoyé. Vérifiez bien votre boite Spam si vous ne le recevez pas. En cas de complication, merci de nous contacter à l'adresse : twister.socialnetwork@gmail.com contactez nous.<br></br>   </span>
                            <div className="form-row">
                                <div className="col">
                                    <input type="text" className="form-control" placeholder="email"
                                           ref={(mail) => { this.mail = mail }}
                                           onChange={this.handleChange}/>
                                </div>
                            </div>
                            <input type="submit" id="logbouton" value="Send"
                                   onClick={this.handleSubmit}
                                   style={{'color': 'cadetblue'}}/>
                        </form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={this.handleClose}>
                            Close
                        </Button>
                    </Modal.Footer>
                </Modal>
            </div>
        )
    }
}

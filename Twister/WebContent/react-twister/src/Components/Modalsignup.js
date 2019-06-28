import {Button, FormControl, Modal} from "react-bootstrap";
import React from 'react'
import axios from "axios";
import Cookies from "js-cookie";
class Modalsignup extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            show: props.show,
            nom:'',
            prenom:'',
            mail:'',
            login:'',
            mdp:'',
        };
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.handleChange=this.handleChange.bind(this);
    }

    handleClose() {
        this.setState({ show: false });
    }

    handleShow() {
        this.setState({ show: true });
    }

    handleSubmit = event => {
        event.preventDefault();
        const params={
            nom:this.state.nom,
            prenom:this.state.prenom,
            mail:this.state.mail,
            login:this.state.login,
            mdp:this.state.mdp,


        };
        axios.post(`http://localhost:8080/Twister/CreatUser`,null, {params}).then(res => {
            console.log(res)
            console.log(res.data)
            console.log(res.data.success)
            if(res.data.ErrorCode===-1){
                alert("Missing params:")
            }
            else if(res.data.ErrorCode===1004){ //Mail already used.
                alert(res.data.Message)
            }
            else if(res.data.ErrorCode===1003){//Login already exists.
                alert(res.data.Message)
            }
            else if(res.data.success===1) {
                alert("User has been created, login in to connect")
                //setTimeout(this.props.connecte(),1000)
                this.handleClose()
            }
        })
    };

    handleChange(){
        this.setState({
            nom:this.nom.value,
            prenom:this.prenom.value,
            mail:this.mail.value,
            login:this.login.value,
            mdp:this.mdp.value,
        })
    }
    render() {
        return (
            <div>


                <Modal show={this.state.show} onHide={this.handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Welcome new member!</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <form>
                            <div className="form-row">
                                <div className="col">
                                    <input type="text" className="form-control" placeholder="First name" ref={(nom) => { this.nom = nom}}
                                           onChange={this.handleChange }/>
                                </div>
                                <div className="col">
                                    <input type="text" className="form-control" placeholder="Last name" ref={(prenom) => { this.prenom = prenom }}
                                           onChange={this.handleChange}/>
                                </div>
                                <div className="col">
                                        <input type="text" className="form-control" placeholder="Login" ref={(login) => { this.login = login }}
                                               onChange={this.handleChange}/>
                                </div>

                            <div className="form-row">

                                <div className="form-group col-md-6">
                                    <label htmlFor="inputEmail4">Email</label>
                                    <input type="email" className="form-control" id="inputEmail4" placeholder="Email" ref={(mail) => { this.mail = mail }}
                                           onChange={this.handleChange}/>
                                </div>
                                <div>
                                    <label htmlFor="inputPassword5">Password</label>
                                    <input type="password" id="inputPassword5" className="form-control" aria-describedby="passwordHelpBlock"
                                           ref={(mdp) => { this.mdp = mdp }}
                                           onChange={this.handleChange}/>
                                    <small id="passwordHelpBlock" className="form-text text-muted">
                                        Your password must be 8-20 characters long, contain letters and numbers, and must not
                                        contain spaces, special characters, or emoji.
                                    </small>
                                </div>
                            </div>
                            </div>
                            <input type="submit" id="logbouton" value="Sign Up"
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

export default Modalsignup;
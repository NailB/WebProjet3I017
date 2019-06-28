import React from 'react';
import {FormControl, InputGroup} from "react-bootstrap";

class Signup extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            fullNameInput: '',
            emailInput: '',
            passwordInput: '',
        }
    }


    //handleChange(login, password) {
    //  this.setState(prevState => {
    //        this.state.login = login
    //      this.state.password = password
    //}
    //)
    //}


    render(){
        return (
            <div className="signup">
                <form>
                    <div className="form-row">
                        <div className="col">
                            <input type="text" className="form-control" placeholder="First name"/>
                        </div>
                        <div className="col">
                            <input type="text" className="form-control" placeholder="Last name"/>
                        </div>
                    </div>
                    <div className="form-row">
                    <InputGroup className="mb-3">
                        <FormControl
                            placeholder="Email"
                            aria-label="Recipient's username"
                            aria-describedby="basic-addon2"
                        />
                        <InputGroup.Append>
                            <InputGroup.Text id="basic-addon2">@example.com</InputGroup.Text>
                        </InputGroup.Append>
                    </InputGroup>
                        <div>
                            <label htmlFor="inputPassword5">Password</label>
                            <input type="password" id="inputPassword5" className="form-control" aria-describedby="passwordHelpBlock"/>
                                <small id="passwordHelpBlock" className="form-text text-muted">
                                Your password must be 8-20 characters long, contain letters and numbers, and must not
                                contain spaces, special characters, or emoji.
                            </small>
                        </div>
                    </div>
                    <div className="form-group">
                        <div className="form-check">
                            <input className="form-check-input" type="checkbox" id="gridCheck"/>
                                <label className="form-check-label" htmlFor="gridCheck">
                                    Check me out
                                </label>
                        </div>
                    </div>
                    <button type="submit" className="btn btn-primary">Sign up</button>
                </form>
            </div>
        )
    }
}
export default Signup
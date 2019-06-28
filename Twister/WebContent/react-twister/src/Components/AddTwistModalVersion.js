import React from 'react';
import Cookies from 'js-cookie';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Input, Label, Form, FormGroup } from 'reactstrap';
import axios from "axios";

class ModalExample extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            modal: this.props.modalAddTwist,
            text:''
        };

        this.toggle = this.toggle.bind(this);
        this.handleChange=this.handleChange.bind(this)
    }
    handleSubmit = event => {
        event.preventDefault();
        const params={
            key:Cookies.get('key'),
            text:this.state.text,
        }
        console.log(params.key)
        console.log(params.text)
        axios.post(`http://localhost:8080/Twister/AddTwist`,null, {params}).then(res => {
            console.log(res);
            console.log(res.data);
            if(res.data.ErrorCode===-1){
                alert(res.data.Message)
            }
            else if(res.data.ErrorCode===10000){ //USER not connected.
                alert(res.data.Message)
            }
            else{
                this.toggle()
                this.props.refreshPage()
                this.props.refreshPage()
                alert("twist added !")

            }
        })
    }
    handleChange(){
        this.setState({
            text:this.text.value
        });

    }
    toggle() {
        this.setState(prevState => ({
            modal: !prevState.modal,
        }));

    }

    render() {
        return (
            <div>
                <Modal isOpen={this.state.modal} toggle={this.toggle} className={this.props.className} unmountOnClose={this.state.unmountOnClose}>
                    <ModalHeader toggle={this.toggle}>Add something here!</ModalHeader>
                    <ModalBody>
                        <form>
                            <input type="text" className="form-control" placeholder="What's new with you!"
                                   ref={(text) => { this.text = text }}
                                   onChange={this.handleChange}/>
                        </form>
                    </ModalBody>
                    <ModalFooter>
                        <input type="submit" id="logbouton" onClick={this.handleSubmit} style={{'color': 'cadetblue'}}/>
                        <Button color="secondary" onClick={this.toggle}>Cancel</Button>
                    </ModalFooter>
                </Modal>
            </div>
        );
    }
}


ModalExample.propTypes ={

};
export default ModalExample;
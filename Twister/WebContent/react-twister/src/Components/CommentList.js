import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Comment from "./Comments";
import {Button, Container} from "react-bootstrap";
import {Card, CardBody, Collapse} from "reactstrap";
import {IconButton, SvgIcon} from "@material-ui/core";
import TextField from "@material-ui/core/TextField";
import MuiThemeProvider from "@material-ui/core/es/styles/MuiThemeProvider";
import {createMuiTheme, withStyles} from "@material-ui/core/es/styles";
import SvgIcons from './ICONS/IconComment'
import axios from "axios";
import Cookies from "js-cookie";

const styles = theme => ({
        root: {
            display: 'flex',
            flexWrap: 'wrap',
        },
        margin: {
            width:'500px',
            margin: theme.spacing.unit,
        },
    }
)
class CommentList extends Component {


    constructor(props) {
        super(props)
            this.state={
            collapse:false,
            text:"",
            }
            this.handleChange=this.handleChange.bind(this)
    }

    postComment = event => {
        event.preventDefault();
        const params={
            key:Cookies.get('key'),
            id_message:this.props.id_message,
            text:this.state.text,
        }
        console.log(params.id_message);
        console.log(params.text);
        axios.post(`http://localhost:8080/Twister/AddComment`,null, {params}).then(res => {
            console.log(res);
            console.log(res.data);
            console.log(params.id_message)
            console.log(res.data.text)
            if(res.data.ErrorCode===-1){
                alert("Missing params")
            }
            else if(res.data.ErrorCode===10000){ //USER not connected.
                alert(res.data.Message)
            }
            else if(res.data.ErrorCode===100){ //Error JSON.
                alert(res.data.Message)
            }
            else{
                alert("Comment has been added") //Post reussi
            }
            this.props.changeState()
            this.props.changeState()
        })
    }
    handleChange(){
        this.setState({
            text:this.text.value,
        })
    }
    render() {
        const {comments} =this.props
        const {classes} = this.props;
        const theme = styles.theme
        return (
            <React.Fragment >
                <div >
                    <MuiThemeProvider theme={theme}>
                        <TextField
                            className={classes.margin}
                            type="text"
                            label="Write your comment down here!"
                            id="mui-theme-provider-standard-input"
                            ref={(text) => { this.text = text }}
                            onChange={(e)=>{this.setState({text:e.target.value})}}/>
                    </MuiThemeProvider>

                    <IconButton onClick={this.postComment}>
                        <SvgIcons />
                    </IconButton>
                </div>
                <Button color="info" onClick={()=>this.setState({collapse:!this.state.collapse})} style={{ marginBottom: '1rem'}}>show comments</Button>
                {comments.map((comment, index) =>
                <Collapse key={index} isOpen={this.state.collapse}>
                    <CardBody className="list-comment-button">
                        <Card>
                            <Comment comment={comment} id_message={this.props.id_message} changeState={()=>this.props.changeState()}/>
                        </Card>
                    </CardBody>
                </Collapse>
            )}
            </React.Fragment>
        );
    }


}
CommentList.propTypes = {
    classes: PropTypes.object.isRequired
};


export default withStyles(styles)(CommentList);

CommentList.propTypes={
    commentList: PropTypes.arrayOf(PropTypes.String)
}
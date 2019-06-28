import React, { Component } from 'react';
import { Collapse, Button, CardBody, Card } from 'reactstrap';
import IconButton from "./ProfileTwists";
import {Media} from "react-bootstrap";
import DeleteIcon from '@material-ui/icons/Delete';
import Avatar from "@material-ui/core/Avatar";
import Grid from '@material-ui/core/Grid';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';
import {withStyles} from "@material-ui/core/es";
import PropTypes from 'prop-types';
import Cookies from "js-cookie";
import axios from "axios";
const styles = theme => ({
    root: {
        color: theme.palette.text.primary,
    },
    icon: {
        margin: theme.spacing.unit,
        fontSize: 32,
    },
});
class Comment extends Component {
    constructor(props) {
        super(props);

        this.state = {
            collapse: this.props.collapse,
            comment: this.props.comment.content,
            login: this.props.comment.author,
    };
    }


    RemoveComment= event => {
    event.preventDefault();
    const params={
        key:Cookies.get('key'),
        id_message:this.props.id_message,
        id_comment:this.props.comment.id_comment,
    }
    console.log(params.id_message);
    console.log(params.id_comment);
    axios.post(`http://localhost:8080/Twister/RemoveComment`,null, {params}).then(res => {
    console.log(res);
    console.log(res.data);
    console.log(params.id_message)
    console.log(res.data.id)
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
        alert(res.data.Message)
    }
    this.props.changeState()
    this.props.changeState()
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
        const {classes}= this.props
        const {comment}=this.props
        return (
        <div>
            <Card>
                <div style={{'marginTop':'10px'}}>
                    <Grid container justify="flex-start" alignItems="center" >
                        <Avatar style={avatarStyle}>{this.state.login.substr(0,1)}</Avatar>
                        {this.state.login}

                    </Grid>


                </div>
                <div style={{
                    display: 'inline-block',
                    'whiteSpace': 'normal',
                }}>
                    <p style={{
                        padding: '10px',
                        'fontSize': '14px',
                        color: 'rgba(0, 0, 0, 0.87)',
                        'overflowWrap': 'break-word'
                    }}>
                                    <span style={{marginLeft:'20px','fontSize': '14px'}}>
                                       {comment.content}
                                    </span>
                    </p>
                    <div className="DeleteButton" style={{marginButtom:'10px'}}>
                        <Grid item xs={8}>
                            <DeleteIcon className={classes.icon} onClick={this.RemoveComment}/>
                        </Grid>
                    </div>
                </div>

            </Card>
        </div>
        );
    }
}
export default withStyles(styles)(Comment);
Comment.propTypes = {
    classes: PropTypes.object.isRequired,
};

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import SvgIcon from '@material-ui/core/SvgIcon';
import blue from "@material-ui/core/colors/blue";
import Icon from "@material-ui/core/Icon";


const styles = theme => ({
        root: {
            display: 'inline-block',
            color: 'rgb(0, 188, 212)',
            fill: 'rgb(0, 188, 212)',
            height: '24px',
            width: '24px',
            transition: 'all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms',
            position: 'absolute',
            top: '-17px',
            margin: '12px',
            right: "-10px",
            'z-index':"0",
            justifyContent: 'center',
            alignItems: 'flex-end',
        },
        icon: {
            margin: theme.spacing.unit * 2,
        },
        iconHover: {
            margin: theme.spacing.unit * 2,
            '&:hover': {
                color: 'cadetblue',
            },
        },
    }
);

function CommentIcon(props) {
    return (
        <div >
            <SvgIcon {...props}>
                    <path d="M21.99 4c0-1.1-.89-2-1.99-2H4c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h14l4 4-.01-18zM18 14H6v-2h12v2zm0-3H6V9h12v2zm0-3H6V6h12v2z"/>
            </SvgIcon>
        </div>
    );

}

function SvgIcons(props) {
    const { classes } = props;
    return (
        <div className={classes.root} >
            <CommentIcon fontSize='large'/>
        </div>
    );
}
SvgIcons.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(SvgIcons);
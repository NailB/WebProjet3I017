import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import SvgIcon from '@material-ui/core/SvgIcon';
import blue from "@material-ui/core/colors/blue";


const styles = theme => ({
        root: {
            display: 'block',
            color: 'rgb(0, 188, 212)',
            fill: 'rgb(0, 188, 212)',
            height: '24px',
            width: '24px',
            transition: 'all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms',
            position: 'absolute',
            top: '4px',
            margin: '12px',
            right: "10px",
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
                color: blue[800],
            },
        },
    }
);

function AddPicIcon(props) {
    return (
        <div >
        <SvgIcon {...props}>
            <path d="M9 2L7.17 4H4c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2h-3.17L15 2H9zm3 15c-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5-2.24 5-5 5z" />
        </SvgIcon>
        </div>
    );

}

function SvgIcons(props) {
    const { classes } = props;
    return (
        <div className={classes.root} >
            <AddPicIcon  fontSize="large"/>
        </div>
    );
}
SvgIcons.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(SvgIcons);
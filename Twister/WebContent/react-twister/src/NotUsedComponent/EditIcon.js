import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import SvgIcon from '@material-ui/core/SvgIcon';


const styles = theme => ({
        root: {
            display: 'inline-block',
            color: 'rgba(0, 0, 0, 0.87)',
            fill: 'rgb(189, 189, 189)',
            height: '24px',
            width: '24px',
            'user-select': 'none',
            transition: 'all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms',

        },
    }
);

function EditIcon(props) {
    return (
        <div >
            <SvgIcon {...props}>
                <path d="M12 8c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zm0 2c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2zm0 6c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2z"/>
            </SvgIcon>
        </div>
    );

}

function SvgIcons(props) {
    const { classes } = props;
    return (
        <div className={classes.root} >
            <EditIcon color="disabled"  fontSize="large"/>
        </div>
    );
}
SvgIcons.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(SvgIcons);
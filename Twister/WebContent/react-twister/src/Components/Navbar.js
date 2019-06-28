import React from 'react';
import PropTypes from 'prop-types';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import InputBase from '@material-ui/core/InputBase';
import { fade } from '@material-ui/core/styles/colorManipulator';
import { withStyles } from '@material-ui/core/styles';

import SearchIcon from '@material-ui/icons/Search';

import SvgIcons from './ICONS/IconHomePage'
import {BrowserRouter as Router, Route} from 'react-router-dom'

const styles = theme => ({
    root: {
        zIndex:1,
        position: 'fixed',
        width: '100%',
        backgroundColor: 'lightgreen',
        color: 'grey',
    },
    grow: {
        flexGrow: 1,
    },
    menuButton: {
        marginLeft: -12,
        marginRight: 20,
    },
    title: {
        display: 'block',
        [theme.breakpoints.up('sm')]: {
            display: 'flex',

        },
        marginLeft: '70px',
        padding:'20px'
    },
    search: {
        position: 'relative',
        borderRadius: theme.shape.borderRadius,
        backgroundColor: fade(theme.palette.common.white, 0.15),
        '&:hover': {
            backgroundColor: fade(theme.palette.common.white, 0.2),
        },
        marginRight: 10,
        width: '100%',
        [theme.breakpoints.up('lg')]: {
            marginLeft: theme.spacing.unit,
            width: 'auto',
        },
    },
    searchIcon: {
        height: '100%',
        position: 'absolute',
        pointerEvents: 'none',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    },
    homeIcon: {
        height: '10%',
        position: 'absolute',
        pointerEvents: 'none',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    },
    inputRoot: {
        color: 'white',
        width: '100%',
    },
    inputInput: {
        paddingTop: theme.spacing.unit,
        paddingRight: theme.spacing.unit,
        paddingBottom: theme.spacing.unit,
        paddingLeft: theme.spacing.unit * 10,
        transition: theme.transitions.create('width'),
        width: '100%',
        [theme.breakpoints.up('sm')]: {
            width: 120,
            '&:focus': {
                width: 200,
            },
        },
    },

});




function SearchAppBar(props) {
    const { classes, home} = props


    return (
        <Router>
            <div className={classes.root} id="test">
                <AppBar position="static">
                    <Toolbar className="NavBar" color="inherit" aria-label="Open drawer">

                        <Typography className={classes.title} variant="h6" color="inherit" noWrap>
                                              <span style={{fontWeight:'16px', fontSize:'40px', fontStyle:'italic'}}>Twister</span>
                        </Typography>
                        <div className={classes.grow} />
                        <Route path="/" render={props => (
                            <React.Fragment>
                                <IconButton onClick={home}>
                                    <SvgIcons/>
                                </IconButton>
                            </React.Fragment>
                        )}/>
                        <div className={classes.search}>
                            <div className={classes.searchIcon}>
                                <SearchIcon/>
                            </div>
                            <InputBase
                                placeholder="Search…"
                                classes={{
                                    root: classes.inputRoot,
                                    input: classes.inputInput,
                                }}
                            />
                        </div>
                    </Toolbar>
                </AppBar>
            </div>
        </Router>
    );
}

SearchAppBar.propTypes = {
    classes: PropTypes.object.isRequired,
};
export default withStyles(styles)( SearchAppBar);


import React from 'react'
import LoginPage from './LoginPage'
import HomePage from './HomePage'
import SearchAppBar from './Navbar'
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import ProfilePage from "./Profile";
class MainPage extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            user:"",
            isConnected : true,
            currentePage: 'HomePage',
            getLog:true
        }
        this.getConnected = this.getConnected.bind(this)
        this.setLogout = this.setLogout.bind(this)
        this.setProfilePage=this.setProfilePage.bind(this);
        this.setUser=this.setUser.bind(this)
    }
    setUser(value){
        this.setState({user:value})
    }
    getConnected() {
        this.setState( {
            isConnected: true,
            currentePage: "HomePage",
            getLog:false
        })
    }
    setLogout(){
        this.setState({
            isConnected: false,
            currentePage: "LoginPage"
        })
    }
    setProfilePage(){
        this.setState({
            currentePage: "ProfilePage",

        })
    }

    render(){
        return (

            <div>
                <div >
                    <SearchAppBar home={this.getConnected}/>
                </div>
                <React.Fragment >
                    {this.state.isConnected === true && this.state.currentePage==='HomePage'?<HomePage profile={this.setProfilePage} logout={this.setLogout} home={this.getConnected} user={this.state.user}/>: this.state.isConnected===false?<LoginPage connecte={()=>this.getConnected()} setUser={this.setUser}/>:''}
                    {this.state.isConnected===true && this.state.currentePage==='ProfilePage'? <ProfilePage user={this.setUser}/>:''}
                </React.Fragment>
            </div>

        )
    }

}
export default MainPage


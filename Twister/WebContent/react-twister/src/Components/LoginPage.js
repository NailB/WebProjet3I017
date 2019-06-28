import React from 'react'
import Login from "./Login"
import logo from "../logo.png"
class LoginPage extends React.Component {
    constructor(props) {
        super(props)
        this.state={
        }
    }


    render() {
        return (
            <div style={{'paddingTop': '60px'}}>
                <img className="logo" src={logo}/>

                <div className="Login-page">
                    <h1 style={{margin:'auto', color:'cadetblue', paddingBottom:'30px'}}> Welcome to Twister! </h1>
                    <Login connecte={()=>this.props.connecte()} setUser={this.props.setUser}/>
                </div>
            </div>
        )
    }
}
export default LoginPage
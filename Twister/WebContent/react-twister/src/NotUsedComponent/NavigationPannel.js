
import React from 'react'
import Login from '../Components/Login'
import Logout from './Logout'
import signUp from "./Signup";
class NavigationPannel extends React.Component{
    constructor(props){
        super(props)
            this.state = {

                }
    }
    render() {
        const connected=this.state.status
        return (
            <div>
                <nav>
                    {connected? "true":"false"}
                    {connected?<Logout logout={this.props.logout}/>:<Login login={this.props.login}/>}
                </nav>
            </div>
        )
    }
}
export default NavigationPannel
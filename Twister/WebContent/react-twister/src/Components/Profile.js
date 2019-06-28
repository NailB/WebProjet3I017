import React, { Component } from 'react';
import ProfileTwists from "./ProfileTwists";
import SideBar from "./SideBar";
import AddTwistModalVersion from "./AddTwistModalVersion";
import SvgIcons from './ICONS/IconPhoto'
import ListFollowers from "./ListFollowers";
class Profile extends Component {
    constructor(props){
        super(props)
        this.state = {
            refreshTwists:false,
        }
        this.OnAddTwist=this.OnAddTwist.bind(this)
        this.OnRefresh=this.OnRefresh.bind(this)
    }
    OnAddTwist(){
        this.setState({
            addTwist:!this.state.addTwist,
        });
    }
    OnRefresh(){
        this.setState({refreshTwists:!this.state.refreshTwists})
    }


    render(){
        return(
            <div >

                <div id="App">
                    <SideBar profile={this.props.profile} logout={this.props.logout} home={this.props.home}/>
                    <div id="page-wrap">
                    </div>
                </div>

                <div className="grid">
                    <div style={{"maxWidth": "700px","minWidth": "100px", 'flex': '0 0 50%', 'flexGrow': '1'}}>
                        <div style={{ marginTop: '150px'}}>
                            <ListFollowers/>
                        </div>
                        <button className="add-twister-div-profile" onClick={()=> this.OnAddTwist()}>
                            <SvgIcons >
                                <circle/>
                            </SvgIcons>
                            <h5 style={{'fontWeight':'10%'}}>
                                Click me to add a twister!
                            </h5>
                            <div className="add-twist-icon">
                                {'a'.substr(0, 1)}
                            </div>
                        </button>

                        {this.state.addTwist?<AddTwistModalVersion modalAddTwist={this.state.addTwist} refreshPage={this.OnRefresh}/>:''}
                        {this.state.refreshTwists?<ProfileTwists user={this.props.user} Refresh={this.state.refreshTwists}/>:''}
                        <div className="animate-Twister">
                            <ProfileTwists user={this.props.user} Refresh={this.state.refreshTwists}/>
                        </div>
                    </div>

                </div>

            </div>
        )
    }
}

export default Profile;

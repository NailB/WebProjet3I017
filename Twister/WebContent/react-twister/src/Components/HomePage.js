import React from 'react';
import SideBar from './SideBar'

import SvgIcons from './ICONS/IconPhoto'
import AddTwistModalVersion from "./AddTwistModalVersion";

import HomeTwists from "./HomeTwists";
import ListFollowers from "./Profile";
class HomePage extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            refreshTwists:false,
            user:this.props.user
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
            <div>
                <div id="App">
                    <SideBar profile={this.props.profile} logout={this.props.logout} home={this.props.home}/>
                    <div id="page-wrap">
                    </div>
                </div>

                <div className="grid">
                <div style={{"maxWidth": "700px","minWidth": "100px", 'flex': '0 0 50%', 'flexGrow': '1', zIndex:1, position:'relative'}}>
                    <button className="add-twister-div" onClick={()=> this.OnAddTwist()}>
                        <SvgIcons >
                            <circle/>
                        </SvgIcons>
                        <h5 style={{'fontWeight':'10%'}}>
                            Click me to add a twister!
                        </h5>
                        <div className="add-twist-icon">
                            {this.props.user.substr(0, 1)}
                        </div>
                    </button>
                    {this.state.addTwist?<AddTwistModalVersion modalAddTwist={this.state.addTwist} refreshPage={this.OnRefresh}/>:''}
                    {this.state.refreshTwists?<HomeTwists user={this.props.user} Refresh={this.state.refreshTwists}/>:''}
                    <div className="animate-Twister">
                    <HomeTwists user={this.props.user}/>
                    </div>
                </div>
                </div>

            </div>
        )
    }
}

export default HomePage
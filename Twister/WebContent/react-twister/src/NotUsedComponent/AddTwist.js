import React from 'react'
import SvgIcons from '../Components/ICONS/IconPhoto'
import IconButton from "@material-ui/core/es/IconButton";
class AddTwist extends React.Component{
    constructor(props){
        super(props)
            this.state = {
                user:"Delpax"
           }
    }



render(){
        return(
            <div>
            <div className="add-twister-div">
            <div>
                <span className="add-twister-span">
                    <div>
                        <div style={{"margin-left": "0px", padding: "20px 56px 20px 72px",position: "relative"}}>
                            <IconButton onClick={()=> alert("Add picture here !")} style={{position:'inherit', left:'600px',bottom:'20px'}}>
                            <SvgIcons >

                                <circle >0</circle>
                            </SvgIcons>
                            </IconButton>
                            <div className="add-twist-icon">
                                {this.state.user.substr(0, 1)}
                            </div>
                            <div  style={{'margin-top':'-30px'}}>
                            <span style={{color: "rgb(189, 189, 189)", cursor: "text"}}>
                                <textarea className="add-twist-text-area"></textarea>
                            </span>
                            </div>
                        </div>
                    </div>
                </span>
            </div>
            </div>
            </div>
            )
    }

}

export default AddTwist
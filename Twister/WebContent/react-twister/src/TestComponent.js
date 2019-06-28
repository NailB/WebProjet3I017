import React from 'react'

class TestComponent extends React.Component{
    constructor(props){
        super(props)
            this.state = {

           }
    }



render(){
        return(
            <div>
                <div>logged in</div>

                <input type="submit" onClick={this.props.test}/>
            </div>
        )
    }

}
export default TestComponent
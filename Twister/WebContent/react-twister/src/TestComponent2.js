import React from 'react'

class TestComponent2 extends React.Component{
    constructor(props){
        super(props)

    }



render(){
        return(
            <div>
                not LoggedIn
                <input type="submit" onClick={this.props.test}/>
            </div>
        )
    }

}
export default TestComponent2
import React from "react";
import {Form ,FormControl} from 'react-bootstrap'

class Sign_in_form extends React.Component{

    constructor(props) {
        super(props);
        this.state = {login: '', password: ''};
        this.loginChange = this.loginChange.bind(this);
        this.passwordChange = this.passwordChange.bind(this);

        this.handleSubmit = this.handleSubmit.bind(this);
    }
    loginChange(event) {    this.setState({login: event.target.value});  }
    passwordChange(event) {    this.setState({password: event.target.value});  }

    handleSubmit(event) {
        console.log(this.state.login);
        console.log(this.state.password);
        if(this.state.login == '' || this.state.password ==''){
            alert("Enter all important fields");
            return; 
        }
        
        event.preventDefault();
    }

    render(){
        return(
        <Form onSubmit={this.handleSubmit}>
            <FormControl className = "login"
                         value={this.state.value}
                         onChange={this.loginChange}
                         type = 'text'
                         placeholder="Login "
                         id="IDlogin"
            />
            <FormControl className = "password"
                         value={this.state.value}
                         onChange={this.passwordChange}
                         type = ''
                         placeholder = "Password "
                         id="IDpassword"
            />
            <button className = "button_local" type='submit' >Sign in </button>
        </Form>)
    }
}

export default Sign_in_form;

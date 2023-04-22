import React, {Component} from 'react';
import {Button, Form, FormControl, Stack} from "react-bootstrap";
import './Sign_in.css'
import Header from "../Components/Header";

class SignIn extends Component {
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
        console.log(this.state.login)
        console.log(this.state.password)
        event.preventDefault();
    }

    render() {
        return (
            <>
                <div className="Sign_in">
                    <h2 className="Te_xt">Hello! Enter me <br/> please daddy
                    </h2>
                    <div className = "block_gray"></div>
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
                                     type = 'password'
                                     placeholder = "Password "
                                     id="IDpassword"
                        />
                        <Button className = "button_local" type='submit' >Sign in </Button>
                    </Form>
                </div>
            </>
        );
    }
}

export default SignIn;
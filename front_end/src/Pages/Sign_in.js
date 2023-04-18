import React, {Component} from 'react';
import {Button, Form,FormControl} from "react-bootstrap";
import '../Sign_menu.css'

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
            <div className="Sign_in">
                <h2>Sign in
                </h2>
                <Form onSubmit={this.handleSubmit}>
                    <FormControl
                    value={this.state.value}
                    onChange={this.loginChange}
                    type = 'text'
                    placeholder="Login "

                    />
                    <FormControl className = "password"
                        value={this.state.value}
                        onChange={this.passwordChange}
                        type = 'password'
                        placeholder="Password "
                    />
                    <Button type='submit' style={{background: "#44944A", margin: "5px", border: "none"}}>Sign in </Button>
                </Form>
            </div>
        );
    }
}

export default SignIn;
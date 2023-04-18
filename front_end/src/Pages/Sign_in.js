import React, {Component} from 'react';
import {Button, Form,FormControl} from "react-bootstrap";
import '../Sign_in.css'

class SignIn extends Component {
    render() {
        return (
            <div className="Sign_in">
                <h2>Sign in
                </h2>
                <Form>
                    <FormControl
                    type = 'text'
                    placeholder="Login "

                    />
                    <FormControl className = "password"
                        type = 'password'
                        placeholder="Password "
                    />
                </Form>
                <Button type='submit' style={{background: "#44944A", margin: "5px", border: "none"}}>Sign in </Button>
            </div>
        );
    }
}

export default SignIn;
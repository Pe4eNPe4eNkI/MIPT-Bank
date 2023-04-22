import React, {Component} from 'react';
import {Button, Form, FormControl} from "react-bootstrap";
import './Sign_in.css'

class SignUp extends Component {

    constructor(props) {
        super(props);
        this.state = {name: '', surname: '', address: '', passport_id: '', login: '', password: ''};
        this.nameChange = this.nameChange.bind(this);
        this.surnameChange = this.surnameChange.bind(this);
        this.addressChange = this.addressChange.bind(this);
        this.passportIdChange = this.passportIdChange.bind(this);
        this.loginChange = this.loginChange.bind(this);
        this.passwordChange = this.passwordChange.bind(this);

        this.handleSubmit = this.handleSubmit.bind(this);
      }
    
      nameChange(event) {    this.setState({name: event.target.value});  }
      surnameChange(event) {    this.setState({surname: event.target.value});  }
      addressChange(event) {    this.setState({address: event.target.value});  }
      passportIdChange(event) {    this.setState({passport_id: event.target.value});  }
      loginChange(event) {    this.setState({login: event.target.value});  }
      passwordChange(event) {    this.setState({password: event.target.value});  }
      handleSubmit(event) {
        console.log(this.state.name)
        console.log(this.state.surname)
        console.log(this.state.address)
        console.log(this.state.passport_id)
        console.log(this.state.login)
        console.log(this.state.password)
        event.preventDefault();
      }

    render() {
        return (
            <>
                <div className="Sign_in">
                    <h2>Sign up </h2>
                    <Form onSubmit={this.handleSubmit}>
                    
                        <FormControl
                            class='name'
                            value={this.state.value}
                            onChange={this.nameChange}
                            type='text'
                            placeholder="Name "
                            className="t1"
                        />
                        <FormControl
                            class='surname'
                            value={this.state.value}
                            onChange={this.surnameChange}
                            type='text'
                            placeholder="Surname "
                            className="t1"
                        />
                        <FormControl
                            class='address'
                            value={this.state.value}
                            onChange={this.addressChange}
                            type='text'
                            placeholder="Address "
                            className="t1"

                        />
                        <FormControl
                            class='passport_id'
                            value={this.state.value}
                            onChange={this.passportIdChange}
                            type='text'
                            placeholder="Passport id "
                            className="t1"

                        />
                        <FormControl
                            class='login'
                            value={this.state.value}
                            onChange={this.loginChange}
                            type='text'
                            placeholder="Login "
                            className="t1"

                        />
                        <FormControl
                            class='password'
                            value={this.state.value}
                            onChange={this.passwordChange}
                            type='password'
                            placeholder="Password "
                            className="t1"
                        />
                        <Button name='sign_in_b' type='submit'
                            style={{background: "#44944A", margin: "5px", border: "none"}}>Sign
                        up </Button>

                        </Form>
                </div>
            </>
        );
    }
}

export default SignUp;
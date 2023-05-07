import React from "react";
import {Form ,FormControl} from 'react-bootstrap'

class Sign_up_form extends React.Component{
    
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
        if(this.state.login == '' || this.state.password == '' || this.state.name == ''|| this.state.surname == ''){
            alert("Enter all important fields! ");
            return;
        }
        console.log(this.state.name)
        console.log(this.state.surname)
        console.log(this.state.address)
        console.log(this.state.passport_id)
        console.log(this.state.login)
        console.log(this.state.password)
        event.preventDefault();
      }

    render(){
        return(
            <Form onSubmit={this.handleSubmit}>
            <FormControl className = "name"
                         value={this.state.value}
                         onChange={this.nameChange}
                         type = 'name'
                         placeholder="Name "
                         id="IDname"
            />       
            <FormControl className = "surname"
                         value={this.state.value}
                         onChange={this.surnameChange}
                         type = 'surname'
                         placeholder="Surname "
                         id="IDsurname"
            />
            <FormControl className = "address"
                         value={this.state.value}
                         onChange={this.addressChange}
                         type = 'address'
                         placeholder="Address "
                         id="IDaddress"
            />
            <FormControl className = "passport"
                         value={this.state.value}
                         onChange={this.passportIdChange}
                         type = 'passrort'
                         placeholder="Passport id "
                         id="IDpassport"
            />
            <FormControl className = "login"
                         value={this.state.value}
                         onChange={this.loginChange}
                         type = 'Login'
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
            <button className = "button_local" type='submit' >Sign up </button>

            </Form>
            )
    }
}

export default Sign_up_form;

import React, {Component} from 'react';
import {Button, Form, FormControl} from "react-bootstrap";
import './Operations.css'
import companyLogo from "../Components/images/logo_based.png";
class Withdraw extends Component {

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
                <div className= 'fucking_header_invise'>
                    {/*<img className = "logo" src = "../Components/images/logo_based.png"alt = " "/>*/}
                    <a href="http://localhost:3000/"><img className = "logo" src={companyLogo} alt=" " width="50" height="auto"/></a>
                    {/*<a href="http://localhost:3000/"><text className = "name_logo" width="auto" height="auto">POLYBANK</text></a>*/}
                    {/*<a href="http://localhost:3000/"><View> <Text style = {{fontSize : '65px', color : 'white', marginLeft: '10%', fontWeight: '6   00'}}>POLYBANK</Text></View></a>*/}

                    {/*<a href="http://localhost:3000/sign_in"><text className = "sign_in_header" width="auto" height="auto">Sign in</text></a>*/}
                    {/*<a href="http://localhost:3000/sign_up"><text className = "sign_up_header" width="auto" height="auto">BECOME A CLIENT</text></a>*/}
                </div>
                <div className="operations_div">
                    <h2 className = "Te_xt">Please confirm operation</h2>
                    <div className = "block_gray"></div>
                    <Form onSubmit={this.handleSubmit}>
                        <FormControl className = "sum_operations"
                                     value={this.state.value}
                                     onChange={this.nameChange}
                                     type = 'sum_operations'
                                     placeholder="Sum "
                                     id="IDsum_operations"
                        />
                        <FormControl className = "accnum_operations"
                                     value={this.state.value}
                                     onChange={this.surnameChange}
                                     type = 'accnum_operations'
                                     placeholder="Account number "
                                     id="IDaccnum_operations"
                        />
                        <FormControl className = "password_operation"
                                     value={this.state.value}
                                     onChange={this.addressChange}
                                     type = 'password_operation'
                                     placeholder="Password "
                                     id="IDpassword_operation"
                        />
                        <button className = "button_local_operations" type='submit' onClick={(e) => {
                            e.preventDefault();
                            window.location.href='http://localhost:3000/account';
                        }}>Sign in </button>

                    </Form>
                </div>
            </>
        );
    }
}

export default Withdraw;
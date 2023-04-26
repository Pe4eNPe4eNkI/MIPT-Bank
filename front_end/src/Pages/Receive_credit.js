import React, {Component} from 'react';
import {Button, Form, FormControl} from "react-bootstrap";
import './Receive_all.css'
import companyLogo from "../Components/images/logo_based.png";
import card_debit from "../Components/images/debit.png";

class ReceiveCredit extends Component {
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
                <div className= 'fucking_header_invise'>
                    {/*<img className = "logo" src = "../Components/images/logo_based.png"alt = " "/>*/}
                    <a href="http://localhost:3000/"><img className = "logo" src={companyLogo} alt=" " width="50" height="auto"/></a>
                    {/*<a href="http://localhost:3000/"><text className = "name_logo" width="auto" height="auto">POLYBANK</text></a>*/}
                    {/*<a href="http://localhost:3000/"><View> <Text style = {{fontSize : '65px', color : 'white', marginLeft: '10%', fontWeight: '6   00'}}>POLYBANK</Text></View></a>*/}

                    {/*<a href="http://localhost:3000/sign_in"><text className = "sign_in_header" width="auto" height="auto">Sign in</text></a>*/}
                </div>
                <div className="Sign_in">
                    <h2 className="Te_xt">Are you sure you want<br/> to receive the product?
                    </h2>
                    <div className = "block_gray"></div>
                    <div className = "Card_block">
                        <div className="card">
                            <img className = "logo_card" src={companyLogo} alt=" " width="40" height="auto"/>
                            <text className = "type_card" width="auto" height="auto">Credit</text>
                            <text className = "numbers_card" width="auto" height="auto">1234 5678 9456 7362</text>
                            <text className = "name_card" width="auto" height="auto">Mr. Voronin Arseniy</text>
                        </div>
                        <text className = "characteristics_card_1" width="auto" height="auto">Free service forever</text>
                        <text className = "characteristics_card_2" width="auto" height="auto">Only 5% payment</text>
                        <text className = "characteristics_card_3" width="auto" height="auto">Government-free system</text>
                        <text className = "characteristics_card_4" width="auto" height="auto">Up to 100.000$ limit</text>
                        {/*<br/>Cash-back 5% for every purchase<br/>Government-free system<br/>Transfer up to 500.00$ per month*/}
                    </div>
                    <button className = "button_local_card" type='submit' >Get it </button>
                </div>
            </>
        );
    }
}
export default ReceiveCredit;
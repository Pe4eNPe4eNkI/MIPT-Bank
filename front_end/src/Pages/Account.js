import React, {Component} from 'react';
import {Text, View} from 'react-native';
import CarouselBox from '../Components/Carousel';
import './Account.css';
import companyLogo from '../Components/images/logo_based.png';

class Account extends Component {
    render() {
        return (
            <div className="big_div">
                <div className= 'fucking_header'>
                    {/*<img className = "logo" src = "../Components/images/logo_based.png"alt = " "/>*/}
                    <a href="http://localhost:3000/"><img className = "logo" src={companyLogo} alt=" " width="50" height="auto"/></a>
                    <a href="http://localhost:3000/" style={{textDecoration: 'none'}}>
                        <View style={{float: 'left', top: '22px', width: '300px'}}>
                            <Text style = {{fontSize : '40px', color : '#9D9FA2', marginLeft: '5px', fontWeight: '6   00'}}>
                                POLYBANK
                            </Text>
                        </View></a>
                    {/*<a href="http://localhost:3000/"><View> <Text style = {{fontSize : '65px', color : 'white', marginLeft: '10%', fontWeight: '6   00'}}>POLYBANK</Text></View></a>*/}

                    <a href="http://localhost:3000/"><text className = "exit_acc" width="auto" height="auto">Exit</text></a>
                </div>
                <div className= 'quote_acc'>

                </div>
                <table className= 'table' width = '100%'>
                    <tr class='up_tabble'>
                        <td>
                            <br/>
                            Our statistic
                            <div style={{width: '70px', height: '10px', background: '#FAA634'}}></div>
                            <br/>
                        </td>
                        <td>
                            <br/>
                            Our Products
                            <div style={{width: '70px', height: '1px', background: '#FAA634'}}></div>
                            <br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <tabble class="border_tabble">
                                <tr style = {{color : '#9D9FA2'}}>
                                    Gold resourse
                                </tr>
                                <tr>
                                    270.000 t
                                </tr>
                                <tr style = {{color : '#9D9FA2'}}>
                                    Users
                                </tr>
                                <tr>
                                    34.553
                                </tr>
                            </tabble>
                        </td>
                        <td>
                            <table class='card_tabble' width = '100%'>
                                <tr>
                                    <td>
                                        <div class = 'debit'>
                                            <img src='../Components/images/debit.png' alt=""></img>

                                        </div>
                                    </td>
                                    <td>
                                        <div class = 'credit'>
                                            <img src='../Components/images/credit.png' alt=""></img>
                                        </div>
                                    </td>
                                    <td>
                                        <div class = 'deposit'>
                                            <img src='../Components/images/deposit.png' alt=""></img>
                                        </div>
                                    </td>
                                </tr>
                                <tr class="text">
                                    <td>
                                        Free service forever<br/>
                                        Cash-back 5% payment every month<br/>
                                        Govermant-free system<br/>
                                        Transfer up to 500.000$ per month<br/>
                                    </td>
                                    <td>
                                        Free service forever<br/>
                                        Cash-back 5% payment every month<br/>
                                        Govermant-free system<br/>
                                        Up to 100.000$ credit limit<br/>
                                    </td>
                                    <td>
                                        Free service forever<br/>
                                        Up to 7.5% per annum<br/>
                                        Govermant-free system<br/>
                                        Payment like a card up to 5.000$ per month<br/>
                                        Use your deposit like a card with minor restrictions<br/>

                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
        );
    }
}

export default Account;
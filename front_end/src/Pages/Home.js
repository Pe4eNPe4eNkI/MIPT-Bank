import React, {Component} from 'react';
import {Text, View} from 'react-native';
import CarouselBox from '../Components/Carousel';
import './Home.css';
import companyLogo from '../Components/images/logo_based.png';

class Home extends Component {
    render() {
        return (
            <>
            <div className= 'fucking_header'>
                {/*<img className = "logo" src = "../Components/images/logo_based.png"alt = " "/>*/}
                <a href="http://localhost:3000/"><img className = "logo" src={companyLogo} alt=" " width="50" height="auto"/></a>
                <a href="http://localhost:3000/"><text className = "name_logo" width="auto" height="auto">POLYBANK</text></a>
                {/*<a href="http://localhost:3000/"><View> <Text style = {{fontSize : '65px', color : 'white', marginLeft: '10%', fontWeight: '6   00'}}>POLYBANK</Text></View></a>*/}

                <a href="http://localhost:3000/sign_in"><text className = "sign_in_header" width="auto" height="auto">Sign in</text></a>
                {/*<a href="http://localhost:3000/sign_up"><text className = "sign_up_header" width="auto" height="auto">Sign up</text></a>*/}
            </div>
            <div className= 'quote'>
                    <View>
                    <Text style = {{fontSize : '65px', color : 'white', marginLeft: '10%', fontWeight: '6   00'}}>

                       <br/>"GOLD IS OUR <br/>
                        FUTURE AND HOPE<br/>
                        IN A FICKLE WORLD”<br/>
                         — Voronin A. V.<br/>
                        <br/>
                    </Text>                     
                </View>
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
            </>
            );
    }
}

export default Home;
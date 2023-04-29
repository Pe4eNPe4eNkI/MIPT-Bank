import React, {Component} from 'react';
import {Text, View} from 'react-native';
import CarouselBox from '../Components/Carousel';
import './Home.css';
import companyLogo from '../Components/images/logo_based.png';

class Home extends Component {
    render() {
        return (
            <div className="big_div_home">
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

                <a href="http://localhost:3000/sign_in"><text className = "sign_in_header" width="auto" height="auto">Sign in</text></a>
                <a href="http://localhost:3000/account"><text className = "test" width="auto" height="auto">Account</text></a>
            </div>
            <div className= 'quote'>
                    <View>
                    <Text style = {{fontSize : '65px', color : 'white', marginLeft: '30px', fontWeight: '6   00', marginTop: '55px'}}>

                        "GOLD IS OUR <br/>
                        FUTURE AND HOPE<br/>
                        IN A FICKLE WORLD”<br/>
                         — Voronin A. V.
                    </Text>                     
                </View>
            </div>

            <div className="menu_block">
                <div className="statistic">
                    <text style = {{fontSize : '65px', marginLeft: '30px'}}>Our statistic</text>
                    <div className="orange_brick"></div>
                    <div className="statistic_description">
                        <text style = {{fontSize : '40px', marginLeft: '32px', marginTop: '30px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Gold resource</text>
                        <text style = {{fontSize : '40px', marginLeft: '32px', marginTop: '10px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>270.000t</text>
                        <text style = {{fontSize : '40px', marginLeft: '32px', marginTop: '10px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Users</text>
                        <text style = {{fontSize : '40px', marginLeft: '32px', marginTop: '10px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>34.553</text>
                    </div>
                </div>

                <div className="products">
                    <text style = {{fontSize : '65px', marginLeft: '30px', textAlign: 'left'}}>Our products</text>
                    <div className="orange_brick"></div>
                    <div className="products_description">
                        <div className="div">
                            <div className="card_home">
                                <img className = "logo_card_home" src={companyLogo} alt=" " width="25" height="auto"/>
                                <text className = "type_card_home" width="auto" height="auto">Debit</text>
                                <text className = "numbers_card_home" width="auto" height="auto">1234 5678 9456 7362</text>
                                <text className = "name_card_home" width="auto" height="auto">Mr. Voronin Arseniy</text>
                            </div>

                            <text className = "characteristics_card_home_first" width="auto" height="auto">Free service forever</text>
                            <text className = "characteristics_card_home" width="auto" height="auto">Cash-back 5% for every purchase</text>
                            <text className = "characteristics_card_home" width="auto" height="auto">Government-free system</text>
                            <text className = "characteristics_card_home" width="auto" height="auto">Transfer up to 500.000$ per month</text>
                        </div>

                        <div className="div" style={{marginLeft: '100px'}}>
                            <div className="card_home">
                                <img className = "logo_card_home" src={companyLogo} alt=" " width="25" height="auto"/>
                                <text className = "type_card_home" width="auto" height="auto">Credit</text>
                                <text className = "numbers_card_home" width="auto" height="auto">1234 5678 9456 7362</text>
                                <text className = "name_card_home" width="auto" height="auto">Mr. Voronin Arseniy</text>
                            </div>

                            <text className = "characteristics_card_home_first" width="auto" height="auto">Free service forever</text>
                            <text className = "characteristics_card_home" width="auto" height="auto">Only 5% payment every month</text>
                            <text className = "characteristics_card_home" width="auto" height="auto">Government-free system</text>
                            <text className = "characteristics_card_home" width="auto" height="auto">Up to 100.000$ credit limit</text>
                        </div>

                        <div className="div" style={{marginLeft: '100px'}}>
                            <div className="card_home">
                                <img className = "logo_card_home" src={companyLogo} alt=" " width="25" height="auto"/>
                                <text className = "type_card_home" width="auto" height="auto">Deposit</text>
                                <text className = "numbers_card_home" width="auto" height="auto">1234 5678 9456 7362</text>
                                <text className = "name_card_home" width="auto" height="auto">Mr. Voronin Arseniy</text>
                            </div>

                            <text className = "characteristics_card_home_first" width="auto" height="auto">Free service forever</text>
                            <text className = "characteristics_card_home" width="auto" height="auto">Upt to 7.5% per annum</text>
                            <text className = "characteristics_card_home" width="auto" height="auto">Government-free system</text>
                            <text className = "characteristics_card_home" width="auto" height="auto">Payment like a card up to<br/> 5.000$ per month</text>
                        </div>
                        <div className="credit_div"></div>
                        <div className="deposit_div"></div>
                    </div>
                </div>
            </div>
                <div className="fucking_footer"></div>
            </div>
            );
    }
}

export default Home;
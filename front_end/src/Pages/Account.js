import React, {Component} from 'react';
import {Text, View} from 'react-native';
import CarouselBox from '../Components/Carousel';
import './Account.css';
import companyLogo from '../Components/images/logo_based.png';

var i = 1;

class Home extends Component {
    render() {
        return (
            <div className="big_div_account">
                <div className= 'fucking_header_account'>
                    {/*<img className = "logo" src = "../Components/images/logo_based.png"alt = " "/>*/}
                    <a href="http://localhost:3000/"><img className = "logo_account" src={companyLogo} alt=" " width="50" height="auto"/></a>
                    <a href="http://localhost:3000/" style={{textDecoration: 'none'}}>
                        <View style={{float: 'left', top: '22px', width: '300px'}}>
                            <Text style = {{fontSize : '40px', color : '#9D9FA2', marginLeft: '5px', fontWeight: '6   00'}}>
                                POLYBANK
                            </Text>
                        </View></a>
                    {/*<a href="http://localhost:3000/"><View> <Text style = {{fontSize : '65px', color : 'white', marginLeft: '10%', fontWeight: '6   00'}}>POLYBANK</Text></View></a>*/}

                    <a href="http://localhost:3000/"><text className = "exit_account" width="auto" height="auto">Exit</text></a>
                </div>
                <div className= 'quote_account'>
                    <div className="Cashblock">
                        <text style = {{fontSize : '40px', color : '#5E5E5E', marginLeft: '25px', fontWeight: '6   00', textAlign: 'left', float: 'left', marginTop: '20px'}}>
                            CURRENT ACCOUNT
                        </text>

                        <div className="Cashblock_info">
                            <div className="Available">
                                <text style = {{fontSize : '25px', color : '#9D9FA2', marginLeft: '25px', fontWeight: '6   00', textAlign: 'left', float: 'left', marginTop: '10px'}}>
                                    Available
                                </text>
                                <text style = {{fontSize : '50px', color : '#5E5E5E', marginLeft: '25px', fontWeight: '6   00', textAlign: 'left', float: 'left', marginTop: '10px'}}>
                                    5.467$
                                </text>
                            </div>
                            <div className="Debt">
                                <text style = {{fontSize : '25px', color : '#9D9FA2', marginLeft: '25px', fontWeight: '6   00', textAlign: 'left', float: 'left', marginTop: '10px'}}>
                                    Debt
                                </text>
                                <text style = {{fontSize : '50px', color : '#5E5E5E', marginLeft: '25px', fontWeight: '6   00', textAlign: 'left', float: 'left', marginTop: '10px'}}>
                                    -267$
                                </text>
                            </div>
                            <div className="Income">
                                <text style = {{fontSize : '25px', color : '#9D9FA2', marginLeft: '25px', fontWeight: '6   00', textAlign: 'left', float: 'left', marginTop: '10px'}}>
                                    Income
                                </text>
                                <text style = {{fontSize : '50px', color : '#5E5E5E', marginLeft: '25px', fontWeight: '6   00', textAlign: 'left', float: 'left', marginTop: '10px'}}>
                                    647$
                                </text>
                            </div>
                        </div>

                        {/*<Text style = {{fontSize : '27px', color : '#9D9FA2', marginLeft: '30px', fontWeight: '6   00', textAlign: 'left', float: 'left', marginTop: '3px'}}>
                            You have: 3 products
                        </Text>*/}

                        <div className="buttons_operations">
                            <button className = "button_local_operation_first" type='submit' onClick={(e) => {
                                e.preventDefault();
                                window.location.href='http://localhost:3000/replenish';
                            }}>Withdraw</button>
                            <button className = "button_local_operation" type='submit' onClick={(e) => {
                                e.preventDefault();
                                window.location.href='http://localhost:3000/withdraw';
                            }}>Replenish</button>
                        </div>

                        <Text style = {{fontSize : '18px', color : '#9D9FA2', marginRight: '25px', fontWeight: '6   00', textAlign: 'right', float: 'right', marginTop: '65px', textDecorationLine: 'underline'}}>
                            Requisites
                        </Text>
                    </div>
                </div>

                <div className="history">
                    <text style = {{fontSize : '45px', marginLeft: '32px'}}>History of operations</text>
                    <div className="orange_brick_account"></div>
                    <div className="gray_line"></div>
                    <div className="statistic_description_account">
                        <div className="statistic_description_account_inside">
                            <div className="block_of_history">
                                <text style = {{fontSize : '35px', marginLeft: '32px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Monkey grinder</text>
                                <text style = {{fontSize : '20px', marginLeft: '-228px', marginTop: '40px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Fast food</text>
                                <text style = {{fontSize : '40px', marginRight: '1px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>-1.200$</text>
                            </div>

                            <div className="block_of_history">
                                <text style = {{fontSize : '35px', marginLeft: '32px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Monkey grinder</text>
                                <text style = {{fontSize : '20px', marginLeft: '-228px', marginTop: '40px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Fast food</text>
                                <text style = {{fontSize : '40px', marginRight: '1px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>-1.200$</text>
                            </div>

                            <div className="block_of_history">
                                <text style = {{fontSize : '35px', marginLeft: '32px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Monkey grinder</text>
                                <text style = {{fontSize : '20px', marginLeft: '-228px', marginTop: '40px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Fast food</text>
                                <text style = {{fontSize : '40px', marginRight: '1px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>-1.200$</text>
                            </div>

                            <div className="block_of_history">
                                <text style = {{fontSize : '35px', marginLeft: '32px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Monkey grinder</text>
                                <text style = {{fontSize : '20px', marginLeft: '-228px', marginTop: '40px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Fast food</text>
                                <text style = {{fontSize : '40px', marginRight: '1px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>-1.200$</text>
                            </div>

                            <div className="block_of_history">
                                <text style = {{fontSize : '35px', marginLeft: '32px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Monkey grinder</text>
                                <text style = {{fontSize : '20px', marginLeft: '-228px', marginTop: '40px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Fast food</text>
                                <text style = {{fontSize : '40px', marginRight: '1px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>-1.200$</text>
                            </div>

                            <div className="block_of_history">
                                <text style = {{fontSize : '35px', marginLeft: '32px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Monkey grinder</text>
                                <text style = {{fontSize : '20px', marginLeft: '-228px', marginTop: '40px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Fast food</text>
                                <text style = {{fontSize : '40px', marginRight: '1px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>-1.200$</text>
                            </div>

                            <div className="block_of_history">
                                <text style = {{fontSize : '35px', marginLeft: '32px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Monkey grinder</text>
                                <text style = {{fontSize : '20px', marginLeft: '-228px', marginTop: '40px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Fast food</text>
                                <text style = {{fontSize : '40px', marginRight: '1px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>-1.200$</text>
                            </div>

                            <div className="block_of_history">
                                <text style = {{fontSize : '35px', marginLeft: '32px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Monkey grinder</text>
                                <text style = {{fontSize : '20px', marginLeft: '-228px', marginTop: '40px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Fast food</text>
                                <text style = {{fontSize : '40px', marginRight: '1px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>-1.200$</text>
                            </div>

                            <div className="block_of_history">
                                <text style = {{fontSize : '35px', marginLeft: '32px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Monkey grinder</text>
                                <text style = {{fontSize : '20px', marginLeft: '-228px', marginTop: '40px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Fast food</text>
                                <text style = {{fontSize : '40px', marginRight: '1px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>-1.200$</text>
                            </div>

                            <div className="block_of_history">
                                <text style = {{fontSize : '35px', marginLeft: '32px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Monkey grinder</text>
                                <text style = {{fontSize : '20px', marginLeft: '-228px', marginTop: '40px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Fast food</text>
                                <text style = {{fontSize : '40px', marginRight: '1px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>-1.200$</text>
                            </div>

                            <div className="block_of_history">
                                <text style = {{fontSize : '35px', marginLeft: '32px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Monkey grinder</text>
                                <text style = {{fontSize : '20px', marginLeft: '-228px', marginTop: '40px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Fast food</text>
                                <text style = {{fontSize : '40px', marginRight: '1px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>-1.200$</text>
                            </div>

                            <div className="block_of_history">
                                <text style = {{fontSize : '35px', marginLeft: '32px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Monkey grinder</text>
                                <text style = {{fontSize : '20px', marginLeft: '-228px', marginTop: '40px', position: 'relative', float: 'left', textAlign: 'left', color: '#9D9FA2'}}>Fast food</text>
                                <text style = {{fontSize : '40px', marginRight: '1px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>-1.200$</text>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="my_products">
                    <text style = {{fontSize : '45px', marginLeft: '-150px'}}>My products</text>
                    <div className="orange_brick_account"></div>
                    <div className="gray_line_products"></div>
                    <div className="products_description_account">
                        <div className="products_description_account_inside">
                            <div className="block_of_products">
                                <div className="card_account">
                                    <img className = "logo_card_account" src={companyLogo} alt=" " width="25" height="auto"/>
                                    <text className = "type_card_account" width="auto" height="auto">Debit</text>
                                    <text className = "numbers_card_account" width="auto" height="auto">*7362</text>
                                </div>
                                <text style = {{fontSize : '25px', marginLeft: '10px', marginTop: '5px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Available:</text>
                                <text style = {{fontSize : '25px', marginLeft: '-100px', marginTop: '35px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Income:</text>
                                <text style = {{fontSize : '25px', marginRight: '5px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>10.256$</text>
                                <text style = {{fontSize : '25px', marginRight: '-80px', marginTop: '35px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>1.560$</text>
                                <div className="gray_line_products_block"></div>
                            </div>

                            <div className="block_of_products">
                                <div className="card_account">
                                    <img className = "logo_card_account" src={companyLogo} alt=" " width="25" height="auto"/>
                                    <text className = "type_card_account" width="auto" height="auto">Debit</text>
                                    <text className = "numbers_card_account" width="auto" height="auto">*7362</text>
                                </div>
                                <text style = {{fontSize : '25px', marginLeft: '10px', marginTop: '5px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Available:</text>
                                <text style = {{fontSize : '25px', marginLeft: '-100px', marginTop: '35px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Income:</text>
                                <text style = {{fontSize : '25px', marginRight: '5px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>10.256$</text>
                                <text style = {{fontSize : '25px', marginRight: '-80px', marginTop: '35px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>1.560$</text>
                                <div className="gray_line_products_block"></div>
                            </div>

                            <div className="block_of_products">
                                <div className="card_account">
                                    <img className = "logo_card_account" src={companyLogo} alt=" " width="25" height="auto"/>
                                    <text className = "type_card_account" width="auto" height="auto">Debit</text>
                                    <text className = "numbers_card_account" width="auto" height="auto">*7362</text>
                                </div>
                                <text style = {{fontSize : '25px', marginLeft: '10px', marginTop: '5px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Available:</text>
                                <text style = {{fontSize : '25px', marginLeft: '-100px', marginTop: '35px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Income:</text>
                                <text style = {{fontSize : '25px', marginRight: '5px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>10.256$</text>
                                <text style = {{fontSize : '25px', marginRight: '-80px', marginTop: '35px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>1.560$</text>
                                <div className="gray_line_products_block"></div>
                            </div>

                            <div className="block_of_products">
                                <div className="card_account">
                                    <img className = "logo_card_account" src={companyLogo} alt=" " width="25" height="auto"/>
                                    <text className = "type_card_account" width="auto" height="auto">Debit</text>
                                    <text className = "numbers_card_account" width="auto" height="auto">*7362</text>
                                </div>
                                <text style = {{fontSize : '25px', marginLeft: '10px', marginTop: '5px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Available:</text>
                                <text style = {{fontSize : '25px', marginLeft: '-100px', marginTop: '35px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Income:</text>
                                <text style = {{fontSize : '25px', marginRight: '5px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>10.256$</text>
                                <text style = {{fontSize : '25px', marginRight: '-80px', marginTop: '35px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>1.560$</text>
                                <div className="gray_line_products_block"></div>
                            </div>

                            <div className="block_of_products">
                                <div className="card_account">
                                    <img className = "logo_card_account" src={companyLogo} alt=" " width="25" height="auto"/>
                                    <text className = "type_card_account" width="auto" height="auto">Debit</text>
                                    <text className = "numbers_card_account" width="auto" height="auto">*7362</text>
                                </div>
                                <text style = {{fontSize : '25px', marginLeft: '10px', marginTop: '5px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Available:</text>
                                <text style = {{fontSize : '25px', marginLeft: '-100px', marginTop: '35px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Income:</text>
                                <text style = {{fontSize : '25px', marginRight: '5px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>10.256$</text>
                                <text style = {{fontSize : '25px', marginRight: '-80px', marginTop: '35px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>1.560$</text>
                                <div className="gray_line_products_block"></div>
                            </div>

                            <div className="block_of_products">
                                <div className="card_account">
                                    <img className = "logo_card_account" src={companyLogo} alt=" " width="25" height="auto"/>
                                    <text className = "type_card_account" width="auto" height="auto">Debit</text>
                                    <text className = "numbers_card_account" width="auto" height="auto">*7362</text>
                                </div>
                                <text style = {{fontSize : '25px', marginLeft: '10px', marginTop: '5px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Available:</text>
                                <text style = {{fontSize : '25px', marginLeft: '-100px', marginTop: '35px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Income:</text>
                                <text style = {{fontSize : '25px', marginRight: '5px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>10.256$</text>
                                <text style = {{fontSize : '25px', marginRight: '-80px', marginTop: '35px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>1.560$</text>
                                <div className="gray_line_products_block"></div>
                            </div>

                            <div className="block_of_products">
                                <div className="card_account">
                                    <img className = "logo_card_account" src={companyLogo} alt=" " width="25" height="auto"/>
                                    <text className = "type_card_account" width="auto" height="auto">Debit</text>
                                    <text className = "numbers_card_account" width="auto" height="auto">*7362</text>
                                </div>
                                <text style = {{fontSize : '25px', marginLeft: '10px', marginTop: '5px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Available:</text>
                                <text style = {{fontSize : '25px', marginLeft: '-100px', marginTop: '35px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Income:</text>
                                <text style = {{fontSize : '25px', marginRight: '5px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>10.256$</text>
                                <text style = {{fontSize : '25px', marginRight: '-80px', marginTop: '35px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>1.560$</text>
                                <div className="gray_line_products_block"></div>
                            </div>

                            <div className="block_of_products">
                                <div className="card_account">
                                    <img className = "logo_card_account" src={companyLogo} alt=" " width="25" height="auto"/>
                                    <text className = "type_card_account" width="auto" height="auto">Debit</text>
                                    <text className = "numbers_card_account" width="auto" height="auto">*7362</text>
                                </div>
                                <text style = {{fontSize : '25px', marginLeft: '10px', marginTop: '5px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Available:</text>
                                <text style = {{fontSize : '25px', marginLeft: '-100px', marginTop: '35px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Income:</text>
                                <text style = {{fontSize : '25px', marginRight: '5px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>10.256$</text>
                                <text style = {{fontSize : '25px', marginRight: '-80px', marginTop: '35px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>1.560$</text>
                                <div className="gray_line_products_block"></div>
                            </div>

                            <div className="block_of_products">
                                <div className="card_account">
                                    <img className = "logo_card_account" src={companyLogo} alt=" " width="25" height="auto"/>
                                    <text className = "type_card_account" width="auto" height="auto">Debit</text>
                                    <text className = "numbers_card_account" width="auto" height="auto">*7362</text>
                                </div>
                                <text style = {{fontSize : '25px', marginLeft: '10px', marginTop: '5px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Available:</text>
                                <text style = {{fontSize : '25px', marginLeft: '-100px', marginTop: '35px', position: 'relative', float: 'left', textAlign: 'left', color: '#5E5E5E'}}>Income:</text>
                                <text style = {{fontSize : '25px', marginRight: '5px', marginTop: '0px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>10.256$</text>
                                <text style = {{fontSize : '25px', marginRight: '-80px', marginTop: '35px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}>1.560$</text>
                            </div>

                        </div>
                    </div>
                </div>

                <div className="showcase">
                    <text style = {{fontSize : '45px', marginLeft: '32px'}}>Showcase</text>
                    <div className="orange_brick_account"></div>
                    <div className="gray_line_showcase"></div>

                    <div className="card_showcase_top">
                        <a href="http://localhost:3000/receive_debit">
                        <div className="card_account">
                            <img className = "logo_card_account" src={companyLogo} alt=" " width="25" height="auto"/>
                            <text className = "type_card_account" width="auto" height="auto">Debit</text>
                            <text className = "numbers_card_account_showcase_deb" width="auto" height="auto">*1234</text>
                        </div>
                        </a>
                    </div>

                    {/*style = {{fontSize : '25px', marginRight: '-80px', marginTop: '35px', position: 'relative', float: 'right', textAlign: 'right', color: '#5E5E5E'}}*/}

                    <div className="card_showcase">
                        <a href="http://localhost:3000/receive_credit">
                        <div className="card_account">
                            <img className = "logo_card_account" src={companyLogo} alt=" " width="25" height="auto"/>
                            <text className = "type_card_account" width="auto" height="auto">Credit</text>
                            <text className = "numbers_card_account_showcase_cred" width="auto" height="auto">*1234</text>
                        </div>
                        </a>
                    </div>

                    <div className="card_showcase">
                        <a href="http://localhost:3000/receive_deposit">
                        <div className="card_account">
                            <img className = "logo_card_account" src={companyLogo} alt=" " width="25" height="auto"/>
                            <text className = "type_card_account" width="auto" height="auto">Deposit</text>
                            <text className = "numbers_card_account_showcase_dep" width="auto" height="auto">*1234</text>
                        </div>
                        </a>
                    </div>
                </div>
            </div>
        );
    }
}
export default Home;
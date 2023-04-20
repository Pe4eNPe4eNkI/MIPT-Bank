import React, {Component} from 'react';
import {Text, View} from 'react-native';
import CarouselBox from '../Components/Carousel';
import './Home.css';

class Home extends Component {
    render() {
        return (
            <>
            <div class= 'quote'>
                    <View>
                    <Text style = {{fontSize : '2.5rem' ,color : 'white' , marginLeft: '30px', fontWeight: '6   00'}}>

                       <br/>"GOLD IS OUR <br/>
                        FUTURE AND HOPE<br/>
                        IN A FICKLE WORLD”<br/>
                         — Voronin A. V.<br/>
                    <br/>
                    </Text>                     
                </View>
            </div>
            <table class = 'table' cellSpacing='3' width = '100%'>
                <tr>
                    <td>
                        <h2>Our statistic</h2>
                    </td>
                    <td>
                        <h2>Our Products</h2>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h4 class='text1'>Gold resourse</h4>
                        <h3 class='text1' style={{color: "black"}}>270.000 t</h3>
                        <h4 class='text1'>Users</h4>
                        <h3 class='text1' style={{color: "black"}}>34.553</h3>
                    </td>
                    <td>
                        <table cellSpacing='3' width = '100%'>
                            <tr>
                                <td>
                                    <div class = 'deposit'>
                                        <img src='../Components/images/deposit.svg' alt=""></img>
                                    </div>
                                </td>
                                <td>
                                    <div class = 'credit'>
                                        <img src='../Components/images/credit.svg' alt=""></img>
                                    </div>
                                </td>
                                <td>
                                    <div class = 'debit'>
                                        <img src='../Components/images/deposit.svg' alt=""></img>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h6 class = 'text'>Free service forever</h6>
                                    <h6 class = 'text'>Cash-back 5% payment every month</h6>
                                    <h6 class = 'text'>Govermant-free system</h6>
                                    <h6 class = 'text'>Transfer up to 500.000$ per month</h6>
                                </td>
                                <td>
                                    <h6 class = 'text'>Free service forever</h6>
                                    <h6 class = 'text'>Cash-back 5% payment every month</h6>
                                    <h6 class = 'text'>Govermant-free system</h6>
                                    <h6 class = 'text'>Up to 100.000$ credit limit</h6>
                                </td>
                                <td>
                                    <h6 class = 'text'>Free service forever</h6>
                                    <h6 class = 'text'>Up to 7.5% per annum</h6>
                                    <h6 class = 'text'>Govermant-free system</h6>
                                    <h6 class = 'text'>Payment like a card up to 5.000$ per month</h6>
                                    <h6 class = 'text'>Use your deposit like a card with minor restrictions</h6>

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
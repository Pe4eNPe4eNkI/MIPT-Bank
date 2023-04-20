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
            <table class = 'table'cellSpacing='3' width = '100%'>
                <tr><td><h2>Our statistic</h2></td><td><h2>Our Products</h2></td></tr>
            </table>
            </>
            );
    }
}

export default Home;
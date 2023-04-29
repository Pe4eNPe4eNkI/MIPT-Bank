import React, {Component } from "react";
import Carousel from 'react-bootstrap/Carousel';
import photo_3 from './images/fon_3.jpg'
import photo_4 from './images/fon_4.jpg'

export default class Home extends Component{
    render() {
        return(
            <Carousel interval = "5000" pause="false">
                <Carousel.Item>
                    <img
                        className = "d-block w-100"
                        src = {photo_3}
                        alt = "photo_3"
                        height={'0%'}
                    />
                    <Carousel.Caption className = "color : black">
                        <h2 text-light bg-dark>PolyBank</h2>
                        <p text-light bg-dark>TEXT ZDES</p>
                    </Carousel.Caption>
                </Carousel.Item>

                <Carousel.Item>
                    <img
                        className = "d-block w-100"
                        src = {photo_4}
                        alt = "photo_4"
                    />
                </Carousel.Item>

            </Carousel>

        )
    }
}
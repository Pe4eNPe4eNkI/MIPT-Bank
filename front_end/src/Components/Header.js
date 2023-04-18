import React, {Component} from 'react'
import {Navbar, Container, Nav, Button} from 'react-bootstrap'
import logo from "./images/new_logo.png"
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import Blog from '../Pages/Blog'
import Contacts from '../Pages/Contacts'
import About from '../Pages/About'
import Home from '../Pages/Home'
import Sign_in from "../Pages/Sign_in";
import Sign_up from "../Pages/Sign_up";


export default class Header extends Component {
    render() {
        return (
            <>
                <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                    <Container>
                        <Navbar.Brand href="/">
                            <img
                                src={logo}
                                height='50px'
                                className="d-inline-block align-top"
                                alt="logo"
                            />
                        </Navbar.Brand>
                        <Navbar.Toggle aria-controls="responsive-navbar-nav"/>
                        <Navbar.Collapse id="responsive-navbar-nav">
                            <Nav className="mb-1,15">
                                <Nav.Link href="/">Home </Nav.Link>
                                <Nav.Link href="/about"> About us </Nav.Link>
                                <Nav.Link href="/contacts"> Contacts </Nav.Link>
                                <Nav.Link href="/blog"> Blog </Nav.Link>
                            </Nav>

                            <div style={{marginLeft: "60%", marginButton: "1.15px"}}>
                                <Button style={{background: "#44944A", margin: "5px", border: "none"}} href="/sign_in">Sign
                                    in </Button>

                                <Button style={{background: "#44944A", border: "none"}} href="/sign_up">Sign
                                    up </Button>
                            </div>


                        </Navbar.Collapse>
                    </Container>
                </Navbar>

                <Router>
                    <Routes>
                        <Route exact path="/" element={<Home/>}/>
                        <Route exact path="/about" element={<About/>}/>
                        <Route exact path="/contacts" element={<Contacts/>}/>
                        <Route exact path="/blog" element={<Blog/>}/>
                        <Route exact path="/sign_in" element={<Sign_in/>}/>
                        <Route exact path="/sign_up" element={<Sign_up/>}/>
                    </Routes>
                </Router>

            </>
        )
    }
}
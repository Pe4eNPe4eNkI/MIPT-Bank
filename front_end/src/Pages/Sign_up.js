import React, {Component} from 'react';
import './Sign_up.css'
import companyLogo from "../Components/images/logo_based.png";
import Sign_up_form from './Sign_up_form';

class SignUp extends Component {

    
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
                <div className="Sign_up">
                    <h2 className = "Te_xt">Enter information <br/> about you to use <br/> me daddy </h2>
                    <div className = "block_gray"></div>
                    <Sign_up_form/>
                </div>
            </>
        );
    }
}

export default SignUp;
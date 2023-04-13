import My_text_button_panel from "./My_text_button_panel";

function Sign__menu (params){

return (<div className="sign_menu">
    <My_text_button_panel></My_text_button_panel>

    <div className="first_submit_button">
        <input type="submit" value = "Sign in"/>
    </div>

    <div className="second_submit_button">
        <input type="submit"  value = "Sign up"/>
    </div>

    </div>
);
}

export default Sign__menu;
import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";

const ShowHomeMenu = ({children}) => {

    const location = useLocation();
    const [showNavbar, setShowNavbar] = useState(false)

    useEffect(() => {
        console.log("location: " + location)
        if(location.pathname == '/login' || location.pathname == '/registration'){
            setShowNavbar(false)
        } else {
            setShowNavbar(true)
        }
        
      }, [location]);


    return (
        <div>
            {showNavbar && children}
        </div>
    );
};

export default ShowHomeMenu;
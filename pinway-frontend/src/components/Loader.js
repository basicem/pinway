import React from "react";



const Loader = ({ isLoading }) => (
    <div className="text-center">
        <div className={`spinner-border text-secondary ${isLoading ? 'visible' : 'invisible'}`} role="status" >
        </div>
    </div>
);

export default Loader;
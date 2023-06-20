import axios from "axios";

const API = axios.create({
    // url na apigateway
    baseURL: "http://localhost:8085",
    
});

export default API;
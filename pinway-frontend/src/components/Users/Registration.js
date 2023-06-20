import React, { useEffect, useState } from "react";
import './AccessForms.css';
import axios from 'axios';
import Logo from 'images/pinway_logo.png';
import { addUSer } from 'api/users';
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

import placeholder from  "images/place_holder.png";

function Registration(){

    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const [nameError, setNameError] = useState('');
    const [surnameError, setSurnameError] = useState('');
    const [usernameError, setUsernameError] = useState('');
    const [emailError, setEmailError]  = useState('');
    const [passwordError, setPasswordError]  = useState('');
    const navigate = useNavigate();

    async function save(event)
        {
        // event.preventDefault();
        // setUsernameError("");
        // setNameError("");
        // setSurnameError("");
        // setPasswordError("");
        // setEmailError("")
        // let error = false;
        // let regName = new RegExp('.+[@].+[\\.].+')
        // if (username.length == 0){
        //     setUsernameError('Username cannot be empty')
        //     error = true;
        // }
        // if (name.length == 0){
        //     setNameError('First name cannot be empty')
        //     error = true;
        // }
        // if (surname.length == 0){
        //     setSurnameError('Last name cannot be empty')
        //     error = true;
        // }
        // if (password.length == 0){
        //     setPasswordError('Password cannot be empty')
        //     error = true;
        // }
        // if (!regName.test(email)){
        //     setEmailError('Please enter a valid email')
        //     error = true;
        // }

        // if(error){
        //     return;
        // }

        // error = false

        try {
            const data = {
                name: name,
                surname: surname,
                username: username,
                email: email,
                password: password
            };
            await addUSer(data)
            toast.success("Registration confirmed");
            navigate("/login")
        } catch(err) {
            toast.error("Username or email already taken");
        }
   }


        return (
        <div>
            <div className='form-container mx-auto mt-4'>
                <div className='form-content-left rounded px-5'>
                    <div className=" text-center">
                        <img className="image image-contain w-50 mt-2 mx-auto" src={Logo} alt="Pinway logo"></img>
                    </div>
                        <h3>Please Sign Up</h3>

                        <div className='form-group'>
                            <label className='form-label'>First name</label>
                            <input
                                className='form-control'
                                type='text'
                                name='name'
                                placeholder='Enter first name'
                                value={name}
                                onChange={(event) =>
                                    {
                                    setName(event.target.value);
                                    }}
                            />
                            {nameError && <p style={{ color: 'red', fontSize: '12px', marginTop: '5px' }}>{nameError}</p>}
                        </div>

                        <div className='form-group'>
                        <label className='form-label'>Last name</label>
                            <input
                                className='form-control'
                                type='text'
                                name='surname'
                                placeholder='Enter last name'
                                value={surname}
                                onChange={(event) =>
                                    {
                                        setSurname(event.target.value);
                                    }}
                            />
                            {surnameError && <p style={{ color: 'red', fontSize: '12px', marginTop: '5px' }}>{surnameError}</p>}
                        </div>     

                        <div className='form-group'>
                        <label className='form-label'>Username</label>
                            <input
                                className='form-control'
                                type='text'
                                name='username'
                                placeholder='Enter your username'
                                value={username}
                                onChange={(event) =>
                                   {
                                       setUsername(event.target.value);
                                }}
                            />
                            {usernameError && <p style={{ color: 'red', fontSize: '12px', marginTop: '5px' }}>{usernameError}</p>}
                        </div>

                        <div className='form-group'>
                        <label className='form-label'>Email</label>
                            <input
                                className='form-control'
                                type='email'
                                name='email'
                                placeholder='Enter your email'
                                value={email}
                                onChange={(event) =>
                                   {
                                       setEmail(event.target.value);
                                   }}
                            />
                            {emailError && <p style={{ color: 'red', fontSize: '12px', marginTop: '5px' }}>{emailError}</p>}
                        </div>

                        <div className='form-group'>
                            <label className='form-label'>Password</label>
                                <input
                                    className='form-control'
                                    type='password'
                                    name='password'
                                    placeholder='Enter your password'
                                    value={password}
                                    onChange={(event) =>
                                    {
                                        setPassword(event.target.value);
                                    }}
                            />
                            {passwordError && <p style={{ color: 'red', fontSize: '12px', marginTop: '5px' }}>{passwordError}</p>}
                        </div>

                        <button className="button-login" onClick={save}>
                            Sign Up
                        </button>
                        <div className="text-center">
                            <span  className='form-input-login mb-1' onClick={() => {navigate("/login")}}>
                                Already have created account? Login <a>here</a>
                            </span>
                        </div>

                </div>
            </div>
        </div>

    );
}

export default Registration;
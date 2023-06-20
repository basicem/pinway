import React from "react";
import {
  BrowserRouter as Router, Routes, Route, Navigate,
} from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import HomeMenu from "components/HomeMenu";
import Collections from "components/Collections/Collections";
import LogIn from "./Users/LogIn"
import Registration from "./Users/Registration"
import Footer from 'components/Footer';


import CollectionDetails from "components/Collections/CollectionDetails";
import PostCreate from "./Posts/PostCreate";
import PostDetails from "./Posts/PostDetails";
import HomePage from "components/HomePage";
import EditProfile from "./Users/EditProfile";

import UserProfile from "components/Users/UserProfile";
import UserDetails from "components/Users/UserDetails";
import ShowHomeMenu from "./ShowHomeMenu";
import CollectionOtherUser from "./Collections/CollectionOtherUser";

const Root = () => (
  <div>
    <ToastContainer
      position="top-center"
      autoClose={5000}
      hideProgressBar={false}
      newestOnTop={false}
      closeOnClick
      rtl={false}
      pauseOnFocusLoss
      draggable
      pauseOnHover
    />
    <Router>
      <ShowHomeMenu>
        <HomeMenu />
      </ShowHomeMenu>
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/collections" element={<Collections />} />
        <Route path="/collections/details" element={<CollectionDetails />} />
        <Route path="/collections/user/details" element={<CollectionOtherUser />} />
        <Route path="/users/details" element={<UserDetails/>} />
        <Route path="/users/profile" element={<UserProfile/>} />
        <Route path="/login" element={<LogIn />} />
        <Route path="/registration" element={<Registration />} />
        <Route path="/posts/create" element={<PostCreate/>} />
        <Route path="/posts/details" element={<PostDetails/>} />
        <Route path="/addPost" element={<PostCreate/>} />
        <Route path="/postDetails" element={<PostDetails/>} />
        <Route path="/users/profile/edit" element={<EditProfile/>} />
      </Routes>
    </Router>
    {/* <Footer></Footer> */}
  </div>
);

export default Root;
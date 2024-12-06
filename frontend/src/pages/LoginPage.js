import React from 'react';
import Navbar from '../components/commons/Navbar';
import BrandTitle from '../components/commons/BrandTitle';
import Login from '../components/auth/Login';
import Footer from '../components/commons/Footer';
import './LoginPage.css';

function LoginPage() {
  return (
    <div className="loginPage">
       <Navbar />
      <BrandTitle />
      <div className="loginContent ">
      <div className="login-container">
      <Login />
      </div>
      </div>
      <Footer />
    </div>
  );
}

export default LoginPage;

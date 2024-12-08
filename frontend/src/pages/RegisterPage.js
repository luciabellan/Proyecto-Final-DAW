import React from 'react';
import Navbar from '../components/commons/Navbar';
import BrandTitle from '../components/commons/BrandTitle';
import Footer from '../components/commons/Footer';
import Register from '../components/auth/Register';


function RegisterPage() {
  return (
    <div className="registerPage">
      <Navbar />
      <BrandTitle />
      <div className="registerContent ">
      <div className="register-container">
      <Register />
      </div>
      </div>
      
      <Footer />
    </div>
  );
}

export default RegisterPage;

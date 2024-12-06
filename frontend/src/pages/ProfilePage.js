import React from 'react';
import Navbar from '../components/commons/Navbar';
import BrandTitle from '../components/commons/BrandTitle';
import Profile from '../components/profile/Profile';
import Footer from '../components/commons/Footer';



function ProfilePage() {
  return (
    <div className="profilePage">
      <Navbar />
      <BrandTitle />
      <Profile />
      <Footer />
    </div>
  );
}

export default ProfilePage;

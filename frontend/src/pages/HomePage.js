import React from 'react';
import Navbar from '../components/commons/Navbar';
import BrandTitle from '../components/commons/BrandTitle';
import Pricing from '../components/homePage/Pricing';
import HowItWorks from '../components/homePage/HowItWorks';
import BookOfTheMonth from '../components/homePage/BookOfTheMonth';
import PreviousBooks from '../components/homePage/PreviousBooks';
import FAQ from '../components/homePage/FAQ';
import Testimonials from '../components/homePage/Testimonials';
import Footer from '../components/commons/Footer';

function HomePage() {
  return (
    <div className="homepage">
      <Navbar />
      <BrandTitle />
      <Pricing />
      <HowItWorks />
      <BookOfTheMonth />
      <PreviousBooks />
      <Testimonials />
      <FAQ />
      <Footer />
    </div>
  );
}

export default HomePage;


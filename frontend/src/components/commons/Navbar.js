import React, { useState } from 'react';
import './Navbar.scss'; // Estilos específicos para la barra de menú
import logo from '../../assets/logo/logo_stories.svg';
import { Link } from 'react-router-dom';
import { FaBars, FaTimes } from 'react-icons/fa';
import './Navbar.scss';

const Navbar = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  return (
    <nav className="navbar">
      <div className="logo-container">
        <Link to="/">
          <img src={logo} alt="Cartagenita Stories" className="logo-image" />
        </Link>
      </div>

      {/* Botón de menú hamburguesa para móvil */}
      <div className="menu-icon" onClick={toggleMenu}>
        {isMenuOpen ? <FaTimes /> : <FaBars />}
      </div>

      {/* Links de navegación */}
      <div className={`nav-links ${isMenuOpen ? 'show' : ''}`}>
        <a href="/login">Login</a>
        <a href="/#faq-list">FAQ</a>
        <a href="/#contacto">Contacto</a>
      </div>
    </nav>
  );
};

export default Navbar;
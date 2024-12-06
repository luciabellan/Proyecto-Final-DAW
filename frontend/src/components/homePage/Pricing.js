import React from 'react';
import './Pricing.scss'; // Importar los estilos
import bookImage from '../../assets/images/cuento-agosto.svg'; // Asegúrate de tener esta imagen exportada
import { Link } from 'react-router-dom';
function Pricing() {
  return (
    <section className="pricing-section">
      <div className="pricing-container">
        <div className="pricing-image">
          <img src={bookImage} alt="El Faro Mágico de Cabo de Palos" />
        </div>
        <div className="pricing-details">
          <h2>Historias mágicas para los más peques</h2>
          <p>Una diferente cada mes</p>
          <h3>REGALA POR 19,99€</h3>
          <Link to="/register">
          <button className="customize-btn">REGÍSTRATE</button>
          </Link>
        </div>
      </div>
    </section>
  );
}

export default Pricing;

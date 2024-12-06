import React from 'react';
import './BookOfTheMonth.scss';
import bookImage from '../../assets/images/cuento-agosto.svg'; 

function BookOfTheMonth() {
  return (
    <section className="book-of-the-month">
      <h2>LIBRO DEL MES DE AGOSTO</h2>

      <div className="book-content">
        <div className="book-image">
          <img src={bookImage} alt="El Faro Mágico de Cabo de Palos" />
        </div>
        <div className="book-description">
          <h3>EL FARO MÁGICO DE CABO DE PALOS</h3>
          <p>
            ¡Embárcate en una aventura mágica en las costas de Cartagena!<br />
            ¿Alguna vez has soñado con tener poderes mágicos para ayudar a los demás? En "El Faro Mágico de Cabo de Palos", serás el héroe de tu propia historia submarina.
          </p>
          <ul>
            <li>Una aventura personalizada</li>
            <li>Descubre los misterios del faro más especial de toda España.</li>
            <li>Sumérgete en las cristalinas aguas del Mediterráneo y explora un mundo lleno de color y naturaleza.</li>
            <li>Descubre la importancia de los faros en la navegación.</li>
            <li>Flamantes aventuras para ti y tu familia.</li>
            <li>¡Y mucho más!</li>
          </ul>
          <button className="subscribe-btn">¡Regístrate!</button>
        </div>
      </div>
    </section>
  );
}

export default BookOfTheMonth;

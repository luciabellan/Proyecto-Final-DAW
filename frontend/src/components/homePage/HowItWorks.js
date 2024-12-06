import React, { useState, useRef } from 'react';
import './HowItWorks.scss';

// Importar los iconos
import subscribeIcon from '../../assets/icons/subscribe-icon.png';
import personalizeIcon from '../../assets/icons/personalize-icon.png';
import giftIcon from '../../assets/icons/gift-icon.png';
import opinionIcon from '../../assets/icons/opinion-icon.png';

function HowItWorks() {
  const [currentSlide, setCurrentSlide] = useState(0); // Estado para el slide actual
  const containerRef = useRef(null); // Referencia al contenedor del carrousel

  // Función para manejar el scroll y cambiar el punto activo
  const handleScroll = () => {
    const container = containerRef.current;
    const index = Math.round(container.scrollLeft / container.offsetWidth);
    setCurrentSlide(index);
  };

  return (
    <section className="how-it-works">
      <h2>¿CÓMO FUNCIONA?</h2>
      <div 
        className="how-it-works-container" 
        onScroll={handleScroll} 
        ref={containerRef}
      >
        <div className="how-it-works-block">
          <img src={subscribeIcon} alt="Suscríbete" />
          <h3>SUSCRÍBETE</h3>
          <p>
            Cada mes lanzamos un cuento diferente para que los más pequeños aprendan sobre un lugar de Cartagena.
          </p>
        </div>
        <div className="how-it-works-block">
          <img src={personalizeIcon} alt="Personaliza" />
          <h3>PERSONALIZA</h3>
          <p>
            Nuestros cuentos son personalizados para que los más pequeños se sientan los protagonistas
            de cada historia.
          </p>
        </div>
        <div className="how-it-works-block">
          <img src={giftIcon} alt="Regala" />
          <h3>¡REGALA!</h3>
          <p>
            Recibe el cuento del mes, toma un momento para dedicarlo a la lectura en familia o con amigos.
          </p>
        </div>
        <div className="how-it-works-block">
          <img src={opinionIcon} alt="Danos tu opinión" />
          <h3>DANOS SU OPINIÓN</h3>
          <p>
            Queremos saber qué opinan los protagonistas en primera persona. Déjanos tus comentarios.
          </p>
        </div>
      </div>

      {/* Indicadores del carrousel */}
      <div className="carousel-indicators">
        <div 
          className={currentSlide === 0 ? 'active' : ''} 
          onClick={() => containerRef.current.scrollTo({ left: 0, behavior: 'smooth' })}
        ></div>
        <div 
          className={currentSlide === 1 ? 'active' : ''} 
          onClick={() => containerRef.current.scrollTo({ left: containerRef.current.offsetWidth, behavior: 'smooth' })}
        ></div>
        <div 
          className={currentSlide === 2 ? 'active' : ''} 
          onClick={() => containerRef.current.scrollTo({ left: 2 * containerRef.current.offsetWidth, behavior: 'smooth' })}
        ></div>
        <div 
          className={currentSlide === 3 ? 'active' : ''} 
          onClick={() => containerRef.current.scrollTo({ left: 3 * containerRef.current.offsetWidth, behavior: 'smooth' })}
        ></div>
      </div>
    </section>
  );
}

export default HowItWorks;

import React from 'react';
import './Testimonials.css';

function Testimonials() {
  return (
    <section className="testimonials">
      <h2>Opiniones</h2>
      <div className="testimonial-item">
        <p>"Un libro increíble, a mi hijo le encantó verse como el protagonista. ¡Recomendado 100%!"</p>
        <span>- María G.</span>
      </div>
      <div className="testimonial-item">
        <p>"La calidad es excelente y el proceso de personalización fue muy sencillo."</p>
        <span>- Juan P.</span>
      </div>
    </section>
  );
}

export default Testimonials;

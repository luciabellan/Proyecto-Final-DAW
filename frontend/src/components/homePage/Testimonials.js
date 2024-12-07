import React, { useState } from 'react';
import './Testimonials.scss';

function Testimonials() {
  const testimonials = [
    {
      text: '"Un libro increíble, a mi hijo le encantó verse como el protagonista. ¡Recomendado 100%!"',
      author: '- María G.',
    },
    {
      text: '"La calidad es excelente y el proceso de personalización fue muy sencillo."',
      author: '- Juan P.',
    },
    {
      text: '"Un regalo inolvidable para mis sobrinos. Se divirtieron mucho leyéndolo."',
      author: '- Ana T.',
    },
  ];

  const [currentIndex, setCurrentIndex] = useState(0);

  const nextTestimonial = () => {
    setCurrentIndex((prevIndex) => (prevIndex + 1) % testimonials.length);
  };

  const prevTestimonial = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex === 0 ? testimonials.length - 1 : prevIndex - 1
    );
  };

  return (
    <section className="testimonials">
      <h2>Opiniones</h2>
      <div className="carousel">
        <button className="prev" onClick={prevTestimonial}>
          &#8592;
        </button>
        <div className="testimonial-item">
          <p>{testimonials[currentIndex].text}</p>
          <span>{testimonials[currentIndex].author}</span>
        </div>
        <button className="next" onClick={nextTestimonial}>
          &#8594;
        </button>
      </div>
    </section>
  );
}

export default Testimonials;

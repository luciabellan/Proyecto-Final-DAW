import React, { useState } from 'react';
import './FAQ.scss';

const FAQ = () => {
  const [activeIndex, setActiveIndex] = useState(null);

  const faqs = [
    {
      question: "¿Cuál es la temática de los cuentos?",
      answer: " Los cuentos están basados en lugares o elementos conocidos de la ciudad de Cartagena y alrededores."
    },
    {
      question: "¿Cómo creo un cuento personalizado?",
      answer: "Si no estás registrado, primero deberás crear tu cuenta de usuario. Una vez creada, desde tu panel de usuario podrás entrar el panel de personalización, ¡y comenzar a crear!"
    },
   
    {
      question: "¿Cúantos personajes puedo incluir?",
      answer: "Puedes incluir hasta un máximo de 3 personajes en el mismo cuento."
    },
    {
      question: "¿Cuándo recibiré el cuento?",
      answer: "Los cuentos se mandan a principio de cada mes."
    }
    // ... resto de FAQs
  ];

  return (
    <div className="faq-container">
      <h1>Preguntas Frecuentes</h1>
      <div className="faq-list" id='faq-list'>
        {faqs.map((faq, index) => (
          <div 
            key={index} 
            className="faq-item"
          >
            <button 
              className={`faq-question ${activeIndex === index ? 'active' : ''}`}
              onClick={() => setActiveIndex(activeIndex === index ? null : index)}
            >
              {faq.question}
              <span className="arrow"></span>
            </button>
            {activeIndex === index && (
              <div className="faq-answer">
                <p>{faq.answer}</p>
              </div>
            )}
          </div>
        ))}
      </div>
    </div>
  );
};

export default FAQ;

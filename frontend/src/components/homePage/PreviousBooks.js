import React, { useState, useEffect } from 'react';
import { Carousel } from 'react-bootstrap';
import axios from 'axios';
import './PreviousBooks.scss';

const apiUrl = process.env.REACT_APP_API_URL;


const PreviousBooks = () => {
  const [cuentos, setCuentos] = useState([]);

  useEffect(() => {
    const fetchCuentos = async () => {
      try {
        const response = await axios.get( `${apiUrl}/api/cuentos-disponibles`);
        setCuentos(response.data);
      } catch (error) {
        console.error('Error al cargar los cuentos:', error);
      }
    };

    fetchCuentos();
  }, []);

  return (
    <div className="previous-books-container">
      <h2 className="section-title">Nuestros Cuentos</h2>
      <div className="carousel-container">
        <Carousel>
          {cuentos.map((cuento) => (
            <Carousel.Item key={cuento.id}>
              <img
                className="d-block  carousel-image"
                src={require(`../../assets/images/${cuento.imagenUrl}`)}
                alt={cuento.titulo}
              />
              <Carousel.Caption className="carousel-caption">
                <h3>{cuento.titulo}</h3>
                <p>{cuento.descripcion}</p>
              </Carousel.Caption>
            </Carousel.Item>
          ))}
        </Carousel>
      </div>
    </div>
  );
};

export default PreviousBooks;

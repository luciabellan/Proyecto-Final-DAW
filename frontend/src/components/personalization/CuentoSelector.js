import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './CuentoSelector.scss';

const apiUrl = process.env.REACT_APP_API_URL;

const CuentoSelector = ({ formData, onChange }) => {
  const [cuentosDisponibles, setCuentosDisponibles] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchCuentos = async () => {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.get(`${apiUrl}/api/cuentos-disponibles`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setCuentosDisponibles(response.data);
      } catch (error) {
        console.error('Error al cargar los cuentos:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchCuentos();
  }, []);

  const handleCuentoChange = (e) => {
    const selectedCuentoId = e.target.value;
    onChange({
      cuento_id: selectedCuentoId
    });
  };

  const getImageUrl = (imageName) => {
    try {
      return require(`../../assets/images/${imageName}`);
    } catch (error) {
      console.error('Error al cargar la imagen:', error);
      return require('../../assets/images/default-book.png');
    }
  };

  const cuentoSeleccionado = cuentosDisponibles.find(
    cuento => cuento.id === parseInt(formData.cuento_id)
  );

  if (loading) {
    return <div className="loading">Cargando cuentos disponibles...</div>;
  }

  return (
    <div className="cuento-selector">
      <div className="form-group">
        <label htmlFor="cuento_id">
          Selecciona el cuento que quieres personalizar:
        </label>
        <select
          id="cuento_id"
          name="cuento_id"
          value={formData.cuento_id}
          onChange={handleCuentoChange}
          required
        >
          <option value="">Selecciona un cuento</option>
          {cuentosDisponibles.map((cuento) => (
            <option key={cuento.id} value={cuento.id}>
              {cuento.titulo}
            </option>
          ))}
        </select>
      </div>

      {cuentoSeleccionado && (
        <div className="cuento-preview">
          <div className="cuento-info">
            <div className="imagen-container">
              <img
                src={getImageUrl(cuentoSeleccionado.imagenUrl)}
                alt={cuentoSeleccionado.titulo}
                className="cuento-imagen"
              />
            </div>
            <div className="cuento-detalles">
              <h3>{cuentoSeleccionado.titulo}</h3>
              <p>{cuentoSeleccionado.descripcion}</p>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default CuentoSelector;
import React from 'react';
import './NumeroPersonajes.css';

const NumeroPersonajes = ({ onNumeroChange, numeroPersonajes }) => {
  return (
    <div className="numero-personajes-container">
      <h2>Paso 2: Elige el número de personajes</h2>
      <div className="form-group">
        <label htmlFor="numero-personajes">¿Cuántos personajes tendrá tu cuento?</label>
        <select
          id="numero-personajes"
          value={numeroPersonajes}
          onChange={(e) => onNumeroChange(parseInt(e.target.value))}
          className="select-personajes"
        >
          <option value="">Selecciona el número de personajes</option>
          <option value="1">1 personaje</option>
          <option value="2">2 personajes</option>
          <option value="3">3 personajes</option>
        </select>
      </div>
    </div>
  );
};

export default NumeroPersonajes;
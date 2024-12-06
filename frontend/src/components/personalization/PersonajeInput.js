// PersonajeInput.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './PersonajeInput.css';

const PersonajeInput = ({ index, onPersonajeChange, personajesPredefinidos }) => {
  const [personajeData, setPersonajeData] = useState({
    nombre: '',
    tipo: '', // boy o girl
    personaje_id: ''
  });

  // Cuando cambian los datos, notificamos al padre
  useEffect(() => {
    onPersonajeChange(index, personajeData);
  }, [personajeData]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setPersonajeData(prev => ({
      ...prev,
      [name]: value,
      // Si cambia el tipo, reseteamos el personaje_id
      ...(name === 'tipo' ? { personaje_id: '' } : {})
    }));
  };

  const handlePersonajeSelect = (id) => {
    setPersonajeData(prev => ({
      ...prev,
      personaje_id: id
    }));
  };

  return (
    <div className="personaje-input-container">
      <h3>Personaje {index + 1}</h3>
      
      <div className="form-group">
        <label htmlFor={`nombre-${index}`}>Nombre del personaje:</label>
        <input
          type="text"
          id={`nombre-${index}`}
          name="nombre"
          value={personajeData.nombre}
          onChange={handleChange}
          placeholder="Escribe un nombre"
          required
        />
      </div>

      <div className="form-group">
        <label htmlFor={`tipo-${index}`}>¿Es niño o niña?</label>
        <select
          id={`tipo-${index}`}
          name="tipo"
          value={personajeData.tipo}
          onChange={handleChange}
          required
        >
          <option value="">Selecciona</option>
          <option value="boy">Niño</option>
          <option value="girl">Niña</option>
        </select>
      </div>

      {personajeData.tipo && (
        <div className="personajes-grid">
          {personajesPredefinidos
            .filter(p => p.tipo === personajeData.tipo)
            .map(personaje => (
              <div
                key={personaje.id}
                className={`personaje-option ${personajeData.personaje_id === personaje.id ? 'selected' : ''}`}
                onClick={() => handlePersonajeSelect(personaje.id)}
              >
                <img 
                  src={require(`../../assets/images/characters/${personaje.imagenUrl}`)}
                  alt={personaje.nombre}
                />
               
              </div>
            ))}
        </div>
      )}
    </div>
  );
};

export default PersonajeInput;
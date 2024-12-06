import React, { useState } from 'react';
import NumberOfChildren from './NumberOfChildren';
import ChildDataForm from './ChildDataForm';
import CharacterImages from './CharacterImages';
import SubmitPersonalization from './SubmitPersonalization';
import './PersonalizationForm.css';

function PersonalizationForm() {
  const [childrenData, setChildrenData] = useState([]);

  const handleNumberOfChildrenChange = (number) => {
    setChildrenData(Array(number).fill({ name: '', gender: '', selectedImage: '' }));
  };

  const handleChildDataChange = (index, data) => {
    const newChildrenData = [...childrenData];
    newChildrenData[index] = { ...newChildrenData[index], ...data };
    setChildrenData(newChildrenData);
  };

  const handleSubmit = () => {
    // Procesar los datos de personalización
    console.log(childrenData);
    alert('Personalización enviada con éxito');
  };

  return (
    <div className="personalization-container">
      <div className="image-section">
        <img src="https://via.placeholder.com/600x400" alt="Imagen del personaje" />
      </div>

      <div className="form-section">
        <h2>Personalización de Personajes</h2>
        <NumberOfChildren onChange={handleNumberOfChildrenChange} />

        {childrenData.map((child, index) => (
          <div key={index}>
            <ChildDataForm childIndex={index} onChange={(data) => handleChildDataChange(index, data)} />
            <CharacterImages
              childIndex={index}
              gender={child.gender}
              onChange={(data) => handleChildDataChange(index, data)}
            />
          </div>
        ))}

        {/* Componente de envío y resumen */}
        <SubmitPersonalization onSubmit={handleSubmit} childrenData={childrenData} />
      </div>
    </div>
  );
}

export default PersonalizationForm;

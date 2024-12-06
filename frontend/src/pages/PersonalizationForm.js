import React, { useState } from 'react';
import { useForm, Controller } from 'react-hook-form';
import * as yup from 'yup';
import { yupResolver } from '@hookform/resolvers/yup';
import axios from 'axios';
import './PersonalizationForm.css';
import MonthStorySelector from '../components/personalization/MonthStorySelector';

// Actualizar el esquema de validación para incluir el mes y precio
const schema = yup.object().shape({
  numberOfChildren: yup.number().required('Selecciona el número de niños').min(1).max(4),
  selectedMonth: yup.string().required('Selecciona un mes'),
  price: yup.number().required(),
  storyId: yup.string().required('Error al seleccionar el cuento'),
  children: yup.array().of(
    yup.object().shape({
      name: yup.string().required('El nombre es obligatorio'),
      gender: yup.string().required('Selecciona el género'),
      skinColor: yup.string().required('Selecciona el color de piel'),
      hairColor: yup.string().required('Selecciona el color de cabello'),
      hairStyle: yup.string().required('Selecciona el estilo de cabello'),
    })
  )
});

function PersonalizationForm() {
  const [numberOfChildren, setNumberOfChildren] = useState(1);
  const { control, handleSubmit, watch, setValue, formState: { errors } } = useForm({
    resolver: yupResolver(schema),
    defaultValues: {
      numberOfChildren: 1,
      selectedMonth: '',
      price: 0,
      storyId: '',
      children: Array(4).fill({ name: '', gender: '', skinColor: '', hairColor: '', hairStyle: '' })
    }
  });

  // Manejar cambios en el selector de mes
  const handleMonthSelection = (monthData) => {
    setValue('selectedMonth', monthData.month);
    setValue('price', monthData.price);
    setValue('storyId', monthData.storyId);
  };

  const onSubmit = async (data) => {
    try {
      const token = localStorage.getItem('token');
      
      if (!token) {
        throw new Error('No estás autenticado. Inicia sesión.');
      }

      // Incluir los datos del mes y precio en el envío
      const formData = {
        ...data,
        children: data.children.slice(0, data.numberOfChildren) // Solo enviar los niños seleccionados
      };

      const response = await axios.post('http://localhost:8080/api/personalizar-libro', formData, {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        }
      });

      console.log('Personalización enviada:', response.data);
      alert('Libro personalizado guardado con éxito');
      
    } catch (error) {
      console.error('Error al enviar la personalización:', error);
      alert('Ocurrió un error al enviar la personalización. Inténtalo de nuevo.');
    }
  };

  const selectedChildren = watch('numberOfChildren', numberOfChildren);

  return (
    <div className="personalization-form">
      <h2>Personalización de Personajes</h2>

      <form onSubmit={handleSubmit(onSubmit)}>
        {/* Selector de mes */}
        <div className="form-group month-selector">
          <Controller
            name="selectedMonth"
            control={control}
            render={({ field }) => (
              <MonthStorySelector
                onChange={handleMonthSelection}
                value={field.value}
              />
            )}
          />
          {errors.selectedMonth && <p className="error">{errors.selectedMonth.message}</p>}
        </div>

        {/* Seleccionar el número de niños */}
        <div className="form-group">
          <label>Número de niños:</label>
          <Controller
            name="numberOfChildren"
            control={control}
            render={({ field }) => (
              <select {...field} onChange={(e) => setNumberOfChildren(parseInt(e.target.value))}>
                <option value="1">1 niño/a</option>
                <option value="2">2 niños/as</option>
                <option value="3">3 niños/as</option>
                <option value="4">4 niños/as</option>
              </select>
            )}
          />
          {errors.numberOfChildren && <p className="error">{errors.numberOfChildren.message}</p>}
        </div>

        {/* Bloques de personalización para cada niño */}
        {Array.from({ length: selectedChildren }).map((_, index) => (
          <div key={index} className="child-block">
            <h3>Niño/a {index + 1}</h3>

            {/* ... (resto del código de los campos de personalización) ... */}
            {/* Los campos existentes se mantienen igual */}
          </div>
        ))}

        <button type="submit">Enviar Personalización</button>
      </form>
    </div>
  );
}

export default PersonalizationForm;
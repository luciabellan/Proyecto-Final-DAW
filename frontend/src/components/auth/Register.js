import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Register.css'; // Asegúrate de tener estilos para el formulario

const Register = () => {
  const [nombre, setNombre] = useState('');
  const [apellidos, setApellidos] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [direccion, setDireccion] = useState('');
  const [codigoPostal, setCodigoPostal] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault(); // Evitar el comportamiento predeterminado del formulario

    try {
      // Hacer la solicitud de registro al backend
      await axios.post('http://localhost:8080/api/usuarios', {
        nombre,
        apellidos,
        email,
        password,
        direccion,
        codigoPostal
      });
      // Redirigir al login después de registrar al usuario
      navigate('/login');
    } catch (error) {
      if (error.response && error.response.status === 500) {
        setError('El correo electrónico ya está en uso.'); // Mensaje específico
    } else {
        setError('Hubo un problema con el registro. Intenta de nuevo.');
    }
  }
  };

  return (
    <div className="register-container">
      <h2>Registrarse</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>} {/* Mostrar el error si existe */}
      <form onSubmit={handleRegister}>
        <div>
          <label>Nombre:</label>
          <input
            type="text"
            value={nombre}
            onChange={(e) => setNombre(e.target.value)}
            required
            placeholder="Ingresa tu nombre"
          />
        </div>
        <div>
          <label>Apellidos:</label>
          <input
            type="text"
            value={apellidos}
            onChange={(e) => setApellidos(e.target.value)}
            required
            placeholder="Ingresa tus apellidos"
          />
        </div>
        <div>
          <label>Email:</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            placeholder="Ingresa tu email"
          />
        </div>
        <div>
          <label>Contraseña:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            placeholder="Ingresa una contraseña"
          />
        </div>
        <div>
          <label>Dirección:</label>
          <input
            type="text"
            value={direccion}
            onChange={(e) => setDireccion(e.target.value)}
            required
            placeholder="Ingresa tu dirección"
          />
        </div>
        <div>
          <label>Código Postal:</label>
          <input
            type="text"
            value={codigoPostal}
            onChange={(e) => setCodigoPostal(e.target.value)}
            required
            placeholder="Ingresa tu código postal"
          />
        </div>
        <button type="submit">Registrarse</button>
      </form>
    </div>
  );
};

export default Register;

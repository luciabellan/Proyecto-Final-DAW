import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Login.scss';

const Login = () => {
  const apiUrl = process.env.REACT_APP_API_URL;
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const history = useNavigate();

  const handleLogin = async () => {
    setLoading(true);
    setError('');

    try {
      if (!email || !password) {
        setError('Por favor, introduce email y contraseña.');
        return;
      }

      // 1. Primer paso: Login normal
      const loginResponse = await axios.post(`${apiUrl}/api/login`, { email, password });
      const token = loginResponse.data;
      
      // 2. Segundo paso: Verificar si el usuario existe
      try {
        await axios.get(`${apiUrl}/api/perfil`, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });

        // Si llegamos aquí, el usuario existe y podemos proceder
        localStorage.setItem('token', token);
        history('/profile');
      } catch (userError) {
        // Si hay error 404 o 500, significa que el usuario no existe
        if (userError.response && (userError.response.status === 404 || userError.response.status === 500)) {
          // Limpiamos el token si existe
          localStorage.removeItem('token');
          throw new Error('Este usuario ha sido eliminado y no puede acceder');
        }
        throw userError;
      }

    } catch (error) {
      console.error('Login failed:', error.response ? error.response.data : error.message);
      
      // Manejamos diferentes tipos de errores
      if (error.message.includes('eliminado')) {
        setError('Este usuario ha sido eliminado y no puede acceder');
      } else if (error.response && error.response.status === 401) {
        setError('Email o contraseña incorrectos');
      } else {
        setError('Error al iniciar sesión. Por favor, inténtalo de nuevo.');
      }

      // Nos aseguramos de limpiar cualquier token que pudiera haber quedado
      localStorage.removeItem('token');
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    handleLogin();
  };

  return (
    <div className="login-page">
      <div className="login-image-section">
        {/* La imagen se muestra como background-image en CSS */}
      </div>
      <div className="login-content">
        <div className="login-container">
          <h2>Iniciar sesión</h2>
          <form onSubmit={handleSubmit}>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="Email"
              required
            />
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Contraseña"
              required
            />
            {error && <div className="error-message">{error}</div>}
            <button 
              className='customize-btn' 
              type="submit" 
              disabled={loading}
            >
              {loading ? 'Iniciando sesión...' : 'Login'}
            </button>
          </form>
          <div className="register-link">
            ¿No tienes cuenta? <a href="/register">Regístrate</a>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
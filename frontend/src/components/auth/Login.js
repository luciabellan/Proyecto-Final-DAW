import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Login.scss';



const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
    const history = useNavigate();

  const handleLogin = async () => {
    try {
        if (!email || !password) {
            setError('Please enter both username and password.');
            return;
        }

        const response = await axios.post('http://localhost:8080/api/login', { email, password });
        console.log('Login successful:', response.data);
        localStorage.setItem('token', response.data); 
        history('/profile');
    } catch (error) {
        console.error('Login failed:', error.response ? error.response.data : error.message);
        setError('Invalid username or password.');
    }
};

  const handleSubmit = (event) => {
    event.preventDefault();
    handleLogin(email, password);
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
      <button type="submit">Login</button>
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

import React, { useState } from 'react'; // React y hooks para manejar estado
import axios from 'axios'; // Biblioteca para realizar peticiones HTTP
import { useNavigate } from 'react-router-dom'; // Hook para navegación programada
import './Login.scss';

const Login = () => {

  // URL base de la API obtenida desde variables de entorno
  const apiUrl = process.env.REACT_APP_API_URL;

   // Estados para manejar inputs, errores, y la lógica de carga
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  // Hook para navegar entre rutas
  const history = useNavigate();


  // Función para manejar el proceso de login
  const handleLogin = async () => {
    setLoading(true); // Activamos el estado de carga
    setError(''); // Limpiamos errores previos

    try {
      // Validación simple: verificar que email y contraseña no estén vacíos
      if (!email || !password) {
        setError('Por favor, introduce email y contraseña.');
        return;
      }

      // 1. Enviar solicitud de login al backend
      const loginResponse = await axios.post(`${apiUrl}/api/login`, { email, password });
      const token = loginResponse.data;  // Obtener el token del usuario
      
      // 2. Verificar si el usuario existe usando el token recibido
      try {
        await axios.get(`${apiUrl}/api/perfil`, {
          headers: {
            Authorization: `Bearer ${token}` // Token en el header para autenticación
          }
        });

        // Si no hay errores, almacenamos el token y navegamos al perfil
        localStorage.setItem('token', token); // Guardamos el token localmente
        history('/profile');// Navegamos a la página del perfil
      } catch (userError) {
         // Si el usuario no existe (404) o hay errores en el servidor (500)
        if (userError.response && (userError.response.status === 404 || userError.response.status === 500)) {
          // Eliminamos cualquier token almacenado
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
      setLoading(false);  // Finalizamos el estado de carga
    }
  };

   // Manejador del envío del formulario
  const handleSubmit = (event) => {
    event.preventDefault(); // Prevenimos el comportamiento predeterminado del formulario
    handleLogin(); // Llamamos al proceso de login
  };

  // Renderizado del componente
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
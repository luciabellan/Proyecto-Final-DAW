import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'; //Biblioteca para manejar rutas en React.
import HomePage from './pages/HomePage';  // Tu componente de la página de inicio
import LoginPage from './pages/LoginPage';
import PersonalizationPage from './pages/PersonalizationPage';
import ProfilePage from './pages/ProfilePage';
import RegisterPage from './pages/RegisterPage';
import 'bootstrap/dist/css/bootstrap.min.css'; // Importación de estilos de Bootstrap
import './styles/main.scss'; // Estilos globales personalizados
import EditUser from './components/profile/EditUser'; // Componente para edición de usuarios

function App() {
  return (
    <Router>
      <Routes>
        {/* Ruta para la página de inicio */}
        <Route path="/" element={<HomePage />} />

        {/* Ruta para la página de personalización */}
        <Route path="/personalization" element={<PersonalizationPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/profile" element={<ProfilePage />} />
        <Route path="/edit-user/:id" element={<EditUser />} />
      </Routes>
    </Router>
  );
}



export default App;

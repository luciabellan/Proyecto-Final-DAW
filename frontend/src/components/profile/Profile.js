import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import defaultBookImage from "../../assets/images/default-book.png";
import './Profile.scss';
import personalizarIcon from "../../assets/botones/boton_personalizar.svg";
import historialIcon from "../../assets/botones/boton_historial_pedidos.svg";
import EditForm from "./EditForm";




const Profile = () => {
  const apiUrl = process.env.REACT_APP_API_URL;

  const [librosPersonalizados, setLibrosPersonalizados] = useState([]);
  const [, setLoading] = useState(true); // Añadir estado de loading
  const [userData, setUserData] = useState({}); // Guardará los datos del usuario
  const [isEditing, setIsEditing] = useState(false); // Estado para mostrar el formulario de edición
  //const [formData, setFormData] = useState({}); // Datos del formulario de edición
 
  const navigate = useNavigate();

  const obtenerIdUsuarioActual = () => {
    const userId =userData.id;
    if (!userId) {
      throw new Error('No se encontró el ID del usuario actual');
    }
    return userId;
  };
  
  
  const handleDelete = async () => {
    const confirmDelete = window.confirm('¿Estás seguro de que deseas eliminar tu cuenta? Esta acción no se puede deshacer.');
    if (!confirmDelete) return;
  
    const token = localStorage.getItem("token");
    if (!token) {
      console.error("No se encontró token de autenticación.");
      return;
    }
    
  
    try {

      const userId = obtenerIdUsuarioActual();
      // Eliminar el perfil del usuario actual
      await axios.delete(`${apiUrl}/api/eliminar/${userId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
  
      // Limpiar el token y redirigir al inicio
      localStorage.removeItem("token");
      localStorage.removeItem("userId");
      navigate("/");
  
      alert('Cuenta eliminada');
    } catch (error) {
      console.error("Error al eliminar la cuenta y los recursos asociados", error);
      alert('Hubo un error al eliminar la cuenta. Por favor, inténtalo de nuevo.');
    }
  };
  
  const handleLogout = () => {
    // Eliminar el token del localStorage
    localStorage.removeItem("token");
    // Redirigir a inicio
    navigate("/");
  };

  const handlePersonalizarClick = () => {
    navigate("/personalization", {
      state: {
        userId: userData.id,
        userName: userData.nombre,
      },
    });
  };

  // Función auxiliar para obtener la URL de la imagen
  const getImageUrl = (imageName) => {
    try {
      // Importar la imagen dinámicamente desde assets
      return require(`../../assets/images/${imageName}`);
    } catch {
      // Si hay error, usar la imagen por defecto
      return defaultBookImage;
    }
  };

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const token = localStorage.getItem("token");
        // Hacer solicitud para obtener datos del perfil del usuario
        const userResponse = await axios.get(
          "http://localhost:8080/api/perfil",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setUserData(userResponse.data);
      
        // Hacer solicitud para obtener los libros personalizados del usuario
        const librosResponse = await axios.get(
          "http://localhost:8080/api/libros-personalizados",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        // Asegurarnos de que siempre sea un array
        setLibrosPersonalizados(
          Array.isArray(librosResponse.data) ? librosResponse.data : []
        );

        console.log("Libros recibidos:", librosResponse.data); // Para debug
      } catch (error) {
        console.error(
          "Error al obtener los datos del perfil y los libros",
          error
        );
      } finally {
        setLoading(false); // Finaliza el estado de carga
      }
    };

    fetchProfile();
  }, []);

  const handleSave = (updatedData) => {
    setUserData(updatedData); // Actualiza los datos en el estado del usuario
    setIsEditing(false); // Cierra el formulario
  };

  const handleCancelEdit = () => {
    setIsEditing(false); // Cierra el formulario sin guardar
  };

  return (
    <div className="profile-page">
      <div className="profile-title">
          <h1>PERFIL</h1>
      </div>
      <div className="main-content">
        {/* Panel de información del usuario */}
        <div className="user-info-panel">
          {isEditing ? (
            <EditForm
              userData={userData}
              onSave={handleSave}
              onCancel={handleCancelEdit}
            />
          ) : (
            <>
              <div className="info-item">
                <label>Nombre:</label>
                <p>{userData.nombre}</p>
              </div>
              <div className="info-item">
                <label>Email:</label>
                <p>{userData.email}</p>
              </div>
              <div className="info-item">
                <label>Dirección:</label>
                <p>{userData.direccion}</p>
              </div>
              <div className="info-item">
                <label>Código Postal:</label>
                <p>{userData.codigoPostal}</p>
              </div>
              {/* Botones */}
              <div className="button-group">
                <button className="btn edit-button" onClick={() => setIsEditing(true)}>
                  Editar datos de contacto
                </button>
                <button className="btn delete-button" onClick={handleDelete}>
                  Eliminar cuenta
                </button>
              </div>
              {/* Panel de botones de acción */}
              <div className="actions-panel">
                <div className="action-button" onClick={handlePersonalizarClick}>
                  <div className="button-content">
                    <img src={personalizarIcon} alt="Personalizar" />
                  </div>
                </div>
                <div className="action-button" onClick={() => document.querySelector('.orders-history').scrollIntoView({ behavior: 'smooth' })}>
                  <div className="button-content">
                    <img src={historialIcon} alt="Historial" />
                  </div>
                </div>
                <button onClick={handleLogout} className="btn btn-logout">
                  Cerrar Sesión
                </button>
              </div>
            </>
          )}
        </div>
        {/* Sección de historial de pedidos */}
        <div className="orders-history">
          <h2>HISTORIAL DE PEDIDOS</h2>
          <div className="order-cards">
            {librosPersonalizados.map((libro) => (
              <div key={libro.id} className="order-card">
                <img 
                  src={getImageUrl(libro.cuentoImagenUrl)} 
                  alt={libro.titulo} 
                  className="book-image"
                />
                <div className="book-info">
                  <h3>{libro.titulo}</h3>
                  <div className="characters">
                    {libro.personajes?.map((personaje, index) => (
                      <div key={index} className="character">
                        {personaje.personajeNombre}
                      </div>
                    ))}
                  </div>
                  <div className="date">
                    Creado el: {new Date(libro.fechaCreacion).toLocaleDateString()}
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Profile;
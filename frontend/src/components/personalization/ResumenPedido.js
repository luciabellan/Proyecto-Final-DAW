// ResumenPedido.js
import React from 'react';
import './ResumenPedido.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;


const ResumenPedido = ({ userData, formData, personajesPredefinidos }) => {
    const navigate = useNavigate(); //redirigir después del éxito
    
    if (!userData || !formData || !personajesPredefinidos) {
        return null; // O un loading spinner
    }

    // Cambiamos personajes por personajesCreados
    /*  formData.personajesCreados?.every(
        p => p.nombre && p.tipo && p.personaje_id
    ); */


    const handleConfirm = async () => {
        try {
            const token = localStorage.getItem('token');
            const pedidoData = {
                cuentoId: formData.cuento_id,
                usuarioId: userData.id,
                personajes: formData.personajesCreados.map(personaje => ({
                    personajeId: personaje.personaje_id,
                    nombre: personaje.nombre,
                    tipo: personaje.tipo
                }))
            };

            console.log('Enviando pedido:', pedidoData); // Para debug
            const response = await axios.post(
                 `${apiUrl}/api/libros-personalizados`,
                pedidoData,
                {
                    headers: {
                        'Authorization': `Bearer ${token}`,
                        'Content-Type': 'application/json'
                    }
                }
            );

            if (response.status === 201 || response.status === 200) {
                alert('¡Tu cuento personalizado se ha creado con éxito!');
                navigate('/profile'); // Redirige al perfil
            }
        } catch (error) {
            console.error('Error al confirmar el pedido:', error.response?.data || error);
            alert('Hubo un error al procesar tu pedido. Por favor, inténtalo de nuevo.');
        }
    };

    return (
        <div className="resumen-container">
            <h2>Resumen de tu Cuento Personalizado</h2>

            {/* Datos de envío */}
            <div className="seccion">
                <h3>Datos de envío</h3>
                <div className="datos-envio">
                    <p><strong>Nombre:</strong> {userData.nombre}</p>
                    <p><strong>Email:</strong> {userData.email}</p>
                    <p><strong>Dirección:</strong> {userData.direccion}</p>
                    <p><strong>Código Postal:</strong> {userData.codigoPostal}</p>
                </div>
            </div>

            {/* Personajes configurados */}
            <div className="seccion">
                <h3>Personajes del cuento</h3>
                <div className="personajes-resumen">
                    {formData.personajesCreados?.map((personaje, index) => {
                        const personajePredefinido = personajesPredefinidos.find(
                            p => p.id === parseInt(personaje.personaje_id)
                        );

                        if (!personajePredefinido) return null;

                        return (
                            <div key={index} className="personaje-resumen-card">
                                <h4>Personaje {index + 1}</h4>
                                <div className="personaje-info">
                                    {personajePredefinido.imagenUrl && (
                                        <img 
                                            src={require(`../../assets/images/characters/${personajePredefinido.imagenUrl}`)}
                                            alt={personaje.nombre}
                                            className="personaje-imagen"
                                        />
                                    )}
                                    <div className="personaje-detalles">
                                        <p><strong>{personaje.nombre}</strong> </p>
                                    </div>
                                </div>
                            </div>
                        );
                    })}
                </div>
            </div>

            {/* Botón de confirmación */}
            <div className="acciones">
                <button 
                    className="btn-confirmar"
                    
                    onClick={handleConfirm}
                >
                    Confirmar Pedido
                </button>
            </div>
        </div>
    );
};

export default ResumenPedido;
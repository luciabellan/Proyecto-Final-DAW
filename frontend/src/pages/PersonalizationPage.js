import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import CuentoSelector from "../components/personalization/CuentoSelector";
import NumeroPersonajes from "../components/personalization/NumeroPersonajes";
import PersonajeInput from "../components/personalization/PersonajeInput";
import axios from "axios";
import ResumenPedido from "../components/personalization/ResumenPedido";


const PersonalizationPage = () => {
  const location = useLocation();
  const { userId, userName } = location.state || {};
  const [personajesPredefinidos, setPersonajesPredefinidos] = useState([]);
  const [userData, setUserData] = useState(null);
  const [formData, setFormData] = useState({
    cuento_id: "",
    numeroPersonajes: "",
    personajesCreados: [],
  });

  const handleCuentoChange = (cuentoData) => {
    setFormData((prev) => ({
      ...prev,
      ...cuentoData,
    }));
  };

  const handleNumeroPersonajesChange = (numero) => {
    setFormData((prev) => ({
      ...prev,
      numeroPersonajes: numero,
      personajesCreados: Array(numero).fill({}), // Inicializa array vacío del tamaño seleccionado
    }));
  };

  //Obtener datos de los personajes
  useEffect(() => {
    const fetchPersonajes = async () => {
      try {
        const token = localStorage.getItem("token");
        const response = await axios.get(
          "http://localhost:8080/api/personajes-predefinidos",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setPersonajesPredefinidos(response.data);
      } catch (error) {
        console.error("Error al cargar personajes:", error);
      }
    };

    fetchPersonajes();
  }, []);


  //Obtener datos del usuario
  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.get('http://localhost:8080/api/perfil', {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setUserData(response.data);
      } catch (error) {
        console.error('Error al obtener datos del usuario:', error);
      }
    };

    fetchUserData();
  }, []);

  console.log('Estado actual:', {
    tienePersonajes: formData.personajesCreados?.length > 0,
    personajesCompletos: formData.personajesCreados?.every(p => p.nombre && p.tipo && p.personaje_id),
    personajes: formData.personajesCreados
});

  return (
    <div className="personalization-page">
      <h1>Personalizar Cuento</h1>

      <CuentoSelector formData={formData} onChange={handleCuentoChange} />

      {formData.cuento_id && ( // Solo muestra el selector de número si hay un cuento seleccionado
        <NumeroPersonajes
          numeroPersonajes={formData.numeroPersonajes}
          onNumeroChange={handleNumeroPersonajesChange}
        />
      )}

      {/* Personajes input:*/}
      {formData.numeroPersonajes > 0 && (
        <div className="personajes-container">
          {Array.from({ length: formData.numeroPersonajes }).map((_, index) => (
            <PersonajeInput
              key={index}
              index={index}
              onPersonajeChange={(index, data) => {
                const newPersonajes = [...formData.personajesCreados];
                newPersonajes[index] = data;
                setFormData((prev) => ({
                  ...prev,
                  personajesCreados: newPersonajes,
                }));
              }}
              personajesPredefinidos={personajesPredefinidos}
            />
          ))}
        </div>
      )}



{/* Mostrar resumen cuando todos los personajes estén configurados */}
{formData.personajesCreados?.length > 0 && 
     formData.personajesCreados.every(p => p.nombre && p.tipo && p.personaje_id) && (
      <ResumenPedido
        userData={userData}
        formData={formData}
        personajesPredefinidos={personajesPredefinidos}
      />
    )}



    </div>
  );
};

export default PersonalizationPage;

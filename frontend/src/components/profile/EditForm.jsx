import React, { useState } from "react";
import axios from "axios";

const EditForm = ({ userData, onSave, onCancel }) => {
  const [formData, setFormData] = useState(userData || {});

  const handleFormChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    try {
      const token = localStorage.getItem("token");
      const response = await axios.put(
        `http://localhost:8080/api/${userData.id}`,
        formData,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );
      alert("Datos actualizados correctamente.");
      onSave(response.data); // Notifica al padre que se guardaron los cambios
    } catch (error) {
      console.error("Error al actualizar los datos", error);
      alert("Ocurrió un error al actualizar los datos.");
    }
  };

  return (
    <form onSubmit={handleFormSubmit} className="edit-form">
      <div className="form-group">
        <label>Nombre:</label>
        <input
          type="text"
          name="nombre"
          value={formData.nombre || ""}
          onChange={handleFormChange}
          required
        />
      </div>
      <div className="form-group">
        <label>Email:</label>
        <input
          type="email"
          name="email"
          value={formData.email || ""}
          onChange={handleFormChange}
          required
        />
      </div>
      <div className="form-group">
        <label>Dirección:</label>
        <input
          type="text"
          name="direccion"
          value={formData.direccion || ""}
          onChange={handleFormChange}
        />
      </div>
      <div className="form-group">
        <label>Código Postal:</label>
        <input
          type="text"
          name="codigoPostal"
          value={formData.codigoPostal || ""}
          onChange={handleFormChange}
        />
      </div>
      <div className="button-group">
        <button type="submit" className="btn save-button">
          Guardar
        </button>
        <button
          type="button"
          className="btn cancel-button"
          onClick={onCancel}
        >
          Cancelar
        </button>
      </div>
    </form>
  );
};

export default EditForm;

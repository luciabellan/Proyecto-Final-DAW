import React, { useState, useEffect } from "react";
import { Form, Button, Alert } from "react-bootstrap";
import axios from "axios";

const EditUser = ({ userId }) => {
  const [formData, setFormData] = useState({
    nombre: "",
    email: "",
    direccion: "",
    codigoPostal: "",
  });
  const [message, setMessage] = useState("");

  useEffect(() => {
    // Obtener datos del usuario desde el backend
    axios.get(`/api/usuarios/${userId}`)
      .then((response) => setFormData(response.data))
      .catch((error) => setMessage("Error al cargar los datos del usuario."));
  }, [userId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Enviar los datos actualizados al backend
    axios.put(`/api/usuarios/${userId}`, formData)
      .then((response) => setMessage("Datos actualizados correctamente."))
      .catch((error) => setMessage("Error al actualizar los datos."));
  };

  return (
    <div className="editar-usuario">
      <h3>Editar Perfil</h3>
      {message && <Alert variant="info">{message}</Alert>}
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formNombre">
          <Form.Label>Nombre</Form.Label>
          <Form.Control
            type="text"
            name="nombre"
            value={formData.nombre}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formEmail">
          <Form.Label>Email</Form.Label>
          <Form.Control
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formDireccion">
          <Form.Label>Dirección</Form.Label>
          <Form.Control
            type="text"
            name="direccion"
            value={formData.direccion}
            onChange={handleChange}
          />
        </Form.Group>
        <Form.Group controlId="formCodigoPostal">
          <Form.Label>Código Postal</Form.Label>
          <Form.Control
            type="text"
            name="codigoPostal"
            value={formData.codigoPostal}
            onChange={handleChange}
          />
        </Form.Group>
        <Button variant="primary" type="submit" className="mt-3">
          Guardar Cambios
        </Button>
      </Form>
    </div>
  );
};

export default EditUser;

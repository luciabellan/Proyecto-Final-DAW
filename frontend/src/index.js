import React from "react"; // Biblioteca React para crear componentes
import ReactDOM from "react-dom/client"; // Herramienta para renderizar en el DOM
import "./index.css";
import "./styles.css";
import App from "./App"; // Componente principal de la aplicación

import CustomizationProvider from "./context/CustomizationProvider"; // Contexto para la personalización

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <CustomizationProvider> 
      <App />
    </CustomizationProvider>
  </React.StrictMode>
);

import { useState } from 'react'; //Se utiliza para manejar el estado de personalización.
import { CustomizationContext } from './CustomizationContext'; // Importa el contexto creado anteriormente para ser utilizado como proveedor.


function CustomizationProvider({ children }) {
  const [customizationData, setCustomizationData] = useState({});

  return (
    <CustomizationContext.Provider value={{ customizationData, setCustomizationData }}>
      {children}
    </CustomizationContext.Provider>
  );
}

export default CustomizationProvider;

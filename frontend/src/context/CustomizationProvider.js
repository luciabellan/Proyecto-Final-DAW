import { useState } from 'react';
import { CustomizationContext } from './CustomizationContext';

function CustomizationProvider({ children }) {
  const [customizationData, setCustomizationData] = useState({});

  return (
    <CustomizationContext.Provider value={{ customizationData, setCustomizationData }}>
      {children}
    </CustomizationContext.Provider>
  );
}

export default CustomizationProvider;

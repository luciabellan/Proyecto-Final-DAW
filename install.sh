#!/bin/bash

# Instalar nvm (Node Version Manager)
# curl: Descarga el script de instalación de NVM desde su repositorio oficial en GitHub.
# bash: Ejecuta el script descargado para instalar NVM.
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.5/install.sh | bash

# Cargar nvm en el entorno
# NVM_DIR: Define el directorio donde se instala NVM (por defecto, $HOME/.nvm).
export NVM_DIR="$HOME/.nvm"

# [ -s "$NVM_DIR/nvm.sh" ]: Verifica si el script de inicialización de NVM existe.
# \. "$NVM_DIR/nvm.sh": Carga NVM en el entorno actual para que esté disponible en el script.
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"

# Instalar Node.js 20
nvm install 20
# Configura la versión 20 como la activa para este entorno.
nvm use 20

# Verificar la versión de Node.js
node -v
npm -v
#!/bin/bash

# Instalar nvm (Node Version Manager)
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.5/install.sh | bash

# Cargar nvm en el entorno
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"

# Instalar Node.js 20
nvm install 20
nvm use 20

# Verificar la versión de Node.js y npm
echo "Node.js version: $(node -v)"
echo "npm version: $(npm -v)"

# Navega al directorio del frontend.
cd frontend

#  Instala las dependencias necesarias del proyecto frontend especificadas en el archivo package.json.
npm install

# Compila el proyecto frontend en una versión optimizada para producción. Los archivos generados se colocan en la carpeta build.
npm run build 

# Elimina los archivos existentes en el directorio static del backend, asegurando que no queden residuos de compilaciones anteriores.
rm -rfv ../backend/src/main/resources/static/*

# Mueve los archivos generados en el build del frontend al directorio static del backend. 
mv -fv build/* ../backend/src/main/resources/static

#  Navega al directorio del backend.
cd ../backend
# Limpia la compilación anterior y crea un nuevo archivo .jar ejecutable del backend.
mvn clean package



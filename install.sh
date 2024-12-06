#!/bin/bash

# Instalar nvm (Node Version Manager)
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.5/install.sh | bash

# Cargar nvm en el entorno
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"

# Instalar Node.js 20
nvm install 20
nvm use 20

# Verificar la versión de Node.js
node -v
npm -v

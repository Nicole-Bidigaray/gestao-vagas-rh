#!/bin/sh
set -e

echo "🚀 Iniciando aplicação em modo desenvolvimento..."

# Garante que a pasta src exista antes de iniciar o watcher
if [ ! -d "src" ]; then
  echo "❌ Diretório src não encontrado."
  exit 1
fi

# Entr monitora alterações e reinicia a aplicação
exec sh -c "find src -type f | entr -n -r mvn spring-boot:run -Dspring-boot.run.fork=false"

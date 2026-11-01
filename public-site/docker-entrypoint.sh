#!/bin/sh
set -e

# umask 0 para que el socket de php-fpm sea accesible por nginx
umask 0
mkdir -p /run

# PHP-FPM como daemon
php-fpm83 -D

# nginx en foreground (mantén el contenedor vivo)
exec nginx -g "daemon off;"

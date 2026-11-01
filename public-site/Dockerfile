FROM nginx:stable-alpine

# PHP + FPM para el formulario de contacto (contact.php)
RUN apk add --no-cache php83 php83-fpm php83-mbstring php83-opcache

# Remove the default welcome page
RUN rm -rf /usr/share/nginx/html

# Copy the static site
COPY . /usr/share/nginx/html

# Custom config: gzip, cache headers, index, PHP
COPY nginx/default.conf /etc/nginx/conf.d/default.conf

# PHP-FPM pool: usuario nginx + socket compartido con nginx
RUN { \
      echo '[www]'; \
      echo 'user = nginx'; \
      echo 'group = nginx'; \
      echo 'listen = /run/php-fpm.sock'; \
      echo 'listen.owner = root'; \
      echo 'listen.group = nginx'; \
      echo 'listen.allowed_clients = *'; \
    } > /etc/php83/php-fpm.d/zz-socket.conf

# Permite que php-fpm escriba el log de envíos fallidos
RUN chown -R nginx:nginx /usr/share/nginx/html/logs

# Entrypoint: arranca php-fpm (daemon) + nginx (foreground)
COPY docker-entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

EXPOSE 80

ENTRYPOINT ["/entrypoint.sh"]

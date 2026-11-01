<?php
/**
 * Formulario "Contactanos" — Polyquímica S.R.L.
 *
 * Recibe el POST del formulario (nombre, email, asunto, mensaje y
 * opcionalmente teléfono) y envía un correo a informes@polyquimica.com
 * usando la función mail() de PHP. Compatible con hosting compartido
 * (cPanel, Ferozo, etc.).
 *
 * Respuesta: siempre redirige a contact.html?sent=success|error
 */

declare(strict_types=1);

// ------------------------------------------------------------------
// Configuración
// ------------------------------------------------------------------
const RECEIVER_EMAIL = 'informes@polyquimica.com';
// From con dominio propio para reducir el riesgo de rechazo/SPF.
// El remitente real del mensaje se pasa en Reply-To.
const FROM_NAME  = 'Polyquímica S.R.L - Sitio Web';
const FROM_EMAIL = 'informes@polyquimica.com';

const LOG_DIR  = __DIR__ . '/logs';
const LOG_FILE = LOG_DIR . '/contact.log';

// ------------------------------------------------------------------
// Helpers
// ------------------------------------------------------------------

/** Limpia un valor de un solo campo: sin saltos de línea (anti header-injection). */
function clean_field($value): string
{
    $value = trim((string) $value);
    $value = preg_replace('/[\r\n\t]+/', ' ', $value);
    return trim((string) $value);
}

/** Limpia un campo multi-línea conservando los saltos de línea. */
function clean_text($value): string
{
    $value = trim((string) $value);
    $value = str_replace(["\r\n", "\r"], "\n", $value);
    return trim($value);
}

/** Redirige de vuelta al formulario con el estado de la operación. */
function redirect_with_status(string $status): void
{
    header('Location: contact.html?sent=' . $status);
    exit;
}

// ------------------------------------------------------------------
// Lógica principal
// ------------------------------------------------------------------

// El formulario solo acepta POST.
if (($_SERVER['REQUEST_METHOD'] ?? 'GET') !== 'POST') {
    redirect_with_status('error');
}

// Honeypot anti-spam: los bots suelen llenar todos los campos.
// Si este campo oculto tiene contenido, descartamos en silencio.
$honeypot = clean_field($_POST['empresa'] ?? '');
if ($honeypot !== '') {
    redirect_with_status('success');
}

$nombre   = clean_field($_POST['nombre'] ?? '');
$email    = filter_var($_POST['email'] ?? '', FILTER_VALIDATE_EMAIL);
$asunto   = clean_field($_POST['asunto'] ?? '');
$telefono = clean_field($_POST['telefono'] ?? '');
$mensaje  = clean_text($_POST['mensaje'] ?? '');

// Validación básica.
if ($nombre === '' || !$email || $mensaje === '') {
    redirect_with_status('error');
}

// URL de origen (para el pie del correo).
$https  = !empty($_SERVER['HTTPS']) && $_SERVER['HTTPS'] !== 'off';
$host   = $_SERVER['HTTP_HOST'] ?? 'polyquimica.com';
$origin = ($https ? 'https://' : 'http://') . $host;

$subject = 'Nuevo contacto desde el sitio web';
if ($asunto !== '') {
    $subject .= ' - ' . $asunto;
}

$body  = 'Nuevo contacto recibido desde el sitio web.' . PHP_EOL . PHP_EOL;
$body .= 'Nombre:    ' . $nombre . PHP_EOL;
$body .= 'Email:     ' . $email . PHP_EOL;
if ($asunto !== '') {
    $body .= 'Asunto:    ' . $asunto . PHP_EOL;
}
if ($telefono !== '') {
    $body .= 'Teléfono:  ' . $telefono . PHP_EOL;
}
$body .= 'Fecha:     ' . date('d/m/Y H:i') . PHP_EOL . PHP_EOL;
$body .= 'Mensaje:' . PHP_EOL;
$body .= $mensaje . PHP_EOL . PHP_EOL;
$body .= '---' . PHP_EOL;
$body .= 'Enviado desde: ' . $origin;

$headers  = 'MIME-Version: 1.0' . PHP_EOL;
$headers .= 'Content-Type: text/plain; charset=UTF-8' . PHP_EOL;
$headers .= 'From: ' . FROM_NAME . ' <' . FROM_EMAIL . '>' . PHP_EOL;
$headers .= 'Reply-To: ' . $email . PHP_EOL;

$ok = @mail(RECEIVER_EMAIL, $subject, $body, $headers);

if (!$ok) {
    // Guardamos el mensaje fallido en un log para poder revisarlo.
    @mkdir(LOG_DIR, 0775, true);
    $log  = '[' . date('Y-m-d H:i:s') . '] FALLÓ el envío' . PHP_EOL;
    $log .= 'To: ' . RECEIVER_EMAIL . PHP_EOL;
    $log .= 'Subject: ' . $subject . PHP_EOL . PHP_EOL;
    $log .= $body . PHP_EOL . PHP_EOL;
    @file_put_contents(LOG_FILE, $log, FILE_APPEND);
}

redirect_with_status($ok ? 'success' : 'error');

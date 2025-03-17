## BLOG APP

## REQUISITOS
- **JAVA JDK 17**
- **SQL SERVER**
## INDICACIONES 
En caso de que el cliente frontend se ejecute en otro puerto, realizar el cambio por el nuevo puerto en el archivo **CorsConfig.java**
## CONFIGURACIÓN DE LA BASE DE DATOS
1. Crear la base de datos "**blogdb**"
2. En el archivo **application.properties**
- Cambiar la cadena de conexión/url
- Cambiar el usuario y contraseña de acuerdo con su base de datos
3. Ejecutar el archivo **script.sql** que contiene los insert de prueba

## POSTMAN
- Se adjunta la colección de pruebas **blog-app.postman_collection.json**

## OBSERVACIONES
1. Requisitos incompletos:
- refresh token
- migraciones
- pruebas unitarias
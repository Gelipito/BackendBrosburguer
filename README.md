Configuración de la base de datos e inicio de Bros Burguer

##1. Crear un proyecto en Supabase

Antes de ejecutar el proyecto, necesitas crear tu propia base de datos en Supabase.

Accede a Supabase.

Crea una cuenta o inicia sesión.

Haz clic en New Project.

Elige un nombre para el proyecto, por ejemplo, brosburguer.

Crea una contraseña para la base de datos y guárdala en un lugar seguro.

Espera a que Supabase termine de crear el proyecto.

Importante: Puedes utilizar el nombre de proyecto que prefieras y crear tu propia contraseña. No necesitas utilizar los mismos datos que yo.

##2. Obtener los datos de conexión y configurar start-backend.sh

Una vez creado el proyecto, necesitas obtener los datos que permitirán a Spring Boot conectarse a tu base de datos.

2.1. Obtener los datos de conexión

Abre tu proyecto en Supabase.

Haz clic en Connect.

Busca las opciones de conexión y selecciona Session pooler.

Copia los datos de conexión que aparecen para esta opción.

Supabase te proporcionará información como el host, el puerto, el nombre de la base de datos y el usuario. La contraseña será la que configuraste al crear el proyecto.

2.2. Reemplazar los datos en start-backend.sh

Descarga o clona el repositorio de Bros Burguer y abre la carpeta del backend.

Busca el archivo start-backend.sh y ábrelo con tu editor de código.

Dentro del archivo encontrarás las variables de entorno que utiliza Spring Boot para conectarse a PostgreSQL. Debes reemplazar los valores de ejemplo por los datos de conexión de tu propio proyecto de Supabase.

Cada dato corresponde a una variable diferente:

Dato de Supabase

Variable que debes configurar

Host + puerto + nombre de la base de datos

SUPABASE_DB_URL

Usuario de la base de datos

SUPABASE_DB_USERNAME

Contraseña configurada al crear el proyecto

SUPABASE_DB_PASSWORD

Por ejemplo, las variables tendrán una estructura similar a esta:

export SUPABASE_DB_URL='jdbc:postgresql://TU_HOST:TU_PUERTO/TU_BASE_DE_DATOS?sslmode=require'
export SUPABASE_DB_USERNAME='TU_USUARIO'
export SUPABASE_DB_PASSWORD='TU_CONTRASEÑA'

No copies literalmente los valores del ejemplo. Debes sustituir TU_HOST, TU_PUERTO, TU_BASE_DE_DATOS, TU_USUARIO y TU_CONTRASEÑA por los datos reales que te proporciona Supabase.

Ten en cuenta lo siguiente:

SUPABASE_DB_URL: contiene el host, el puerto y el nombre de la base de datos. Conserva el prefijo jdbc:postgresql:// y el parámetro ?sslmode=require.

SUPABASE_DB_USERNAME: contiene el usuario que aparece en los datos de conexión de Supabase.

SUPABASE_DB_PASSWORD: contiene la contraseña que configuraste al crear el proyecto de Supabase.

Guarda los cambios en start-backend.sh antes de continuar.

Importante: No publiques tus contraseñas en GitHub ni compartas el archivo con credenciales reales. Para un proyecto público, es preferible introducir la contraseña cuando se ejecute el script o utilizar variables de entorno configuradas localmente.





##3 Cómo iniciar el backend de Bros Burguer

### 1. Abre Git Bash

Abre Git Bash en Windows o si estas en springboot le das terminal-new terminal Y abres  gitbash.

<img width="851" height="565" alt="image" src="https://github.com/user-attachments/assets/4124b460-f388-4a1f-a2bb-8af56cec78bd" />

<img width="1332" height="837" alt="image" src="https://github.com/user-attachments/assets/0a5263f6-2d74-4a7a-b027-33749cfebf30" />




### 2. Entra a la carpeta del backend



```bash
cd ~/OneDrive/Documentos/FINAL_BROSBURGUER_LOCAL/backend_bros_burguer
```

Presiona **Enter**.

### 3. Ejecuta el script

Escribe el siguiente comando:

```bash
bash start-backend.sh
```

Presiona **Enter** y espera a que termine de iniciar.

### 4. Verifica que el backend esté funcionando

Si todo sale bien, verás mensajes similares a estos:

```text
Tomcat started on port 8080 (http)
Started DemoApplication
```

Esto significa que **Spring Boot está funcionando y se ha conectado a la base de datos de Supabase**.

### 5. Inicia el frontend

Deja abierta la primera terminal de Git Bash. Abre una segunda terminal, entra a la carpeta del frontend y ejecuta:

```bash
npm run dev
```

Así tendrás el backend de Java y el frontend de React ejecutándose al mismo tiempo.

**Importante:** no cierres la terminal del backend mientras estés utilizando la aplicación. Además, no subas las contraseñas de Supabase a GitHub.

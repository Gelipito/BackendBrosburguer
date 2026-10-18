## Tabla de Contenido / Introducción

| # | Sección | Descripción / Alcance |
| :-: | :--- | :--- |
| **1** | Configuración de la base de datos e inicio de Bros Burguer | Guía de conexión e inicio del sistema. |
| **2** | Cómo crear un admin | Procedimiento para registrar o asignar privilegios de administrador. |
| **3** | Modelo Entidad-Relación | Estructura conceptual y relacional de las tablas. |
| **4** | Scripts SQL (`schema.sql` y `seed.sql`) | Código para la creación de tablas y datos iniciales. |

<h1 style="color: red;">#1 Configuración de la base de datos e inicio de Bros Burguer</h1>


# 1. Crear un proyecto en Supabase

Antes de ejecutar el proyecto, necesitas crear tu propia base de datos en Supabase.

Accede a Supabase.

Crea una cuenta o inicia sesión.

Haz clic en New Project.

Elige un nombre para el proyecto, por ejemplo, brosburguer.

Crea una contraseña para la base de datos y guárdala en un lugar seguro.

Espera a que Supabase termine de crear el proyecto.

Importante: Puedes utilizar el nombre de proyecto que prefieras y crear tu propia contraseña. No necesitas utilizar los mismos datos que yo.

# 2. Obtener los datos de conexión y configurar start-backend.sh

Una vez creado el proyecto, necesitas obtener los datos que permitirán a Spring Boot conectarse a tu base de datos.

### 2.1. Obtener los datos de conexión

Abre tu proyecto en Supabase.

Haz clic en Connect.

Busca las opciones de conexión y selecciona Session pooler.

Copia los datos de conexión que aparecen para esta opción.

Supabase te proporcionará información como el host, el puerto, el nombre de la base de datos y el usuario. La contraseña será la que configuraste al crear el proyecto.

### 2.2. Reemplazar los datos en start-backend.sh

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

```bash
export SUPABASE_DB_URL='jdbc:postgresql://TU_HOST:TU_PUERTO/TU_BASE_DE_DATOS?sslmode=require'
export SUPABASE_DB_USERNAME='TU_USUARIO'
export SUPABASE_DB_PASSWORD='TU_CONTRASEÑA'

Ten en cuenta lo siguiente:

SUPABASE_DB_URL: contiene el host, el puerto y el nombre de la base de datos. Conserva el prefijo jdbc:postgresql:// y el parámetro ?sslmode=require.

SUPABASE_DB_USERNAME: contiene el usuario que aparece en los datos de conexión de Supabase.

SUPABASE_DB_PASSWORD: contiene la contraseña que configuraste al crear el proyecto de Supabase.

Guarda los cambios en start-backend.sh antes de continuar.

Importante: No publiques tus contraseñas en GitHub ni compartas el archivo con credenciales reales. Para un proyecto público, es preferible introducir la contraseña cuando se ejecute el script o utilizar variables de entorno configuradas localmente.





# 3 Cómo iniciar el backend de Bros Burguer

### 1. Abre Git Bash

Abre Git Bash en Windows o si estas en springboot le das terminal-new terminal y abres  gitbash.

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



<h1 style="color: red;">#2 Como crear un admin</h1>

Para poder iniciar sesion en nuestra aplicacion necesitas crear un admin , lo cual es un proceso en donde el archivo "ComoCrearUnAdmin" lo tiene en detalle.




<h1 style="color: red;">#3 Modelo entidad relacion</h1>

<img width="900" height="757" alt="image" src="https://github.com/user-attachments/assets/6b0d5563-1655-4c8c-a464-d739b0a5a471" />


<h1 style="color: red;">#4 Script SQL de creación y población de tablas (schema.sql y seed.sql)</h1>

#Creacion de tablas:

CREATE TABLE public.ingredients (
  expiration_date date NOT NULL,
  purchase_price real NOT NULL,
  stock real NOT NULL,
  id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  name character varying NOT NULL UNIQUE,
  CONSTRAINT ingredients_pkey PRIMARY KEY (id)
);
CREATE TABLE public.product_ingredient (
  required_quantity real NOT NULL,
  id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  ingredient_id bigint,
  product_id bigint,
  CONSTRAINT product_ingredient_pkey PRIMARY KEY (id),
  CONSTRAINT fknq2ep6l5t0j3i50grpvp6w4u5 FOREIGN KEY (ingredient_id) REFERENCES public.ingredients(id),
  CONSTRAINT fkjo7tovprand27eoge6byibep7 FOREIGN KEY (product_id) REFERENCES public.products(id)
);
CREATE TABLE public.products (
  selling_price real NOT NULL,
  id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  name character varying NOT NULL,
  CONSTRAINT products_pkey PRIMARY KEY (id)
);
CREATE TABLE public.users (
  id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  password character varying,
  role character varying,
  username character varying,
  CONSTRAINT users_pkey PRIMARY KEY (id)
);
CREATE TABLE public.sales (
  date date NOT NULL,
  total real,
  id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  cc_cliente character varying NOT NULL,
  CONSTRAINT sales_pkey PRIMARY KEY (id)
);
CREATE TABLE public.sale_item (
  quantity integer NOT NULL,
  unit_price real NOT NULL,
  id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  product_id bigint,
  sale_id bigint,
  CONSTRAINT sale_item_pkey PRIMARY KEY (id),
  CONSTRAINT fkdjr346l9a8kku0uao1jorl1nj FOREIGN KEY (sale_id) REFERENCES public.sales(id),
  CONSTRAINT fkguhoj8vdb8hgj02orwwu4jv20 FOREIGN KEY (product_id) REFERENCES public.products(id)
);
CREATE TABLE public.audit_logs (
  id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  table_name character varying NOT NULL,
  record_id bigint NOT NULL,
  action character varying NOT NULL,
  user_id bigint NOT NULL,
  details text,
  created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT audit_logs_pkey PRIMARY KEY (id),
  CONSTRAINT fk_audit_logs_users FOREIGN KEY (user_id) REFERENCES public.users(id)
);


#Registros

##Tabla ingredients

INSERT INTO public.ingredients (expiration_date, purchase_price, stock, id, name) VALUES ('2027-03-15', 1200, 248, 1, 'Pan brioche'), ('2026-11-15', 8500, 76, 2, 'Carne de res'), ('2026-12-20', 4200, 58, 3, 'Queso cheddar'), ('2026-10-25', 1800, 25, 4, 'Lechuga'), ('2026-10-22', 2200, 30, 5, 'Tomate'), ('2026-11-10', 1600, 20, 6, 'Cebolla'), ('2026-12-10', 6000, 31, 7, 'Tocineta'), ('2026-12-01', 3500, 15, 8, 'Salsa de la casa'), ('2027-05-15', 5000, 100, 9, 'Papas congeladas'), ('2026-11-20', 7000, 45, 10, 'Pollo'), ('2027-02-15', 900, 100, 11, 'Pan de perro'), ('2026-12-15', 2500, 50, 12, 'Salchicha'), ('2027-01-15', 3000, 20, 13, 'Pepinillos'), ('2026-12-20', 4500, 40, 14, 'Queso mozzarella'), ('2026-11-25', 5500, 15, 15, 'Champiñones'), ('2026-11-26', 3000, 100, 16, 'Aceite para fritar');


##Tabla products

INSERT INTO public.products (selling_price, id, name) VALUES (16000, 1, 'Bros Clasica'), (22000, 2, 'Bros Doble'), (20000, 3, 'Bros Bacon'), (21000, 4, 'Bros BBQ'), (18000, 5, 'Bros Pollo'), (25000, 6, 'Bros Especial'), (23000, 7, 'Bros Champinones'), (12000, 8, 'Perro Caliente'), (7000, 9, 'Papas Fritas'), (22000, 10, 'Bros Mozzarella');



##Tabla users

INSERT INTO public.users (id, password, role, username) VALUES (1, '$2a$10$O61XP.Te8ktTtGu2AT.80OXIfveg4927EISYtvoUZ9AXGDtgWz5ey', 'ADMIN', 'admin');  

Nota= la contraseña sin encryptar es 1234





##Tabla sales

INSERT INTO public.sales (date, total, id, cc_cliente) VALUES ('2026-10-01', 23000, 1, 'TEST-CLIENT-001'), ('2026-10-01', 22000, 2, 'TEST-CLIENT-002'), ('2026-10-02', 27000, 3, 'TEST-CLIENT-003'), ('2026-10-02', 21000, 4, 'TEST-CLIENT-004'), ('2026-10-03', 25000, 5, 'TEST-CLIENT-005'), ('2026-10-03', 32000, 6, 'TEST-CLIENT-006'), ('2026-10-04', 30000, 7, 'TEST-CLIENT-007'), ('2026-10-04', 12000, 8, 'TEST-CLIENT-008'), ('2026-10-05', 29000, 9, 'TEST-CLIENT-009'), ('2026-10-05', 14000, 10, 'TEST-CLIENT-010'), ('2026-10-06', 29000, 11, 'TEST-CLIENT-011'), ('2026-10-06', 16000, 12, 'TEST-CLIENT-012'), ('2026-10-07', 27000, 13, 'TEST-CLIENT-013'), ('2026-10-07', 12000, 14, 'TEST-CLIENT-014'), ('2026-10-08', 32000, 15, 'TEST-CLIENT-015'), ('2026-10-08', 18000, 16, 'TEST-CLIENT-016'), ('2026-10-09', 28000, 17, 'TEST-CLIENT-017'), ('2026-10-09', 23000, 18, 'TEST-CLIENT-018'), ('2026-10-10', 29000, 19, 'TEST-CLIENT-019'), ('2026-10-10', 32000, 20, 'TEST-CLIENT-020'), ('2026-10-11', 29000, 21, 'TEST-CLIENT-021'), ('2026-10-11', 24000, 22, 'TEST-CLIENT-022'), ('2026-10-12', 27000, 23, 'TEST-CLIENT-023'), ('2026-10-12', 18000, 24, 'TEST-CLIENT-024'), ('2026-10-13', 32000, 25, 'TEST-CLIENT-025'), ('2026-10-13', 21000, 26, 'TEST-CLIENT-026'), ('2026-10-13', 16000, 27, 'TEST-CLIENT-027'), ('2026-10-13', 23000, 28, 'TEST-CLIENT-028'), ('2026-10-13', 29000, 29, 'TEST-CLIENT-029'), ('2026-10-13', 44000, 30, 'TEST-CLIENT-030'), ('2026-09-12', 104000, 31, 'TEST-CLIENT-031'), ('2026-09-29', 12000, 32, 'TEST-CLIENT-032'), ('2026-01-16', 42000, 33, 'TEST-CLIENT-033'), ('2026-02-02', 74000, 34, 'TEST-CLIENT-034'), ('2026-02-19', 59000, 35, 'TEST-CLIENT-035'), ('2026-03-08', 25000, 36, 'TEST-CLIENT-036'), ('2026-03-25', 45000, 37, 'TEST-CLIENT-037'), ('2026-04-11', 87000, 38, 'TEST-CLIENT-038'), ('2026-04-28', 113000, 39, 'TEST-CLIENT-039'), ('2026-05-15', 21000, 40, 'TEST-CLIENT-040'), ('2026-06-01', 37000, 41, 'TEST-CLIENT-041'), ('2026-06-18', 70000, 42, 'TEST-CLIENT-042'), ('2026-07-05', 79000, 43, 'TEST-CLIENT-043'), ('2026-07-22', 44000, 44, 'TEST-CLIENT-044'), ('2026-08-08', 29000, 45, 'TEST-CLIENT-045'), ('2026-08-25', 54000, 46, 'TEST-CLIENT-046'), ('2026-09-11', 81000, 47, 'TEST-CLIENT-047'), ('2026-09-28', 22000, 48, 'TEST-CLIENT-048'), ('2026-01-15', 68000, 49, 'TEST-CLIENT-049'), ('2026-02-01', 66000, 50, 'TEST-CLIENT-050'), ('2026-02-18', 104000, 51, 'TEST-CLIENT-051'), ('2026-03-07', 12000, 52, 'TEST-CLIENT-052'), ('2026-03-24', 42000, 53, 'TEST-CLIENT-053'), ('2026-04-10', 74000, 54, 'TEST-CLIENT-054'), ('2026-04-27', 59000, 55, 'TEST-CLIENT-055'), ('2026-05-14', 25000, 56, 'TEST-CLIENT-056'), ('2026-05-31', 45000, 57, 'TEST-CLIENT-057'), ('2026-06-17', 87000, 58, 'TEST-CLIENT-058'), ('2026-07-04', 113000, 59, 'TEST-CLIENT-059'), ('2026-07-21', 21000, 60, 'TEST-CLIENT-060'), ('2026-08-07', 37000, 61, 'TEST-CLIENT-061'), ('2026-08-24', 70000, 62, 'TEST-CLIENT-062'), ('2026-09-10', 79000, 63, 'TEST-CLIENT-063'), ('2026-09-27', 44000, 64, 'TEST-CLIENT-064'), ('2026-01-14', 29000, 65, 'TEST-CLIENT-065'), ('2026-01-31', 54000, 66, 'TEST-CLIENT-066'), ('2026-02-17', 81000, 67, 'TEST-CLIENT-067'), ('2026-03-06', 22000, 68, 'TEST-CLIENT-068'), ('2026-03-23', 68000, 69, 'TEST-CLIENT-069'), ('2026-04-09', 66000, 70, 'TEST-CLIENT-070'), ('2026-04-26', 104000, 71, 'TEST-CLIENT-071'), ('2026-05-13', 12000, 72, 'TEST-CLIENT-072'), ('2026-05-30', 42000, 73, 'TEST-CLIENT-073'), ('2026-06-16', 74000, 74, 'TEST-CLIENT-074'), ('2026-07-03', 59000, 75, 'TEST-CLIENT-075'), ('2026-07-20', 25000, 76, 'TEST-CLIENT-076'), ('2026-08-06', 45000, 77, 'TEST-CLIENT-077'), ('2026-08-23', 87000, 78, 'TEST-CLIENT-078'), ('2026-09-09', 113000, 79, 'TEST-CLIENT-079'), ('2026-09-26', 21000, 80, 'TEST-CLIENT-080'), ('2026-01-13', 37000, 81, 'TEST-CLIENT-081'), ('2026-01-30', 70000, 82, 'TEST-CLIENT-082'), ('2026-02-16', 79000, 83, 'TEST-CLIENT-083'), ('2026-03-05', 44000, 84, 'TEST-CLIENT-084'), ('2026-03-22', 29000, 85, 'TEST-CLIENT-085'), ('2026-04-08', 54000, 86, 'TEST-CLIENT-086'), ('2026-04-25', 81000, 87, 'TEST-CLIENT-087'), ('2026-05-12', 22000, 88, 'TEST-CLIENT-088'), ('2026-05-29', 68000, 89, 'TEST-CLIENT-089'), ('2026-06-15', 66000, 90, 'TEST-CLIENT-090'), ('2026-07-02', 104000, 91, 'TEST-CLIENT-091'), ('2026-07-19', 12000, 92, 'TEST-CLIENT-092'), ('2026-08-05', 42000, 93, 'TEST-CLIENT-093'), ('2026-08-22', 74000, 94, 'TEST-CLIENT-094'), ('2026-09-08', 59000, 95, 'TEST-CLIENT-095'), ('2026-09-25', 25000, 96, 'TEST-CLIENT-096'), ('2026-01-12', 45000, 97, 'TEST-CLIENT-097'), ('2026-01-29', 87000, 98, 'TEST-CLIENT-098'), ('2026-02-15', 113000, 99, 'TEST-CLIENT-099'), ('2026-03-04', 21000, 100, 'TEST-CLIENT-100');



##Table sale_item

INSERT INTO public.sale_item (quantity, unit_price, id, product_id, sale_id) VALUES (1, 16000, 1, 1, 27), (2, 16000, 2, 1, 20), (1, 16000, 3, 1, 12), (1, 16000, 4, 1, 1), (2, 22000, 5, 2, 30), (1, 22000, 6, 2, 21), (1, 22000, 7, 2, 11), (1, 22000, 8, 2, 2), (1, 20000, 9, 3, 23), (1, 20000, 10, 3, 13), (1, 20000, 11, 3, 3), (1, 21000, 12, 4, 26), (1, 21000, 13, 4, 17), (1, 21000, 14, 4, 4), (1, 18000, 15, 5, 24), (1, 18000, 16, 5, 16), (1, 18000, 17, 5, 5), (1, 25000, 18, 6, 25), (1, 25000, 19, 6, 15), (1, 25000, 20, 6, 6), (1, 23000, 21, 7, 28), (1, 23000, 22, 7, 18), (1, 23000, 23, 7, 7), (2, 12000, 24, 8, 22), (1, 12000, 25, 8, 14), (1, 12000, 26, 8, 8), (1, 7000, 27, 9, 29), (1, 7000, 28, 9, 25), (1, 7000, 29, 9, 23), (1, 7000, 30, 9, 21), (1, 7000, 31, 9, 19), (1, 7000, 32, 9, 17), (1, 7000, 33, 9, 15), (1, 7000, 34, 9, 13), (1, 7000, 35, 9, 11), (2, 7000, 36, 9, 10), (1, 7000, 37, 9, 9), (1, 7000, 38, 9, 7), (1, 7000, 39, 9, 6), (1, 7000, 40, 9, 5), (1, 7000, 41, 9, 3), (1, 7000, 42, 9, 1), (1, 22000, 43, 10, 29), (1, 22000, 44, 10, 19), (1, 22000, 45, 10, 9), (1, 16000, 46, 1, 31), (1, 21000, 47, 4, 31), (1, 23000, 48, 7, 31), (2, 22000, 49, 10, 31), (1, 12000, 50, 8, 32), (1, 18000, 51, 5, 33), (2, 12000, 52, 8, 33), (2, 22000, 53, 2, 34), (1, 18000, 54, 5, 34), (1, 12000, 55, 8, 34), (1, 7000, 56, 9, 35), (1, 22000, 57, 2, 35), (1, 18000, 58, 5, 35), (1, 12000, 59, 8, 35), (1, 25000, 60, 6, 36), (1, 20000, 61, 3, 37), (1, 25000, 62, 6, 37), (1, 22000, 63, 10, 38), (2, 20000, 64, 3, 38), (1, 25000, 65, 6, 38), (2, 23000, 66, 7, 39), (1, 22000, 67, 10, 39), (1, 20000, 68, 3, 39), (1, 25000, 69, 6, 39), (1, 21000, 70, 4, 40), (1, 16000, 71, 1, 41), (1, 21000, 72, 4, 41), (1, 12000, 73, 8, 42), (1, 16000, 74, 1, 42), (2, 21000, 75, 4, 42), (1, 18000, 76, 5, 43), (2, 12000, 77, 8, 43), (1, 16000, 78, 1, 43), (1, 21000, 79, 4, 43), (2, 22000, 80, 2, 44), (1, 7000, 81, 9, 45), (1, 22000, 82, 2, 45), (1, 25000, 83, 6, 46), (1, 7000, 84, 9, 46), (1, 22000, 85, 2, 46), (1, 20000, 86, 3, 47), (1, 25000, 87, 6, 47), (2, 7000, 88, 9, 47), (1, 22000, 89, 2, 47), (1, 22000, 90, 10, 48), (2, 23000, 91, 7, 49), (1, 22000, 92, 10, 49), (1, 21000, 93, 4, 50), (1, 23000, 94, 7, 50), (1, 22000, 95, 10, 50), (1, 16000, 96, 1, 51), (1, 21000, 97, 4, 51), (1, 23000, 98, 7, 51), (2, 22000, 99, 10, 51), (1, 12000, 100, 8, 52);


##Table audit_logs

INSERT INTO public.audit_logs (id, table_name, record_id, action, user_id, details, created_at) VALUES (1, 'ingredients', 2, 'INSERT', 1, 'Initial test inventory loaded', '2026-10-14 04:17:12.473279+00'), (2, 'products', 1, 'INSERT', 1, 'Initial menu loaded', '2026-10-14 04:17:12.473279+00'), (3, 'sales', 1, 'INSERT', 1, 'Sample transaction created', '2026-10-14 04:17:12.473279+00'), (4, 'product_ingredient', 1, 'INSERT', 1, 'Product recipe relationship created', '2026-10-14 04:17:12.473279+00'), (5, 'sale_item', 1, 'INSERT', 1, 'Sample sale detail created', '2026-10-14 04:17:12.473279+00');


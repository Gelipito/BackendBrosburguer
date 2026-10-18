# Cómo crear un nuevo administrador — Bros Burguer

## 1. Introducción

Actualmente, la aplicación Bros Burguer no cuenta con una función para registrar nuevos administradores desde la interfaz de la aplicación. Por lo tanto, debes crear el administrador directamente en la base de datos de Supabase.

Para crear un administrador, necesitas configurar tres atributos:

| Atributo | Valor | Descripción |
|---|---|---|
| `user` | El nombre que elijas | Nombre de usuario para iniciar sesión. |
| `password` | Hash generado con BCrypt | Contraseña almacenada de forma segura. |
| `role` | `ADMIN` | Rol que identifica al usuario como administrador. |

**Importante:** El valor del atributo `role` debe ser exactamente `ADMIN`, respetando las letras mayúsculas. No debes utilizar `admin` ni `ROLE_ADMIN`.

## 2. Generar una contraseña con BCrypt

Antes de insertar el administrador en la base de datos, debes generar el hash BCrypt de la contraseña que deseas asignarle.

### Paso 1. Ubicar el archivo `Test.java`

Dentro del proyecto, navega hasta la siguiente ruta:

```text
backend_bros_burguer/
└── demo/
    └── src/
        └── main/
            └── java/
                └── com/
                    └── example/
                        └── demo/
                            └── test/
                                └── Test.java
```

Abre el archivo `Test.java` desde tu IDE.

### Paso 2. Escribir la contraseña que deseas utilizar

El archivo debe tener una estructura similar a esta:

```java
package com.example.demo.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println(encoder.encode("1234"));
    }
}
```

Reemplaza `"1234"` por la contraseña que quieras asignarle al nuevo administrador.

Por ejemplo:

```java
System.out.println(encoder.encode("MiNuevaClave123"));
```

En este caso, `MiNuevaClave123` es la contraseña original que utilizarás para iniciar sesión. Puedes elegir una contraseña diferente.

### Paso 3. Ejecutar `Test.java`

Ejecuta la clase `Test` desde tu IDE.

El programa imprimirá en la consola un hash BCrypt parecido a este:

```text
$2a$10$.....................................................
```

El resultado real será diferente. BCrypt genera una sal aleatoria, por lo que ejecutar el programa varias veces con la misma contraseña producirá hashes distintos.

**Copia el hash completo que aparezca en tu consola.** No utilices el hash ilustrativo de este documento.

Recuerda que debes guardar el hash en la base de datos, pero utilizar la contraseña original al iniciar sesión.

## 3. Crear el administrador en Supabase

Una vez generado el hash BCrypt, puedes registrar el administrador directamente en tu base de datos PostgreSQL.

### Paso 1. Abrir el editor SQL de Supabase

1. Ingresa a tu proyecto en [Supabase](https://supabase.com/).
2. Abre la sección **SQL Editor**.
3. Crea una nueva consulta SQL.




### Paso 2. Insertar el administrador mediante SQL

 La tabla se llama `users` y contiene las columnas `password` , `role` y `username`,  ejecuta una consulta con esta estructura:

```sql
INSERT INTO admins (user, password, role)
VALUES (
    'admin',
    '$2a$10$PEGA_AQUI_EL_HASH_BCRYPT_COMPLETO',
    'ADMIN'
);
```

Debes reemplazar los siguientes valores:

- **`admin`:** por el nombre de usuario que deseas asignarle al administrador.
- **`$2a$10$PEGA_AQUI_EL_HASH_BCRYPT_COMPLETO`:** por el hash completo generado mediante `Test.java`.
- **`'ADMIN'`:** déjalo exactamente así, porque es el rol que requiere tu aplicación.

Observa que `'ADMIN'` lleva comillas simples porque es un valor de texto en SQL.


## 3. Crear el administrador desde la interfaz de Supabase

Si prefieres insertar el administrador desde la interfaz gráfica, puedes hacerlo sin escribir una consulta SQL.

1. Abre tu proyecto en Supabase.
2. Entra en **Table Editor**.
3. Abre la tabla que almacena los administradores o usuarios.
4. Crea una nueva fila.
5. Introduce los valores correspondientes a cada columna:

| Columna | Qué debes introducir |
|---|---|
| `user` | El nombre de usuario que elegiste. |
| `password` | El hash BCrypt completo que generaste en `Test.java`. |
| `role` | `ADMIN` |

En la columna `role`, escribe simplemente `ADMIN`, sin comillas. La interfaz guardará el valor según el tipo de dato definido para esa columna.

**No introduzcas la contraseña original en la columna `password`.** Debes pegar el hash generado por BCrypt.

## 4. Verificar que el administrador se haya creado correctamente

Después de insertar el registro:

1. Comprueba que el administrador aparezca en la tabla correspondiente de Supabase.
2. Verifica que el campo `user` contenga el nombre de usuario elegido.
3. Comprueba que `password` contenga el hash BCrypt y no la contraseña original.
4. Confirma que `role` tenga exactamente el valor `ADMIN`.
5. Abre la aplicación Bros Burguer.
6. Intenta iniciar sesión utilizando el nombre de usuario y la contraseña original que elegiste al generar el hash.

Si los datos son correctos y la lógica de autenticación de la aplicación coincide con ellos, el administrador debería poder autenticarse y acceder a las funciones correspondientes a su rol.

## 5. ¿Cómo funciona BCrypt?

El proceso de almacenamiento y verificación de contraseñas funciona así:

1. Eliges una contraseña, por ejemplo, `MiNuevaClave123`.
2. `BCryptPasswordEncoder` genera un hash a partir de la contraseña y una sal aleatoria.
3. Guardas el hash resultante en la base de datos.
4. Cuando el usuario inicia sesión, la aplicación recibe la contraseña original.
5. BCrypt verifica si la contraseña introducida coincide con el hash almacenado.
6. Si la verificación es correcta, la autenticación puede continuar.

No es posible descifrar un hash BCrypt para recuperar la contraseña original. BCrypt es un algoritmo de hash unidireccional, no un mecanismo de cifrado reversible.




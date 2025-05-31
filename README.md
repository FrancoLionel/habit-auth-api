# 🧠 HabitAuth API

Una API RESTful construida con **Spring Boot 3.3**, enfocada en la gestión de hábitos personales con autenticación mediante **JWT**.

---

## 🚀 Funcionalidades principales

- Registro e inicio de sesión con autenticación basada en JWT  
- Protección de rutas y recursos mediante seguridad basada en roles  
- CRUD completo para la gestión de hábitos personales  
- Logout seguro con revocación de tokens JWT  
- Compatible con clientes externos (Unity, React, Angular, etc.)  
- Documentación interactiva disponible (Swagger UI)

---

## 🛠️ Tecnologías utilizadas

- **Java 17**  
- **Spring Boot 3.3** (Security, Web, Data JPA, Validation)  
- **JWT** con biblioteca jjwt  
- **MySQL** (compatible también con PostgreSQL)  
- **Lombok** para reducción de código boilerplate  
- **Springdoc OpenAPI** para documentación de la API  

---

## 📚 Endpoints principales

### 🔐 Autenticación

| Método | Ruta                       | Descripción                            | Request Body                     | Response                         |
|--------|----------------------------|--------------------------------------|---------------------------------|---------------------------------|
| POST   | `/api/v1/auth/register`    | Registrar un nuevo usuario            | Datos usuario (nombre, email, contraseña, etc.) | JWT + Refresh Token              |
| POST   | `/api/v1/auth/authenticate`| Iniciar sesión                       | Email y contraseña               | JWT + Refresh Token              |
| POST   | `/api/v1/auth/refresh-token`| Renovar token JWT                   | Refresh Token                   | Nuevo JWT                       |
| POST   | `/api/v1/auth/logout`       | Cerrar sesión y revocar token actual | Authorization: Bearer {token}   | Estado 200 OK                   |

---

### 📅 Gestión de hábitos

| Método | Ruta                 | Descripción                           | Headers                        | Request Body                      | Response                        |
|--------|----------------------|-------------------------------------|-------------------------------|----------------------------------|--------------------------------|
| GET    | `/api/habits`        | Obtener lista de hábitos del usuario autenticado | Authorization: Bearer {token} | N/A                              | Lista de hábitos (JSON)         |
| POST   | `/api/habits`        | Crear un nuevo hábito                | Authorization: Bearer {token} | Datos del hábito (nombre, fecha límite, estado) | Hábito creado                  |
| PUT    | `/api/habits/{id}`   | Actualizar hábito existente         | Authorization: Bearer {token} | Datos actualizados del hábito    | Hábito actualizado o error 403  |
| DELETE | `/api/habits/{id}`   | Eliminar hábito                     | Authorization: Bearer {token} | N/A                              | Estado 204 No Content o error 403|

## 🐳 Dockerización de la aplicación
Este proyecto utiliza Docker y Docker Compose para contenerizar la API de autenticación JWT junto a una base de datos MySQL. A continuación se detallan los componentes y su configuración:

### 🔧 Estructura
- Dockerfile
Define una imagen personalizada basada en eclipse-temurin:17-jdk. Copia el .jar generado por Spring Boot y lo expone en el puerto 8080 para ejecución.

- docker-compose.yml
orquesta dos contenedores:

  * api: Construye la aplicación desde el Dockerfile y se conecta a la base de datos.

  * db: Utiliza la imagen oficial de MySQL 8 y persiste datos con un volumen llamado db_data.

- application.properties
Configura los parámetros de conexión a la base de datos, el sistema de JWT (clave secreta y expiraciones) y JPA.

## ▶️ Cómo levantar el entorno
Asegúrate de haber generado el .jar de la aplicación con Maven:

## ▶️ Cómo levantar el entorno

Asegúrate de haber generado el `.jar` de la aplicación con Maven:

```bash
mvn clean package
```
Luego levanta los servicios con Docker Compose:

```bash
sudo docker compose up --build
```
La API estará disponible en: http://localhost:8080.

Nota: La base de datos MySQL está expuesta en el puerto 3306 y utiliza root como usuario y contraseña. La base de datos se llama habitsdb.

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

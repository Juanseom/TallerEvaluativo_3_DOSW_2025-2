# 🍳 TallerEvaluativo_3_DOSW_2025-2

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=spring-boot)
![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![MongoDB](https://img.shields.io/badge/MongoDB-NoSQL-green?style=for-the-badge&logo=mongodb)
![Maven](https://img.shields.io/badge/Maven-3.9-red?style=for-the-badge&logo=apache-maven)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue?style=for-the-badge&logo=docker)
![Azure](https://img.shields.io/badge/Azure-Deployed-0078D4?style=for-the-badge&logo=microsoft-azure)

### 📺 API de Gestión de Recetas de Cocina

*Una solución completa para gestionar recetas de concursantes, chefs y televidentes*

[Características](#-características-principales) •
[Instalación](#-instalación) •
[Documentación API](#-documentación-de-la-api) •
[Arquitectura](#-arquitectura-mvc) •
[CI/CD](#-cicd)

</div>

---

## 📑 Tabla de Contenidos

- [👤 Autor](#-autor)
- [📋 Descripción del Proyecto](#-descripción-del-proyecto)
- [🎯 Objetivos](#-objetivos)
- [✨ Características Principales](#-características-principales)
- [📌 Requerimientos](#-requerimientos)
    - [Requerimientos del Cliente](#requerimientos-del-cliente)
    - [Requerimientos Funcionales](#requerimientos-funcionales)
- [⚙️ Stack Tecnológico](#️-stack-tecnológico)
- [🏗️ Arquitectura MVC](#️-arquitectura-mvc)
- [🚀 Instalación](#-instalación)
- [📖 Documentación de la API](#-documentación-de-la-api)
- [🌳 Estrategia de Versionamiento](#-estrategia-de-versionamiento)
- [🧪 Testing y Calidad](#-testing-y-calidad)
- [🔄 CI/CD](#-cicd)
- [📊 Métricas de Calidad](#-métricas-de-calidad)
- [🤝 Contribuir](#-contribuir)
- [📄 Licencia](#-licencia)

---

## 👤 Autor

**Juan Sebastian Ortega Muñoz**

[![GitHub](https://img.shields.io/badge/GitHub-Juanseom-181717?style=flat&logo=github)](https://github.com/Juanseom)

---

## 📋 Descripción del Proyecto

Este proyecto implementa una **API RESTful robusta** para la gestión de recetas de cocina destinada a un programa de telerrealidad. La plataforma permite a televidentes, concursantes y chefs jurados **consultar, publicar e interactuar** con recetas que han aparecido a lo largo de las temporadas del programa.

### 🎯 Objetivos

- Desarrollar una API RESTful escalable y mantenible
- Implementar sistema de gestión diferenciado por tipo de autor
- Proporcionar búsquedas avanzadas (ingrediente, temporada, autor)
- Garantizar seguridad y buenas prácticas de desarrollo
- Integrar CI/CD con despliegue automático en Azure
- Mantener cobertura de pruebas superior al 80%

---

## ✨ Características Principales

<table>
<tr>
<td width="50%">

### 🔐 Gestión de Recetas
- Registro diferenciado por tipo de autor
- Actualización y eliminación de recetas
- Consulta por ID consecutivo
- Persistencia en MongoDB

</td>
<td width="50%">

### 🔍 Búsquedas Avanzadas
- Filtrado por tipo de autor
- Búsqueda por temporada
- Búsqueda por ingredientes
- Listado completo de recetas

</td>
</tr>
<tr>
<td width="50%">

### 📚 Documentación
- Swagger/OpenAPI 3.0
- Endpoints interactivos
- Ejemplos de uso
- Esquemas de datos

</td>
<td width="50%">

### 🚀 DevOps
- CI/CD con GitHub Actions
- Despliegue automático en Azure
- Pruebas automatizadas
- Análisis de código con SonarQube

</td>
</tr>
</table>

---

## 📌 Requerimientos

### Requerimientos del Cliente

| # | Requerimiento | Estado |
|---|---------------|--------|
| 1 | Registro de recetas por televidentes, participantes y chefs | ✅ |
| 2 | Consulta de todas las recetas o filtradas por autor | ✅ |
| 3 | Consulta por temporada o ingrediente | ✅ |
| 4 | Consulta por número consecutivo | ✅ |
| 5 | Actualización y eliminación de recetas | ✅ |
| 6 | Persistencia en MongoDB | ✅ |
| 7 | Documentación Swagger | ✅ |
| 8 | Pruebas unitarias completas | ✅ |
| 9 | CI/CD con GitHub Actions y Azure | ✅ |

### Requerimientos Funcionales

<details>
<summary><b>Ver lista completa de 16 requerimientos funcionales</b></summary>

1. ✅ Registrar una receta de un televidente
2. ✅ Registrar una receta de un participante (incluyendo temporada)
3. ✅ Registrar una receta de un chef
4. ✅ Listar todas las recetas guardadas
5. ✅ Consultar una receta por número consecutivo
6. ✅ Listar recetas creadas por participantes
7. ✅ Listar recetas creadas por televidentes
8. ✅ Listar recetas creadas por chefs
9. ✅ Listar recetas por temporada
10. ✅ Buscar recetas por ingrediente específico
11. ✅ Eliminar una receta
12. ✅ Actualizar una receta
13. ✅ Persistir datos en MongoDB
14. ✅ Documentar API con Swagger
15. ✅ Incluir pruebas unitarias
16. ✅ Configurar CI/CD con GitHub Actions y Azure

</details>

---

## ⚙️ Stack Tecnológico

### Backend & Framework
![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=flat&logo=spring-boot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=flat&logo=apache-maven&logoColor=white)

### Base de Datos
![MongoDB](https://img.shields.io/badge/MongoDB-NoSQL-47A248?style=flat&logo=mongodb&logoColor=white)

### Testing & Quality
![JUnit](https://img.shields.io/badge/JUnit-5.x-25A162?style=flat&logo=junit5&logoColor=white)
![JaCoCo](https://img.shields.io/badge/JaCoCo-Coverage-red?style=flat)
![SonarQube](https://img.shields.io/badge/SonarQube-Quality-4E9BCD?style=flat&logo=sonarqube&logoColor=white)

### DevOps & Tools
![Docker](https://img.shields.io/badge/Docker-Container-2496ED?style=flat&logo=docker&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-CI/CD-2088FF?style=flat&logo=github-actions&logoColor=white)
![Azure](https://img.shields.io/badge/Azure-Cloud-0078D4?style=flat&logo=microsoft-azure&logoColor=white)

### Documentation & Utilities
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=flat&logo=swagger&logoColor=black)
![Lombok](https://img.shields.io/badge/Lombok-Productivity-BC4521?style=flat)

---

## 🏗️ Arquitectura (MVC)

El backend sigue la **arquitectura Modelo–Vista–Controlador (MVC)**. La estructura de carpetas se organizó para mantener separación de responsabilidades y buenas prácticas de ingeniería:


```
📁 src/main/java/edu/dosw/taller/
│
├── 📁 configs/               # ⚙️ Configuraciones globales (Swagger, seguridad, etc.)
├── 📁 controller/            # 🌐 Controladores REST (exposición de endpoints)
│   └── 📁 dtos/              # 📦 Data Transfer Objects (DTOs) para request/response
│   └── 📁 utils/             # 🛠️ Utilidades para el controlador (Excepciones, mapeos, etc.)
├── 📁 model/                 # 📊 Módulo de modelos y lógica base
│   ├── 📁 components/        # 🧩 Componentes adicionales (clases auxiliares)
│   │    └── 📁 utils/        # 🛠️ Utilidades para el modelo (mappers, validaciones, etc.)
│   ├── 📁 entities/          # 📝 Entidades de dominio (Task, User, etc.)
│   ├── 📁 persistence/       # 🗄️ Persistencia y acceso a datos
│   │   └── 📁 repository/    # 🔗 Interfaces con la BD (MongoDB Repositories)
│   └── 📁 services/          # 🔧 Servicios dentro de la capa de modelo


``` 

### 📋 Descripción de capas:

| **Capa**         | **Responsabilidad**                                        | **Tecnologías / Anotaciones** |
|------------------|------------------------------------------------------------|--------------------------------|
| **Controller**   | Manejo de peticiones HTTP y exposición de endpoints REST.  | Spring MVC, `@RestController` |
| **Model**        | Contiene el modelo de dominio y submódulos relacionados.   | Java classes, Lombok, DTOs |
| ├─ **Entities**  | Definición de entidades principales (Task, User, etc.).    | `@Document`, `@Entity` |
| ├─ **Components**| Clases auxiliares/componentes dentro del dominio.          | Beans, helpers de dominio |
| ├─ **Persistence/Repository** | Interfaces de acceso a datos y consultas CRUD. | MongoDB, Spring Data |
| └─ **Services**  | Servicios asociados al modelo (lógica de negocio puntual). | `@Service` |
| **Configs**      | Configuración global de la aplicación.                     | Swagger, Security, CORS |

---

# 🌳 Manejo de Estrategia de versionamiento y branches


## Estrategia de Ramas (Git Flow)

![GitFlow.jpg](docs/imagenes/GitFlow.jpg)

## Ramas y propósito
- Manejaremos GitFlow, el modelo de ramificación para el control de versiones de Git

### `main`
- **Propósito:** rama **estable** con la versión final (lista para demo/producción).
- **Reglas:**
    - Solo recibe merges desde `release/*` y `hotfix/*`.
    - Cada merge a `main` debe crear un **tag** SemVer (`vX.Y.Z`).
    - Rama **protegida**: PR obligatorio, 1–2 aprobaciones, checks de CI en verde.

### `develop`
- **Propósito:** integración continua de trabajo; base de nuevas funcionalidades.
- **Reglas:**
    - Recibe merges desde `feature/*` y también desde `release/*` al finalizar un release.
    - Rama **protegida** similar a `main`.

### `feature/*`
- **Propósito:** desarrollo de una funcionalidad, refactor o spike.
- **Base:** `develop`.
- **Cierre:** se fusiona a `develop` mediante **PR**


### `release/*`
- **Propósito:** congelar cambios para estabilizar pruebas, textos y versiones previas al deploy.
- **Base:** `develop`.
- **Cierre:** merge a `main` (crear **tag** `vX.Y.Z`) **y** merge de vuelta a `develop`.
- **Ejemplo de nombre:**  
  `release/1.3.0`

### `hotfix/*`
- **Propósito:** corregir un bug **crítico** detectado en `main`.
- **Base:** `main`.
- **Cierre:** merge a `main` (crear **tag** de **PATCH**) **y** merge a `develop` para mantener paridad.
- **Ejemplos de nombre:**  
  `hotfix/fix-blank-screen`, `hotfix/css-broken-header`

### 🏷️ Convención de Commits

```bash
feat: nueva funcionalidad
fix: corrección de bug
docs: cambios en documentación
style: formato, espacios (sin cambios de código)
refactor: refactorización de código
test: agregar o modificar tests
chore: tareas de mantenimiento
```

### 📌 Versionado Semántico (SemVer)

```
vMAYOR.MINOR.PATCH

v1.0.0 → Primera versión estable
v1.1.0 → Nueva funcionalidad (retrocompatible)
v1.1.1 → Corrección de bug
v2.0.0 → Cambio incompatible con versiones anteriores
```

---

## 🚀 Instalación

### Prerrequisitos

```bash
☕ Java 17+
📦 Maven 3.9+
🐳 Docker (opcional)
🗄️ MongoDB 5.0+
```

### Instalación Local

```bash
# 1. Clonar el repositorio
git clone https://github.com/Juanseom/TallerEvaluativo_3_DOSW_2025-2.git
cd TallerEvaluativo_3_DOSW_2025-2

# 2. Configurar MongoDB (application.yml)
spring.data.mongodb.uri=mongodb://localhost:27017/recetas_db

# 3. Compilar el proyecto
mvn clean install

# 4. Ejecutar la aplicación
mvn spring-boot:run
```

### 🐳 Con Docker

```bash
# Construir imagen
docker build -t recetas-api .

# Ejecutar contenedor
docker run -p 8080:8080 recetas-api
```

---

## 📖 Documentación de la API

### 🌐 Swagger UI
**URL Local:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Endpoints Principales

#### 🟢 POST `/api/recetas`
**Registrar nueva receta**

**Entrada:**

![Entrada Post](docs/imagenes/post_entrada.png)

**Salida:**

![Salida Post](docs/imagenes/post_salida.png)

---

#### 🔵 GET `/api/recetas/search`
**Buscar recetas con filtros**

**Query Params:**
- `ingrediente` (opcional)
- `temporada` (opcional)
- `tipoAutor` (opcional)

**Entrada:**

![Entrada Get](docs/imagenes/get_entrada.png)

**Salida:**

![Salida Get](docs/imagenes/get_salida.png)

---

#### 🔴 DELETE `/api/recetas/{titulo}`
**Eliminar receta**

**Entrada:**

![Entrada Delete](docs/imagenes/delete1.png)

**Salida:**

![Proceso Delete](docs/imagenes/delete2.png)

---

## 🧪 Testing y Calidad

### Cobertura de Código (JaCoCo)

![JaCOCO](docs/imagenes/jacoco.png)

### Análisis Estático (SonarQube)

![SonarQube](docs/imagenes/sonar.png)

---

## 🔄 CI/CD

### GitHub Actions Pipeline

```
🔄 Build → 🧪 Test → 📊 Quality → 🐳 Docker → 🚀 Deploy
```

### Pipeline Stages

| Stage | Herramienta | Descripción |
|-------|-------------|-------------|
| 🔍 **Code Analysis** | SonarQube | Análisis estático de código |
| 🧪 **Testing** | JUnit + JaCoCo | Pruebas unitarias y cobertura |
| 🏗️ **Build** | Maven | Compilación y empaquetado |
| 🐳 **Containerization** | Docker | Creación de imagen |
| 🚀 **Deployment** | Azure | Despliegue automático |

**URL Backend desplegado:** [https://recetas-api.azurewebsites.net/swagger-ui/index.html]
### Status Badges

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Tests](https://img.shields.io/badge/tests-passing-brightgreen)
![Quality Gate](https://img.shields.io/badge/quality%20gate-passed-brightgreen)

---

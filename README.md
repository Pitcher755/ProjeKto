# 🚀 ProjeKto  

<div align="center">

**Gestor de Proyectos Colaborativo con Kanban, Roles y Métricas (Vaadin + Spring Boot)** 



![Java](https://img.shields.io/badge/Java-17-red?logo=openjdk) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-brightgreen?logo=springboot) ![Vaadin](https://img.shields.io/badge/Vaadin-24.8.8-blue?logo=vaadin) ![Maven](https://img.shields.io/badge/Maven-Build-orange?logo=apachemaven) ![License](https://img.shields.io/badge/License-MIT-yellow)  


---
</div>

## 📖 Descripción  
**ProjeKto** es una aplicación web para la **gestión colaborativa de proyectos** con:  
- Tableros Kanban intuitivos.  
- Control de roles y permisos.  
- Visualización del progreso con gráficas dinámicas (Vaadin Charts).  
- Interfaz simple pero moderna para equipos que buscan **organización sin complicaciones**.  

Este repositorio se centra en construir un **MVP sólido y escalable**, pensado tanto para **usuarios finales** como para ser presentado en un **portfolio profesional**.  

---

## 🛠️ Stack Tecnológico  

- **Backend**: Spring Boot 3.5.5  
- **Frontend/UI**: Vaadin 24.8.8  
- **Base de datos (dev)**: H2 en memoria  
- **Build Tool**: Maven  
- **Seguridad**: Spring Security (roles: Admin, Gestor, Colaborador)  
- **Test**: JUnit + Spring Security Test  

---

## 🚀 Cómo ejecutar el proyecto  

### 🔧 Requisitos previos
- Java 17  
- Maven 3.9+  

### ▶️ Pasos  
```bash
# Clonar el repositorio
git clone https://github.com/TU-USUARIO/projekto.git
cd projekto

# Ejecutar en modo desarrollo
mvn spring-boot:run
````

La aplicación estará disponible en 👉 `http://localhost:8080`

---

## 🗂️ Estructura del proyecto

```bash
projekto/
│── src/
│   ├── main/
│   │   ├── java/com/github/com/pitcher755/projekto
│   │   │   ├── config/       # Configuración de Spring y seguridad
│   │   │   ├── model/        # Entidades JPA
│   │   │   ├── repository/   # Repositorios (Spring Data JPA)
│   │   │   ├── service/      # Lógica de negocio
│   │   │   ├── ui/           # Vistas Vaadin
│   │   │   └── ProjeKtoApp   # Clase principal
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/       # Archivos estáticos si aplica
│   └── test/                 # Tests unitarios e integración
│
├── pom.xml                   # Configuración de Maven
├── README.md                 # Documentación del proyecto
└── .gitignore
```

---

## 📌 Roadmap (MVP → Futuras iteraciones)

✅ **Iteración 1 – MVP básico**

* Autenticación + roles.
* Creación de proyectos.
* Tablero Kanban simple.
* Gráficas de progreso.

🔜 **Iteración 2 – Usabilidad**

* Comentarios en tareas.
* Subtareas/checklists.
* Notificaciones básicas.

🔮 **Iteración 3 – Métricas avanzadas**

* Burn-down chart.
* Productividad por usuario.

🎮 **Iteración 4 – Diferenciales**

* Gamificación (logros, rachas, ranking).
* Wiki del proyecto.
* Modo Enfoque (Pomodoro).

---

## 🤝 Contribuciones

Las contribuciones son bienvenidas 🎉.
Para proponer cambios:

1. Haz un fork del proyecto.
2. Crea tu rama feature: `git checkout -b feature/nueva-funcionalidad`.
3. Haz commit: `git commit -m "Añadir nueva funcionalidad"`.
4. Haz push: `git push origin feature/nueva-funcionalidad`.
5. Abre un Pull Request.

---

## 📜 Licencia

Este proyecto está bajo licencia **MIT** – puedes usarlo libremente para proyectos personales o profesionales.

---

✨ *Desarrollado con Spring Boot + Vaadin para demostrar que la productividad y la simplicidad pueden ir de la mano.*


# Lab 7 - Load Balancer con Microservicios

Sistema de gestión con 3 microservicios en Java Spring Boot, balanceados por Nginx.

## Arquitectura

```
                    ┌─────────────────┐
                    │   Nginx (LB)    │
                    │   Puerto: 7000  │
                    └────────┬────────┘
                             │
           ┌─────────────────┼─────────────────┐
           │                 │                 │
           ▼                 ▼                 ▼
    ┌─────────────┐   ┌─────────────┐   ┌─────────────┐
    │   App01     │   │   App02     │   │   App03     │
    │  Clientes   │   │  Productos  │   │ Proveedores │
    │ Puerto:5001 │   │ Puerto:6001 │   │ Puerto:7001 │
    └──────┬──────┘   └──────┬──────┘   └──────┬──────┘
           │                 │                 │
           └─────────────────┼─────────────────┘
                             │
                    ┌────────▼────────┐
                    │   PostgreSQL    │
                    │  Puerto: 5434   │
                    └─────────────────┘
```

## Requisitos

- Docker
- Docker Compose

## Ejecución

1. Clonar el repositorio y navegar al directorio:
```bash
cd lab7
```

2. Construir e iniciar todos los contenedores:
```bash
docker-compose up -d --build
```

3. Esperar aproximadamente 30 segundos para que las aplicaciones inicien completamente.

4. Verificar que los contenedores estén corriendo:
```bash
docker-compose ps
```

## URLs de Acceso

| Servicio | URL | Descripción |
|----------|-----|-------------|
| Load Balancer | http://localhost:7000 | Round-robin entre las 3 apps |
| Clientes | http://localhost:7000/clientes | Siempre va a App01 |
| Productos | http://localhost:7000/productos | Siempre va a App02 |
| Proveedores | http://localhost:7000/proveedores | Siempre va a App03 |

### Acceso directo (sin Load Balancer)
- App01 Clientes: http://localhost:5001
- App02 Productos: http://localhost:6001
- App03 Proveedores: http://localhost:7001

## Características

- **Load Balancer**: Nginx distribuye las peticiones usando round-robin en la ruta raíz (`/`)
- **Rutas específicas**: Cada microservicio tiene su propia ruta (`/clientes`, `/productos`, `/proveedores`)
- **CRUD Completo**: Crear, Leer, Actualizar y Eliminar registros
- **Paginación**: 10 registros por página
- **Base de datos**: PostgreSQL con 20 registros por tabla

## Funcionalidades CRUD

Cada microservicio implementa las siguientes operaciones:

| Operación | Ruta | Descripción |
|-----------|------|-------------|
| Listar | `/clientes`, `/productos`, `/proveedores` | Lista paginada de registros |
| Crear | `/clientes/nuevo`, `/productos/nuevo`, `/proveedores/nuevo` | Formulario para nuevo registro |
| Editar | `/clientes/editar/{id}`, etc. | Formulario para editar registro |
| Eliminar | `/clientes/eliminar/{id}`, etc. | Elimina el registro |

### Modelos de Datos

**Clientes (App01)**
- ID, Nombre, Email, Teléfono, Ciudad

**Productos (App02)**
- ID, Nombre, Descripción, Precio, Stock

**Proveedores (App03)**
- ID, Nombre, Email, Teléfono, Ciudad

## Comandos Útiles

```bash
# Ver logs de todos los servicios
docker-compose logs -f

# Ver logs de un servicio específico
docker-compose logs -f app01-clientes

# Reiniciar todos los servicios
docker-compose restart

# Detener todos los servicios
docker-compose down

# Detener y eliminar volúmenes (resetea la base de datos)
docker-compose down -v
```

## Estructura del Proyecto

```
lab7/
├── docker-compose.yml          # Orquestación de contenedores
├── init.sql                    # Script de inicialización de BD
├── nginx-loadbalancer/
│   └── nginx.conf              # Configuración del load balancer
├── app01/                      # Microservicio Clientes
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/
│       ├── java/               # Código Java (Controller, Model, Repository)
│       └── resources/          # Templates y CSS
├── app02/                      # Microservicio Productos
│   └── ...
└── app03/                      # Microservicio Proveedores
    └── ...
```

## Tecnologías

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- Thymeleaf
- PostgreSQL 15
- Nginx
- Docker & Docker Compose

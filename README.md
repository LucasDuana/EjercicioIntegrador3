# EjercicioIntegrador3

## Inserción de Datos de prueba para la aplicación
Se generan datos para las entidades de la base de datos.

estudiante
  ```sql
    INSERT INTO estudiante (dni, apellido, ciudad, edad, genero, lu, nombre)
    VALUES
    (12345678, 'Pérez', 'Buenos Aires', 20, 'Masculino', 'LU1234', 'Juan'),
    (23456789, 'González', 'Córdoba', 22, 'Femenino', 'LU5678', 'María'),
    (34567890, 'Fernández', 'Rosario', 21, 'Masculino', 'LU9101', 'Lucas'),
    (45678901, 'Martínez', 'Mendoza', 23, 'Femenino', 'LU1122', 'Ana'),
    (56789012, 'Sánchez', 'Salta', 24, 'Masculino', 'LU3344', 'Pedro'),
    (67890123, 'López', 'Tucumán', 25, 'Femenino', 'LU5566', 'Laura'),
    (78901234, 'Rodríguez', 'Mar del Plata', 22, 'Masculino', 'LU7788', 'Jorge'),
    (89012345, 'Gómez', 'La Plata', 20, 'Femenino', 'LU9900', 'Carla'),
    (90123456, 'Díaz', 'Neuquén', 23, 'Masculino', 'LU1235', 'Tomás'),
    (12309876, 'Romero', 'Bahía Blanca', 24, 'Femenino', 'LU5679', 'Sofía');
  ```

carrera
  ```sql
  INSERT INTO carrera (carrera,duracion) 
  VALUES
    ('Ingenieria en sistemas',5),
    ('Tudai',3)
    ('Ingenieria',6)
    ('Medicina',4)
    ('Derecho',7)
    ('Arquitectura',5)
    ('Economia',6)
  ```
estudiante_carrera

  ```sql
  INSERT INTO estudiante_carrera (antiguedad, graduacion, inscripcion, carrera_id, dni)
  VALUES
    (2, null, 2022, 1, 12345678),  -- Juan Pérez en Ingeniería en Sistemas
    (1, null, 2023, 2, 23456789),  -- María González en Tudai
    (4, 2024, 2020, 3, 34567890),  -- Lucas Fernández en Ingeniería
    (3, null, 2021, 4, 45678901),  -- Ana Martínez en Medicina
    (5, 2023, 2018, 5, 56789012),  -- Pedro Sánchez en Derecho
    (2, null, 2022, 6, 67890123),  -- Laura López en Arquitectura
    (6, 2024, 2017, 7, 78901234),  -- Jorge Rodríguez en Economía
    (2, null, 2022, 1, 89012345),  -- Carla Gómez en Ingeniería en Sistemas
    (4, null, 2020, 3, 90123456),  -- Tomás Díaz en Ingeniería
    (1, null, 2023, 2, 12309876);  -- Sofía Romero en Tudai
  ```


## Documentación de Endpoints

Esta sección detalla la implementación de los endpoints de la API REST del sistema requeridos para el trabajo practico integrador 3.

### 1. Estudiantes "/estudiantes"

#### 1.1 Obtener todos los estudiantes

- **Método**: `GET`
- **Ruta**: ``
- **Descripción**: Obtiene una lista de todos los estudiantes registrados en el sistema.
- **Respuesta**:
   - **Código 200 OK**: Retorna una lista de objetos `Estudiante`.

#### 1.2 Crear un nuevo estudiante

- **Método**: `POST`
- **Ruta**: ``
- **Descripción**: Registra un nuevo estudiante en el sistema.
- **Cuerpo de la solicitud**:
  ```json
  {
      "dni": 12345678,
      "nombre": "Juan",
      "apellido": "Pérez",
      "edad": 20,
      "genero": "Masculino",
      "ciudad": "Buenos Aires",
      "lu": "12345"
  }
  
#### 1.3 Matricular un estudiante a una carrera
- **Método**: `POST`
- **Ruta**: `/{estudianteId}/carrera/{carreraId}`
  **Ejemplo**: `http://localhost:8080/estudiantes/90123456/carrera/2` Inscribiria a tomas Diaz a tudai


#### 1.3 Obtener estudiantes mediante algun criterio de ordenamiento simple

- **Método**: `GET`
- **Ruta**: `/estudiantes/ordenados`
- **Parametros**: campoOrden = "{value}"  
- **Descripción**: Obtiene los estudiantes mediante algun criterio,si no se inserta criterio,se hace por defecto por nombre
  
**Ejemplo**: `http://localhost:8080/estudiantes?campoOrden=edad`


#### 1.4 Obtener estudiantes mediante su LU
- **Método**: `GET`
- **Ruta**: `/libreta/{lu}`
- - **Respuesta**:
- **Código 200 OK**: Retorna unobjeto `Estudiante` con dicho LU.
- **Código 404 Not Found**: No se encuentra dicho estudiante con tal LU

**Ejemplo**: `http://localhost:8080/estudiantes/libreta/LU9101` Traeria a Lucas Fernandez

#### 1.5 Obtener estudiantes en base a su género
- **Método**: `GET`
- **Ruta**: `/libreta/{genero}`
- - **Parametros**: genero = "{value}"
- - **Respuesta**:
- **Código 200 OK**: Retorna una lista de objetos `Estudiante` en base a su genero.

**Ejemplo**: `http://localhost:8080/estudiantes/genero?genero=masculino`

#### 1.6 recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
- **Método**: `GET`
- **Ruta**: `/carrera/{carreraId}/ciudad/{ciudad}`
- **Código 200 OK**: Retorna una lista de objetos `Estudiante` con dicha carrera residentes a la ciudad solicitada.
- **Código 404 Not Found**: No se encuentran alumnos.

**Ejemplo**: `http://localhost:8080/estudiantes/carrera/2/ciudad/córdoba` 

### 2. Carreras

#### 2.1 Crear una carrera
- **Método**: `POST`
- **Ruta**: `/carreras`
- **Descripcion**: Crea una nueva carrera

**Ejemplo**: `http://localhost:8080/carreras`

- **Cuerpo de la solicitud**:
  ```json
  {
      "nombre":"Nombre de carrera",
      "duracion":4
  }

- **Respuesta**:
- **Código 200 OK**: Se crea la carrera 

#### 2.2 Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
- **Método**: `GET`
- **Ruta**: `/con-estudiantes`

#### 2.3 generar un reporte de las carreras, que para cada carrera incluya información de los inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar los años de manera cronológica.
- **Método**: `GET`
- **Ruta**: `/reporte`
- **Respuesta**:
  **Código 200 OK**: Se retorna una lista de info de cada carrera por año con la cantidad de inscriptos y grauados

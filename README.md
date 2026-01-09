# PacmanProject

[![CI/CT](https://github.com/Dany0424/pacmanProject/actions/workflows/ci-ct.yml/badge.svg)](https://github.com/Dany0424/pacmanProject/actions/workflows/ci-ct.yml)
[![CD](https://github.com/Dany0424/pacmanProject/actions/workflows/cd.yml/badge.svg)](https://github.com/Dany0424/pacmanProject/actions/workflows/cd.yml)
![Coverage](https://img.shields.io/badge/coverage-94%25-brightgreen)

Juego básico de Pac-Man desarrollado en Java utilizando Swing para la interfaz gráfica.

## Características del Juego

- **Laberinto dinámico**: 3 niveles progresivos con diferentes diseños de laberinto
- **Sistema de vidas**: Pac-Man comienza con 3 vidas (máximo 9)
- **Movimiento fluido**: Control por teclas de flecha con detección precisa de colisiones
- **Fantasmas inteligentes**: 4 fantasmas con movimiento automático aleatorio y colores distintivos
- **Sistema de puntuación**:
  - Puntos normales (dots): incrementan el puntaje base
  - Frutas especiales: otorgan 50 puntos de bonificación
- **Progresión de niveles**: Avanza automáticamente al siguiente nivel al completar todos los puntos
- **Condiciones de juego**:
  - **Victoria**: Completar todos los niveles comiendo todos los puntos
  - **Derrota**: Perder todas las vidas al ser atrapado por fantasmas
- **Interfaz gráfica**: Panel de estado con información de nivel, puntaje y vidas
- **Detección de colisiones**: Sistema preciso para paredes, fantasmas y objetos coleccionables

## Estructura de Archivos

```
pacmanProject/
├── .github/
│   └── workflows/
│       ├── ci-ct.yml          # Integración Continua y Pruebas
│       └── cd.yml             # Despliegue Continuo (GitHub Pages)
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Game.java      # Clase principal: inicializa la ventana del juego
│   │       ├── Board.java     # Lógica del juego, renderizado y gestión de niveles
│   │       ├── Pacman.java    # Lógica y renderizado de Pac-Man
│   │       ├── Ghost.java     # Lógica y renderizado de los fantasmas
│   │       └── Direction.java # Enum para las direcciones de movimiento
│   └── test/
│       └── java/
│           ├── GameTest.java      # Pruebas unitarias de Game
│           ├── BoardTest.java     # Pruebas unitarias de Board
│           ├── PacmanTest.java    # Pruebas unitarias de Pacman
│           ├── GhostTest.java     # Pruebas unitarias de Ghost
│           └── DirectionTest.java # Pruebas unitarias de Direction
├── pom.xml                    # Configuración de Maven
├── formatter-config.xml       # Configuración de formato de código
└── README.md                  # Este archivo
```

## Requisitos

- **Java 17** o superior
- **Maven 3.8+** para gestión de dependencias y construcción
- (Opcional) IDE como IntelliJ IDEA, Eclipse, VSCode, etc.

### Dependencias

- **JUnit 5** (5.10.1) - Framework de pruebas unitarias
- **Mockito** (5.8.0) - Framework para mocks en pruebas
- **JaCoCo** (0.8.11) - Análisis de cobertura de código

## Compilación y Ejecución

### Con Maven (Recomendado)

```bash
# Compilar el proyecto
mvn clean install

# Ejecutar las pruebas
mvn test

# Generar reporte de cobertura
mvn jacoco:report

# Verificar cobertura mínima (94%)
mvn verify

# Crear JAR ejecutable
mvn package

# Ejecutar el juego
java -jar target/pacman-project-1.0-SNAPSHOT.jar
```

### Formateo de código

```bash
# Formatear el código según las convenciones
mvn formatter:format
```

## Controles

- **←** Flecha izquierda: Mover a Pac-Man hacia la izquierda
- **→** Flecha derecha: Mover a Pac-Man hacia la derecha
- **↑** Flecha arriba: Mover a Pac-Man hacia arriba
- **↓** Flecha abajo: Mover a Pac-Man hacia abajo

## GitHub Actions

### CI/CT - Integración y Prueba Continua

El workflow `ci-ct.yml` se ejecuta automáticamente en cada push y pull request a las ramas `main` y `develop`:

1. **Checkout del código**: Clona el repositorio
2. **Configuración de Java 17**: Configura el entorno de ejecución
3. **Instalación de dependencias**: Ejecuta `mvn clean install`
4. **Ejecución de pruebas**: Ejecuta `mvn verify` con análisis de cobertura
5. **Generación de reporte JaCoCo**: Crea reporte de cobertura de código
6. **Publicación de artefactos**: Sube el reporte como artefacto (30 días de retención)
7. **Limpieza**: Elimina archivos temporales
8. **Notificación**: Reporta el estado del build (éxito/fallo)

**Requisito de cobertura**: El build falla si la cobertura es inferior al 94%

### CD - Despliegue Continuo

El workflow `cd.yml` se ejecuta automáticamente cuando el workflow CI/CT finaliza exitosamente:

1. **Construcción del JAR**: Genera el ejecutable del juego
2. **Extracción de metadatos**: Obtiene información del build (nombre, tamaño, fecha, commit)
3. **Creación de landing page**: Genera página HTML atractiva para descarga
4. **Generación de metadata JSON**: Crea archivo con información del build
5. **Despliegue en GitHub Pages**: Publica la página y el JAR
6. **Resumen del despliegue**: Proporciona información completa del deployment

**URL de descarga**: `https://dany0424.github.io/pacmanProject/`

## Pruebas Unitarias

El proyecto cuenta con una suite completa de pruebas unitarias que cubren:

- Todas las clases del juego (Board, Pacman, Ghost, Direction, Game)
- Lógica de movimiento y colisiones
- Sistema de puntuación y vidas
- Progresión de niveles
- Detección de condiciones de victoria y derrota

**Cobertura actual**: 94% (verificada automáticamente en cada build)

## Extensiones Futuras

- Mejorar la IA de los fantasmas con algoritmos de pathfinding
- Añadir power-ups clásicos (pastillas de poder para comer fantasmas)
- Implementar efectos de sonido y música
- Añadir más niveles y diseños de laberinto
- Implementar tabla de puntuaciones máximas (high scores)
- Añadir animaciones y efectos visuales mejorados

## Autor

Proyecto desarrollado por [paberlo].

---

¡Disfruta programando y jugando Pac-Man! 🎮👾

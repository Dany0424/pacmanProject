# Tests Unitarios - PacMan Project

Este proyecto ahora incluye una suite completa de tests unitarios con cobertura del 94% de código.

## 📊 Estadísticas de Cobertura

- **Total de tests**: 66
- **Cobertura de instrucciones**: 94%
- **Cobertura de ramas**: 71%

### Cobertura por Clase

| Clase | Instrucciones | Ramas | Tests |
|-------|--------------|--------|-------|
| Direction | 100% ✅ | N/A | 6 |
| Pacman | 100% ✅ | 81% | 20 |
| Ghost | 100% ✅ | 93% | 9 |
| Board | 94% ✅ | 63% | 29 |
| Board$PacmanKeyAdapter | 100% ✅ | N/A | - |
| Game | 0% * | N/A | 2 |

\* La clase `Game` es una clase GUI que no puede probarse en modo headless. Está excluida de los requisitos de cobertura.

## 🚀 Comandos Maven

### Ejecutar tests
```bash
mvn test
```

### Generar reporte de cobertura
```bash
mvn jacoco:report
```
El reporte HTML se genera en: `target/site/jacoco/index.html`

### Validar cobertura
```bash
mvn verify
```
Este comando falla si la cobertura es menor al 94% (excluyendo Game).

### Compilar proyecto
```bash
mvn compile
```

### Formatear código
```bash
mvn formatter:format
```

### Limpiar y ejecutar todo
```bash
mvn clean verify
```

## 📝 Estructura de Tests

```
src/test/java/
├── DirectionTest.java      # Tests para enum Direction
├── PacmanTest.java         # Tests para la clase Pacman
├── GhostTest.java          # Tests para la clase Ghost
├── BoardTest.java          # Tests para la clase Board
└── GameTest.java           # Tests básicos para Game (GUI)
```

## 🔧 Dependencias de Testing

- **JUnit 5.10.1**: Framework de testing
- **Mockito 5.8.0**: Framework de mocking
- **JaCoCo 0.8.11**: Herramienta de cobertura de código

## 📋 Convenciones de Testing

1. **Nomenclatura**: Cada clase de test termina en `Test` (ej: `PacmanTest`)
2. **Ubicación**: Tests en `src/test/java` replicando estructura de `src/main/java`
3. **Cobertura**: Se requiere mínimo 94% de cobertura de instrucciones
4. **Aislamiento**: Los tests son independientes y no comparten estado
5. **Mocking**: Se usa Mockito para simular dependencias (Board, Graphics, etc.)

## ✨ Características de los Tests

### DirectionTest
- Valida todos los valores del enum Direction
- Prueba los ángulos correctos para cada dirección
- Verifica métodos `values()` y `valueOf()`

### PacmanTest
- Tests de inicialización y posición
- Tests de puntuación y vidas
- Tests de movimiento en todas direcciones
- Tests de colisiones con paredes
- Tests de dibujo (con mocking)
- Tests de teclado

### GhostTest
- Tests de inicialización
- Tests de movimiento aleatorio
- Tests de reset a posición inicial
- Tests con diferentes colores
- Tests de colisiones
- Tests de dibujo

### BoardTest
- Tests de dimensiones del tablero
- Tests de detección de paredes
- Tests de colisión con puntos
- Tests de progresión de niveles
- Tests de key listeners
- Tests de game loop (actionPerformed)

### GameTest
- Tests básicos de inicialización
- Tests de jerarquía de clases (JFrame)
- Limitado por modo headless

## 🎯 Notas Importantes

1. **Modo Headless**: Los tests se ejecutan en modo headless (`-Djava.awt.headless=true`) para permitir tests en entornos CI/CD sin interfaz gráfica.

2. **Clase Game**: La clase `Game` no puede probarse completamente en modo headless ya que extiende JFrame y requiere un entorno gráfico. Por esto está excluida de los requisitos de cobertura.

3. **Cobertura de Ramas**: Mientras que la cobertura de instrucciones es del 94%, la cobertura de ramas es del 71%. Esto es aceptable ya que cubrir todas las ramas en código con GUI y lógica compleja puede requerir tests muy elaborados.

4. **Validación Automática**: El build falla automáticamente si la cobertura cae por debajo del 94% (excluyendo Game).

## 🔍 Ver Reporte de Cobertura

Después de ejecutar `mvn jacoco:report`, abre el archivo:
```
target/site/jacoco/index.html
```

Este reporte muestra:
- Cobertura por paquete
- Cobertura por clase
- Líneas cubiertas y no cubiertas (código fuente con colores)
- Ramas cubiertas y no cubiertas

## ✅ Estado del Build

Todos los tests pasan: ✅  
Cobertura cumplida: ✅  
Build Maven: SUCCESS ✅

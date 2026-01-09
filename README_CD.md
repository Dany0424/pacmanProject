# CD Workflow - Continuous Deployment

Este documento describe el workflow de Despliegue Continuo (CD) que publica automáticamente el juego PacMan en GitHub Pages.

## Descripción

El workflow `cd.yml` se ejecuta automáticamente cuando el workflow `ci-ct.yml` (Integración Continua) se completa exitosamente. Realiza las siguientes acciones:

1. **Construye el JAR**: Compila el proyecto y genera el archivo JAR ejecutable
2. **Crea una landing page**: Genera una página HTML atractiva con información del build
3. **Despliega en GitHub Pages**: Publica la página y el JAR para descarga pública

## Configuración Inicial

### 1. Habilitar GitHub Pages

Para que el workflow funcione correctamente, debes habilitar GitHub Pages en tu repositorio:

1. Ve a **Settings** → **Pages** en tu repositorio de GitHub
2. En **Source**, selecciona **GitHub Actions**
3. Guarda los cambios

### 2. Permisos del Workflow

El workflow ya tiene los permisos necesarios configurados:
- `contents: write` - Para acceder al código
- `pages: write` - Para desplegar en GitHub Pages
- `id-token: write` - Para autenticación

## Funcionamiento

### Trigger del Workflow

El workflow se activa automáticamente cuando:
- El workflow `CI/CT` se completa exitosamente
- En las ramas `main` o `develop`

```yaml
on:
  workflow_run:
    workflows: ["CI/CT"]
    types:
      - completed
    branches:
      - main
      - develop
```

### Pasos del Workflow

1. **Checkout código**: Descarga el código del repositorio
2. **Configurar Java 17**: Instala Java para compilar el proyecto
3. **Construir JAR**: Ejecuta `mvn clean package -DskipTests`
4. **Obtener información del artefacto**: Extrae metadatos (nombre, tamaño, fecha, commit)
5. **Crear landing page**: Genera `index.html` con diseño responsive
6. **Crear archivo de metadatos**: Genera `build-info.json` con información del build
7. **Configurar GitHub Pages**: Prepara el entorno de despliegue
8. **Subir artefactos**: Sube los archivos a GitHub Pages
9. **Desplegar**: Publica la página en GitHub Pages

## Landing Page

La landing page incluye:

- **Título y descripción** del juego
- **Información del build**:
  - Nombre del archivo JAR
  - Tamaño del archivo
  - Fecha de construcción
  - Hash del commit
  - Rama del repositorio
- **Botón de descarga** del JAR
- **Instrucciones de ejecución**:
  - Requisitos (Java 17+)
  - Comando para ejecutar el juego
- **Diseño moderno y responsive** con gradientes y animaciones

## Acceso a la Página

Una vez desplegado, la página estará disponible en:

```
https://<usuario>.github.io/<repositorio>/
```

Por ejemplo:
```
https://yourusername.github.io/yourrepository/
```

Para este proyecto específico:
```
https://Dany0424.github.io/pacmanProject/
```

## Ejecutar el Juego

Para ejecutar el juego descargado:

1. Asegúrate de tener Java 17 o superior instalado
2. Descarga el archivo JAR desde la landing page
3. Abre una terminal en la carpeta de descarga
4. Ejecuta:
   ```bash
   java -jar pacman-project-1.0-SNAPSHOT.jar
   ```

## Estructura de Archivos Desplegados

```
gh-pages/
├── index.html              # Landing page principal
├── build-info.json         # Metadatos del build
└── pacman-project-1.0-SNAPSHOT.jar  # Juego ejecutable
```

## Modificación del JAR Ejecutable

El archivo `pom.xml` incluye el plugin `maven-jar-plugin` para crear un JAR ejecutable:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <version>3.3.0</version>
    <configuration>
        <archive>
            <manifest>
                <mainClass>Game</mainClass>
                <addClasspath>true</addClasspath>
            </manifest>
        </archive>
    </configuration>
</plugin>
```

La clase principal es `Game`, que contiene el método `main()`.

## Personalización

### Cambiar el Diseño de la Landing Page

Puedes modificar el paso "Crear página de descarga" en `.github/workflows/cd.yml` para personalizar:
- Colores y estilos CSS
- Texto e instrucciones
- Estructura HTML

### Agregar Más Información al Build

Modifica el paso "Crear archivo de metadatos" para incluir información adicional en `build-info.json`.

## Troubleshooting

### El workflow no se ejecuta

- Verifica que GitHub Pages esté habilitado con "GitHub Actions" como fuente
- Asegúrate de que el workflow `CI/CT` se complete exitosamente
- Revisa los permisos del workflow en Settings → Actions

### La página no se muestra correctamente

- Espera unos minutos después del despliegue
- Verifica que no haya errores en el paso "Desplegar en GitHub Pages"
- Limpia la caché del navegador

### Error al construir el JAR

- Verifica que el código compile correctamente localmente
- Revisa que todas las dependencias estén disponibles
- Consulta los logs del workflow en la pestaña Actions

## Mejoras Futuras

Posibles mejoras al workflow:

- [ ] Versionar los JARs automáticamente
- [ ] Agregar changelog en la landing page
- [ ] Incluir capturas de pantalla del juego
- [ ] Publicar releases en GitHub
- [ ] Agregar estadísticas de descargas
- [ ] Soporte para múltiples versiones del JAR

## Referencias

- [GitHub Actions - workflow_run](https://docs.github.com/en/actions/using-workflows/events-that-trigger-workflows#workflow_run)
- [GitHub Pages with Actions](https://docs.github.com/en/pages/getting-started-with-github-pages/configuring-a-publishing-source-for-your-github-pages-site#publishing-with-a-custom-github-actions-workflow)
- [Maven JAR Plugin](https://maven.apache.org/plugins/maven-jar-plugin/)

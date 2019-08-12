# TONK

**Tank Online (Tonk)**

*Contains the back-end using Java and Spring WebSocket (among other technologies).*

## Build
- **Run** `gradlew clean build` to run the build
    - The build will fail when at least one test does not pass.

## Jacoco
- **Run** `gradlew build jacocoTestReport` to generate the JaCoCo test coverage reports.
- **Open** `build/reports/jacoco/test/html/index.html` of the module for which you want to view the report. 

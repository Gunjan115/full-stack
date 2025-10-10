Train Management Module (Spring Boot)
- Run: mvn spring-boot:run
- H2 console: http://localhost:8081/h2-console (JDBC URL: jdbc:h2:mem:traindb)
APIs:
- GET    /api/trains
- GET    /api/trains/search?source=...&destination=...
- GET    /api/trains/{id}
- POST   /api/trains
- PUT    /api/trains/{id}
- DELETE /api/trains/{id}

User Management Module (Spring Boot)
- Run: mvn spring-boot:run
- H2 console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:userdb)
APIs:
- POST /api/auth/register
- POST /api/auth/login  -> returns token
- GET  /api/auth/me    (X-Auth-Token header)
- PUT  /api/auth/me
- PUT  /api/auth/change-password

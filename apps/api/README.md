# 자취톡 API

Spring Boot 기반 백엔드 API입니다.

## 로컬 실행

루트 디렉터리에서 PostgreSQL을 실행합니다.

```bash
docker compose up -d postgres
```

API 서버를 실행합니다.

```bash
cd apps/api
mvn spring-boot:run
```

헬스체크:

```bash
curl http://localhost:8080/api/health
```

Swagger:

```txt
http://localhost:8080/swagger-ui.html
```

## 테스트

```bash
mvn test
```

## 현재 포함된 구성

- Spring Web
- Spring Security
- OAuth2 Client
- Spring Data JPA
- PostgreSQL Driver
- Flyway
- Spring Validation
- Spring Actuator
- springdoc-openapi

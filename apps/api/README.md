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

방 양도글 생성:

```bash
curl -X POST http://localhost:8080/api/room-handover-posts \
  -H "Content-Type: application/json" \
  -d '{
    "authorId": 1,
    "regionId": 1,
    "title": "신림역 근처 원룸 양도합니다",
    "deposit": 10000000,
    "monthlyRent": 600000,
    "maintenanceFee": 70000,
    "roomType": "ONE_ROOM",
    "description": "입주일 협의 가능합니다."
  }'
```

방 양도글 목록:

```bash
curl "http://localhost:8080/api/room-handover-posts?page=0&size=20"
```

방 양도글 상세:

```bash
curl http://localhost:8080/api/room-handover-posts/1
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

## 개발용 시드 데이터

`V3__insert_dev_seed_data.sql`에서 개발용 사용자와 지역을 넣습니다.

- 사용자: `authorId=1`, `authorId=2`
- 지역: `regionId=1` 신림동, `regionId=2` 상도동, `regionId=3` 서교동

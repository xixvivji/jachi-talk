# 백엔드 로컬 실행

## 필요 환경

- Java 17
- Maven
- Docker
- PostgreSQL

백엔드는 Spring Boot 3, Spring Data JPA, PostgreSQL, Flyway를 사용한다.

## DB 실행

루트 디렉터리에서 PostgreSQL 컨테이너를 실행한다.

```bash
docker compose up -d postgres
```

기본 접속 정보는 `application.yml` 기준으로 다음과 같다.

```txt
database: jachi_talk
username: jachi
password: jachi_local_password
```

환경변수로 덮어쓸 수 있다.

```txt
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
SERVER_PORT
```

## API 서버 실행

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

## 컴파일

```bash
cd apps/api
mvn -DskipTests compile
```

`JAVA_HOME`이 잡혀 있지 않으면 macOS에서 아래처럼 Java 17 경로를 지정해서 실행할 수 있다.

```bash
JAVA_HOME=$(/usr/libexec/java_home -v 17) mvn -DskipTests compile
```

## 테스트

```bash
cd apps/api
mvn test
```

## 개발용 시드 데이터

`V3__insert_dev_seed_data.sql`에서 개발용 사용자와 지역을 넣는다.

```txt
authorId=1
authorId=2
regionId=1 신림동
regionId=2 상도동
regionId=3 서교동
```

인증이 붙기 전까지 일부 API는 임시로 `authorId`를 요청 본문에서 받는다.


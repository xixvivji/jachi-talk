# Jachi Talk

Jachi Talk is a community platform for room handovers, neighborhood reviews, and data-backed housing decisions for renters.

자취톡은 방 양도, 자취 동네 후기, 공공데이터 기반 동네 정보를 함께 제공하는 커뮤니티 플랫폼입니다.

## Core Ideas

- 방 양도/계약 승계 게시글을 지역, 예산, 입주일 기준으로 탐색합니다.
- 동네별 커뮤니티에서 실제 거주 후기와 질문을 모읍니다.
- 공공데이터를 이용해 실거래가, 상권, 교통, 생활 편의 지표를 제공합니다.
- AI로 양도글 위험 요소, 동네 후기 요약, 글 작성 보조 기능을 제공합니다.
- 운영 로그, 외부 API 호출 로그, 사용자 행동 이벤트를 수집해 AWS 운영 경험을 쌓습니다.

## Tech Stack

- Frontend: Next.js, TypeScript, Tailwind CSS
- Backend: Java 21, Spring Boot 3
- Database: PostgreSQL
- ORM: Spring Data JPA
- Migration: Flyway
- Auth: Kakao OAuth, Naver OAuth
- Storage: Amazon S3
- Logs and Metrics: Amazon CloudWatch
- Deployment: EC2 or ECS after MVP

## Repository Layout

```txt
jachi-talk/
  apps/
    web/       # Next.js frontend
    api/       # Spring Boot backend
  docs/
    product/
    architecture/
  infra/
```

## MVP Scope

1. OAuth login with Kakao/Naver
2. Room handover post CRUD
3. Region-based community posts
4. Comments and reports
5. Basic public data integration
6. User event logs and external API logs
7. Admin dashboard for reports and operational metrics

## Documentation

- [Product Overview](docs/product/overview.md)
- [MVP Plan](docs/product/mvp.md)
- [Domain Model](docs/product/domain-model.md)
- [Public API Plan](docs/product/public-api-plan.md)
- [AWS Plan](docs/architecture/aws-plan.md)
- [Logging Plan](docs/architecture/logging-plan.md)

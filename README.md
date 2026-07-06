# 자취톡

자취톡은 방 양도, 자취 동네 후기, 공공데이터 기반 동네 정보를 함께 제공하는 커뮤니티 플랫폼입니다.

## 핵심 방향

- 방 양도/계약 승계 게시글을 지역, 예산, 입주일 기준으로 탐색합니다.
- 동네별 커뮤니티에서 실제 거주 후기와 질문을 모읍니다.
- 공공데이터를 이용해 실거래가, 상권, 교통, 생활 편의 지표를 제공합니다.
- AI로 양도글 위험 요소, 동네 후기 요약, 글 작성 보조 기능을 제공합니다.
- 운영 로그, 외부 API 호출 로그, 사용자 행동 이벤트를 수집해 AWS 운영 경험을 쌓습니다.

## 기술 스택

- 프론트엔드: Next.js, TypeScript, Tailwind CSS
- 백엔드: Java 17+, Spring Boot 3
- 데이터베이스: PostgreSQL
- ORM: Spring Data JPA
- 마이그레이션: Flyway
- 인증: 카카오 OAuth, 네이버 OAuth
- 이미지 저장소: Amazon S3
- 로그/지표: Amazon CloudWatch
- 배포: MVP 단계에서는 EC2, 이후 ECS 검토

## 저장소 구조

```txt
jachi-talk/
  apps/
    web/       # Next.js 프론트엔드
    api/       # Spring Boot 백엔드
  docs/
    product/
    architecture/
    database/
    development/
    api/
    domain/
  infra/
```

## MVP 범위

1. 카카오/네이버 OAuth 로그인
2. 방 양도 게시글 CRUD
3. 동네별 커뮤니티 게시글
4. 댓글과 신고
5. 기본 공공데이터 연동
6. 사용자 이벤트 로그와 외부 API 호출 로그
7. 신고/운영 지표를 확인하는 관리자 화면

## 문서

- [제품 개요](docs/product/overview.md)
- [MVP 계획](docs/product/mvp.md)
- [도메인 모델](docs/product/domain-model.md)
- [공공 API 계획](docs/product/public-api-plan.md)
- [AWS 계획](docs/architecture/aws-plan.md)
- [로그 수집 계획](docs/architecture/logging-plan.md)
- [DB 설계 의사결정](docs/database/schema-decisions.md)
- [Git 작업 전략](docs/development/git-workflow.md)
- [백엔드 로컬 실행](docs/development/backend-local-run.md)
- [방 양도글 API](docs/api/room-handover-posts.md)
- [커뮤니티 게시글 API](docs/api/community-posts.md)
- [공통 에러 응답](docs/api/error-response.md)
- [방 양도글 도메인 기록](docs/domain/room-handover.md)
- [커뮤니티 도메인 기록](docs/domain/community.md)

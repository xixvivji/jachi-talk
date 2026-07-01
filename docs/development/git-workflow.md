# Git 작업 전략

## 브랜치 구조

자취톡은 `main`, `develop`, `feature/*` 흐름으로 작업한다.

```txt
main
└── develop
    ├── feature/backend-room-handover-api
    ├── feature/backend-community-api
    ├── feature/frontend-room-list
    └── feature/infra-aws
```

## 브랜치 역할

### main

`main`은 배포 가능한 안정 버전이다.

- 직접 커밋하지 않는다.
- 충분히 검증된 `develop`만 PR로 merge한다.
- 운영 배포나 릴리즈 기준으로 사용한다.

### develop

`develop`은 다음 배포 후보를 모으는 통합 브랜치다.

- 기능 브랜치의 PR 대상이다.
- 백엔드, 프론트엔드, 인프라 작업이 모이는 브랜치다.
- 기능이 어느 정도 모이면 `main`으로 PR을 보낸다.

### feature/*

`feature/*`는 실제 기능 작업 브랜치다.

- 기능 하나 또는 명확한 작업 단위 하나만 담는다.
- 작업 완료 후 `develop`으로 PR을 보낸다.
- PR에서 CodeRabbit AI 리뷰와 사람 리뷰를 확인한다.

## 기본 작업 흐름

처음 `develop`을 만들 때:

```bash
git checkout main
git pull origin main
git checkout -b develop
git push origin develop
```

기능 작업을 시작할 때:

```bash
git checkout develop
git pull origin develop
git checkout -b feature/backend-community-api
```

작업 중 커밋:

```bash
git add .
git commit -m "feat: add community post create api"
```

기능 작업 완료 후:

```bash
git push origin feature/backend-community-api
```

PR 방향:

```txt
feature/backend-community-api -> develop
```

배포 후보를 `main`에 반영할 때:

```txt
develop -> main
```

## 브랜치 이름 규칙

백엔드:

```txt
feature/backend-room-handover-api
feature/backend-community-api
feature/backend-comment-api
feature/backend-report-api
feature/backend-auth-oauth
```

프론트엔드:

```txt
feature/frontend-room-list
feature/frontend-room-detail
feature/frontend-login
feature/frontend-community
```

인프라:

```txt
feature/infra-docker
feature/infra-aws
feature/infra-ci
```

## 커밋 메시지 규칙

Conventional Commits 형식을 기본으로 사용한다.

```txt
feat: add room handover post update api
fix: hide deleted room handover posts
docs: add database schema decisions
chore: add flyway migration
test: add room handover post service test
refactor: simplify room handover post query
```

## PR 본문 템플릿

PR에는 단순 변경 목록보다 문제 정의와 의사결정을 함께 남긴다.

```md
## 문제 정의

## 대안 및 트레이드오프

## 구현 내용

## 고민한 점 / 막혔던 부분

## 추후 개선

## 검증

## AI 리뷰 / 도구 활용
```

## CodeRabbit AI 사용 방식

PR을 열면 CodeRabbit AI 리뷰를 확인한다.

- 기능 안정성, 예외 처리, 쿼리 구조, 테스트 관련 피드백은 우선 반영한다.
- MVP 범위를 벗어나는 구조 개선 제안은 추후 개선 항목으로 분리한다.
- 반영하지 않은 리뷰는 이유를 PR 코멘트나 본문에 남긴다.


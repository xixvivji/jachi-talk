# AWS 계획

## MVP 배포

- EC2: 프론트엔드와 백엔드를 Docker Compose로 실행
- RDS PostgreSQL: 기본 데이터베이스
- S3: 방 양도 게시글 이미지 저장
- CloudFront: 이후 이미지/정적 파일 전달에 사용
- CloudWatch Logs: 애플리케이션 로그
- CloudWatch Metrics: 사용자 정의 지표

## 이후 배포 구조

- ECS Fargate: 프론트엔드/백엔드 컨테이너 실행
- EventBridge: 공공데이터 주기 수집
- Lambda: 작은 배치 작업
- SQS: 이미지 처리, AI 요약 같은 비동기 작업
- OpenSearch: PostgreSQL 검색만으로 부족할 때 도입

## 환경

- local
- dev
- prod

## 비밀값

비밀값은 저장소에 올리지 않습니다.

- OAuth 클라이언트 ID와 시크릿
- 데이터베이스 비밀번호
- S3 접근 권한 또는 IAM 역할
- OpenAI API 키
- 공공 API 서비스 키

## 초기 Docker 서비스

- web
- api
- postgres

클라우드 배포는 `web`, `api`, RDS PostgreSQL만으로 시작할 수 있습니다.

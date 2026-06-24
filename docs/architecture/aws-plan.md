# AWS Plan

## MVP Deployment

- EC2: Docker Compose host for frontend and backend
- RDS PostgreSQL: primary database
- S3: room post images
- CloudFront: image/static asset delivery later
- CloudWatch Logs: application logs
- CloudWatch Metrics: custom metrics

## Later Deployment

- ECS Fargate for frontend/backend containers
- EventBridge for scheduled public data collection
- Lambda for small batch jobs
- SQS for async jobs such as image processing and AI summary
- OpenSearch for full-text search if PostgreSQL search is not enough

## Environments

- local
- dev
- prod

## Secrets

Store secrets outside the repository.

- OAuth client IDs and secrets
- Database password
- S3 credentials or IAM role
- OpenAI API key
- Public API service keys

## Initial Docker Services

- web
- api
- postgres

Cloud deployment can start with only `web`, `api`, and RDS PostgreSQL.

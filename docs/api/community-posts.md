# 커뮤니티 게시글 API

## 개요

커뮤니티 게시글 API는 지역 기반 동네 커뮤니티 기능을 제공한다.

현재 지원하는 기능:

- 커뮤니티 게시글 작성
- 커뮤니티 게시글 목록 조회
- 커뮤니티 게시글 상세 조회
- 커뮤니티 게시글 수정
- 커뮤니티 게시글 숨김 삭제

인증이 붙기 전까지 작성 API는 임시로 `authorId`를 요청 본문에서 받는다.

## 카테고리

```txt
QUESTION
REVIEW
NOISE
SAFETY
MAINTENANCE_FEE
FACILITY
RESTAURANT
FREE_TALK
```

## POST /api/community-posts

커뮤니티 게시글을 작성한다.

요청 예시:

```json
{
  "authorId": 1,
  "regionId": 1,
  "category": "QUESTION",
  "title": "신림동 밤에 조용한 편인가요?",
  "content": "신림역 근처로 이사 고민 중인데 소음이 어떤지 궁금합니다."
}
```

필수 값:

- `authorId`
- `regionId`
- `category`
- `title`
- `content`

## GET /api/community-posts

커뮤니티 게시글 목록을 조회한다.

기본적으로 숨김 처리되지 않은 글만 반환한다.

쿼리 파라미터:

- `regionId`: 지역 ID
- `category`: 커뮤니티 카테고리
- `page`: 페이지 번호
- `size`: 페이지 크기

예시:

```bash
curl "http://localhost:8080/api/community-posts?regionId=1&category=QUESTION&page=0&size=20"
```

## GET /api/community-posts/{id}

커뮤니티 게시글 상세를 조회한다.

숨김 처리된 글은 조회되지 않고 `NOT_FOUND`를 반환한다.

예시:

```bash
curl http://localhost:8080/api/community-posts/1
```

## PATCH /api/community-posts/{id}

커뮤니티 게시글을 수정한다.

PATCH 방식이므로 요청에 포함된 필드만 변경하고, `null`이거나 누락된 필드는 기존 값을 유지한다.

요청 예시:

```json
{
  "category": "REVIEW",
  "title": "신림동 거주 후기",
  "content": "역 근처는 편하지만 밤 시간대 소음은 조금 있습니다."
}
```

## DELETE /api/community-posts/{id}

커뮤니티 게시글을 숨김 처리한다.

실제 row를 삭제하지 않고 `hidden = true`로 변경한다.

응답:

```txt
204 No Content
```

## 현재 한계

- 인증이 없어 작성자 권한 검증이 없다.
- 작성 API에서 임시로 `authorId`를 받는다.
- 좋아요, 조회수, 이미지 기능이 없다.
- 숨김 처리에는 삭제자, 삭제 시각, 삭제 사유가 아직 없다.

## 추후 개선

- 로그인 사용자 기준 작성자 자동 지정
- 작성자 본인만 수정/삭제 가능하도록 권한 검증
- 댓글 API 연동
- 신고 API 연동
- 조회수와 좋아요 기능 추가
- `deleted_at`, `deleted_by`, `delete_reason` 추가

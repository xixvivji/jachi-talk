# 방 양도글 API

## 개요

방 양도글 API는 MVP의 핵심 기능이다.

현재 지원하는 기능:

- 방 양도글 작성
- 방 양도글 목록 조회
- 방 양도글 상세 조회
- 방 양도글 수정
- 방 양도글 상태 변경
- 방 양도글 숨김 삭제

인증이 붙기 전까지 작성 API는 임시로 `authorId`를 요청 본문에서 받는다.

## 상태값

```txt
OPEN        모집중
IN_PROGRESS 대화중
CLOSED      완료
```

## 방 유형

```txt
ONE_ROOM
OFFICETEL
VILLA
APARTMENT
SHARE_HOUSE
ETC
```

## 임대인 동의 상태

```txt
NOT_CHECKED
CHECKING
APPROVED
REJECTED
```

## POST /api/room-handover-posts

방 양도글을 작성한다.

요청 예시:

```json
{
  "authorId": 1,
  "regionId": 1,
  "title": "신림역 근처 원룸 양도합니다",
  "deposit": 10000000,
  "monthlyRent": 600000,
  "maintenanceFee": 70000,
  "availableFrom": "2026-07-20",
  "contractUntil": "2027-02-28",
  "nearestStation": "신림역",
  "roomType": "ONE_ROOM",
  "floor": "3층",
  "petAllowed": false,
  "landlordConsentStatus": "NOT_CHECKED",
  "description": "입주일 협의 가능합니다."
}
```

필수 값:

- `authorId`
- `regionId`
- `title`
- `deposit`
- `monthlyRent`
- `roomType`
- `description`

응답:

```json
{
  "id": 1,
  "authorId": 1,
  "authorNickname": "테스트유저1",
  "regionId": 1,
  "regionName": "서울특별시 관악구 신림동",
  "title": "신림역 근처 원룸 양도합니다",
  "deposit": 10000000,
  "monthlyRent": 600000,
  "maintenanceFee": 70000,
  "availableFrom": "2026-07-20",
  "contractUntil": "2027-02-28",
  "nearestStation": "신림역",
  "roomType": "ONE_ROOM",
  "floor": "3층",
  "petAllowed": false,
  "landlordConsentStatus": "NOT_CHECKED",
  "description": "입주일 협의 가능합니다.",
  "status": "OPEN",
  "createdAt": "2026-07-01T10:00:00Z",
  "updatedAt": "2026-07-01T10:00:00Z"
}
```

## GET /api/room-handover-posts

방 양도글 목록을 조회한다.

기본적으로 숨김 처리되지 않은 `OPEN` 상태의 글만 반환한다.

쿼리 파라미터:

- `regionId`: 지역 ID
- `minRent`: 최소 월세
- `maxRent`: 최대 월세
- `page`: 페이지 번호
- `size`: 페이지 크기

예시:

```bash
curl "http://localhost:8080/api/room-handover-posts?regionId=1&minRent=300000&maxRent=800000&page=0&size=20"
```

## GET /api/room-handover-posts/{id}

방 양도글 상세를 조회한다.

숨김 처리된 글은 조회되지 않고 `NOT_FOUND`를 반환한다.

예시:

```bash
curl http://localhost:8080/api/room-handover-posts/1
```

## PATCH /api/room-handover-posts/{id}

방 양도글 내용을 수정한다.

PATCH 방식이므로 요청에 포함된 필드만 변경하고, `null`이거나 누락된 필드는 기존 값을 유지한다.

요청 예시:

```json
{
  "title": "신림역 도보 5분 원룸 양도합니다",
  "monthlyRent": 580000,
  "maintenanceFee": 70000,
  "description": "월세 조정했습니다."
}
```

## PATCH /api/room-handover-posts/{id}/status

방 양도글 상태를 변경한다.

요청 예시:

```json
{
  "status": "IN_PROGRESS"
}
```

사용 가능한 값:

```txt
OPEN
IN_PROGRESS
CLOSED
```

## DELETE /api/room-handover-posts/{id}

방 양도글을 숨김 처리한다.

실제 row를 삭제하지 않고 `hidden = true`로 변경한다.

응답:

```txt
204 No Content
```

## 현재 한계

- 인증이 없어 작성자 권한 검증이 없다.
- 작성 API에서 임시로 `authorId`를 받는다.
- 이미지 업로드가 없다.
- 목록 검색은 지역과 월세 범위만 지원한다.
- 숨김 처리에는 삭제자, 삭제 시각, 삭제 사유가 아직 없다.

## 추후 개선

- 로그인 사용자 기준 작성자 자동 지정
- 작성자 본인만 수정/삭제/상태 변경 가능하도록 권한 검증
- 이미지 업로드와 S3 연동
- 방 옵션 테이블 또는 JSON 필드 추가
- `deleted_at`, `deleted_by`, `delete_reason` 추가
- 검색 조건이 늘어나면 QueryDSL 도입

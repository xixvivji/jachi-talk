# 공통 에러 응답

## 응답 형식

API 에러는 `ApiErrorResponse` 형식을 사용한다.

```json
{
  "code": "NOT_FOUND",
  "message": "방 양도글을 찾을 수 없습니다. id=1",
  "timestamp": "2026-07-01T10:00:00Z"
}
```

필드:

- `code`: 에러 코드
- `message`: 사용자 또는 개발자가 확인할 수 있는 에러 메시지
- `timestamp`: 에러 응답 생성 시각

## NOT_FOUND

리소스를 찾지 못했을 때 사용한다.

예시:

```json
{
  "code": "NOT_FOUND",
  "message": "지역을 찾을 수 없습니다. id=999",
  "timestamp": "2026-07-01T10:00:00Z"
}
```

현재 사용 사례:

- 존재하지 않는 사용자
- 존재하지 않는 지역
- 존재하지 않는 방 양도글
- 숨김 처리된 방 양도글

## VALIDATION_ERROR

요청 값 검증에 실패했을 때 사용한다.

예시:

```json
{
  "code": "VALIDATION_ERROR",
  "message": "title: 공백일 수 없습니다",
  "timestamp": "2026-07-01T10:00:00Z"
}
```

현재 검증 예시:

- 필수 값 누락
- 빈 제목 또는 빈 본문
- 보증금, 월세, 관리비 음수 입력
- 제목 길이 초과
- 가까운 역, 층수 길이 초과

## 추후 개선

- enum 변환 실패 응답 정리
- 인증/인가 에러 코드 추가
- 중복 신고 같은 비즈니스 에러 코드 추가
- 필드별 validation error 목록 응답 검토


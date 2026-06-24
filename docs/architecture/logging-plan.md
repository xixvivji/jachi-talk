# 로그 수집 계획

## 애플리케이션 로그

백엔드 로그에는 다음 값을 포함합니다.

- request_id
- user_id, 확인 가능한 경우
- method
- path
- status_code
- latency_ms
- error_code
- exception_name

다음 값은 로그에 남기지 않습니다.

- 액세스 토큰
- 리프레시 토큰
- OAuth 제공자 시크릿
- 정확한 개인 주소
- 전화번호
- 계약서
- 신분증

## 이벤트 로그

초기 이벤트 이름:

- user_signed_in
- room_post_created
- room_post_viewed
- room_post_filtered
- handover_inquiry_clicked
- community_post_created
- comment_created
- report_submitted
- region_viewed
- public_api_called
- public_api_failed
- ai_risk_check_requested

## 외부 API 로그

기록할 값:

- provider
- endpoint
- region_code
- status_code
- latency_ms
- success
- error_message
- requested_at

## 관리자 지표

화면에 보여줄 값:

- 일간 활성 사용자
- 신규 방 양도 게시글 수
- 신규 커뮤니티 게시글 수
- 신고 수
- 많이 조회된 지역
- 외부 API 실패율
- 외부 API 평균 응답 시간
- AI 요청 수와 예상 비용

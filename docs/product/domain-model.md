# 도메인 모델

## 주요 엔티티

### 사용자 (`User`)

- id
- email
- nickname
- profile_image_url
- role
- created_at
- updated_at

### OAuth 계정 (`OAuthAccount`)

- id
- user_id
- provider: kakao, naver
- provider_user_id
- created_at

### 지역 (`Region`)

- id
- sido
- sigungu
- eupmyeondong
- legal_dong_code
- center_latitude
- center_longitude

### 방 양도 게시글 (`RoomHandoverPost`)

- id
- author_id
- region_id
- title
- deposit
- monthly_rent
- maintenance_fee
- available_from
- contract_until
- nearest_station
- room_type
- floor
- pet_allowed
- landlord_consent_status
- description
- status
- created_at
- updated_at

### 커뮤니티 게시글 (`CommunityPost`)

- id
- author_id
- region_id
- category
- title
- content
- created_at
- updated_at

### 댓글 (`Comment`)

- id
- author_id
- post_type
- post_id
- content
- created_at
- updated_at

### 신고 (`Report`)

- id
- reporter_id
- target_type
- target_id
- reason
- status
- created_at

### 공공데이터 스냅샷 (`PublicDataSnapshot`)

- id
- region_id
- source
- data_type
- payload_json
- collected_at

### 이벤트 로그 (`EventLog`)

- id
- user_id
- event_name
- properties_json
- request_id
- created_at

## 초기 커뮤니티 카테고리

커뮤니티 카테고리:

- 질문
- 후기
- 소음
- 치안
- 관리비
- 생활시설
- 맛집
- 자유게시판

# Domain Model

## Main Entities

### User

- id
- email
- nickname
- profile_image_url
- role
- created_at
- updated_at

### OAuthAccount

- id
- user_id
- provider: kakao, naver
- provider_user_id
- created_at

### Region

- id
- sido
- sigungu
- eupmyeondong
- legal_dong_code
- center_latitude
- center_longitude

### RoomHandoverPost

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

### CommunityPost

- id
- author_id
- region_id
- category
- title
- content
- created_at
- updated_at

### Comment

- id
- author_id
- post_type
- post_id
- content
- created_at
- updated_at

### Report

- id
- reporter_id
- target_type
- target_id
- reason
- status
- created_at

### PublicDataSnapshot

- id
- region_id
- source
- data_type
- payload_json
- collected_at

### EventLog

- id
- user_id
- event_name
- properties_json
- request_id
- created_at

## Initial Categories

Community categories:

- question
- review
- noise
- safety
- maintenance_fee
- facility
- restaurant
- free_talk

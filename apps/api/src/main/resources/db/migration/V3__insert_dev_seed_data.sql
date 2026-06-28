INSERT INTO users (id, email, nickname, profile_image_url, role, created_at, updated_at)
VALUES
    (1, 'dev1@jachi-talk.local', '개발용사용자1', NULL, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'dev2@jachi-talk.local', '개발용사용자2', NULL, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO regions (id, sido, sigungu, eupmyeondong, legal_dong_code, center_latitude, center_longitude)
VALUES
    (1, '서울특별시', '관악구', '신림동', '1162010200', 37.4875000, 126.9291000),
    (2, '서울특별시', '동작구', '상도동', '1159010200', 37.4992000, 126.9410000),
    (3, '서울특별시', '마포구', '서교동', '1144012000', 37.5552000, 126.9237000);

ALTER TABLE users ALTER COLUMN id RESTART WITH 3;
ALTER TABLE regions ALTER COLUMN id RESTART WITH 4;

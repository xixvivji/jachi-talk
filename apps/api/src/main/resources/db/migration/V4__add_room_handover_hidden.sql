ALTER TABLE room_handover_posts
    ADD COLUMN hidden BOOLEAN NOT NULL DEFAULT FALSE;

CREATE INDEX idx_room_handover_posts_hidden_status ON room_handover_posts (hidden, status);

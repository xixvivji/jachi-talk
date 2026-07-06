ALTER TABLE community_posts
    ADD COLUMN hidden BOOLEAN NOT NULL DEFAULT FALSE;

CREATE INDEX idx_community_posts_hidden_region_category ON community_posts (hidden, region_id, category);

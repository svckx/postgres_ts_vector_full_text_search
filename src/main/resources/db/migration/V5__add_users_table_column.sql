ALTER TABLE users ADD COLUMN status VARCHAR(10) NOT NULL DEFAULT 'ACTIVE';

UPDATE users SET status = 'ACTIVE' WHERE id < 40 and id > 80;

UPDATE users SET status = 'INACTIVE' WHERE id > 40 and id < 80;
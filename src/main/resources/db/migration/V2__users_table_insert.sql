-- Insert roles
INSERT INTO roles (role_name) VALUES ('ADMIN');
INSERT INTO roles (role_name) VALUES ('USER');
INSERT INTO roles (role_name) VALUES ('MANAGER');

-- Insert users
INSERT INTO users (name, email, role_id) VALUES ('Alice', 'alice@example.com', 1);
INSERT INTO users (name, email, role_id) VALUES ('Bob', 'bob@example.com', 2);
INSERT INTO users (name, email, role_id) VALUES ('Charlie', 'charlie@example.com', 3);
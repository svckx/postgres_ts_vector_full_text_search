ALTER TABLE users ADD COLUMN search_vector tsvector;

UPDATE users u
SET search_vector =
    to_tsvector('english', u.id::text || ' ' || u.name || ' ' || u.email || ' ' || coalesce(r.role_name,''))
FROM roles r
WHERE u.role_id = r.id;

CREATE INDEX idx_users_search_vector ON users USING GIN(search_vector);

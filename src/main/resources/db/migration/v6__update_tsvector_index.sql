-- Trigger function to update search_vector automatically
CREATE OR REPLACE FUNCTION update_users_search_vector() RETURNS trigger AS $$
BEGIN
    NEW.search_vector := to_tsvector('english',
        NEW.id::text || ' ' || NEW.name || ' ' || NEW.email || ' ' ||
        coalesce((SELECT role_name FROM roles WHERE id = NEW.role_id), ''));
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger to update search_vector on INSERT or UPDATE
CREATE TRIGGER tsvectorupdate BEFORE INSERT OR UPDATE
ON users FOR EACH ROW EXECUTE FUNCTION update_users_search_vector();
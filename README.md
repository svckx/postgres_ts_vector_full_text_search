# postgres_ts_vector_full_text_search
Example microservice for spring boot and ts vector search with postgres

## How ts vector index looks like
![users_db.png](users_db.png)

## Advantages
1. Native & Integrated 
   1. Built into PostgreSQL — no extra service like Elasticsearch, Solr, or OpenSearch. 
   2. No need to sync/search external indexes; queries happen inside your DB.

2. Powerful Search Features 
   1. Supports lexeme normalization (stemming, lowercasing, removing stop words). 
   2. Language aware (english, french, etc.) → "running" and "run" match. 
   3. Can handle boolean operators, phrase search, ranking (ts_rank), and highlighting (ts_headline).

3. Efficient with Indexes 
   1. Combined with GIN indexes → search is very fast, even on large text columns.
   ```shell
    CREATE INDEX idx_users_search
    ON users
    USING GIN (to_tsvector('english', name || ' ' || bio));
   ```

4. Flexible Schema 
   1. We can concatenate multiple columns (title, description, tags) into one tsvector. 
   2. Can be stored as a materialized/generated column for performance.

5. No Vendor Lock-in 
   1. Pure SQL, portable within PostgreSQL. 
   2. Avoids operational overhead of managing an external search engine.

## Disadvantages
1. Limited vs Dedicated Search Engines 
   1. Doesn’t support fuzzy matching, typo tolerance, advanced relevance scoring, synonyms, etc. as well as Elasticsearch. 
   2. No built-in faceted search, aggregations, or ML-based ranking.

2. Indexing Overhead 
   1. GIN indexes on tsvector can be large and increase write cost. 
   2. Inserts/updates slow down because indexes must be updated.

3. Manual Maintenance (if materialized)
   1. If you store tsvector in a column (materialized approach), you must keep it in sync:
   2. Via triggers in Postgres, or 
   3. Update manually (risk of stale data).

4. Language Limitations 
   1. Tokenization & stemming are language-specific. 
   2. Works well for English & supported languages, but struggles with complex morphology languages (e.g., Chinese, Japanese).

5. Scaling Limits 
   1. Fine for millions of rows, but for huge datasets (100M+) with heavy search load, Elasticsearch/OpenSearch often scale better (distributed, search-first design).
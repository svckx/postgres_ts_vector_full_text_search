package com.svick.brz.spring.repository;

import com.svick.brz.spring.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM users u WHERE u.search_vector @@ to_tsquery(:query)",
            countQuery = "SELECT count(*) FROM users u WHERE u.search_vector @@ to_tsquery(:query)",
            nativeQuery = true)
    Page<UserEntity> search(@Param("query") String query, Pageable pageable);

//    @Query(
//            value = """
//        SELECT u.*\s
//        FROM users u
//        LEFT JOIN roles r ON u.role_id = r.id
//        WHERE\s
//            to_tsvector('english', u.id::text || ' ' || u.name || ' ' || u.email || ' ' || r.role_name)\s
//            @@ to_tsquery(:query)
//       \s""",
//            countQuery = """
//        SELECT count(*)\s
//        FROM users u
//        LEFT JOIN roles r ON u.role_id = r.id
//        WHERE\s
//            to_tsvector('english', u.id::text || ' ' || u.name || ' ' || u.email || ' ' || r.role_name)\s
//            @@ to_tsquery(:query)
//       \s""",
//            nativeQuery = true
//    )
//    Page<UserEntity> search(@Param("query") String query, Pageable pageable);

}

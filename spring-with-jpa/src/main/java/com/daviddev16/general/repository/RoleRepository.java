package com.daviddev16.general.repository;

import com.daviddev16.general.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository
        extends JpaRepository<Role, Long> {

    @Query(value = "SELECT * FROM roles WHERE name = :name", nativeQuery = true)
    Optional<Role> findByName(@Param("name") String name);

}

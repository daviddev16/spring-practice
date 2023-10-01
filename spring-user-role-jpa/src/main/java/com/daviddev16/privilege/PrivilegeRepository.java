package com.daviddev16.privilege;

import com.daviddev16.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findPrivilegeByName(String name);

}

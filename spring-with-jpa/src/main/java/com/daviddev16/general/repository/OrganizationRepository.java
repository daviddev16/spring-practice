package com.daviddev16.general.repository;

import com.daviddev16.general.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository
        extends JpaRepository<Organization, Long> { }

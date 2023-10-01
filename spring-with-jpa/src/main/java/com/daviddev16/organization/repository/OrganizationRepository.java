package com.daviddev16.organization.repository;

import com.daviddev16.organization.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository
        extends JpaRepository<Organization, Long> { }

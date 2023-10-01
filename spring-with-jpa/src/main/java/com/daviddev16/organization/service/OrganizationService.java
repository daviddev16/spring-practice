package com.daviddev16.organization.service;

import com.daviddev16.organization.model.Organization;
import com.daviddev16.organization.payload.OrganizationCreateRequest;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {

    Organization createOrganization( OrganizationCreateRequest createRequest );

    Optional<Organization> findById( Long id );

    List<Organization> getAllOrganizations();
}

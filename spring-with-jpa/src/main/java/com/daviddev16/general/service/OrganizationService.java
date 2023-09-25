package com.daviddev16.general.service;

import com.daviddev16.general.model.Organization;
import com.daviddev16.general.payload.OrganizationCreateRequest;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {

    Organization createOrganization( OrganizationCreateRequest createRequest );

    Optional<Organization> findById( Long id );

    List<Organization> getAllOrganizations();
}

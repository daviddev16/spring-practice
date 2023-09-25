package com.daviddev16.general.service.impl;

import com.daviddev16.exception.base.GenericNotFoundException;
import com.daviddev16.general.model.Organization;
import com.daviddev16.general.model.User;
import com.daviddev16.general.payload.OrganizationCreateRequest;
import com.daviddev16.general.repository.OrganizationRepository;
import com.daviddev16.general.service.OrganizationService;
import com.daviddev16.general.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final UserService userService;
    private final OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, UserService userService) {
        this.organizationRepository = organizationRepository;
        this.userService = userService;
    }

    @Override
    public Organization createOrganization(OrganizationCreateRequest createRequest) {

        Optional<User> optionalOwnerUser = userService.findById(createRequest.getOwnerId());
        if (optionalOwnerUser.isEmpty())
            throw new GenericNotFoundException("Não é possível criar uma organização sem usuário master.");

        Organization organization = new Organization();
        BeanUtils.copyProperties(createRequest, organization, "ownerId");
        organization.setOwner(optionalOwnerUser.get());

        return organizationRepository.save(organization);

    }

    @Override
    public Optional<Organization> findById(Long organizationId) {
        return organizationRepository.findById(organizationId);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }
}

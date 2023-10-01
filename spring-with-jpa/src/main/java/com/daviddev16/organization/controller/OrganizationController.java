package com.daviddev16.organization.controller;

import com.daviddev16.organization.model.Organization;
import com.daviddev16.organization.payload.OrganizationCreateRequest;
import com.daviddev16.organization.service.OrganizationService;
import com.daviddev16.organization.response.OrganizationGenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/api/v1/organizations" )
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    public ResponseEntity<List<OrganizationGenericResponse>> listAllOrganizations() {

        List<OrganizationGenericResponse> organizationResponses = organizationService
                .getAllOrganizations()
                .stream()
                .map(OrganizationGenericResponse::of)
                .toList();

        return new ResponseEntity<>(organizationResponses, HttpStatus.OK);

    }

    @GetMapping("/id/{organizationId}")
    public ResponseEntity<?> findOrganizationById(@PathVariable("organizationId")
                                                                                Long organizationId) {

        Optional<Organization> optionalOrganization = organizationService.findById(organizationId);

        if (optionalOrganization.isEmpty())
            return new ResponseEntity<>("Não foi possível localizar uma " +
                    "organização com esse id.", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(OrganizationGenericResponse
                .of(optionalOrganization.get()), HttpStatus.FOUND);

    }

    @PostMapping
    public ResponseEntity<OrganizationGenericResponse> createOrganization(@RequestBody
                                                                              OrganizationCreateRequest createRequest) {
        Organization organization =  organizationService.createOrganization(createRequest);
        return new ResponseEntity<>(OrganizationGenericResponse.of(organization), HttpStatus.CREATED);

    }

}

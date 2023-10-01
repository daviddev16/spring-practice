package com.daviddev16.organization.response;

import com.daviddev16.organization.model.Organization;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationGenericResponse {

    private Long id;
    private String suffixName;
    private String displayName;
    private Long ownerId;

    public static OrganizationGenericResponse of(Organization organization) {
        return OrganizationGenericResponse.builder()
                .displayName(organization.getDisplayName())
                .ownerId(organization.getOwner().getId())
                .suffixName(organization.getSuffixName())
                .id(organization.getId())
                .build();
    }

}

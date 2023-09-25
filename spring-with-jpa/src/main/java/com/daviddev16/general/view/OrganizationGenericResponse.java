package com.daviddev16.general.view;

import com.daviddev16.general.model.Organization;
import com.daviddev16.general.model.Role;
import com.daviddev16.general.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

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

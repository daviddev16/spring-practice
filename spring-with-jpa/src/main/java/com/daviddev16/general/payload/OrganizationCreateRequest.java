package com.daviddev16.general.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrganizationCreateRequest {

    private String suffixName;
    private String displayName;
    private Long ownerId;

}

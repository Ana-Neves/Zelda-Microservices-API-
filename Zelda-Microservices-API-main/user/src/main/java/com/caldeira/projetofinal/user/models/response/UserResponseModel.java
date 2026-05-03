package com.caldeira.projetofinal.user.models.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseModel {
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDateTime creationDate;
}

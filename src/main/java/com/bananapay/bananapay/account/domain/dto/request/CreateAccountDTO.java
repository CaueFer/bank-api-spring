package com.bananapay.bananapay.account.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountDTO {
    @NotBlank()
    private String ownerName;

    @NotBlank()
    @Size(min = 11, max = 11)
    private String ownerCpf;

    @NotBlank()
    private String password;
}

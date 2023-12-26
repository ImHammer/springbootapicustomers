package com.hammerdev.customersapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInputDTO
{
    @NotBlank
    @Size(min = 6, max = 16)
    @Pattern(regexp = "^\\w+$")
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3, max = 16)
    @Pattern(regexp = "^\\w+$")
    private String firstName;

    @NotBlank
    @Size(max = 16)
    @Pattern(flags = Flag.UNIX_LINES, regexp = "^\\w+$")
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^[\\d]{2}\\/[\\d]{2}\\/[\\d]{4}$", flags = Flag.UNIX_LINES, message = "deve corresponder a dd/mm/YYYY")
    private String birthDate;
}

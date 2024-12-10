package com.solarflare.ecommerce.dto;

import com.solarflare.ecommerce.entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
         String id,
         @NotNull(message = "Customer firstname is required")
         String firstname,
         @NotNull(message = "Customer lastname is required")
         String lastname,
         @NotNull(message = "Customer email is required")
         @Email(message = "Not a valid email")
         String email,
         Address address
) {
}

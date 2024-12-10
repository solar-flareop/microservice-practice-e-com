package com.solarflare.ecommerce.dto;

import com.solarflare.ecommerce.entities.Address;

public record CustomerResponse(
         String id,
         String firstname,
         String lastname,
         String email,
         Address address
) {
}

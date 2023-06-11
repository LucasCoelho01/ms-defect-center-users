package com.lucas.msdefectcenterusers.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRecordDTO(@NotBlank String name, @NotBlank String email, @NotBlank String password) {
}

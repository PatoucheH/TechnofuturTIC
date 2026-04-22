package be.bstorm.tf2026javaspringmvc.pl.models.home;

import jakarta.validation.constraints.NotBlank;

public record PersonForm(
        @NotBlank(message = "Connard") String firstname,
        @NotBlank String lastname
) {
}

package be.spring_flavyan.validation;

import be.spring_flavyan.dtos.request.AddressRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ZipValidator implements ConstraintValidator<ValidZip, AddressRequest> {

    @Override
    public boolean isValid(AddressRequest address, ConstraintValidatorContext context) {
        if (address == null || address.getCountry() == null || address.getCountry().isBlank()) {
            return true;
        }
        if (address.getZip() == null || address.getZip().isBlank()) {
            return true;
        }

        String country = address.getCountry().trim().toLowerCase();
        String zip = address.getZip().trim();

        if (isBelgium(country) && !zip.matches("\\d{4}")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Belgian ZIP code must be exactly 4 digits")
                    .addPropertyNode("zip")
                    .addConstraintViolation();
            return false;
        }

        if (isFrance(country) && !zip.matches("\\d{5}")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("French ZIP code must be exactly 5 digits")
                    .addPropertyNode("zip")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

    private boolean isBelgium(String country) {
        return country.equals("belgique") || country.equals("belgium") || country.equals("be");
    }

    private boolean isFrance(String country) {
        return country.equals("france") || country.equals("fr");
    }
}

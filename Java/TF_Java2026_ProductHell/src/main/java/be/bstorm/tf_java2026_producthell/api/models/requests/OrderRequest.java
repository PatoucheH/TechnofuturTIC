package be.bstorm.tf_java2026_producthell.api.models.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequest(
        @NotNull Integer userId,
        @NotEmpty List<@Valid OrderLineRequest> lines
) {
}

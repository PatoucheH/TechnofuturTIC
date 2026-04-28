package be.bstorm.tf_java2026_producthell.api.models;

import java.util.List;

public record CustomPage<T>(
        List<T> content,
        long currentPage,
        long totalPages,
        long count
) {
}
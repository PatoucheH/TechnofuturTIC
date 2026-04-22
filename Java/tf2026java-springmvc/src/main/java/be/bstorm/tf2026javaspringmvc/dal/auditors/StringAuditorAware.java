package be.bstorm.tf2026javaspringmvc.dal.auditors;


import lombok.Builder;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Builder
public class StringAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("server");
    }
}

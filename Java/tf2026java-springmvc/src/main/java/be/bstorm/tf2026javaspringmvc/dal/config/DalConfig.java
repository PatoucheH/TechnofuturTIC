package be.bstorm.tf2026javaspringmvc.dal.config;

import be.bstorm.tf2026javaspringmvc.dal.auditors.StringAuditorAware;
import be.bstorm.tf2026javaspringmvc.dal.embbed.Address;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class DalConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return StringAuditorAware.builder()
                .build();
    }
}

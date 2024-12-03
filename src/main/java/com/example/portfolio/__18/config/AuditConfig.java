package com.example.portfolio.__18.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
//JAP의 Auditing기능을 활성화
@EnableJpaAuditing
public class AuditConfig {
    @Bean
    //등록자와 수정자를 처리해주는 AuditorAware을 빈으로 등록
    public AuditorAware<String> auditorProvider(){
        return new AuditorAwareImpl();
    }
}

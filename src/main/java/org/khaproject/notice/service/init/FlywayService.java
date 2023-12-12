package org.khaproject.notice.service.init;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Service
public class FlywayService {
    private final Flyway flyway;
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @PostConstruct
    public void migrate() {
        if("local".equals(activeProfile)) {
            flyway.migrate();
        }
        log.info("========== [FLYWAY] Migrate End ==========");
    }
}

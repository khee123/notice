package org.khaproject.notice.service.init;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Service
public class FlywayService {
    private final Flyway flyway;

    @PostConstruct
    public void migrate() {
        flyway.migrate();
        log.info("========== [FLYWAY] Migrate End ==========");
    }
}

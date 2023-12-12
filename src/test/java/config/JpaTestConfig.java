package config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@EnableJpaAuditing
@TestConfiguration
public class JpaTestConfig {

    @PersistenceContext
    EntityManager em;

    @Bean
    JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}

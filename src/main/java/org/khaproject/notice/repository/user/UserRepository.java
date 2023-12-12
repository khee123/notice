package org.khaproject.notice.repository.user;

import org.khaproject.notice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

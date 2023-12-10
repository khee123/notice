package org.khaproject.notice.repository.file;

import org.khaproject.notice.model.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long>, FileRepositoryCustom {
}

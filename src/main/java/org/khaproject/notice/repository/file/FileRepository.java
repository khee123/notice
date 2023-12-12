package org.khaproject.notice.repository.file;

import org.khaproject.notice.model.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    @Transactional
    @Modifying
    @Query("update from File f set f.delYn = 'Y' where f.menuCd = :menuCd and f.useIdx = :ntceIdx")
    void deleteFile(String menuCd, Long ntceIdx);

    @Transactional
    @Modifying
    @Query("update from File f set f.delYn = 'N', f.tempYn = 'N', f.useIdx = :ntceIdx, f.menuCd = :menuCd where f.fileIdx in :fileIdxList")
    void updateFile(String menuCd, Long ntceIdx, List<Long> fileIdxList);
}

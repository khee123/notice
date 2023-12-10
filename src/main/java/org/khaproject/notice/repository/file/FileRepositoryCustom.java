package org.khaproject.notice.repository.file;

import java.util.List;

public interface FileRepositoryCustom {
    void deleteFile(String menuCd, Long ntceIdx);
    void updateFile(String menuCd, Long ntceIdx, List<Long> fileIdxList);
}

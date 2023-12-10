package org.khaproject.notice.service.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.khaproject.notice.NoticeApplication;
import org.khaproject.notice.model.entity.File;
import org.khaproject.notice.model.web.common.response.UploadFileRes;
import org.khaproject.notice.repository.file.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;

    @Value("${spring.upload.path}")
    private String uploadPath;

    /**
     * 파일 업로드
     */
    public List<UploadFileRes> uploadFile(List<MultipartFile> files) {
        List<UploadFileRes> fileList = new ArrayList<>();

        for(MultipartFile file : files) {
            String fileNm = RandomStringUtils.randomAlphanumeric(10);

            try{
                Path directoryPath = Paths.get(uploadPath);
                if(Files.notExists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                }

                Path filePath = Paths.get(directoryPath.toString(), file.getOriginalFilename());
                file.transferTo(filePath);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            File saveFile = new File();
            saveFile.setFileNm(fileNm);
            saveFile.setOrgnFileNm(file.getOriginalFilename());
            saveFile.setFileSize(file.getSize());
            saveFile.setFilePath(uploadPath);
            saveFile.setTempYn("Y");
            saveFile.setDelYn("N");
            saveFile.setRegUser(NoticeApplication.userId);

            File saveInfo = fileRepository.save(saveFile);

            fileList.add(new UploadFileRes(saveInfo));
        }
        return fileList;
    }
}

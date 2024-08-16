package flow.assignment.file.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 파일 업로드 Service
 * 파일 업로드, 파일 저장, 폴더 생성
 */
@Service
public class FileUploadService {

    @Value("${project.upload.path}")
    private String uploadPath;

    /**
     * 파일을 업로드하는 메서드
     * @param file
     * @return String
     */
    public String uploadFile(MultipartFile file) {
        return saveFile(file);
    }

    /**
     * 파일을 특정 경로에 저장하는 메서드
     * @param file
     * @return String
     */
    private String saveFile(MultipartFile file) {
        String originFileName = file.getOriginalFilename();
        String folderPath = makeFolder();

        String savedFileName = folderPath + File.separator + UUID.randomUUID() + "_" + originFileName;
        File savedFile = new File(savedFileName);

        try {
            file.transferTo(savedFile);
            return savedFileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 파일을 저장할 폴더를 생성하는 메서드
     * @return String
     */
    private String makeFolder() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = date.replace("/", File.separator);
        folderPath = uploadPath + File.separator + folderPath;
        File uploadFolderPath = new File(folderPath);

        if (uploadFolderPath.exists() == false) {
            uploadFolderPath.mkdirs();
        }

        return folderPath;
    }
}

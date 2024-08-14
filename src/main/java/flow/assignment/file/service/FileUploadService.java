package flow.assignment.file.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Slf4j
public class FileUploadService {

    @Value("${project.upload.path}")
    private String uploadPath;

    public String uploadFile(MultipartFile file) {
        return saveFile(file);
    }

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

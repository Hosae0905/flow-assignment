package flow.assignment.file.service;

import flow.assignment.common.BaseResponse;
import flow.assignment.file.model.entity.UploadFile;
import flow.assignment.file.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {
    private final UploadFileRepository fileRepository;
    private final FileUploadService fileUploadService;

    public BaseResponse<String> uploadFile(MultipartFile file) {
        String uploadFileInfo = fileUploadService.uploadFile(file);
        String extension = uploadFileInfo.substring(uploadFileInfo.lastIndexOf('.') + 1);
        String fileName = uploadFileInfo.substring(uploadFileInfo.lastIndexOf('\\') + 1, uploadFileInfo.lastIndexOf('.'));
        String path = uploadFileInfo.substring(0, uploadFileInfo.lastIndexOf('\\') + 1);

        UploadFile uploadedFile = fileRepository.save(UploadFile.buildUploadFile(fileName, extension, path));

        return BaseResponse.successResponse("UPLOAD_001", true, "파일 업로드 성공", uploadedFile.getFileName());
    }


}

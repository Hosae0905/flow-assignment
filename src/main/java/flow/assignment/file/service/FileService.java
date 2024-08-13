package flow.assignment.file.service;

import flow.assignment.file.model.entity.Extension;
import flow.assignment.file.model.entity.UploadFile;
import flow.assignment.file.model.request.PostExtensionCheckedReq;
import flow.assignment.file.repository.ExtensionRepository;
import flow.assignment.file.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {
    private final UploadFileRepository fileRepository;
    private final ExtensionRepository extensionRepository;

    public Object uploadFile(MultipartFile file) {
        String[] fileInfo = file.getOriginalFilename().split("\\.");
        fileRepository.save(UploadFile.buildUploadFile(fileInfo[0], fileInfo[1]));
        return true;
    }

    public Object restrictExtension(PostExtensionCheckedReq postExtensionCheckedReq) {
        Extension extension = extensionRepository.findByExtension(postExtensionCheckedReq.getExtension());
        extension.setStatus(true);
        extensionRepository.save(extension);
        return true;
    }
}

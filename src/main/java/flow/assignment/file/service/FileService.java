package flow.assignment.file.service;

import flow.assignment.file.model.entity.Extension;
import flow.assignment.file.model.entity.UploadFile;
import flow.assignment.file.model.request.PostExtensionCheckedReq;
import flow.assignment.file.model.request.PostExtensionUnCheckedReq;
import flow.assignment.file.model.response.GetExtensionListRes;
import flow.assignment.file.repository.ExtensionRepository;
import flow.assignment.file.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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

    public Object openExtension(PostExtensionUnCheckedReq postExtensionUnCheckedReq) {
        Extension extension = extensionRepository.findByExtension(postExtensionUnCheckedReq.getExtension());
        extension.setStatus(false);
        extensionRepository.save(extension);
        return true;
    }

    public Object getExtensionList() {
        List<Extension> extensions = extensionRepository.findAll();
        ArrayList<GetExtensionListRes> extensionList = new ArrayList<>();

        for (Extension extension : extensions) {
            extensionList.add(GetExtensionListRes.buildExtensionList(extension.getExtension(), extension.getStatus()));
        }

        return extensionList;
    }
}

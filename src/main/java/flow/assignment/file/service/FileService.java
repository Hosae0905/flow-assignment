package flow.assignment.file.service;

import flow.assignment.file.model.entity.CustomExtension;
import flow.assignment.file.model.entity.Extension;
import flow.assignment.file.model.entity.UploadFile;
import flow.assignment.file.model.request.PostAddExtensionReq;
import flow.assignment.file.model.request.PostExtensionCheckedReq;
import flow.assignment.file.model.request.PostExtensionUnCheckedReq;
import flow.assignment.file.model.response.GetCustomExtensionListRes;
import flow.assignment.file.model.response.GetExtensionListRes;
import flow.assignment.file.repository.CustomExtensionRepository;
import flow.assignment.file.repository.ExtensionRepository;
import flow.assignment.file.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {
    private final UploadFileRepository fileRepository;
    private final ExtensionRepository extensionRepository;
    private final CustomExtensionRepository customExtensionRepository;

    public Object uploadFile(MultipartFile file) {
        String[] fileInfo = file.getOriginalFilename().split("\\.");

        if (checkDefaultExtension(fileInfo[1]) && checkCustomExtension(fileInfo[1])) {
            fileRepository.save(UploadFile.buildUploadFile(fileInfo[0], fileInfo[1]));
            return true;
        } else {
            return false;
        }
    }

    public Object restrictExtension(PostExtensionCheckedReq postExtensionCheckedReq) {
        Optional<Extension> extension = extensionRepository.findByExtension(postExtensionCheckedReq.getExtension());
        if (extension.isPresent()) {
            extension.get().setStatus(true);
            extensionRepository.save(extension.get());
            return true;
        } else {
            return false;
        }
    }

    public Object openExtension(PostExtensionUnCheckedReq postExtensionUnCheckedReq) {
        Optional<Extension> extension = extensionRepository.findByExtension(postExtensionUnCheckedReq.getExtension());
        if (extension.isPresent()) {
            extension.get().setStatus(false);
            extensionRepository.save(extension.get());
            return true;
        } else {
            return false;
        }
    }

    public Object getExtensionList() {
        List<Extension> extensions = extensionRepository.findAll();
        ArrayList<GetExtensionListRes> extensionList = new ArrayList<>();

        for (Extension extension : extensions) {
            extensionList.add(GetExtensionListRes.buildExtensionList(extension.getExtension(), extension.getStatus()));
        }

        return extensionList;
    }

    public Object addCustomExtension(PostAddExtensionReq postAddExtensionReq) {
        customExtensionRepository.save(CustomExtension.buildCustomExtension(postAddExtensionReq.getExtension()));
        return true;
    }

    public Object getCustomExtensionList() {
        List<CustomExtension> customExtensions = customExtensionRepository.findAll();
        ArrayList<GetCustomExtensionListRes> customExtensionList = new ArrayList<>();

        for (CustomExtension customExtension : customExtensions) {
            customExtensionList.add(GetCustomExtensionListRes.buildCustomExtensionList(customExtension.getExtension()));
        }

        return customExtensionList;
    }

    private Boolean checkDefaultExtension(String extension) {
        Optional<Extension> extensionInfo = extensionRepository.findByExtension(extension);
        if (extensionInfo.isPresent()) {
            if (extensionInfo.get().getStatus()) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private Boolean checkCustomExtension(String extension) {
        Optional<CustomExtension> customExtension = customExtensionRepository.findByExtension(extension);
        if (customExtension.isPresent()) {
            return false;
        } else {
            return true;
        }
    }
}

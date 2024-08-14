package flow.assignment.file.service;

import flow.assignment.common.BaseResponse;
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
            UploadFile uploadedFile = fileRepository.save(UploadFile.buildUploadFile(fileInfo[0], fileInfo[1]));
            return BaseResponse.successResponse("UPLOAD_001", true, "파일 업로드 성공", uploadedFile.getFileName());
        } else {
            return false;
        }
    }

    public Object restrictExtension(PostExtensionCheckedReq postExtensionCheckedReq) {
        Optional<Extension> extension = extensionRepository.findByExtension(postExtensionCheckedReq.getExtension());
        if (extension.isPresent()) {
            extension.get().setStatus(true);
            Extension extensionInfo = extensionRepository.save(extension.get());
            return BaseResponse.successResponse("EXTENSION_001", true, "고정 확장자 설정 완료", extensionInfo.getExtension());
        } else {
            return false;
        }
    }

    public Object openExtension(PostExtensionUnCheckedReq postExtensionUnCheckedReq) {
        Optional<Extension> extension = extensionRepository.findByExtension(postExtensionUnCheckedReq.getExtension());
        if (extension.isPresent()) {
            extension.get().setStatus(false);
            Extension extensionInfo = extensionRepository.save(extension.get());
            return BaseResponse.successResponse("EXTENSION_002", true, "고정 확장자 설정 해제", extensionInfo.getExtension());
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
        return BaseResponse.successResponse("EXTENSION_003", true, "고정 확장자 목록을 정상적으로 불러왔습니다.", extensionList);
    }

    public Object addCustomExtension(PostAddExtensionReq postAddExtensionReq) {
        CustomExtension customExtensionInfo =
                customExtensionRepository.save(CustomExtension.buildCustomExtension(postAddExtensionReq.getExtension()));
        return BaseResponse.successResponse("CUSTOM_001", true, "커스텀 확장자 추가 완료", customExtensionInfo.getExtension());
    }

    public Object getCustomExtensionList() {
        List<CustomExtension> customExtensions = customExtensionRepository.findAll();
        ArrayList<GetCustomExtensionListRes> customExtensionList = new ArrayList<>();

        for (CustomExtension customExtension : customExtensions) {
            customExtensionList.add(GetCustomExtensionListRes.buildCustomExtensionList(customExtension.getExtension()));
        }

        return BaseResponse.successResponse("CUSTOM_002", true, "커스텀 확장자 목록을 정상적으로 불러왔습니다.", customExtensionList);
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

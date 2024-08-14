package flow.assignment.file.service;

import flow.assignment.common.BaseResponse;
import flow.assignment.file.model.entity.Extension;
import flow.assignment.file.model.entity.UploadFile;
import flow.assignment.file.model.request.PostAddExtensionReq;
import flow.assignment.file.model.request.PostExtensionCheckedReq;
import flow.assignment.file.model.request.PostExtensionUnCheckedReq;
import flow.assignment.file.model.response.GetCustomExtensionListRes;
import flow.assignment.file.model.response.GetExtensionListRes;
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
    private final FileUploadService fileUploadService;

    public BaseResponse<String> uploadFile(MultipartFile file) {
        String uploadFileInfo = fileUploadService.uploadFile(file);
        String extension = uploadFileInfo.substring(uploadFileInfo.lastIndexOf('.') + 1);
        String fileName = uploadFileInfo.substring(uploadFileInfo.lastIndexOf('\\') + 1, uploadFileInfo.lastIndexOf('.'));
        String path = uploadFileInfo.substring(0, uploadFileInfo.lastIndexOf('\\') + 1);

        UploadFile uploadedFile = fileRepository.save(UploadFile.buildUploadFile(fileName, extension, path));

        return BaseResponse.successResponse("UPLOAD_001", true, "파일 업로드 성공", uploadedFile.getFileName());
    }

    public BaseResponse<String> restrictExtension(PostExtensionCheckedReq postExtensionCheckedReq) {
        if (postExtensionCheckedReq.getOption().equals("default")) {
            Optional<Extension> extension = extensionRepository.findByExtension(postExtensionCheckedReq.getExtension());
            if (extension.isPresent()) {
                extension.get().setStatus(true);
                Extension extensionInfo = extensionRepository.save(extension.get());
                return BaseResponse.successResponse("EXTENSION_001", true, "고정 확장자 설정 완료", extensionInfo.getExtension());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public BaseResponse<String> openExtension(PostExtensionUnCheckedReq postExtensionUnCheckedReq) {
        if (postExtensionUnCheckedReq.getOption().equals("default")) {
            Optional<Extension> extension = extensionRepository.findByExtension(postExtensionUnCheckedReq.getExtension());
            if (extension.isPresent()) {
                extension.get().setStatus(false);
                Extension extensionInfo = extensionRepository.save(extension.get());
                return BaseResponse.successResponse("EXTENSION_002", true, "고정 확장자 설정 해제", extensionInfo.getExtension());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public BaseResponse<ArrayList<GetExtensionListRes>> getDefaultExtensionList(String option) {
        List<Extension> extensions = extensionRepository.findAllByOption(option);
        ArrayList<GetExtensionListRes> extensionList = new ArrayList<>();

        for (Extension extension : extensions) {
            extensionList.add(GetExtensionListRes.buildExtensionList(extension.getExtension(), extension.getStatus()));
        }
        return BaseResponse.successResponse("EXTENSION_003", true, "고정 확장자 목록을 정상적으로 불러왔습니다.", extensionList);
    }

    public BaseResponse<String> addCustomExtension(PostAddExtensionReq postAddExtensionReq) {
        if (postAddExtensionReq.getOption().equals("custom")) {
            Extension customExtension =
                    extensionRepository.save(Extension.buildCustonExtension(postAddExtensionReq.getExtension(), postAddExtensionReq.getOption()));
            return BaseResponse.successResponse("CUSTOM_001", true, "커스텀 확장자 추가 완료", customExtension.getExtension());
        } else {
            return null;
        }
    }

    public BaseResponse<ArrayList<GetCustomExtensionListRes>> getCustomExtensionList(String option) {
        List<Extension> customExtensions = extensionRepository.findAllByOption(option);
        ArrayList<GetCustomExtensionListRes> customExtensionList = new ArrayList<>();

        for (Extension customExtension : customExtensions) {
            customExtensionList.add(GetCustomExtensionListRes.buildCustomExtensionList(customExtension.getExtension()));
        }

        return BaseResponse.successResponse("CUSTOM_002", true, "커스텀 확장자 목록을 정상적으로 불러왔습니다.", customExtensionList);
    }
}

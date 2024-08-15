package flow.assignment.file.service;

import flow.assignment.common.BaseResponse;
import flow.assignment.common.error.ErrorCode;
import flow.assignment.common.error.exception.ExtensionException;
import flow.assignment.file.model.entity.Extension;
import flow.assignment.file.model.entity.UploadFile;
import flow.assignment.file.model.request.DeleteCustomExtensionReq;
import flow.assignment.file.model.request.PostAddExtensionReq;
import flow.assignment.file.model.request.PostExtensionCheckedReq;
import flow.assignment.file.model.request.PostExtensionUnCheckedReq;
import flow.assignment.file.model.response.GetCustomExtensionListRes;
import flow.assignment.file.model.response.GetExtensionListRes;
import flow.assignment.file.repository.ExtensionRepository;
import flow.assignment.file.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public BaseResponse<String> restrictExtension(PostExtensionCheckedReq postExtensionCheckedReq) {
        if (postExtensionCheckedReq.getOption().equals("default")) {
            Extension extension = extensionRepository.findByExtension(postExtensionCheckedReq.getExtension()).orElseThrow(() ->
                    new ExtensionException(ErrorCode.NOT_FOUND_EXTENSION, "존재하지 않는 확장자입니다."));
            extension.setStatus(true);
            Extension extensionInfo = extensionRepository.save(extension);
            return BaseResponse.successResponse("EXTENSION_001", true, "고정 확장자 설정 완료", extensionInfo.getExtension());

        } else {
            throw new ExtensionException(ErrorCode.NOT_MATCH_OPTION, "확장자의 옵션이 잘못되었습니다.");
        }
    }

    @Transactional
    public BaseResponse<String> openExtension(PostExtensionUnCheckedReq postExtensionUnCheckedReq) {
        if (postExtensionUnCheckedReq.getOption().equals("default")) {
            Extension extension = extensionRepository.findByExtension(postExtensionUnCheckedReq.getExtension()).orElseThrow(() ->
                    new ExtensionException(ErrorCode.NOT_FOUND_EXTENSION, "존재하지 않는 확장자입니다."));
            extension.setStatus(false);
            Extension extensionInfo = extensionRepository.save(extension);
            return BaseResponse.successResponse("EXTENSION_002", true, "고정 확장자 설정 해제", extensionInfo.getExtension());
        } else {
            throw new ExtensionException(ErrorCode.NOT_MATCH_OPTION, "확장자의 옵션이 잘못되었습니다.");
        }
    }

    public BaseResponse<ArrayList<GetExtensionListRes>> getDefaultExtensionList(String option) {
        if (option.equals("default")) {
            List<Extension> extensions = extensionRepository.findAllByOption(option);
            ArrayList<GetExtensionListRes> extensionList = new ArrayList<>();

            for (Extension extension : extensions) {
                extensionList.add(GetExtensionListRes.buildExtensionList(extension.getExtension(), extension.getStatus()));
            }
            return BaseResponse.successResponse("EXTENSION_003", true, "고정 확장자 목록을 정상적으로 불러왔습니다.", extensionList);
        } else {
            throw new ExtensionException(ErrorCode.NOT_MATCH_OPTION, "확장자의 옵션이 잘못되었습니다.");
        }
    }

    @Transactional
    public BaseResponse<String> addCustomExtension(PostAddExtensionReq postAddExtensionReq) {
        if (postAddExtensionReq.getOption().equals("custom")) {
            extensionRepository.findByExtension(postAddExtensionReq.getExtension()).ifPresent(customExtension -> {
                throw new ExtensionException(ErrorCode.CONFLICT_EXTENSION, "중복된" + customExtension.getExtension() + "은 차단할 수 없습니다.");
            });
            Extension customExtension =
                    extensionRepository.save(Extension.buildCustonExtension(postAddExtensionReq.getExtension(), postAddExtensionReq.getOption()));
            return BaseResponse.successResponse("CUSTOM_001", true, "커스텀 확장자 추가 완료", customExtension.getExtension());
        } else {
            throw new ExtensionException(ErrorCode.NOT_MATCH_OPTION, "확장자의 옵션이 잘못되었습니다.");
        }
    }

    public BaseResponse<ArrayList<GetCustomExtensionListRes>> getCustomExtensionList(String option) {
        if (option.equals("custom")) {
            List<Extension> customExtensions = extensionRepository.findAllByOption(option);
            ArrayList<GetCustomExtensionListRes> customExtensionList = new ArrayList<>();

            for (Extension customExtension : customExtensions) {
                customExtensionList.add(GetCustomExtensionListRes.buildCustomExtensionList(customExtension.getExtension()));
            }

            return BaseResponse.successResponse("CUSTOM_002", true, "커스텀 확장자 목록을 정상적으로 불러왔습니다.", customExtensionList);
        } else {
            throw new ExtensionException(ErrorCode.NOT_MATCH_OPTION, "확장자의 옵션이 잘못되었습니다.");
        }
    }

    @Transactional
    public Object removeCustomExtension(DeleteCustomExtensionReq deleteCustomExtensionReq) {
        if (deleteCustomExtensionReq.getOption().equals("custom")) {
            Extension extension = extensionRepository.findByExtension(deleteCustomExtensionReq.getExtension()).orElseThrow(() ->
                    new ExtensionException(ErrorCode.NOT_FOUND_EXTENSION, "존재하지 않는 확장자입니다."));
            extensionRepository.deleteById(extension.getExtensionIdx());
            return BaseResponse.successResponse("CUSTOM_003", true, "커스텀 확장자를 삭제하였습니다.",
                    deleteCustomExtensionReq.getExtension());
        } else {
            throw new ExtensionException(ErrorCode.NOT_MATCH_OPTION, "확장자의 옵션이 잘못되었습니다.");
        }
    }
}

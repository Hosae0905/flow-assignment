package flow.assignment.extension.service;

import flow.assignment.common.BaseResponse;
import flow.assignment.common.error.ErrorCode;
import flow.assignment.common.error.exception.ExtensionException;
import flow.assignment.extension.model.entity.Extension;
import flow.assignment.extension.repository.ExtensionRepository;
import flow.assignment.extension.model.request.DeleteCustomExtensionReq;
import flow.assignment.extension.model.request.PostAddExtensionReq;
import flow.assignment.extension.model.request.PostExtensionCheckedReq;
import flow.assignment.extension.model.request.PostExtensionUnCheckedReq;
import flow.assignment.extension.model.response.GetCustomExtensionListRes;
import flow.assignment.extension.model.response.GetExtensionListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 확장자 관련 Service
 * 고정 확장자 차단, 차단 해제, 목록 조회
 * 커스텀 확장자 추가, 삭제, 목록 조회
 */

@Service
@RequiredArgsConstructor
public class ExtensionService {

    private final ExtensionRepository extensionRepository;

    /**
     * 고정 확장자를 차단하는 메서드
     * @param postExtensionCheckedReq
     * @return BaseResponse<String>
     */
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

    /**
     * 고정 확장자 차단을 해제하는 메서드
     * @param postExtensionUnCheckedReq
     * @return BaseResponse<String>
     */
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

    /**
     * 고정 확장자 목록을 불러오는 메서드
     * @param option
     * @return BaseResponse<ArrayList<GetExtensionListRes>>
     */
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

    /**
     * 커스텀 확장자를 추가하는 메서드
     * @param postAddExtensionReq
     * @return BaseResponse<String>
     */
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

    /**
     * 커스텀 확장자 목록을 불러오는 메서드
     * @param option
     * @return BaseResponse<ArrayList<GetCustomExtensionListRes>>
     */
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

    /**
     * 커스텀 확장자를 삭제하는 메서드
     * @param deleteCustomExtensionReq
     * @return BaseResponse<String>
     */
    @Transactional
    public BaseResponse<String> removeCustomExtension(DeleteCustomExtensionReq deleteCustomExtensionReq) {
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

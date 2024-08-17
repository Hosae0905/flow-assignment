package flow.assignment.interceptor;

import flow.assignment.common.filetype.FileSignature;
import flow.assignment.common.filetype.GeneralFile;
import flow.assignment.common.filetype.MimeType;
import flow.assignment.common.error.ErrorCode;
import flow.assignment.common.error.exception.InterceptorException;
import flow.assignment.extension.model.entity.Extension;
import flow.assignment.extension.repository.ExtensionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Optional;

/**
 * 확장자 관련 Interceptor
 */
@Component
@RequiredArgsConstructor
public class ExtensionHandlerInterceptor implements HandlerInterceptor {

    private final ExtensionRepository extensionRepository;

    /**
     * 파일 데이터의 확장자를 처리하기 위한 preHandle 메서드
     * @param request
     * @param response
     * @param handler
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Collection<Part> result = request.getParts();
        try {
            for (Part part : result) {
                String contentType = part.getContentType();
                String extension = mimeTypeFilter(contentType);
                if (isGeneralFile(extension)) {
                    return extensionFilter(extension);
                } else {
                    String fileSignature = convertByteToSignature(part.getInputStream());
                    if (fileSignatureFilter(extension, fileSignature)) {
                        return extensionFilter(extension);
                    } else {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 요청으로 받은 파일의 확장자를 DB에 조회하여 해당 확장자가 차단되어있는지 필터링하는 메서드
     * @param extension
     * @return Boolean
     */
    private Boolean extensionFilter(String extension) {
        Optional<Extension> extensionInfo = extensionRepository.findByExtension(extension);
        if (extensionInfo.isPresent()) {
            Extension getExtension = extensionInfo.get();
            if (!getExtension.getStatus()) {
                return true;
            } else {
                throw new InterceptorException(ErrorCode.INVALID_EXTENSION, "해당 파일은 업로드할 수 없습니다.");
            }
        } else {
            return true;
        }
    }

    /**
     * 요청으로 받은 파일의 Content-Type의 MimeType을 확인하는 메서드
     * @param mimeType
     * @return String
     */
    private String mimeTypeFilter(String mimeType) {
        String validMimeType = MimeType.isValidMimeType(mimeType);
        if (validMimeType == null) {
            throw new InterceptorException(ErrorCode.NOT_FOUND_MIMETYPE, "파일의 형식이 올바르지 않습니다.");
        } else {
            return validMimeType;
        }
    }

    /**
     * 요청으로 받은 파일의 확장자와 시그니처 정보를 필터링해주는 메서드
     * @param extension
     * @param fileSignature
     * @return Boolean
     */
    private Boolean fileSignatureFilter(String extension, String fileSignature) {
        Boolean signature = FileSignature.isEqualsSignature(extension, fileSignature);
        if (!signature) {
            throw new InterceptorException(ErrorCode.INVALID_FILE_SIGNATURE, "유효하지 않은 파일입니다.");
        } else {
            return true;
        }
    }

    /**
     * 요청으로 받은 파일의 확장자를 통해 일반 텍스트 파일인지 확인하는 메서드
     * @param extension
     * @return Boolean
     */
    private Boolean isGeneralFile(String extension) {
        Boolean generalFile = GeneralFile.isEqualsGeneralFile(extension);
        if (generalFile == null) {
            throw new InterceptorException(ErrorCode.INVALID_FILE_SIGNATURE, "유효하지 않은 파일입니다.");
        } else {
            return true;
        }
    }

    /**
     * 요청으로 받은 파일을 읽어 byte 값을 16진수로 변환해 해당 파일의 시그니처 값을 구하는 메서드
     * @param inputStream
     * @return String
     * @throws IOException
     */
    private String convertByteToSignature(InputStream inputStream) throws IOException {
        byte[] bytes = inputStream.readNBytes(2);
        String signature = "";
        for (int i = 0; i < bytes.length; i++) {
            signature += String.format("%02x", bytes[i]);
        }

        return signature;
    }
}

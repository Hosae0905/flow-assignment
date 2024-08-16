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

@Component
@RequiredArgsConstructor
public class ExtensionHandlerInterceptor implements HandlerInterceptor {

    private final ExtensionRepository extensionRepository;

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

    private String mimeTypeFilter(String mimeType) {
        String validMimeType = MimeType.isValidMimeType(mimeType);
        if (validMimeType == null) {
            throw new InterceptorException(ErrorCode.NOT_FOUND_MIMETYPE, "파일의 형식이 올바르지 않습니다.");
        } else {
            return validMimeType;
        }
    }

    private Boolean fileSignatureFilter(String extension, String fileSignature) {
        Boolean signature = FileSignature.isEqualsSignature(extension, fileSignature);
        if (!signature) {
            throw new InterceptorException(ErrorCode.INVALID_FILE_SIGNATURE, "유효하지 않은 파일입니다.");
        } else {
            return true;
        }
    }

    private Boolean isGeneralFile(String extension) {
        Boolean generalFile = GeneralFile.isEqualsGeneralFile(extension);
        if (generalFile == null) {
            throw new InterceptorException(ErrorCode.INVALID_FILE_SIGNATURE, "유효하지 않은 파일입니다.");
        } else {
            return true;
        }
    }

    private String convertByteToSignature(InputStream inputStream) throws IOException {
        byte[] bytes = inputStream.readNBytes(2);
        String signature = "";
        for (int i = 0; i < bytes.length; i++) {
            signature += String.format("%02x", bytes[i]);
        }

        return signature;
    }
}

package flow.assignment.interceptor;

import flow.assignment.common.FileSignature;
import flow.assignment.common.MimeType;
import flow.assignment.file.model.entity.Extension;
import flow.assignment.file.repository.ExtensionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExtensionHandlerInterceptor implements HandlerInterceptor {

    private final ExtensionRepository extensionRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Collection<Part> result = request.getParts();
        for (Part part : result) {
            String contentType = part.getContentType();
            String fileName = part.getSubmittedFileName();
            if (fileSignatureFilter(fileName, part.getInputStream())) {
                String extension = mimeTypeFilter(contentType);
                return extensionFilter(extension);
            } else {
                return false;
            }
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private Boolean extensionFilter(String extension) {
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

    private String mimeTypeFilter(String mimeType) {
        return MimeType.isValidMimeType(mimeType);
    }

    private Boolean fileSignatureFilter(String fileName, InputStream inputStream) throws IOException {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        byte[] bytes = inputStream.readAllBytes();
        String signature = "";
        for (int i = 0; i < 4; i++) {
            signature += String.format("%02x", bytes[i]);
        }

        return FileSignature.isEqualsSignature(extension, signature);
    }
}

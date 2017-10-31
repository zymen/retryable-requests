package net.zymen.retryablerequests;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

@Service
class RequestToChecksumService {

    private static final Logger log = getLogger(RequestToChecksumService.class);

    String requestToChecksum(HttpServletRequest request) {
//        try {
//            log.info("Parts: {}", request.getParts());
//        } catch (IOException | ServletException e) {
//            e.printStackTrace();
//        }

        return request.getRequestURI();
    }
}

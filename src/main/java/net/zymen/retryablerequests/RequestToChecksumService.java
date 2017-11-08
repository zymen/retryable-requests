package net.zymen.retryablerequests;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
class RequestToChecksumService {

    String requestToChecksum(final HttpServletRequest request) {

        return request.getRequestURI();
    }
}

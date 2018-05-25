package net.zymen.retryablerequests;

import net.zymen.retryablerequests.annotations.RetryableRequest
import net.zymen.retryablerequests.retryableby.RetryableBy
import org.springframework.stereotype.Service

@Service
class RequestToChecksumService {

    fun requestToChecksum(request: MyRequestWrapper, annotation: RetryableRequest): String = when (annotation.retryableBy) {
        RetryableBy.QUERY_PARAMS -> request.requestURI + request.queryString
        RetryableBy.BODY -> request.requestURI + request.body
    }
}

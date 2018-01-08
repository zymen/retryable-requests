package net.zymen.retryablerequests.tests.app.endpoints;

import net.zymen.retryablerequests.annotations.RetryableRequest;
import net.zymen.retryablerequests.retryableby.RetryableBy;
import net.zymen.retryablerequests.tests.app.dto.CodeDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static net.zymen.retryablerequests.tests.app.data.RandomDataSupplier.randomString;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/random-data")
public class RandomDataCacheByController {

    @RetryableRequest(retryableBy = RetryableBy.QUERY_PARAMS)
    @ResponseStatus(OK)
    @RequestMapping(value = "/cache-by/query-params", method = RequestMethod.GET)
    public CodeDto generateByQueryParams(@RequestParam("q1") final String q1,
                                         @RequestParam("q2") final String q2) {
        return new CodeDto(randomString(10));
    }

    @RetryableRequest(retryableBy = RetryableBy.BODY)
    @ResponseStatus(OK)
    @RequestMapping(value = "/cache-by/body", method = RequestMethod.POST)
    public CodeDto generateByBody(@RequestBody final String content) {
        return new CodeDto(randomString(10));
    }
}
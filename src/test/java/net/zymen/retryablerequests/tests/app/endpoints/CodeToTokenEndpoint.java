package net.zymen.retryablerequests.tests.app.endpoints;

import net.zymen.retryablerequests.annotations.RetryableRequest;
import net.zymen.retryablerequests.tests.app.dto.CodeDto;
import net.zymen.retryablerequests.tests.app.dto.TokenDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static net.zymen.retryablerequests.retryableby.RetryableBy.QUERY_PARAMS;
import static net.zymen.retryablerequests.tests.app.data.RandomDataSupplier.randomString;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping("/token")
public class CodeToTokenEndpoint {

    @RetryableRequest(retryableBy = QUERY_PARAMS)
    @RequestMapping(value = "/exchange-body", method = POST)
    public TokenDto exchangeCode(@RequestBody final CodeDto codeDto) {
        return new TokenDto(randomString(10));
    }
}

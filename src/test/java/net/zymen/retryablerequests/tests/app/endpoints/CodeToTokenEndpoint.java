package net.zymen.retryablerequests.tests.app.endpoints;

import net.zymen.retryablerequests.annotations.RetryableRequest;
import net.zymen.retryablerequests.tests.app.dto.CodeDto;
import net.zymen.retryablerequests.tests.app.dto.TokenDto;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping("/token")
public class CodeToTokenEndpoint {

    private final RandomStringGenerator stringGenerator =
            new RandomStringGenerator.Builder()
                    .withinRange('a', 'z')
                    .build();

    @RetryableRequest
    @RequestMapping(value = "/exchange-body", method = POST)
    public TokenDto exchangeCode(@RequestBody CodeDto codeDto) {
        return new TokenDto(stringGenerator.generate(10));
    }
}

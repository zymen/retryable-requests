package net.zymen.retryablerequests.tests.app.endpoints;

import net.zymen.retryablerequests.annotations.RetryableRequest;
import net.zymen.retryablerequests.tests.app.dto.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(method = GET)
    public User user() {
        return new User("ab", "bf", (long) 15);
    }

    @RetryableRequest
    @RequestMapping(method = POST)
    public User user(@RequestBody String input) {
        return new User("baaaa", "xxx", 12l);
    }
}

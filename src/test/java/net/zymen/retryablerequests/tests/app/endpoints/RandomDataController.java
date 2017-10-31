package net.zymen.retryablerequests.tests.app.endpoints;

import net.zymen.retryablerequests.annotations.RetryableRequest;
import net.zymen.retryablerequests.tests.app.dto.User;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/random-data")
public class RandomDataController {

    private final RandomStringGenerator stringGenerator =
            new RandomStringGenerator.Builder()
                    .withinRange('a', 'z')
                    .build();

    private final Random random = new SecureRandom();

    @RequestMapping( value = "/user-without-annotation", method = GET)
    public User user() {
        return new User(
                stringGenerator.generate(5),
                stringGenerator.generate(10),
                random.nextLong() % 100
        );
    }

    @RetryableRequest
    @RequestMapping(value = "/user-with-annotation", method = POST)
    public User user(@RequestBody String input) {
        return new User(
                stringGenerator.generate(5),
                stringGenerator.generate(10),
                random.nextLong() % 100
        );
    }
}

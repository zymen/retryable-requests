package net.zymen.retryablerequests.tests.app.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static net.zymen.retryablerequests.tests.app.data.RandomDataSupplier.randomString;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("/errors")
public class ErrorsEndpoint {

    @RequestMapping("/not-found-404")
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public String notFound404() {
        return randomString(10);
    }
}

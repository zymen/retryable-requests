package net.zymen.retryablerequests.tests

import net.zymen.retryablerequests.tests.app.App
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

@SpringBootTest(
        classes = App.class,
        webEnvironment = DEFINED_PORT,
        properties = [
                "server.port=8090"
        ]
)
class BaseTests extends Specification {

    protected RestTemplate restTemplate = new RestTemplate()

    def buildUrl(String path, Map<String, String> queryParams = [:]) {
        def content = new StringBuilder("http://localhost:8090")
        content.append(path)

        if (queryParams.size() > 0) {
            content.append("?")
            queryParams.each { content.append(it.key).append("=").append(it.value).append("&") }
        }

        return content.toString()
    }

    def multipleCallsShouldReturnTheSameBodies(call) {
        def firstCallResponse = call()

        for (def counter = 0; counter < 10; counter++) {

            def nextCallResponse = call()
            assert firstCallResponse.body == nextCallResponse.body
        }

        return true
    }

    def multipleCallsShouldReturnDifferentBodies(call) {
        def firstCallResponse = call()

        for (def counter = 0; counter < 10; counter++) {

            def nextCallResponse = call()
            assert firstCallResponse.body != nextCallResponse.body
        }

        return true
    }
}

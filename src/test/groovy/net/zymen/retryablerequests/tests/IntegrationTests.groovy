package net.zymen.retryablerequests.tests

import net.zymen.retryablerequests.tests.app.App
import net.zymen.retryablerequests.tests.app.dto.User
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
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
class IntegrationTests extends Specification {

    private RestTemplate restTemplate = new RestTemplate()

    def buildUrl(String path) {
        "http://localhost:8090" + path
    }

    def "should return the same results for the same request on endpoint with @RetryRequest annotation"() {
        given: "there is some call to endpoint"
        def restQuery = {
            -> restTemplate.postForEntity(
                    buildUrl("/random-data/user-with-annotation"), [], User.class
            )
        }

        when: "asking for a first time"
        def response1 = restQuery()

        then: "it should return some response"
        response1.statusCode == HttpStatus.OK

        when: "asking for it second time"
        def response2 = restQuery()

        then: "it should return the same results"
        response1.body == response2.body
    }

    def "should return different results for the same request on endpoint without @RetryRequest annotation"() {
        given: "there is some call to endpoint"
        def restQuery = {
            -> restTemplate.getForEntity(
                    buildUrl("/random-data/user-without-annotation"), User.class
            )
        }

        when: "asking for a first time"
        def response1 = restQuery()

        then: "it should return some response"
        response1.statusCode == HttpStatus.OK

        when: "asking for it second time"
        def response2 = restQuery()

        then: "it should return different results"
        response1.body != response2.body
    }
}

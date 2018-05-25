package net.zymen.retryablerequests.tests

import net.zymen.retryablerequests.tests.app.dto.User
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException

class SmokeTests extends BaseTests {

    def "should return the same results for the same request on endpoint with @RetryRequest annotation"() {
        when: "there is some call to endpoint"
        def restQuery = {
            ->
            restTemplate.postForEntity(
                    buildUrl("/random-data/user-with-annotation"), [], User.class
            )
        }

        then: "it should return the same results"
        multipleCallsShouldReturnTheSameBodies(restQuery)
    }

    def "should return different results for the same request on endpoint without @RetryRequest annotation"() {
        when: "there is some call to endpoint"
        def restQuery = {
            ->
            restTemplate.getForEntity(
                    buildUrl("/random-data/user-without-annotation"), User.class
            )
        }

        then: "it should return the same results"
        multipleCallsShouldReturnDifferentBodies(restQuery)
    }

    def "should not cache error responses by default"() {
        given: "there is call non existing to endpoint"
        def restQuery = {
            ->
            try {
                restTemplate.exchange(
                        buildUrl("/errors/not-found-404"),
                        HttpMethod.GET,
                        HttpEntity.EMPTY, String.class
                )
            } catch (HttpClientErrorException e) {
                return new ResponseEntity<String>(
                        e.responseBodyAsString,
                        e.statusCode
                )
            }
        }

        when:
        def response = restQuery()

        then:
        response.statusCode == HttpStatus.NOT_FOUND
        multipleCallsShouldReturnDifferentBodies(restQuery)
    }
}

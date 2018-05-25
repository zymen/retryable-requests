package net.zymen.retryablerequests.tests.cacheby

import net.zymen.retryablerequests.tests.BaseTests
import net.zymen.retryablerequests.tests.app.dto.CodeDto

import static net.zymen.retryablerequests.tests.app.data.RandomDataSupplier.randomString

class BodyTests extends BaseTests {

    def "should return the same result for the same query params"() {
        when: "there is some call to endpoint with query params"
        def restQuery = {
            ->
            def data = ["content": "abc"]
            restTemplate.postForEntity(buildUrl("/random-data/cache-by/body"), data, CodeDto.class)
        }

        then:
        multipleCallsShouldReturnTheSameBodies(restQuery)
    }

    def "should return different results for different query params"() {
        when: "there is some call to endpoint with query params"
        def restQuery = {
            ->
            def data = ["content": randomString(5)]
            restTemplate.postForEntity(buildUrl("/random-data/cache-by/body"), data, CodeDto.class)
        }

        then:
        multipleCallsShouldReturnDifferentBodies(restQuery)
    }
}

package net.zymen.retryablerequests.tests.cacheby

import net.zymen.retryablerequests.tests.BaseTests
import net.zymen.retryablerequests.tests.app.dto.CodeDto

import static net.zymen.retryablerequests.tests.app.data.RandomDataSupplier.randomString

class QueryParamsTests extends BaseTests {

    def "should return the same result for the same query params"() {
        when: "there is some call to endpoint with query params"
        def restQuery = {
            ->
            def params = ["q1": "abc", "q2": "exz"]

            restTemplate.getForEntity(
                    buildUrl("/random-data/cache-by/query-params", params), CodeDto.class
            )
        }

        then:
        multipleCallsShouldReturnTheSameBodies(restQuery)
    }

    def "should return different results for different query params"() {
        when: "there is some call to endpoint with query params"
        def restQuery = {
            ->
            def params = ["q1": randomString(5), "q2": randomString(5)]

            restTemplate.getForEntity(
                    buildUrl("/random-data/cache-by/query-params", params), CodeDto.class
            )
        }

        then:
        multipleCallsShouldReturnDifferentBodies(restQuery)
    }
}

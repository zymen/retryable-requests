package net.zymen.retryablerequests.tests.cacheby

import net.zymen.retryablerequests.tests.BaseTests
import net.zymen.retryablerequests.tests.app.dto.User
import spock.lang.Ignore

class QueryParamsTests extends BaseTests {

    @Ignore
    def "should return the same result for the same query params"() {
        when: "there is some call to endpoint with query params"
        def restQuery = {
            ->
            restTemplate.postForEntity(
                    buildUrl("/random-data/cache-by/query-params?q1=abc&q2=test"), [], User.class
            )
        }

        then:
        multipleCallsShouldReturnTheSameBodies(restQuery)
    }
}

package io.zerocontribution.charge.tests

import io.zerocontribution.charge.fixtures.FixtureService
import io.zerocontribution.charge.fixtures.generators.EmployerGenerator
import io.zerocontribution.charge.fixtures.generators.NewMember
import io.zerocontribution.charge.gatling.GatlingDriver
import io.zerocontribution.charge.ChargeModule
import com.google.inject.Inject
import spock.guice.UseModules
import spock.lang.Specification

import static groovyx.gpars.GParsPool.withPool

import java.util.concurrent.Future

@UseModules(ChargeModule)
class GuiceSpec extends Specification {

    @Inject
    GatlingDriver gatling

    @Inject
    FixtureService fixtures

    void 'Gatling example with setup'() {
//        given: 'Create named templates for people to reuse as a base across multiple tests'
//        List<NewMember> members = fixtures.newEmployer("groupActive").generate([numEmployees: 5] as EmployerGenerator.EmployerConfiguration).members

        when:
        def a = 'a'
        // TODO A better way to pass configuration around?
        // Gatling simulations will to be able to have access to simple configurations like this, as well as lists of
        // domain objects (roster load, for example). This solution works, but it means that only one GoogleSimulation
        // can be run at a time. It would not be possible to concurrently run two GoogleSimulations.
//        GoogleSimulation.RunConfiguration$.numUsers_$eq(members.size());

        def result = gatling.drive("GoogleSimulation")

        then:
        // May make sense that drive() is done in "when". This block could then be used for developer-defined assertions.
        assert result
    }

    /**
     * TODO Gatling doesn't support running multiple simulations at the same time?
     * https://groups.google.com/forum/#!topic/gatling/Bp8rbquS3Mk
     */
    void 'Concurrent Gatling tests'() {
        when:
        def a = 'a'

        then:
        boolean result = false
        withPool(2) { pool ->
            Future googleResult = { gatling.drive("GoogleSimulation") }.callAsync()
            result = googleResult.get()
        }
        assert result
    }

}

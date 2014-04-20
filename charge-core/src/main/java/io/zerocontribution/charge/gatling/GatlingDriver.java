package io.zerocontribution.charge.gatling;

import io.gatling.app.GatlingStatusCodes;
import io.zerocontribution.charge.Driver;
import io.zerocontribution.charge.config.ChargeConfiguration;
import com.google.inject.Inject;
import io.gatling.app.Gatling$;
import io.gatling.core.ConfigurationConstants;
import io.gatling.core.scenario.Simulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GatlingDriver implements Driver {
    private final Logger log = LoggerFactory.getLogger(GatlingDriver.class);

    @Inject
    ChargeConfiguration config;

    private int numUsers = 0;

    public GatlingDriver withUsers(int numUsers) {
        this.numUsers = numUsers;
        return this;
    }

    public boolean drive(String targetName) {
        final scala.Option<Class<Simulation>> none = scala.Option.apply(null);

        scala.collection.mutable.Map<String, Object> runMap = new scala.collection.mutable.HashMap<>();
//        runMap.put(ConfigurationConstants.CONF_CORE_DIRECTORY_DATA(), new File("user-files/data"));
//        runMap.put(ConfigurationConstants.CONF_CORE_DIRECTORY_REQUEST_BODIES(), new File("user-files/request-bodies"));
        runMap.put(ConfigurationConstants.CONF_CORE_DIRECTORY_RESULTS(), "build/reports/");
        runMap.put(ConfigurationConstants.CONF_CORE_DISABLE_COMPILER(), true);
        runMap.put(ConfigurationConstants.CONF_CORE_SIMULATION_CLASS(), getResolvedSimulationClassName(targetName));

        int result = Gatling$.MODULE$.fromMap(runMap, none);

        return result == GatlingStatusCodes.Success();
    }

    private String getResolvedSimulationClassName(String targetName) {
        return "io.zerocontribution.charge.gatling.simulations." + targetName;
    }

    public void reset() {
        log.info("Reset");
    }
}

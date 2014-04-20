package io.zerocontribution.charge;

import io.zerocontribution.charge.config.ChargeConfiguration;
import io.zerocontribution.charge.config.ConfigReader;
import io.zerocontribution.charge.gatling.GatlingDriver;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class ChargeModule extends AbstractModule {

    protected void configure() {
        bind(ChargeConfiguration.class).toInstance(new ConfigReader().read());
        bind(Driver.class).annotatedWith(Names.named("gatling")).to(GatlingDriver.class);
    }
}

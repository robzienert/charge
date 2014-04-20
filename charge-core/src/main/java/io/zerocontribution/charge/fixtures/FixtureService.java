package io.zerocontribution.charge.fixtures;

import io.zerocontribution.charge.config.ChargeConfiguration;
import io.zerocontribution.charge.config.ConfigReader;
import io.zerocontribution.charge.fixtures.generators.EmployerGenerator;
import com.google.common.base.Optional;
import com.google.inject.Inject;

public class FixtureService {

    private final static String FIXTURES_PATH = "fixtures/";

    @Inject
    ChargeConfiguration config;

    public EmployerGenerator newEmployer(String template) {
        return newEmployer(template, Optional.<EmployerGenerator.EmployerConfiguration>absent());
    }

    public EmployerGenerator newEmployer(String template, Optional<EmployerGenerator.EmployerConfiguration> config) {
        EmployerGenerator.EmployerConfiguration configuration = readFixture(template + "Employer", EmployerGenerator.EmployerConfiguration.class);

        if (config.isPresent()) {
            configuration.mergeWith(config.get());
        }

        EmployerGenerator generator = new EmployerGenerator();
        generator.generate(configuration);

        return generator;
    }

    private <T> T readFixture(String template, Class<T> type) {
        return new ConfigReader().readAs(FIXTURES_PATH + template, type);
    }

}

package io.zerocontribution.charge.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class ConfigReader {

    private final static String FRAMEWORK_DEFAULTS_NAME = "charge-defaults";

    /**
     * TOOD: needs support for CLI-based config locations
     * 1. Command-line properties
     * 2. psi.yml
     * 3. charge-defaults.yaml
     */
    public ChargeConfiguration read() {
        return readAs(FRAMEWORK_DEFAULTS_NAME, ChargeConfiguration.class);
    }

    public <T> T readAs(String name, Class<T> type) {
        assert !name.endsWith(".yaml") || !name.endsWith(".yml") : "Do not include .yml or .yaml in config names";

        Yaml yaml = new Yaml();
        InputStream in = getClass().getResourceAsStream("/" + name + ".yaml");
        return yaml.loadAs(in, type);
    }

}

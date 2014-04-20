package io.zerocontribution.charge.fixtures.generators;

import java.util.ArrayList;
import java.util.List;

public class EmployerGenerator implements Generator {

    Object employer;
    List<NewMember> members = new ArrayList<>();

    public EmployerGenerator generate(Object configuration) {
        assert employer == null : "A generator can only be used once";

        EmployerConfiguration config = (EmployerConfiguration) configuration;

        // 1. Create the employer according to configuration
        // 2. If any members; add them too - then add their information.

        return this;
    }

    public Object getEmployer() {
        if (employer == null) {
            throw new IllegalStateException("The employer must be generated prior to getting it.");
        }
        return employer;
    }

    public List<NewMember> getMembers() {
        return members;
    }

    public enum EmployerType {
        ACTIVE("active"),
        RETIREE("retiree"),
        ACO("aco");

        private final String name;

        private EmployerType(String name) {
            this.name = name;
        }

        public boolean equalsName(String otherName) {
            return (otherName != null) && name.equals(otherName);
        }

        public String toString() {
            return name;
        }
    }

    public static class EmployerConfiguration {
        public int numEmployees;
        public EmployerType type;

        public void mergeWith(EmployerConfiguration other) {
            if (other.numEmployees >= 0) {
                numEmployees = other.numEmployees;
            }
        }
    }
}

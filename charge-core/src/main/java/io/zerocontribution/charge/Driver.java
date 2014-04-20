package io.zerocontribution.charge;

/**
 * A Driver is responsible for executing test scenarios on the Application Under Test. The reset method on the Driver
 * will be called after every Spock invocation (e.g. after table data invocation, feature, spec, etc).
 */
public interface Driver {

    /**
     * @todo Return DriverStatus? Allow drivers to return more than just a true/false condition; or leave drivers to
     *       implement their own means?
     * @param targetName
     * @return Whether or not the driver executed successfully. Should return false if the driver has failed assertions,
     *         as well as if the driver was unable to execute.
     */
    public boolean drive(String targetName);

    public void reset();

}

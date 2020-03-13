package factoryHelpers;

import services.IOSetup;
import services.ProcessCustomers;

/**
 * <p>A helper interface to get required instances</p>
 */
public interface ApplicationHelper {

    IOSetup getIOSetupInstance();

    ProcessCustomers getProcessCustomersInstance();
}

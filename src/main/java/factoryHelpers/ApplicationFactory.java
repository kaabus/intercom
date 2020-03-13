package factoryHelpers;

import services.ProcessCustomers;

/**
 * <p>A class that uses the Application helper implementation to return required
 * instances.</p>
 */
public class ApplicationFactory {

    private ApplicationHelper applicationHelper;

    public ApplicationFactory() {
        this.applicationHelper = new ApplicationHelperImpl();
    }

    /**
     * to get ProcessCustomer class instance
     *
     * @return ProcessCustomer instance
     */
    public ProcessCustomers getProcessCustomers() {
        return applicationHelper.getProcessCustomersInstance();
    }


}

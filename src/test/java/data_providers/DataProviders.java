package data_providers;

import org.testng.annotations.DataProvider;

/**
 * Created on 14-Dec-16 at 10:12 AM.
 */
public class DataProviders {
    @DataProvider(name = "user-credentials")
    public static Object[][] getData() {
        return new Object[][]{
                {"automationqa@protonmail.ch", "qatestlab2005"},
        };
    }

}

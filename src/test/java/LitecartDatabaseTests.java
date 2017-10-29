import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;
import utils.DBHelper;
import utils.DriverFactory;
import utils.PropertyHelper;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class LitecartDatabaseTests {

    private final String APPLINK = PropertyHelper.getProperty("addUserUrl");

    @Before
    public void setup() {
        DriverFactory.getDriver().get(APPLINK);
    }

    @After
    public void tearDown() {
        DriverFactory.shutDownDriver();
    }

    @Test
    public void duckNamesInUIShouldBeEqualToDB() throws SQLException {
        List<String> duckNamesFromUI = new MainPage()
                .openRubberDucksPage()
                .getDuckNames();

        List<String> duckNamesFromDB = DBHelper.getProductNames();

        Collections.sort(duckNamesFromDB);
        Collections.sort(duckNamesFromUI);

        assertEquals(duckNamesFromDB, duckNamesFromUI);
    }

    @Test
    public void ducksCountInUIShouldBeEqualToDB() throws SQLException {

        int getDucksCountFromDB = DBHelper.getProducts().size();
        int getDucksCountFromUI = new MainPage()
                .openRubberDucksPage()
                .getDucksCountOnPage();

        assertEquals(getDucksCountFromDB, getDucksCountFromUI);
    }

    @Test
    public void duckPricesInUIShouldBeEqualToDB() throws SQLException {
        List<Float> duckPricesFromUI = new MainPage()
                .openRubberDucksPage()
                .getDuckPrices();

        List<Float> duckPricesFromDB = DBHelper.getProductPrices();

        Collections.sort(duckPricesFromDB);
        Collections.sort(duckPricesFromUI);

        assertEquals(duckPricesFromDB, duckPricesFromUI);
    }
   @Test
    public void duckPricesInUIShouldBeEqualToDB2() throws SQLException {
        Map<String,Float> duckPricesFromUI = new MainPage()
                .openRubberDucksPage()
                .getDuckPricesToMap();

        Map<String,Float> duckPricesFromDB = DBHelper.getProductPricesToMap();

        assertEquals(duckPricesFromDB, duckPricesFromUI);
    }

}
package tests.daveHaefnnerSiteTests;

import objectrepository.DynamicLoading;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.Base;


import static org.testng.Assert.assertTrue;
public class TestDynamicLoading extends Base {

    private DynamicLoading dynamicLoading;

    @BeforeClass
    public void setUp() {
        dynamicLoading = new DynamicLoading(driver, wait);
    }

    @Test
    public void hiddenElementLoads(){
        dynamicLoading.loadExample("1");
        assertTrue(dynamicLoading.isFinishTextPresent(), "finish text didn't display after loading");
    }

    @Test
    public void elementAppears(){
        dynamicLoading.loadExample("2");
        assertTrue(dynamicLoading.isFinishTextPresent(), "finish text didn't render after loading");
    }
}

package tests;

import objectrepository.DynamicLoading;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import tests.groups.Shallow;

import static org.junit.Assert.assertTrue;

public class TestDynamicLoading extends Base{

    private DynamicLoading dynamicLoading;

    @Before
    public void setUp() {
        dynamicLoading = new DynamicLoading(driver);
    }

    @Test
    public void hiddenElementLoads(){
        dynamicLoading.loadExample("1");
        assertTrue("finish text didn't display after loading", dynamicLoading.isFinishTextPresent());
    }

    @Test
    public void elementAppears(){
        dynamicLoading.loadExample("2");
        assertTrue("finish text didn't render after loading", dynamicLoading.isFinishTextPresent());
    }
}

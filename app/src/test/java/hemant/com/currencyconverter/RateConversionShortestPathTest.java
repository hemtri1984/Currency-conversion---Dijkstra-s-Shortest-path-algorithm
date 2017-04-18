package hemant.com.currencyconverter;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import hemant.com.currencyconverter.models.RateConversionModel;
import hemant.com.currencyconverter.utils.RateConversionShortestPath;

/**
 * Created by Hemant on 4/11/17.
 */

public class RateConversionShortestPathTest {
    List<RateConversionModel> rateConversionModelList = new ArrayList<>();

    @Before
    public void prepareConversionData() {
        rateConversionModelList.add(new RateConversionModel("AUD", "0.67", "CAD"));
        rateConversionModelList.add(new RateConversionModel("USD", "0.23", "GBP"));
        rateConversionModelList.add(new RateConversionModel("CAD", "0.19", "USD"));
        rateConversionModelList.add(new RateConversionModel("AUD", "0.19", "GBP"));
    }

    @Test
    public void testShortestPathconversion() {
        Assert.assertEquals(0.23, RateConversionShortestPath.getConversionValue(rateConversionModelList, "USD"), 0.); //0.23 for USD to GBP conversion in single iteration.
        Assert.assertEquals(0.28, RateConversionShortestPath.getConversionValue(rateConversionModelList, "CAD"), 0.0035820895522388); //1.492537*0.19 = 0.2835820
        Assert.assertEquals(0., RateConversionShortestPath.getConversionValue(rateConversionModelList, "AUS"), 0.); //0 for conversion not found
        Assert.assertEquals(1.0, RateConversionShortestPath.getConversionValue(rateConversionModelList, "GBP"), 0.); //1 for GBP to GBP conversion
    }
}

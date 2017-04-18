package hemant.com.currencyconverter.models;

/**
 * Created by Hemant on 4/11/17.
 */

public class CountModel {
    String sku;
    int count;

    public CountModel(String sku, int count) {
        this.sku = sku;
        this.count = count;
    }

    public String getSku() {
        return sku;
    }

    public int getCount() {
        return count;
    }
}

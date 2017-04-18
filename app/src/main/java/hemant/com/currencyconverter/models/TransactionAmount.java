package hemant.com.currencyconverter.models;

/**
 * Created by Hemant on 4/11/17.
 */

public class TransactionAmount {

    private String amount;
    private String currency;
    private String gbpCurrency;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getGbpCurrency() {
        return gbpCurrency;
    }

    public void setGbpCurrency(String gbpCurrency) {
        this.gbpCurrency = gbpCurrency;
    }
}

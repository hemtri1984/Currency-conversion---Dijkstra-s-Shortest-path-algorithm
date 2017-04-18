
package hemant.com.currencyconverter.models;


public class RateConversionModel {

    private String from;
    private String rate;
    private String to;

    public RateConversionModel(String from, String rate, String to) {
        this.from = from;
        this.rate = rate;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}

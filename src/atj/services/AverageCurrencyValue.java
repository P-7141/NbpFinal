package atj.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "averageCurrencyValue")
public class AverageCurrencyValue {

    public AverageCurrencyValue(String currency, double avg) {
        this.currency = currency;
        this.avg = avg;
    }

    public AverageCurrencyValue() {

    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("avg")
    @Expose
    private double avg;

    public double countAverageRate(List<Rate> rates) {
        double sum = 0;
        for(Rate rate : rates) {
            sum+=rate.getMid();
        }
        return  sum/rates.size();
    }
}
package atj.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class AverageCurrencyValue {

    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("avg")
    @Expose
    private double avg;

    public AverageCurrencyValue(List<Rate> rates, String currency) {
        countAverageRate(rates);
        this.currency = currency;
    }

    public AverageCurrencyValue() {

    }

    @XmlElement
    public String getCurrency() {
        return currency;
    }

    @XmlElement
    public double getAvg() {
        return avg;
    }

    private void countAverageRate(List<Rate> rates) {
        double sum = 0;
        for (Rate rate : rates) {
            sum += rate.getMid();
        }
        this.avg = sum / rates.size();
    }
}

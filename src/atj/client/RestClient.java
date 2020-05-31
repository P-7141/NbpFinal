package atj.client;

import atj.services.AverageCurrencyValue;
import atj.services.Rate;
import atj.services.RatesService;
import com.google.gson.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.List;

public class RestClient {

    String baseNbpUrl = "http://api.nbp.pl/api/exchangerates/rates";

    public RatesService getRates(String table, String code) {
        Client client = ClientBuilder.newClient();
        URI uri = URI.create(baseNbpUrl);

        WebTarget webTarget = client.target(uri);
        webTarget = webTarget.path(table).path(code);

        String plainAnswer = webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class);

        Gson g = new GsonBuilder().setPrettyPrinting().create();

        return g.fromJson(plainAnswer, RatesService.class);
    }

    public AverageCurrencyValue getTable(String table, String code, String days) {
        Client client = ClientBuilder.newClient();
        URI uri = URI.create(baseNbpUrl);

        WebTarget webTarget = client.target(uri);
        webTarget = webTarget.path(table).path(code).path("last").path(days);
        System.out.println(webTarget);

        String plainAnswer = webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class);

        Gson g = new GsonBuilder().setPrettyPrinting().create();
        RatesService service = g.fromJson(plainAnswer, RatesService.class);

        final List<Rate> rates = service.getRates();
        String currency = service.getCurrency();

        //jak tu zrobic
        //1.  zwrocic tak jak napisales pierwotnie (tyle, ze wtedy trzeba byloby tutaj wyliczyc najpierw avg):
        //return new NbpRates.AverageCurrencyValue(currency, avg);

        //2. zwrocic new AverageCurrencyValue i w nim stworzyc nowym obiekt new AverageCurrency value, aby wyliczyc countAvgRate
        //return new AverageCurrencyValue(currency, new AverageCurrencyValue().countAverageRate(rates));

        //3. zrobic jak w pkt 2. tylko, ze countAverageRate dac static i wtedy nie bede musial tworzyc obiektu klasy w konstruktorze
        // w celu wyliczenia tego averageRate

        //4. wypisac jak krowa na rowie jak ponizej
        AverageCurrencyValue avgCurrencyValue = new AverageCurrencyValue();
        avgCurrencyValue.setAvg(avgCurrencyValue.countAverageRate(rates));
        avgCurrencyValue.setCurrency(currency);

        return avgCurrencyValue;
    }
}

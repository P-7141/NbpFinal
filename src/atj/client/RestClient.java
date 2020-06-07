package atj.client;

import atj.services.AverageCurrencyValue;
import atj.services.RatesService;


public class RestClient {

    private static final String LAST_DAYS_PERIOD = "last";

    public RatesService getRates(String table, String code) {
        return NbpClient.builder()
                .withPath(table)
                .withPath(code)
                .request();
    }

    public AverageCurrencyValue getTable(String table, String code, String days) {
        final RatesService service = NbpClient.builder().withPath(table)
                .withPath(code)
                .withPath(LAST_DAYS_PERIOD)
                .withPath(days)
                .request();

        return new AverageCurrencyValue(service.getRates(), service.getCurrency());
    }
}

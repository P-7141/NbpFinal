package atj.ServerApp;

import atj.client.RestClient;
import atj.services.AverageCurrencyValue;
import atj.services.RatesService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

@Path("/nbpRates")
@Produces({APPLICATION_XML, APPLICATION_JSON})
public class NbpRates {

    private final RestClient restClient = new RestClient();

    @GET
    @Path("/{table}/{code}")
    public RatesService getRatesForNBP(@PathParam("table") String table, @PathParam("code") String code) {
        return restClient.getRates(table, code);
    }

    @GET
    @Path("/{table}/{code}/last/{days}")
    public AverageCurrencyValue getTableForCurrencyForLastDaysJsonXML(@PathParam("table") String table, @PathParam("code") String code,
                                                                      @PathParam("days") String days) {
        return restClient.getTable(table, code, days);
    }
}

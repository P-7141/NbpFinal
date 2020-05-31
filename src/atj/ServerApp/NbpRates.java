package atj.ServerApp;

import atj.client.RestClient;
import atj.services.AverageCurrencyValue;
import atj.services.RatesService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/nbpRates")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class NbpRates {

    RestClient restClient = new RestClient();

    public NbpRates() {
    }

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

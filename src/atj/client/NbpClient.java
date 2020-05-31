package atj.client;

import atj.services.RatesService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.URI;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

class NbpClient {
    private static final String baseNbpUrl = "http://api.nbp.pl/api/exchangerates/rates";
    private static final Client client = ClientBuilder.newClient();
    private static final URI uri = URI.create(baseNbpUrl);

    private NbpClient() { }

    static NbpRequestBuilder builder() {
        return new NbpRequestBuilder(client.target(uri));
    }

    static class NbpRequestBuilder {
        private WebTarget webTarget;

        private NbpRequestBuilder(WebTarget target) {
            webTarget = target;
        }

        NbpRequestBuilder withPath(String path) {
            webTarget = webTarget.path(path);
            return this;
        }

        RatesService request() {
            return webTarget.request()
                    .accept(APPLICATION_JSON).get(RatesService.class);
        }
    }
}

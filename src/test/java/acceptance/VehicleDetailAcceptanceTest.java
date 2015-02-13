package acceptance;

import api.Application;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import util.Fixtures;

import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class VehicleDetailAcceptanceTest {

    @Rule
    public MockServerRule mockServerRule = new MockServerRule(8082, this);

    private MockServerClient mockServerClient;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void testGetVehicleJson() {
        mockServerClient.when(
            HttpRequest.request("/vehicledetail/detail/628611860.json")
        ).respond(
            HttpResponse
                .response(Fixtures.read("vehicle_detail_success"))
                .withHeader(new Header("Content-Type", "application/json"))
        );

        RestAssured.when()
            .get("/vehicle/628611860")
            .then()
            .body("listingId", equalTo(628611860))
            .body("model", equalTo("Highlander"))
            .body("year", equalTo(2015))
            .body("make", equalTo("Toyota"));
    }
}

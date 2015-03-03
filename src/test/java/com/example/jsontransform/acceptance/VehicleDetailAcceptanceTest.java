package com.example.jsontransform.acceptance;

import com.example.jsontransform.Application;
import com.example.jsontransform.common.Fixtures;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class VehicleDetailAcceptanceTest {

    @Rule
    public MockServerRule mockServerRule = new MockServerRule(8082, this);

    private MockServerClient mockServerClient;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGetVehicleJson() throws Exception {
        mockServerClient.when(
                HttpRequest.request("/vehicledetail/detail/628611860.json")
        ).respond(
                HttpResponse
                        .response(Fixtures.read("vehicle_detail_success"))
                        .withHeader(new Header("Content-Type", "application/json"))
        );

        mockMvc.perform(get("/vehicle/628611860"))
                .andExpect(jsonPath("$.listingId", equalTo(628611860)))
                .andExpect(jsonPath("$.model", equalTo("Highlander")))
                .andExpect(jsonPath("$.year", equalTo(2015)))
                .andExpect(jsonPath("$.make", equalTo("Toyota")));
    }
}

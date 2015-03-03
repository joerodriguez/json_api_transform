package com.example.jsontransform.detail;

import com.example.jsontransform.Application;
import com.example.jsontransform.common.JsonTransformer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class VehicleDetailControllerTest {

    @Mock
    JsonTransformer jsonTransformer;

    @InjectMocks
    VehicleDetailController controller;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        controller.listingsApiRootUrl = "local";
    }

    @Test
    public void testDetail() {
        when(jsonTransformer.transform(
                eq("listingDetail"),
                eq("local/vehicledetail/detail/334d.json"))
        ).thenReturn("mock-response");

        String response = controller.showVehicle("334d");

        assertThat(response, equalTo("mock-response"));
    }

}
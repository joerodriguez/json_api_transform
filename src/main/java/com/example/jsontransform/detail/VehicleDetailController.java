package com.example.jsontransform.detail;

import com.example.jsontransform.common.JsonTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleDetailController {

    @Value("${cars.listings.com.example.jsontransform.api}")
    String listingsApiRootUrl;

    @Autowired
    JsonTransformer jsonTransformer;

    @RequestMapping(value = "/vehicle/{listingId}", method = RequestMethod.GET, produces = "application/json")
    public String showVehicle(@PathVariable String listingId) {
        String url = listingsApiRootUrl + "/vehicledetail/detail/" + listingId + ".json";

        return jsonTransformer.transform("listingDetail", url);
    }
}

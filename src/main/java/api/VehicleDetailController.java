package api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.Jolt;

@RestController
public class VehicleDetailController {

    @Value("${cars.listings.api}")
    String listingsApiRootUrl;

    @RequestMapping(value = "/vehicle/{listingId}", method = RequestMethod.GET, produces = "application/json")
    public String showVehicle(@PathVariable String listingId) {
        String url = listingsApiRootUrl + "/vehicledetail/detail/" + listingId + ".json";

        return Jolt.transform("listingDetail", url);
    }
}

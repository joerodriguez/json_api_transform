package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class VehicleDetailController {

    @RequestMapping(value = "/vehicle/{listingId}", method = RequestMethod.GET)
    public Vehicle showVehicle(
        @PathVariable String listingId
    ) {
        RestTemplate restTemplate = new RestTemplate();
        Listing listing = restTemplate.getForObject("http://localhost:8082/api/listings/628611860.json", Listing.class);

        int listingId1 = listing.getListingDetailDto().getListingId();
        String makeName = listing.getListingDetailDto().getMakeName();

        return new Vehicle(String.valueOf(listingId1), makeName);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Listing {
        private ListingDetailDto listingDetailDto;

        public ListingDetailDto getListingDetailDto() {
            return listingDetailDto;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ListingDetailDto {
        private String makeName;
        private int listingId;

        public String getMakeName() {
            return makeName;
        }

        public int getListingId() {
            return listingId;
        }
    }

    private class Vehicle {
        private final String listingId;
        private final String make;

        public Vehicle(String listingId, String make) {
            this.listingId = listingId;
            this.make = make;
        }

        public String getListingId() {
            return listingId;
        }

        public String getMake() {
            return make;
        }
    }
}

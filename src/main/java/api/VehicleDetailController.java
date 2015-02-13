package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class VehicleDetailController {

    @Value("${cars.listings.api}")
    String listingsApiRootUrl;

    @RequestMapping(value = "/vehicle/{listingId}", method = RequestMethod.GET)
    public Vehicle showVehicle(
        @PathVariable String listingId
    ) {
        RestTemplate restTemplate = new RestTemplate();
        Listing listing = restTemplate.getForObject(listingsApiRootUrl + "/vehicledetail/detail/" + listingId + ".json", Listing.class);

        String makeName = listing.getListingDetailDto().getMakeName();
        String modelName = listing.getListingDetailDto().getModelName();
        int year = listing.getListingDetailDto().getModelYear();

        return new Vehicle(String.valueOf(listingId), makeName, modelName, year);
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
        private String modelName;
        private int modelYear;
        private int listingId;

        public String getMakeName() { return makeName; }

        public String getModelName() { return modelName; }

        public int getModelYear() { return modelYear; }

        public int getListingId() {
            return listingId;
        }
    }

    private class Vehicle {
        private final String listingId;
        private final String make;
        private final String model;
        private final int year;

        public Vehicle(String listingId, String make, String model, int year) {
            this.listingId = listingId;
            this.make = make;
            this.model = model;
            this.year = year;
        }

        public String getListingId() {
            return listingId;
        }

        public String getMake() { return make; }

        public String getModel() { return model; }

        public int getYear() { return year; }
    }
}

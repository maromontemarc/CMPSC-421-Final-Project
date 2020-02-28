package psu.edu.restaurant;

/*-----------Imports-----------*/
import static psu.edu.restaurant.CustomerController.custH;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.errors.ApiException;
import org.springframework.web.bind.annotation.*;
import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import java.io.IOException;

@RestController
public class GoogleMapsController{
    private static final String API_KEY = "AIzaSyBGniA1rrvIP6kzMywmO9o1DXTt3jCcX9I";

    /**
     * getUserAddress(@RequestParam(name = "id") int id)
     *
     * Asks the user for the userID, gets the user address
     * compares the user address to our set location. Determines if
     * the user is within our deliverable area. By making a request
     * to the DistanceMatrixAPI
     *
     * @param id int
     * @return String
     */
    @GetMapping("cust/get/address")
    public String getUserAddress(@RequestParam(name = "id") int id) {
        Restaurant r = new Restaurant();
        String s;
        GeoApiContext distCalcer = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();

        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(distCalcer);
        DistanceMatrix result = null;
        try {
            result = req.origins(r.getRestaurantAddress())
                    .destinations(custH.get(id).getAddress())
                    .mode(TravelMode.DRIVING)
                    .avoid(RouteRestriction.TOLLS)
                    .language("en-US")
                    .await();
        } catch (ApiException APIE) {
            APIE.printStackTrace();
        } catch (InterruptedException IE) {
            IE.printStackTrace();
        } catch (IOException IOE) {
            IOE.printStackTrace();
        }

        long distApart = result.rows[0].elements[0].distance.inMeters;



        if (distApart >= 20) {
            s = "We do not deliver to your area.\n" +
                    "For an extra fee we will deliver.\n";
        } else if (distApart <= 20) {
            s ="We deliver to your area.";
        } else {
            s ="Error.\n Please try again.";
        }
        return s;
    }
}


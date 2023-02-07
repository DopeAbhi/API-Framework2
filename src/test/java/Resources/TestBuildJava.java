package Resources;

import Pojo.AddPlace;
import Pojo.Location;
import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.List;

public class TestBuildJava {

    public AddPlace addPlacePayload (String name,String language,String address)
    {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number(983833888);
        p.setWebsite("https://rehulshettyacademy.com");
        p.setName(name);

        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);

        Location Lo = new Location();
        Lo.setLat(-38.383494);
        Lo.setLng(33.427362);


        p.setLocation(Lo);
        return p;

    }
    public String deletePlacePayload(String place_id)
    {
        return"{\n" +
                "    \"place_id\":\""+place_id+"\"\n" +
                "}";
    }
}

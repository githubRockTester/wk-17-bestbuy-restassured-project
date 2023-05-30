package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    // 1) Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    // 2) Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total value is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    // 3) Extract the name of 5th store
    @Test
    public void test003() {
        int name = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th store is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    // 4) Extract the names of all the store
    @Test
    public void test004() {
        List<String> allNames = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all stores' name are : " + allNames);
        System.out.println("------------------End of Test---------------------------");
    }

    // 5) Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> storeIDs = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of store IDs : " + storeIDs);
        System.out.println("------------------End of Test---------------------------");
    }

    // 6) Print the size of the data list
    @Test
    public void test006() {
        List<Integer> dataListSize = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list is : " + dataListSize.size());
        System.out.println("------------------End of Test---------------------------");
    }

    // 7) Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<String> storeName = response.extract().path("data.findAll{it.name == 'St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of services are : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 8) Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<String> storeAddress = response.extract().path("data.findAll{it.name == 'Rochester'}.address");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store address is : " + storeAddress);
        System.out.println("------------------End of Test---------------------------");
    }

    // 9) Get all the services of 8th store
    @Test
    public void test009() {
        List<String> allServices = response.extract().path("data[7].services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of services of 8th store is : " + allServices);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10) Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
//        List<HashMap<?, ?>> productListMap = response.extract().path("data.findAll{it.name == 'Windows Store'}.storeservices");
//        HashMap<?, ?> productMap = productListMap.get(0);
//        String storeServices1 = (String) productMap.get("storeservices");
        // List<?> services = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of store services are : ");
        System.out.println("------------------End of Test---------------------------");
    }

    // 11) Get all the storeId of all the store
    @Test
    public void test011() {
        List<Integer> allStoreIDs = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of store IDs of all stores are : " + allStoreIDs);
        System.out.println("------------------End of Test---------------------------");
    }

    // 12) Get id of all the store
    @Test
    public void test012() {
        List<Integer> allIDs = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of ID of all stores are : " + allIDs);
        System.out.println("------------------End of Test---------------------------");
    }

    // 13) Find the store names Where state = ND
    @Test
    public void test013() {
        List<Integer> storeNames = response.extract().path("data.findAll{it.state == 'ND'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of store names are : " + storeNames);
        System.out.println("------------------End of Test---------------------------");
    }

    // 14) Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<Integer> totalServices = response.extract().path("data.find{it.name == 'Rochester'}.services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Number of total services are : " + totalServices.size());
        System.out.println("------------------End of Test---------------------------");
    }

    // 15) Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {
        //List<HashMap<String, ?>> storeService = response.extract().path("data.services.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        List<HashMap<?, ?>> productListMap = response.extract().path("data.services.findAll{it.name == 'Windows Store'}");
        HashMap<?, ?> productMap = productListMap.get(0);
        int createdAt = (Integer) productMap.get("createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Number of total services are : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //16) Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {
        List<String> names = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of services are: " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    // 17) Find the zip of all the store
    @Test
    public void test017() {
        List<Integer> zipOfAll = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip code of all stores are : " + zipOfAll);
        System.out.println("------------------End of Test---------------------------");
    }

    //18) Find the zip of store name = Roseville
    @Test
    public void test018() {
        List<Integer> zipOfRoseville = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip code of Roseville store is : " + zipOfRoseville);
        System.out.println("------------------End of Test---------------------------");
    }

    //19) Find the store services details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        List<HashMap<String, ?>> servicesDetails = response.extract().path("data[2].services.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total services : " + servicesDetails);
        System.out.println("------------------End of Test---------------------------");
    }

    //20) Find the lat of all the stores
    @Test
    public void test020() {
        List<Integer> latOfAllStores = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of all stores : " + latOfAllStores);
        System.out.println("------------------End of Test---------------------------");
    }
}

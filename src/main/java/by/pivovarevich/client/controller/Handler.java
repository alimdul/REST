package by.pivovarevich.client.controller;

import by.pivovarevich.model.FamilyPlants;
import by.pivovarevich.model.Plant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class Handler {

    private String endPoint = "http://localhost:8080/";

    private ClientConfig config = new ClientConfig();
    private Client client = ClientBuilder.newClient(config);
    private WebTarget target = client.target(endPoint);
    private Gson gson = new Gson();

    public List<FamilyPlants> getListFamilyPlants() {
        String response = target.path("rest").
                path("references").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(String.class);
        List<FamilyPlants> familyPlantsList = gson.fromJson(response, new TypeToken<List<FamilyPlants>>(){}.getType());
        return familyPlantsList;
    }

    public void addFamilyPlants(FamilyPlants familyPlants) {
        Form form = new Form();
        form.param("familyPlants", gson.toJson(familyPlants));
        Response response = target.
                path("rest").
                path("references").
                path("add").
                path("familyPlants").
                request().
                post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);

    }

    public void deleteFamilyPlants(String index){
        Response response = target.
                path("rest").
                path("references").
                path("delete").
                path("familyPlants").
                path(index).
                request().
                delete();
    }

    public void changeFamilyPlants(FamilyPlants familyPlants){
        Form form = new Form();
        form.param("newFamilyPlants", gson.toJson(familyPlants));
        Response response = target.
                path("rest").
                path("references").
                path("change").
                path("familyPlants").
                request().
                put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);
    }

    public List<Plant> getListPlants(String index){
        String response = target.path("rest").
                path("references").
                path("getListPlants").
                path("in").
                path("familyPlants").
                path(index).
                request().
                accept(MediaType.APPLICATION_JSON).
                get(String.class);
        List<Plant> plantList = gson.fromJson(response, new TypeToken<List<Plant>>(){}.getType());
        return plantList;
    }

    public void addPlant(String index, Plant plant){
        Form form = new Form();
        form.param("plant", gson.toJson(plant));
        Response response = target.
                path("rest").
                path("references").
                path("add").
                path("plant").
                path("in").
                path("familyPlants").
                path(index).
                request().
                post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);
    }

    public void deletePlant(String indexFamily, String index){
        Response response = target.
                path("rest").
                path("references").
                path("delete").
                path("plant").
                path(index).
                path("in").
                path("familyPlants").
                path(indexFamily).
                request().
                delete();
    }

    public void changePlant(String index, Plant plant){
        Form form = new Form();
        form.param("newPlant", gson.toJson(plant));
        Response response = target.
                path("rest").
                path("references").
                path("change").
                path("plant").
                path("in").
                path("familyPlants").
                path(index).
                request().
                put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);
    }
}

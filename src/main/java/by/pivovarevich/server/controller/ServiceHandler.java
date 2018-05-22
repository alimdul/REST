package by.pivovarevich.server.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("references")
public class ServiceHandler {

    private Controller controller = new Controller();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getListFamilyPlants() {
        return controller.getListFamilyPlants();
    }

    @POST
    @Path("add/familyPlants")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addFamilyPlants(@FormParam("familyPlants") String familyPlants) {
        controller.addFamilyPlants(familyPlants);
    }

    @DELETE
    @Path("delete/familyPlants/{index}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteFamilyPlants(@PathParam("index") String index) {
        controller.deleteFamilyPlants(Integer.parseInt(index));
    }

    @PUT
    @Path("change/familyPlants")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void changeFamilyPlants(@FormParam("newFamilyPlants")String newFamilyPlants) {
        controller.changeFamilyPlants(newFamilyPlants);
    }

    @GET
    @Path("getListPlants/in/familyPlants/{index}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getListPlants(@PathParam("index") String index) {
        return controller.getListPlants(Integer.parseInt(index));
    }

    @POST
    @Path("add/plant/in/familyPlants/{index}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addPlant(@PathParam("index") String index, @FormParam("plant") String plant) {
        controller.addPlant(Integer.parseInt(index), plant);
    }

    @DELETE
    @Path("delete/plant/{index}/in/familyPlants/{indexFamily}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deletePlant(@PathParam("indexFamily") String indexFamily, @PathParam("index") String index) {
        controller.deletePlant(Integer.parseInt(indexFamily), Integer.parseInt(index));
    }

    @PUT
    @Path("change/plant/in/familyPlants/{index}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void changePlant(@PathParam("index") String index, @FormParam("newPlant")String newPlant) {
        controller.changePlant(Integer.parseInt(index), newPlant);
    }
}

package by.pivovarevich.server.controller;

import by.pivovarevich.model.FamilyPlants;
import by.pivovarevich.model.Plant;
import by.pivovarevich.model.ReferenceBook;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.List;

public class Controller {

    private final static String FILE_NAME = "E:\\aipos_laba2_thrift\\fileWithData\\data.json";
    private static ReferenceBook referenceBook = new ReferenceBook();
    private Gson gson = new Gson();

    public String getListFamilyPlants(){
        referenceBook.getList().clear();

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(FILE_NAME));
            ReferenceBook rf = gson.fromJson(reader, ReferenceBook.class);

            if(rf != null){
                int i = 0;
                for(FamilyPlants familyPlants: rf.getList()){
                    familyPlants.setId(++i);
                    referenceBook.getList().add(familyPlants);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return gson.toJson(referenceBook.getList());
    }

    public void addFamilyPlants(String familyPlantsJson){
        FamilyPlants familyPlants = gson.fromJson(familyPlantsJson, FamilyPlants.class);
        referenceBook.getList().add(familyPlants);
        saveChanging();
    }

    public void deleteFamilyPlants(int index){
        referenceBook.getList().remove(index);
        saveChanging();
    }

    public void changeFamilyPlants(String familyPlantsJson){
        FamilyPlants familyPlants = gson.fromJson(familyPlantsJson, FamilyPlants.class);
        int index = familyPlants.getId();
        List<Plant> listPlants = referenceBook.getList().get(index).getListPlants();
        referenceBook.getList().remove(index);
        familyPlants.setListPlants(listPlants);
        referenceBook.getList().add(index, familyPlants);
        saveChanging();
    }

    public String getListPlants(int index){
        for(int i = 0; i < referenceBook.getList().get(index).getListPlants().size(); i++){
            referenceBook.getList().get(index).getListPlants().get(i).setId(i+1);
        }
        return gson.toJson(referenceBook.getList().get(index).getListPlants());
    }

    public void addPlant(int indexFamily, String plantJson){
        Plant plant = gson.fromJson(plantJson, Plant.class);
        referenceBook.getList().get(indexFamily).getListPlants().add(plant);
        saveChanging();
    }

    public void deletePlant(int indexFamily, int index){
        referenceBook.getList().get(indexFamily).getListPlants().remove(index);
        saveChanging();
    }

    public void changePlant(int indexFamily, String plantJson){
        Plant plant = gson.fromJson(plantJson, Plant.class);
        int index = plant.getId();
        referenceBook.getList().get(indexFamily).getListPlants().remove(index);
        referenceBook.getList().get(indexFamily).getListPlants().add(index, plant);
        saveChanging();
    }

    public void saveChanging(){

        ReferenceBook rb = new ReferenceBook();
        rb.setList(referenceBook.getList());

        Gson gs = new GsonBuilder().setPrettyPrinting().create();
        String json = gs.toJson(rb);

        FileWriter writer = null;

        try {
            writer = new FileWriter(FILE_NAME);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

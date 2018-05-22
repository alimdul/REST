package by.pivovarevich.model;

import java.util.ArrayList;
import java.util.List;

public class ReferenceBook {

    private List<FamilyPlants> listFamilyPlants = new ArrayList<FamilyPlants>();

    public List<FamilyPlants> getList() {
        return listFamilyPlants;
    }

    public void setList(List<FamilyPlants> list) {
        listFamilyPlants = list;
    }
}

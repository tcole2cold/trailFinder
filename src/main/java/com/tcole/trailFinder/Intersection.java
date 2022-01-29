package com.tcole.trailFinder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcole.trailFinder.pojos.IntersectionJson;
import com.tcole.trailFinder.pojos.TrailJson;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Intersection {
    int id;
    int[] trails;

    public Intersection(int id) throws IOException {
        this.id = id;
        ObjectMapper mapper = new ObjectMapper();
        File from = new File("src/main/resources/json/intersections.json");
        TypeReference<HashMap<String, IntersectionJson>> typeRef = new TypeReference<HashMap<String,IntersectionJson>>() {};
        HashMap<String,IntersectionJson> intersections = mapper.readValue(from, typeRef);
        IntersectionJson intersectionJson = intersections.get(String.valueOf(id));

        this.id = intersectionJson.getId();
        this.trails = intersectionJson.getTrails();
    }

    public int getId() {
        return id;
    }

    public int[] getTrails() {
        return trails;
    }

    public String printInfo() {
        String strTrails = "";
        for (int trail : getTrails()) {
            strTrails = strTrails + String.valueOf(trail);
            strTrails = strTrails + " ";
        }
        return "Intersection ID: " + getId() + " " + "Trails at this intersection: " + strTrails;
    }
}

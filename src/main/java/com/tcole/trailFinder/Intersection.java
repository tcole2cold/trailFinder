package com.tcole.trailFinder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcole.trailFinder.pojos.IntersectionJson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Intersection {
    private int id;
    private final int[] trails;
    private final int numberOfIntersections;

    public Intersection(int id) throws IOException {
        this.id = id;
        ObjectMapper mapper = new ObjectMapper();
        File from = new File("src/main/resources/json/intersections.json");
        TypeReference<HashMap<String, IntersectionJson>> typeRef = new TypeReference<HashMap<String,IntersectionJson>>() {};
        HashMap<String,IntersectionJson> intersections = mapper.readValue(from, typeRef);
        IntersectionJson intersectionJson = intersections.get(String.valueOf(id));

        this.id = intersectionJson.getId();
        this.trails = intersectionJson.getTrails();
        this.numberOfIntersections = intersections.keySet().size();
    }

    public int getId() {
        return id;
    }

    public int[] getTrails() {
        return trails;
    }

    public List<Trail> getListOfTrails() throws IOException {
        List<Trail> trails = new ArrayList<Trail>();
        for(int trailId : getTrails()) {
            Trail trail = new Trail(trailId);
            trails.add(trail);
        }
        return trails;
    }

    public String printInfo() {
        String strTrails = "";
        for (int trail : getTrails()) {
            strTrails = strTrails + trail;
            strTrails = strTrails + " ";
        }
        return "Intersection ID: " + getId() + " " + "Trails at this intersection: " + strTrails;
    }

    public int getNumberOfIntersections() {
        return numberOfIntersections;
    }

}

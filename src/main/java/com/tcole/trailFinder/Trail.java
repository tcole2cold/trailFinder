package com.tcole.trailFinder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcole.trailFinder.pojos.TrailJson;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Trail {
    private int id;
    private String name;
    private double totalDistance;
    private int terminusA;
    private int terminusB;
    private int[] midPointsAToB;
    private double[] distanceBetweenMidpoints;
    private boolean isLoop;

    public Trail(int id) throws IOException {
        this.id = id;
        ObjectMapper mapper = new ObjectMapper();
        File from = new File("src/main/resources/json/trails.json");
        TypeReference<HashMap<String,TrailJson>> typeRef = new TypeReference<HashMap<String,TrailJson>>() {};
        HashMap<String,TrailJson> trails = mapper.readValue(from, typeRef);
        TrailJson trailJson = trails.get(String.valueOf(id));

        this.name = trailJson.getName();
        this.totalDistance = trailJson.getTotalDistance();
        this.terminusA = trailJson.getTerminusA();
        this.terminusB = trailJson.getTerminusB();
        this.midPointsAToB = trailJson.getMidPointsAToB();
        this.distanceBetweenMidpoints = trailJson.getDistanceBetweenMidpoints();

        if(terminusA == terminusB){
            isLoop = true;
        } else isLoop = false;
    }

    public String printInfo() {
        String trailInfo = "ID = " + id + " " + "NAME = " + " " + name + " " + "Total Distance = " + " " + Double.toString(totalDistance)  + " " +
                "Terminus A = "+ " " + terminusA+ " " + "Terminus B = "+ " " + terminusB;
        return trailInfo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public int getTerminusA() {
        return terminusA;
    }

    public int getTerminusB() {
        return terminusB;
    }

    public int[] getMidPointsAToB() {
        return midPointsAToB;
    }

    public double[] getDistanceBetweenMidpoints() {
        return distanceBetweenMidpoints;
    }
    public boolean isLoop() {
        return isLoop;
    }
}

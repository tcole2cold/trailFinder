package com.tcole.trailFinder;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Trail {
    int id;
    String name;
    double totalDistance;
    int terminusA;
    int terminusB;
    int[] midPointsAToB;
    double[] distanceBetweenMidpoints;

    public Trail(int id) throws IOException {
        this.id = id;
        ObjectMapper objectMapper = new ObjectMapper();
        TrailJson trailJson = objectMapper.readValue(new File("src/main/resources/json/trails.json"), TrailJson.class);
        this.name = trailJson.getName();
        this.totalDistance = trailJson.getTotalDistance();
        this.terminusA = trailJson.getTerminusA();
        this.terminusB = trailJson.getTerminusB();
        this.midPointsAToB = trailJson.getMidPointsAToB();
        this.distanceBetweenMidpoints = trailJson.getDistanceBetweenMidpoints();
    }

    public String printInfo() {
        String trailInfo = "ID = " + id + " " + "NAME = " + " " + name + " " + "Total Distance = " + " " + Double.toString(totalDistance)  + " " +
                "Terminus A = "+ " " + terminusA+ " " + "Terminus B = "+ " " + terminusB;
        return trailInfo;
    }
}

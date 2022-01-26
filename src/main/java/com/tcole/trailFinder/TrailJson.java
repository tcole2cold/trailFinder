package com.tcole.trailFinder;

public class TrailJson {
    int id;
    String name;
    double totalDistance;
    int terminusA;
    int terminusB;
    int[] midPointsAToB;
    double[] distanceBetweenMidpoints;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public int getTerminusA() {
        return terminusA;
    }

    public void setTerminusA(int terminusA) {
        this.terminusA = terminusA;
    }

    public int getTerminusB() {
        return terminusB;
    }

    public void setTerminusB(int terminusB) {
        this.terminusB = terminusB;
    }

    public int[] getMidPointsAToB() {
        return midPointsAToB;
    }

    public void setMidPointsAToB(int[] midPointsAToB) {
        this.midPointsAToB = midPointsAToB;
    }

    public double[] getDistanceBetweenMidpoints() {
        return distanceBetweenMidpoints;
    }

    public void setDistanceBetweenMidpoints(double[] distanceBetweenMidpoints) {
        this.distanceBetweenMidpoints = distanceBetweenMidpoints;
    }
}

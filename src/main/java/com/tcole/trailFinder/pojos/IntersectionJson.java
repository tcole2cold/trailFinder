package com.tcole.trailFinder.pojos;

public class IntersectionJson {
    int id;
    int[] trails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getTrails() {
        return trails;
    }

    public void setTrails(int[] midPointsAToB) {
        this.trails = midPointsAToB;
    }
}

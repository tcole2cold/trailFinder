package com.tcole.trailFinder;

public class Step {

    private double distance;
    private Intersection startingIntersection;
    private Intersection endingIntersection;
    private Trail trail;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Intersection getStartingIntersection() {
        return startingIntersection;
    }

    public void setStartingIntersection(Intersection startingIntersection) {
        this.startingIntersection = startingIntersection;
    }

    public Intersection getEndingIntersection() {
        return endingIntersection;
    }

    public void setEndingIntersection(Intersection endingIntersection) {
        this.endingIntersection = endingIntersection;
    }

    public Trail getTrail() {
        return trail;
    }

    public void setTrail(Trail trail) {
        this.trail = trail;
    }
}

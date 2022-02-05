package com.tcole.trailFinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RouteScout {

    Intersection startingIntersection;
    Trail startingTrail;

    public RouteScout(int intersectionId) throws IOException {
        this.startingIntersection = new Intersection(intersectionId);
        Trail startingTrail = startingIntersection.getListOfTrails().get((int)(Math.random() * startingIntersection.getTrails().length));
        this.startingTrail = startingTrail;
        System.out.println("Starting intersection : " + startingIntersection.getId());
        System.out.println(". . . . . . . . . . .");

    }

    public void findFarthestPossibleIntersection() throws IOException {
       // List<Trail> trails = startingIntersection.getListOfTrails();
        //getDistanceToNextIntersection(startingIntersection, startingTrail, pickAStartingDirection());
        Step returner = getNextIntersectionAlongTrail(startingIntersection, startingTrail, pickAStartingDirection(startingTrail, startingIntersection));
        System.out.println("Next intersection : " +returner.getEndingIntersection().getId());
        System.out.println("Next trail : " +returner.getTrail().getName());
        System.out.println("Distance to next step : " +returner.getDistance());



//        for (Trail trail : trails) {
//            double accumulatedMileage = 0;
//            getDistanceToNextIntersection(startingIntersection, trail, pickAStartingDirection());
//            getNextIntersectionAlongTrail(startingIntersection, trail, pickAStartingDirection());
//        }
    }

    private String determineLocationAlongTrail(Trail trail, Intersection currentIntersection) {
        if(currentIntersection.getId() == trail.getTerminusA()) {
            return "A";
        } else if(currentIntersection.getId() == trail.getTerminusB()) {
            return "B";
        }
        else return String.valueOf(currentIntersection.getId());
    }

    private Step getNextIntersectionAlongTrail(Intersection intersection, Trail trail, String direction) throws IOException {
        Step nextStep = new Step();
        nextStep.setStartingIntersection(intersection);
        nextStep.setTrail(trail);

        int[] intersectionsAlongTrail = trail.getMidPointsAToB();
        int indexOfCurrentLocation = 0;
        Intersection nextIntersection;

        //handling trail with no midpoints
        if(intersectionsAlongTrail.length == 0 && direction.equals("AB")) {
            nextIntersection = new Intersection(trail.getTerminusB());
            nextStep.setEndingIntersection(nextIntersection);
            nextStep.setDistance(trail.getTotalDistance());
            return nextStep;
        } else if(intersectionsAlongTrail.length == 0 && direction.equals("BA")) {
            nextIntersection = new Intersection(trail.getTerminusA());
            nextStep.setEndingIntersection(nextIntersection);
            nextStep.setDistance(trail.getTotalDistance());
            return nextStep;
        }

        //making a list to combine mid-points and terminus intersections into one object.
        //should probably break this out into a helper method later
        List<Intersection> terminusesAndIntersections = new ArrayList<Intersection>();
        terminusesAndIntersections.add(new Intersection(trail.getTerminusA()));

        //getting index of current position on trail if midpoints exist
        for(int i = 0; i < intersectionsAlongTrail.length; i++) {
            if (intersectionsAlongTrail[i] == intersection.getId()) {
                indexOfCurrentLocation = i;
            }
            terminusesAndIntersections.add(new Intersection(intersectionsAlongTrail[i]));
        }
        terminusesAndIntersections.add(new Intersection(trail.getTerminusB()));

        if(intersection.getId() == trail.getTerminusA() && !trail.isLoop()) {
            nextStep.setDistance(trail.getDistanceBetweenMidpoints()[0]);
            nextStep.setEndingIntersection(terminusesAndIntersections.get(1));
            return nextStep;
        }
        else if(intersection.getId() == trail.getTerminusB() && !trail.isLoop()) {
            nextStep.setDistance(trail.getDistanceBetweenMidpoints()[terminusesAndIntersections.size() - 2]);
            nextStep.setEndingIntersection(terminusesAndIntersections.get(terminusesAndIntersections.size() - 2));
            return nextStep;
        }
        else if(intersection.getId() == trail.getTerminusA() && trail.isLoop() || intersection.getId() == trail.getTerminusB() && trail.isLoop()) {
            if(Math.random() > 0.5) {
                nextStep.setDistance(trail.getDistanceBetweenMidpoints()[0]);
                nextStep.setEndingIntersection(terminusesAndIntersections.get(1));
                return nextStep;
            } else{
                nextStep.setDistance(trail.getDistanceBetweenMidpoints()[terminusesAndIntersections.size() - 2]);
                nextStep.setEndingIntersection(terminusesAndIntersections.get(terminusesAndIntersections.size() - 2));
                return nextStep;
            }

        }

        if (direction.equals("AB")) {
            nextStep.setDistance(trail.getDistanceBetweenMidpoints()[indexOfCurrentLocation + 1]);
            nextStep.setEndingIntersection(terminusesAndIntersections.get(indexOfCurrentLocation + 2));
        } else {
            nextStep.setDistance(trail.getDistanceBetweenMidpoints()[indexOfCurrentLocation]);
            nextStep.setEndingIntersection(terminusesAndIntersections.get(indexOfCurrentLocation));
        }

        return nextStep;
    }

    private double getDistanceToNextIntersection(Intersection intersection, Trail trail, String direction) throws IOException {
        int[] intersectionsAlongTrail = trail.getMidPointsAToB();
        double[] midpointDistances = trail.getDistanceBetweenMidpoints();
        int indexOfCurrentLocation = 0;
        double distance;

        if (midpointDistances.length == 0) {
            return trail.getTotalDistance();
        }

        for (int i = 0; i < intersectionsAlongTrail.length; i++) {
            if (intersectionsAlongTrail[i] == intersection.getId()) {
                indexOfCurrentLocation = i;
            }
        }

        if (direction.equals("AB")) {
            distance = midpointDistances[indexOfCurrentLocation + 1];
        } else {
            distance = midpointDistances[indexOfCurrentLocation - 1];
        }
        return distance;
    }

    private String pickAStartingDirection(Trail trail, Intersection intersection) {
        String startLine = determineLocationAlongTrail(trail, intersection);
        if(startLine.equals("A")) {
            return "AB";
        }
        else if(startLine.equals("B")) {
            return "BA";
        }
        else if (Math.random() < 0.5) {
            return "AB";
        }
        else {
            return "BA";
        }
    }



}

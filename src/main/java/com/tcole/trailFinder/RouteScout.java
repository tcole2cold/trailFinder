package com.tcole.trailFinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RouteScout {

    public RouteScout() {
    }

    public Route generateARandomRoute(int startingIntersectionId, double minimumDistance, double maximumDistance) throws IOException {
        double fuzzyMin = convertToFuzzyMinMax(minimumDistance, true);
        double fuzzyMax = convertToFuzzyMinMax(maximumDistance, false);
        Step firstStep = getFirstStep(startingIntersectionId);
        System.out.println("KICKING OFF THE ROUTE SCOUT AT TRAIL NO. " + startingIntersectionId);
        List<Route> eligibleRoutes = new ArrayList<Route>();
        int attemptNumber = 0;

        while(eligibleRoutes.size() < 1) {
                Route possibleRoute = new Route();
                possibleRoute.addStepToRoute(firstStep);
                boolean viable = true;
                boolean eligible = false;
            attemptNumber++;
            System.out.println("ATTEMPT NO. " + String.valueOf(attemptNumber));
            if (attemptNumber >= 300){
                return null;
            }
                while(viable && !eligible) {
                    if (possibleRoute.getTotalDistance() > fuzzyMax) {
                        viable = false;
                    }

                    Trail randomTrailToTry = getRandomTrailAtIntersection(possibleRoute.getLastIntersection().getId());
                    Step newStep = getNextStep(possibleRoute.getLastIntersection(), randomTrailToTry, pickADirection(randomTrailToTry, possibleRoute.getLastIntersection()));


                 //   if (!possibleRoute.doublesBackOverPreviousStep(newStep) && !possibleRoute.alreadyUsedTrailSegmentSameDirection(newStep)) {
                    if (!possibleRoute.doublesBackOverPreviousStep(newStep)) {
                            possibleRoute.addStepToRoute(newStep);
                    }

                    if (fuzzyMin < possibleRoute.getTotalDistance() && fuzzyMax > possibleRoute.getTotalDistance() && possibleRoute.endsAtRouteStart(newStep)) {
                        eligibleRoutes.add(possibleRoute);
                        eligible = true;
                    }
                }

        }
        for (Step step : eligibleRoutes.get(0).getSteps()) {
            System.out.println("GO TO: " + step.getEndingIntersection().getId());
        }
        System.out.println("TOTAL DISTANCE OF THIS ROUTE: " + eligibleRoutes.get(0).getTotalDistance());
        return eligibleRoutes.get(0);
    }

    private Step getFirstStep(int startingIntersectionId) throws IOException {
        Intersection startingIntersection = new Intersection(startingIntersectionId);
        Trail startingTrail = getRandomTrailAtIntersection(startingIntersection.getId());
        return getNextStep(startingIntersection, startingTrail, pickADirection(startingTrail, startingIntersection));
    }

    private Trail getRandomTrailAtIntersection(int intersectionId) throws IOException {
        return new Intersection(intersectionId).getListOfTrails().get((int)(Math.random() * new Intersection(intersectionId).getTrails().length));
    }

    private String determineLocationAlongTrail(Trail trail, Intersection currentIntersection) {
        if(currentIntersection.getId() == trail.getTerminusA()) {
            return "A";
        } else if(currentIntersection.getId() == trail.getTerminusB()) {
            return "B";
        }
        else return String.valueOf(currentIntersection.getId());
    }

    private Step getNextStep(Intersection startingintersection, Trail trail, String direction) throws IOException {
        Step nextStep = new Step();
        nextStep.setStartingIntersection(startingintersection);
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
            if (intersectionsAlongTrail[i] == startingintersection.getId()) {
                indexOfCurrentLocation = i;
            }
            terminusesAndIntersections.add(new Intersection(intersectionsAlongTrail[i]));
        }
        terminusesAndIntersections.add(new Intersection(trail.getTerminusB()));

        if(startingintersection.getId() == trail.getTerminusA() && !trail.isLoop()) {
            nextStep.setDistance(trail.getDistanceBetweenMidpoints()[0]);
            nextStep.setEndingIntersection(terminusesAndIntersections.get(1));
            return nextStep;
        }
        else if(startingintersection.getId() == trail.getTerminusB() && !trail.isLoop()) {
            nextStep.setDistance(trail.getDistanceBetweenMidpoints()[terminusesAndIntersections.size() - 2]);
            nextStep.setEndingIntersection(terminusesAndIntersections.get(terminusesAndIntersections.size() - 2));
            return nextStep;
        }
        else if(startingintersection.getId() == trail.getTerminusA() && trail.isLoop() || startingintersection.getId() == trail.getTerminusB() && trail.isLoop()) {
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

    private String pickADirection(Trail trail, Intersection intersection) {
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

    public double convertToFuzzyMinMax(double rawInputNum, boolean isMin) {
        double fuzzyNum = rawInputNum;
        if(isMin) {
            fuzzyNum = fuzzyNum - 0.15;
        } else fuzzyNum = fuzzyNum + 0.15;
        return fuzzyNum;
    }

}

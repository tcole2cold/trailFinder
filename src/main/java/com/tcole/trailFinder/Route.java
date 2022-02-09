package com.tcole.trailFinder;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private List<Step> steps;
    private double totalDistance;

    public Route() {
        this.steps = new ArrayList<Step>();
        this.totalDistance = 0.0;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public double getTotalDistance() {
        double distanceAdder = 0.0;
        for (Step step : steps) {
            distanceAdder += step.getDistance();
        }
        totalDistance = distanceAdder;
        return distanceAdder;
    }

    public Intersection getLastIntersection() {
        return steps.get(steps.size() - 1).getEndingIntersection();
    }

    public void addStepToRoute(Step newStep) {
        steps.add(newStep);
    }

    public boolean hasSteps() {
        if (steps.size() < 1) {
            return false;
        } else return true;
    }

    public boolean alreadyUsedTrailSegmentSameDirection(Step stepToScreen) {
        for (Step existingStep : steps) {
            if (existingStep.getStartingIntersection().getId() == stepToScreen.getStartingIntersection().getId()) {
                return true;
            } else if (existingStep.getEndingIntersection().getId() == stepToScreen.getEndingIntersection().getId()) {
                return true;
            }
        }
        return false;
    }

    public boolean endsAtRouteStart(Step stepToScreen) {
        if (steps.get(0).getStartingIntersection().getId() == stepToScreen.getEndingIntersection().getId()){
            return true;
        } else return false;
    }

    public boolean doublesBackOverPreviousStep(Step stepToScreen) {
        if (steps.get(steps.size() - 1).getStartingIntersection().getId() == stepToScreen.getEndingIntersection().getId()
             && steps.get(steps.size() - 1).getTrail().getId() == stepToScreen.getTrail().getId()){
            return true;
        } else return  false;
    }

}

package com.tcole.trailFinder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class MainController {

    @GetMapping("/")
    public String heartBeat() {
        return "API is up and running.";
    }

    @GetMapping("/printTrail")
    public String printTrail(@RequestParam int id) throws IOException {
        Trail trail = new Trail(id);
        return trail.printInfo();
    }

    @GetMapping("/printIntersection")
    public String printIntersection(@RequestParam int id) throws IOException {
        Intersection intersection = new Intersection(id);
        return intersection.printInfo();
    }

    @GetMapping("/printAllTrails")
    public List<String>
    printAllTrails() throws IOException {
        List<String> allTrails = new ArrayList<String>();
        Trail trailGeneric = new Trail(1);
        for (int i=1; i<trailGeneric.getNumberOfTrails(); i++) {
            Trail trail = new Trail(i);
            allTrails.add(trail.printInfo());
        }
        return allTrails;
    }

    @GetMapping("/printAllIntersections")
    public List<String>
    printAllIntersections() throws IOException {
        List<String> allIntersections = new ArrayList<String>();
        Intersection intersectionGeneric = new Intersection(1);
        for (int i=1; i<intersectionGeneric.getNumberOfIntersections(); i++) {
            Intersection intersection = new Intersection(i);
            allIntersections.add(intersection.printInfo());
        }
        return allIntersections;
    }

    @GetMapping("/getRoute")
    public Route getRoute(@RequestParam int id, @RequestParam int min, @RequestParam int max) throws IOException {
        RouteScout scout = new RouteScout();
        return scout.generateARandomRoute(id, min, max);
    }

}
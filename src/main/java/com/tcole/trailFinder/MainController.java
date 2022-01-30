package com.tcole.trailFinder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class MainController {

    @GetMapping("/heartbeat")
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
        for (int i=1; i<19; i++) {
            Trail trail = new Trail(i);
            allTrails.add(trail.printInfo());
        }
        return allTrails;
    }

    @GetMapping("/printAllIntersections")
    public List<String>
    printAllIntersections() throws IOException {
        List<String> allIntersections = new ArrayList<String>();
        for (int i=1; i<23; i++) {
            Intersection intersection = new Intersection(i);
            allIntersections.add(intersection.printInfo());
        }
        return allIntersections;
    }

}
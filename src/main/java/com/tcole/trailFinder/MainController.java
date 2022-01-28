package com.tcole.trailFinder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


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

}
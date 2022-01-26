package com.tcole.trailFinder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class MainController {

    @GetMapping("/heartbeat")
    public String heartBeat() {
        return "API is up and running.";
    }
    @GetMapping("/printTrail")
    public String printTrail() throws IOException {
        Trail trail = new Trail(1);
        return trail.printInfo();
    }

}
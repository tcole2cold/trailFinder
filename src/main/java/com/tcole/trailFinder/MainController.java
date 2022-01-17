package com.tcole.trailFinder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/heartbeat")
    public String heartBeat() {
        return "API is up and running.";
    }

}

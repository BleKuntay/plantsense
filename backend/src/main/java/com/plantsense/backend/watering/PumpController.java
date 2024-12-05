package com.plantsense.backend.watering;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/control")
public class PumpController {
    private String lastCommand = "TURN_OFF";

    @PostMapping
    public ResponseEntity<String> controlPump(@RequestBody String command) {
        lastCommand = command;
        return ResponseEntity.ok("Command received: " + command);
    }

    @GetMapping
    public ResponseEntity<String> getPumpStatus() {
        return ResponseEntity.ok(lastCommand);
    }
}

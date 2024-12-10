package org.pack.manager.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.pack.manager.api.util.TimeUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/test")
@Tag(name = "Test", description = "Test Controller")
public class TestController {

    @GetMapping
    @Operation(summary = "Execute the test")
    public ResponseEntity<Object> test(@RequestParam Map<String, String> queryParams) {
        TimeUtil.start("[test] test");
        TimeUtil.stopAndPrintElapsedTime();
        return ResponseEntity.accepted().build();
    }
}

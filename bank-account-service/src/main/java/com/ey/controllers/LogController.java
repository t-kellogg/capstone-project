package com.ey.controllers;

import com.ey.models.Log;
import com.ey.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping
    public ResponseEntity<List<Log>> getAllLogs() {
        List<Log> logs = (List<Log>) logService.getAllLogs();

        return ResponseEntity.ok(logs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Log> getLogById(@PathVariable("id") String id) {
        return ResponseEntity.ok(logService.getLog(Integer.parseInt(id)));
    }

    @PostMapping
    public ResponseEntity<Log> addBankAccount(@RequestBody Log log) {
        Log newLog = logService.addLog(log);
        return ResponseEntity.status(201).body(newLog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Log> updateLog(@RequestBody Log log, @PathVariable String id) {
        log.setId(Integer.parseInt(id));
        Log updatedLog = logService.updateLog(log);
        if (updatedLog != null) {
            return ResponseEntity.ok(updatedLog);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Log> deleteLog(@PathVariable String id) {

        Optional<Log> logOptional = Optional.of(logService.getLog(Integer.parseInt(id)));
        if (logOptional.isPresent()) {
            boolean deleted = logService.deleteLog(Integer.parseInt(id));
            if (deleted)
                return ResponseEntity.ok(logOptional.get());
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.badRequest().build();
    }


}

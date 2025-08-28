package com.example.task.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AgeController {

    @PostMapping("/calculate-age")
    public ResponseEntity<Map<String, String>> calculateAge(@RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        try {
            String birthdayStr = request.get("birthday");
            LocalDate birthday = LocalDate.parse(birthdayStr);
            LocalDateTime birthDateTime = birthday.atStartOfDay();
            LocalDateTime now = LocalDateTime.now();

            if (birthDateTime.isAfter(now)) {
                response.put("error", "Invalid date");
                return ResponseEntity.badRequest().body(response);
            }

            // Calculate years, months, and days
            Period period = Period.between(birthday, now.toLocalDate());

            // Calculate extra hours difference
            long hours = ChronoUnit.HOURS.between(birthday.plusYears(period.getYears())
                                                             .plusMonths(period.getMonths())
                                                             .plusDays(period.getDays())
                                                             .atStartOfDay(), now);

            String age = String.format("%d years, %d months, %d days, %d hours",
                    period.getYears(), period.getMonths(), period.getDays(), hours);

            response.put("age", age);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("error", "Invalid date");
            return ResponseEntity.badRequest().body(response);
        }
    }
}

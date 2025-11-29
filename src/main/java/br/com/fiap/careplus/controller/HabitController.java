package br.com.fiap.careplus.controller;

import br.com.fiap.careplus.model.Habit;
import br.com.fiap.careplus.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    @Autowired
    private HabitService habitService;

    @GetMapping("/user/{userId}")
    public List<Habit> getHabitsByUserId(@PathVariable Long userId) {
        return habitService.findByUserId(userId);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Habit> createHabit(@PathVariable Long userId, @Valid @RequestBody Habit habit) {
        Habit createdHabit = habitService.createHabit(userId, habit);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHabit);
    }

    @PatchMapping("/{habitId}/progress")
    public ResponseEntity<Habit> updateProgress(@PathVariable Long habitId, @RequestParam Integer value) {
        Habit updatedHabit = habitService.updateProgress(habitId, value);
        return ResponseEntity.ok(updatedHabit);
    }
}

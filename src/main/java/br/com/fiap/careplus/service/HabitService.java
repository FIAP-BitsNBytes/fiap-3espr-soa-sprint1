package br.com.fiap.careplus.service;

import br.com.fiap.careplus.model.Habit;
import br.com.fiap.careplus.model.User;
import br.com.fiap.careplus.repository.HabitRepository;
import br.com.fiap.careplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Habit> findByUserId(Long userId) {
        return habitRepository.findByUserId(userId);
    }

    public Habit createHabit(Long userId, Habit habit) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        habit.setUser(user);
        return habitRepository.save(habit);
    }

    public Habit updateProgress(Long habitId, Integer value) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new IllegalArgumentException("Habit not found"));
        habit.setCurrentValue(habit.getCurrentValue() + value);
        return habitRepository.save(habit);
    }
}

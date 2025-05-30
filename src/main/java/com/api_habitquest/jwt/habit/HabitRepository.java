package com.api_habitquest.jwt.habit;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_habitquest.jwt.user.User;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findByUsuario(User usuario);
}

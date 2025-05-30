package com.api_habitquest.jwt.habit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.api_habitquest.jwt.user.User;
import com.api_habitquest.jwt.user.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@CrossOrigin(origins = "*") // Permite llamadas desde cualquier origen, útil para Unity u otras apps
public class HabitController {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Obtiene la lista de hábitos del usuario autenticado.
     * Retorna 401 si el usuario no está autenticado.
     */
    @GetMapping
    public ResponseEntity<List<HabitDTO>> getHabitsForCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return ResponseEntity.status(401).build(); // No autorizado
        }

        List<Habit> habits = habitRepository.findByUsuario(user);

        List<HabitDTO> habitDTOs = habits.stream()
                                        .map(HabitDTO::new)
                                        .toList();

        return ResponseEntity.ok(habitDTOs);
    }

    /**
     * Crea un nuevo hábito para el usuario autenticado.
     * Retorna 401 si el usuario no está autenticado.
     */
    @PostMapping
    public ResponseEntity<HabitDTO> createHabit(@RequestBody HabitDTO habitDTO) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        Habit habit = new Habit();
        habit.setNombre(habitDTO.getNombre());
        habit.setFechaLimite(habitDTO.getFechaLimite());
        habit.setHecho(habitDTO.isHecho());
        habit.setUsuario(user);

        Habit savedHabit = habitRepository.save(habit);
        return ResponseEntity.ok(new HabitDTO(savedHabit));
    }

    /**
     * Actualiza un hábito existente solo si pertenece al usuario autenticado.
     * Retorna 401 si no está autenticado, 403 si el hábito no pertenece al usuario.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable Long id, @RequestBody Habit updatedHabit) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        return habitRepository.findById(id)
                .filter(habit -> habit.getUsuario().getId().equals(user.getId()))
                .map(habit -> {
                    habit.setNombre(updatedHabit.getNombre());
                    habit.setFechaLimite(updatedHabit.getFechaLimite());
                    habit.setHecho(updatedHabit.isHecho());
                    return ResponseEntity.ok(habitRepository.save(habit));
                })
                .orElse(ResponseEntity.status(403).build()); // Forbidden si el hábito no pertenece al usuario
    }

    /**
     * Elimina un hábito solo si pertenece al usuario autenticado.
     * Retorna 401 si no está autenticado, 403 si el hábito no pertenece al usuario.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHabit(@PathVariable Long id) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        return habitRepository.findById(id)
                .filter(habit -> habit.getUsuario().getId().equals(user.getId()))
                .map(habit -> {
                    habitRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.status(403).build());
    }
}

package com.api_habitquest.jwt.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.api_habitquest.jwt.habit.Habit;
import com.api_habitquest.jwt.token.Token;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;
  
  // Relación uno a muchos con la entidad Token: un usuario puede tener varios tokens JWT activos o revocados
  @OneToMany(mappedBy = "user")
  private List<Token> tokens;
  
  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Habit> habits;
}

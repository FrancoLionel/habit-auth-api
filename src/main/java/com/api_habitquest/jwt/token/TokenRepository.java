package com.api_habitquest.jwt.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  // Query personalizada para obtener todos los tokens válidos (no expirados y no revocados)
    // que pertenecen a un usuario dado (por su id).
  @Query("""
    select t from Token t
    inner join t.user u
    where u.id = :id and (t.isExpired = false and t.isRevoked = false)
""")
List<Token> findAllValidTokenByUser(Integer id);

// Busca un token exacto por su valor en la base de datos
  Optional<Token> findByToken(String token);
}

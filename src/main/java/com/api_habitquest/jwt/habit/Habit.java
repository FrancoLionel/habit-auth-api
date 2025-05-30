package com.api_habitquest.jwt.habit;

import com.api_habitquest.jwt.user.User;

import jakarta.persistence.*;


@Entity
@Table(name = "habits")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "fecha_limite") 
    private String fechaLimite;
    
    private boolean hecho;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User usuario;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public User getUsuario() {
        return usuario;
    }

    public boolean isHecho() {
        return hecho;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public void setHecho(boolean hecho) {
        this.hecho = hecho;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}

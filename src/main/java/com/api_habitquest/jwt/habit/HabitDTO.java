package com.api_habitquest.jwt.habit;

public class HabitDTO {

    private Long id;
    private String nombre;
    private String fechaLimite;
    private boolean hecho;

    public HabitDTO(Habit habit) {
        this.id = habit.getId();
        this.nombre = habit.getNombre();
        this.fechaLimite = habit.getFechaLimite();
        this.hecho = habit.isHecho();
    }

    public HabitDTO() {
      
    }

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(String fechaLimite) { this.fechaLimite = fechaLimite; }
    public boolean isHecho() { return hecho; }
    public void setHecho(boolean hecho) { this.hecho = hecho; }
}

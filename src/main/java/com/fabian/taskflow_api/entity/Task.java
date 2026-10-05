package com.fabian.taskflow_api.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tareas")
public class Task
{
    @Id
    @GeneratedValue
    private UUID idTask;
    private String nombreTarea;
    private String descripcionTarea;
    private LocalDate fechaTarea;
    @Enumerated(EnumType.STRING)
    private Priority prioridad;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    public Task() {
    }

    public Task(String nombreTarea, String descripcionTarea, LocalDate fechaTarea, Priority prioridad, Status status, User user) {
        this.nombreTarea = nombreTarea;
        this.descripcionTarea = descripcionTarea;
        this.fechaTarea = fechaTarea;
        this.prioridad = prioridad;
        this.status = status;
        this.user = user;
    }


    public UUID getIdTask() {
        return idTask;
    }

    public void setIdTask(UUID idTask) {
        this.idTask = idTask;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }

    public LocalDate getFechaTarea() {
        return fechaTarea;
    }

    public void setFechaTarea(LocalDate fechaTarea) {
        this.fechaTarea = fechaTarea;
    }

    public Priority getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Priority prioridad) {
        this.prioridad = prioridad;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

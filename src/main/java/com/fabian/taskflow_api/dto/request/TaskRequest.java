package com.fabian.taskflow_api.dto.request;

import com.fabian.taskflow_api.entity.Priority;
import com.fabian.taskflow_api.entity.Status;
import com.fabian.taskflow_api.entity.User;
import com.google.api.client.util.DateTime;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

public class TaskRequest
{
    String nombreTarea;
    String descripcionTarea;
    Priority prioridad;
    private User user;

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

    public Priority getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Priority prioridad) {
        this.prioridad = prioridad;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

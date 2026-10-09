package com.fabian.taskflow_api.dto.response;

import com.fabian.taskflow_api.entity.Priority;
import com.fabian.taskflow_api.entity.Status;

import java.time.LocalDate;
import java.util.UUID;

public record TaskResponse
(
    UUID idTarea,
    String nombreTarea,
    String descripcionTarea,
    LocalDate fechaTarea,
    Priority prioridad,
    Status status,
    String user
) {};

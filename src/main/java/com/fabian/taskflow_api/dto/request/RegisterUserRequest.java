package com.fabian.taskflow_api.dto.request;

import com.fabian.taskflow_api.entity.Sexo;
import com.fabian.taskflow_api.validation.ValidAuthentication;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@ValidAuthentication
public class RegisterUserRequest implements AuthenticationRequest
{
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apellidoPaterno;
    @NotEmpty
    private String apellidoMaterno;
    @NotNull
    private Sexo sexo;
    @NotBlank(message = "El email es requerido")
    @Email(message = "El email debe tener un formato válido")
    private String email;
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número"
    )
    private String password;
	private String googleCredential;
    @Past
    private LocalDate fechaNacimiento;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
	
	@Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
	
	@Override
	public String getGoogleCredential() {
        return googleCredential;
    }

    public void setGoogleCredential(String googleCredential) {
        this.googleCredential = googleCredential;
    }

}

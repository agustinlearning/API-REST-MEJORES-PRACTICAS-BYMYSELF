package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistroMedico(
        @NotBlank String nombre,
        @NotBlank @Email String email,
        @NotBlank String telefono,
        @NotBlank @Pattern(regexp = "\\d{7,9}") String documento, //el documento de argentina usa entre 7 y 9 caracteres (cambiar esto segund el país)
        @NotNull Especialidad especialidad, //al ser un enum spring se encarga de tratar si está correcto o no
        @NotNull @Valid DatosDireccion direccion) {
}

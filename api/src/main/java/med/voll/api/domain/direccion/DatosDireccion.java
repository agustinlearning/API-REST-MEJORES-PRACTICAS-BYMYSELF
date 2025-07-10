package med.voll.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosDireccion(
        @NotBlank String calle,
        String numero,
        String complemento,
        @NotBlank String barrio,
        @NotBlank @Pattern(regexp = "\\d{4}")String codigo_postal,
        @NotBlank String ciudad, //este 4 sigue el patron de argentina (cambiar segun el pais   )
        @NotBlank String estado) {
}

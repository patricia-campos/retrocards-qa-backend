package aceitacao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class AuthUserLogadoDTO {
    private String idUser;
    private String name;
    private String email;
    private String role;

}

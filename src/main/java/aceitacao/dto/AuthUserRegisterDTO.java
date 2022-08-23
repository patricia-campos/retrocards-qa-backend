package aceitacao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class AuthUserRegisterDTO {
    private String idUser;
    private String name;
    private String email;
    private String role;
}

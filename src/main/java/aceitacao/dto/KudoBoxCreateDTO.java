package aceitacao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class KudoBoxCreateDTO {
    private String idKudoBox;
    private String title;
    private String endDate;
    private String status;
}





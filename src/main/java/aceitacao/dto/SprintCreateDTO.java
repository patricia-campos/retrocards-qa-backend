package aceitacao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class SprintCreateDTO {

    private String title;
    private String startDate;
    private String endDate;
    private String idSprint;
    private String status;

}


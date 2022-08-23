package aceitacao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class SprintListDTO {
    private String idSprint;
    private String title;
    private String startDate;
    private String endDate;
    private String status;
    private String verifyRetrospective;
    private String verifyKudoBox;

    }

package aceitacao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class RetrospectiveDTO {

    private String idRetrospective;
    private String title;
    private String occurredDate;
    private String status;
    private String sprint;
}






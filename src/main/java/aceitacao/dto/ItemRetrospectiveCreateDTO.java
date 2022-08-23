package aceitacao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class ItemRetrospectiveCreateDTO {
    private String idItemRetrospective;
    private String idRetrospective;
    private String type;
    private String title;
    private String description;
}





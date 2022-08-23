package aceitacao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class KudoCardCreateDTO {

    private String idKudoCard;
    private String idCreator;
    private String title;
    private String createDate;
    private String sender;
    private String receiver;
    private String description;
}




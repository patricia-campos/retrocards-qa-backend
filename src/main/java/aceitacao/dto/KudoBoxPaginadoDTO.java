package aceitacao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class KudoBoxPaginadoDTO {
    private String totalElements;
    private String totalPages;
    private String page;
    private String size;
    private List<KudoBoxListDTO> content;
}

package aceitacao;


import aceitacao.dto.*;
import aceitacao.service.KudoCardService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class KudoCardAceitacao {
    KudoCardService kudoCardService = new KudoCardService();

    // MASSA DE DADOS PARA O SERVICE
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }


    @Test
    public void criarKudoCardComSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/kudoCardCadastro.json");

        // Adição de sprint através do Json:
        KudoCardCreateDTO resultService = kudoCardService.criarKudoBoxComSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getTitle(), "teste");
        Assert.assertEquals(resultService.getReceiver(), "teste@teste.com");
    }

    @Test
    public void criarKudoCardNaoLogadoSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/kudoCardCadastro.json");

        Response resultService = kudoCardService.criarKudoBoxNaoLogadoSemSucesso(jsonBody);

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 403);

    }

    @Test
    public void criarKudoCardKudoBoxInexistenteSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/kudoCardCadastroErroInexistente.json");
        Object status = 400;

        ResponseErroDTO resultService = kudoCardService.criarKudoBoxInexistenteSemSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getMessage(), "Kudobox não encontrada.");
        // Validação:
    }

    @Test
    public void criarKudoCardKudoBoxTitleMenorSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/kudoCardCadastroErroTitleMenor.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of( "title: size must be between 3 and 60"));
        ResponseErroDTO resultService = kudoCardService.criarKudoBoxInexistenteSemSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
        // Validação:
    }

    @Test
    public void criarKudoCardKudoBoxTitleMaiorSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/kudoCardCadastroErroTitleMaior.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of( "title: size must be between 3 and 60"));
        ResponseErroDTO resultService = kudoCardService.criarKudoBoxInexistenteSemSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
        // Validação:

    }

    @Test
    public void consultarKudoCardPorKudoBoxComSucesso() throws IOException {

        Integer idKudoBox = 6;

        KudoCardPaginadoDTO resultService = kudoCardService.consultarKudoCardPorKudoBoxComSucesso(idKudoBox,0, 2);

        // Validação:
        Assert.assertEquals(resultService.getPage(), "0");
        Assert.assertEquals(resultService.getSize(), "2");
    }


    @Test
    public void consultarKudoCardPorKudoBoxSemSucesso() {

        Integer idKudoBox = 6;
        Response resultService = kudoCardService.consultarKudoCardPorKudoBoxSemSucesso(idKudoBox,0, 0);

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 500);
    }

    @Test
    public void consultarKudoCardPorKudoBoxNaoLogadpoSemSucesso() {

        Integer idKudoBox = 6;
        Response resultService = kudoCardService.consultarKudoCardPorKudoBoxNaoLogadoSemSucesso(idKudoBox,0, 2);

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

    @Test
    public void deletarKudoCardComSucesso() throws IOException {

        // Adição:
        KudoCardCreateDTO resulService = adicionarKudoCardJson();

        Response resultService = kudoCardService.deletarKudoCardComSucesso(Integer.parseInt(resulService.getIdKudoCard()));

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 200);
    }

    @Test
    public void deletarKudoCardKudoBoxNaoProgresoSemSucesso() throws IOException {

        Integer idKudoBox = 6;
        Object status = 400;

        ResponseErroDTO resultService = kudoCardService.deletarKudoCardKudoBoxnaoProgressoSemSucesso(idKudoBox);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getMessage(), "Você não é o criador desse kudo card.");
    }

    @Test
    public void editarKudoCardComSucesso() throws IOException {

        Integer idKudoCard = 6;

        // Leitura da massa de dados:
        String jsonBody = lerJson("src/test/resources/data/KudoCardEditada.json");

        KudoCardCreateDTO resultService = kudoCardService.editarKudoCardComSucesso(idKudoCard, jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getTitle(), "teste");
        Assert.assertEquals(resultService.getDescription(), "teste");
    }

    @Test
    public void editarKudoCardNaoLogadoSemSucesso() throws IOException {

        Integer idKudoCard = 4;

        // Leitura da massa de dados:
        String jsonBody = lerJson("src/test/resources/data/KudoCardEditada.json");

        Response resultService = kudoCardService.editarKudoCardNaoLogadoSemSucesso(idKudoCard, jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

    public KudoCardCreateDTO adicionarKudoCardJson() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/kudoCardCadastro.json");

        return kudoCardService.criarKudoBoxComSucesso(jsonBody);
    }
}

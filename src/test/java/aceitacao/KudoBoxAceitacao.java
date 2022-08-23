package aceitacao;

import aceitacao.dto.KudoBoxCreateDTO;
import aceitacao.dto.KudoBoxPaginadoDTO;
import aceitacao.dto.ResponseErroDTO;
import aceitacao.service.KudoBoxService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class KudoBoxAceitacao {
    KudoBoxService kudoBoxService = new KudoBoxService();

    // MASSA DE DADOS PARA O SERVICE
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test
    public void criarKudoBoxComSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/kudoBoxCadastro.json");

        // Adição de sprint através do Json:
        KudoBoxCreateDTO resultService = kudoBoxService.criarKudoBoxComSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getTitle(), "kudo box controller");
        Assert.assertEquals(resultService.getStatus(), "IN_PROGRESS");
    }

    @Test
    public void criarKudoBoxNaoLogadoSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/kudoBoxCadastro.json");

        // Adição de sprint através do Json:
        Response resultService = kudoBoxService.criarKudoBoxNaoLogadoSemSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

    @Test
    public void criarKudoBoxInexistenteSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/KudoBoxCadastroSprintInexistente.json");
        Object status = 400;

        ResponseErroDTO resultService = kudoBoxService.criarKudoBoxInexistenteSemSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getMessage(), "Sprint não encontrada!");
        // Validação:
    }

    @Test
    public void criarKudoBoxTitleMenorSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/KudoBoxCadastroErroTitleMenor.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("title: size must be between 3 and 60"));
        ResponseErroDTO resultService = kudoBoxService.criarKudoBoxInexistenteSemSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
        // Validação:
    }

    @Test
    public void criarKudoBoxTitleMaiorSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/KudoBoxCadastroErroTitleMaior.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("title: size must be between 3 and 60"));
        ResponseErroDTO resultService = kudoBoxService.criarKudoBoxInexistenteSemSucesso(jsonBody);


        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
    }

    @Test
    public void editarKudoBoxComSucesso() throws IOException {

        Integer idKudoBox = 4;

        // Leitura da massa de dados:
        String jsonBody = lerJson("src/test/resources/data/KudoBoxEditada.json");

        KudoBoxCreateDTO resultService = kudoBoxService.editarKudoBoxComSucesso(4, jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getTitle(), "teste");
        Assert.assertEquals(resultService.getStatus(), "FINISHED");
    }

    @Test
    public void editarKudoBoxNaoLogadoSemSucesso() throws IOException {

        Integer idKudoBox = 4;

        // Leitura da massa de dados:
        String jsonBody = lerJson("src/test/resources/data/KudoBoxEditada.json");

        Response resultService = kudoBoxService.editarKudoBoxNaoLogadoSemSucesso(4, jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

    @Test
    public void consultarKudoBoxPorSprintComSucesso() throws IOException {

        Integer idSprint = 1;

        KudoBoxPaginadoDTO resultService = kudoBoxService.consultarKudoBoxPorSprintComSucesso(idSprint, 0, 2);

        // Validação:
        Assert.assertEquals(resultService.getPage(), "0");
        Assert.assertEquals(resultService.getSize(), "2");
    }

    @Test
    public void consultarKudoBoxPorSprintNaoLogadoSemSucesso() {

        Integer idSprint = 1;
        Response resultService = kudoBoxService.consultarKudoBoxPorSprintNaoLogadoSemSucesso(idSprint, 0, 2);

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

    @Test
    public void consultarKudoBoxPorKudoBoxComSucesso() throws IOException {

        Integer idKudoBox = 1;

        KudoBoxCreateDTO resultService = kudoBoxService.consultarKudoBoxPorKudoBoxComSucesso(idKudoBox);

        // Validação:
        Assert.assertEquals(resultService.getStatus(), "FINISHED");
        Assert.assertEquals(resultService.getTitle(), "Melhorias");
    }

    @Test
    public void consultarKudoBoxPorKudoBoxNaoLogadoSemSucesso() {

        Integer idKudoBox = 1;
        Response resultService = kudoBoxService.consultarKudoBoxPorKudoBoxNaoLogadoSemSucesso(idKudoBox);

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

}

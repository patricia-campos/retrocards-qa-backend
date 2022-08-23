package aceitacao;

import aceitacao.dto.ResponseErroDTO;
import aceitacao.dto.SprintCreateDTO;
import aceitacao.dto.SprintListDTO;
import aceitacao.dto.SprintPaginadoDTO;
import aceitacao.service.SprintService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SprintAceitacao {


    SprintService sprintService = new SprintService();

    // MASSA DE DADOS PARA O SERVICE
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test
    public void consultarSprintComSucesso() throws IOException {

        Integer idSprint = 20;

        SprintListDTO resultService = sprintService.consultarSprintComSucesso(idSprint);

        // Validação:
        Assert.assertEquals(resultService.getTitle(), "teste");
        Assert.assertEquals(resultService.getStatus(), "IN_PROGRESS");
    }

    @Test
    public void consultarSprintNaoLogadoSemSucesso() throws IOException {

        Integer idSprint = 20;

        Response resultService = sprintService.consultarSprintNaoLogadoSemSucesso(idSprint);

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 403);

    }

    @Test
    public void consultarSprintPaginadoComSucesso() throws IOException {

        SprintPaginadoDTO resultService = sprintService.consultarSprintPaginadoComSucesso(0, 2);

        // Validação:
        Assert.assertEquals(resultService.getPage(), "0");
        Assert.assertEquals(resultService.getSize(), "2");
    }


    @Test
    public void consultarSprintPaginadoNaoLogadoSemSucesso() throws IOException {

        Response resultService = sprintService.consultarSprintPaginadoNaoLogadoSemSucesso(0, 2);

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 403);

    }

    @Test
    public void consultarSprintPaginadoSemSucesso() {

        Response resultService = sprintService.consultarSprintPaginadoSemSucesso(0, 0);

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 500);
    }

    @Test
    public void criarSprintComSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/sprintCadastro.json");

        // Adição de sprint através do Json:
        SprintCreateDTO resultService = sprintService.criarSprintComSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getTitle(), "Análise final de Projeto - Cliente: Lojas Renner");
    }


    @Test
    public void criarSprintNaoLogadoSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/sprintCadastro.json");

        // Adição de sprint através do Json:
        Response resultService = sprintService.criarSprintNaoLogadoSemSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

    @Test
    public void criarSprintTitleMenorSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/sprintCadastroErroTitleMenor.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("title: O título da sprint deve conter no mínimo 3 e no máximo 60 caracteres."));
        ResponseErroDTO resultService = sprintService.criarSprintInexistenteSemSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
    }

    @Test
    public void criarSprintTitleMaiorSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/sprintCadastroErroTitleMaior.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("title: O título da sprint deve conter no mínimo 3 e no máximo 60 caracteres."));
        ResponseErroDTO resultService = sprintService.criarSprintInexistenteSemSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);

    }

    @Test
    public void criarSprintInicioPassadoSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/sprintCadastroErroInicioPassado.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("startDate: must be a date in the present or in the future"));
        ResponseErroDTO resultService = sprintService.criarSprintInexistenteSemSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
    }

    @Test
    public void criarSprintConclusaoPassadoSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/sprintCadastroErroConclusaoPassado.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("endDate: must be a date in the present or in the future"));
        ResponseErroDTO resultService = sprintService.criarSprintInexistenteSemSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
    }

    @Test
    public void criarSprintInicioMaiorSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/sprintCadastroErroInicioMaior.json");
        Object status = 400;
        //List<String> errors = new ArrayList<>(List.of( "endDate: must be a date in the present or in the future"));
        ResponseErroDTO resultService = sprintService.criarSprintInexistenteSemSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getMessage(), "A data final deve ser posterior a data de início.");
    }


    @Test
    public void editarSprintComSucesso() throws IOException {

        Integer idSprint = 20;

        // Leitura da massa de dados:
        String jsonBody = lerJson("src/test/resources/data/SprintEditada.json");

        SprintCreateDTO resultService = sprintService.editarSprintComSucesso(idSprint, jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getTitle(), "teste");
        Assert.assertEquals(resultService.getStatus(), "IN_PROGRESS");
    }

    @Test
    public void editarSprintNaoLogadoSemSucesso() throws IOException {

        Integer idSprint = 20;

        // Leitura da massa de dados:
        String jsonBody = lerJson("src/test/resources/data/SprintEditada.json");

        Response resultService = sprintService.editarSprintNaoLogadoSemSucesso(idSprint, jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }


    @Test
    public void deletaSprintComSucesso() throws IOException {

        // Adição:
        SprintCreateDTO resulService = adicionarSprintJson();

        Response resultService = sprintService.deletarSprintComSucesso(Integer.parseInt(resulService.getIdSprint()));

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 200);
    }


    public SprintCreateDTO adicionarSprintJson() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/sprintCadastro.json");

        return sprintService.criarSprintComSucesso(jsonBody);
    }
}
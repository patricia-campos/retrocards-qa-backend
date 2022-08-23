package aceitacao;

import aceitacao.dto.*;
import aceitacao.service.RetrospectiveService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RetrospectiveAceitacao {


   RetrospectiveService retrospectiveService = new RetrospectiveService();

    // MASSA DE DADOS PARA O SERVICE
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test
    public void consultarRetrospectivePorIDSprintComSucesso() throws IOException {

        Integer idSprint = 1;

        RetrospectivePaginadoDTO resultService = retrospectiveService.consultarRetrospectivePorIDSprintComSucesso(idSprint,0, 2);

        // Validação:
        Assert.assertEquals(resultService.getPage(), "0");
        Assert.assertEquals(resultService.getSize(), "2");
    }

    @Test
    public void consultarRetrospectivePorIDSprintSemNaoLogadoSucesso() throws IOException {

        Integer idSprint = 1;

        Response resultService = retrospectiveService.consultarRetrospectivePorIDSprintSemNaoLogadoSucesso(idSprint,0, 2);

        // Validações:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }


    @Test
    public void consultarRetrospectiveComSucesso() throws IOException {

        Integer idRetrospective = 1;

        RetrospectiveCreateDTO resultService = retrospectiveService.consultarRetrospectiveComSucesso(idRetrospective);

        // Validação:
        Assert.assertEquals(resultService.getTitle(), "Divisão das equipes em trios");
        Assert.assertEquals(resultService.getStatus(), "FINISHED");
    }

    @Test
    public void consultarRetrospectiveNaoLogadoSemSucesso() throws IOException {

        Integer idRetrospective = 1;

        Response resultService = retrospectiveService.consultarRetrospectiveNaoLogadoSemSucesso(idRetrospective);

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

    @Test
    public void retrospectiveCreate() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/retrospectiveCadastro.json");

        // Adição de login através do Json:
        RetrospectiveCreateDTO resultService = retrospectiveService.retrospectiveCreate(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getTitle(), "retrospective");
        Assert.assertEquals(resultService.getStatus(), "CREATE");
    }

    @Test
    public void retrospectiveCreateNaoLogadoSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/retrospectiveCadastro.json");

        // Adição de login através do Json:
        Response resultService = retrospectiveService.retrospectiveCreateNaoLogadoSemSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

    @Test
    public void criarRetrospectiveTitleMenorSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/retrospectiveCadastroErroDescMenor.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of( "title: size must be between 3 and 60"));
        ResponseErroDTO resultService = retrospectiveService.criarRetrospectiveInexistenteSemSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
        // Validação:

    }

    @Test
    public void criarRetrospectiveTitleMaiorSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/retrospectiveCadastroErroDescMaior.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of( "title: size must be between 3 and 60"));
        ResponseErroDTO resultService = retrospectiveService.criarRetrospectiveInexistenteSemSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
    }


    @Test
    public void editarRetrospectiveComSucesso() throws IOException {

        Integer idRestrospective = 44;

        // Leitura da massa de dados:
        String jsonBody = lerJson("src/test/resources/data/retrospectiveEditada.json");

        RetrospectiveCreateDTO resultService = retrospectiveService.editarRetrospectiveComSucesso(idRestrospective, jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getTitle(), "teste");
        Assert.assertEquals(resultService.getStatus(), "CREATE");
    }

    @Test
    public void editarRetrospectiveNaoLogadoSemSucesso() throws IOException {

        Integer idRestrospective = 10;

        // Leitura da massa de dados:
        String jsonBody = lerJson("src/test/resources/data/retrospectiveEditada.json");

        Response resultService = retrospectiveService.editarRetrospectiveNaoLogadoSemSucesso(idRestrospective, jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

    @Test
    public void updateStatus() throws IOException {

        Integer idRetrospective = 10;
        String status = "CREATE";

        RetrospectiveCreateDTO resultService = retrospectiveService.updateStatus(idRetrospective, status);

        // Validações:
        Assert.assertEquals(resultService.getIdRetrospective(), "10");
        Assert.assertEquals(resultService.getStatus(), "CREATE");
    }

    @Test
    public void updateStatusNaoLogadoSemSucesso() throws IOException {

        Integer idRetrospective = 10;
        String status = "CREATE";

        Response resultService = retrospectiveService.updateStatusNaoLogadoSemSucesso(idRetrospective, status);

        // Validações:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

}

package aceitacao;

import aceitacao.dto.ItemRetrospectiveCreateDTO;
import aceitacao.dto.ResponseErroDTO;
import aceitacao.service.ItemRetrospectiveService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ItemRetrospectiveAceitacao {
    ItemRetrospectiveService itemRetrospectiveService = new ItemRetrospectiveService();

    // MASSA DE DADOS PARA O SERVICE
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test
    public void criarItemRetrospectiveComSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/ItemRetrospectiveCadastro.json");
        String itemType = "WORKED";

        // Adição de sprint através do Json:
        ItemRetrospectiveCreateDTO resultService = itemRetrospectiveService.criarItemRetrospectiveComSucesso(jsonBody, itemType);

        // Validações:
        Assert.assertEquals(resultService.getTitle(), "item");
        Assert.assertEquals(resultService.getDescription(), "TESTE ITEM");
    }

    @Test
    public void criarItemRetrospectiveInexistenteSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/ItemRetrospectiveInexistente.json");
        String itemType = "WORKED";
        Object status = 400;

        // Adição de login com e-mail em branco através do Json:
        ResponseErroDTO resultService = itemRetrospectiveService.criarItemRetrospectiveInexistenteSemSucesso(jsonBody, itemType);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getMessage(), "Retrospectiva não encontrada");
    }

    @Test
    public void criarItemRetrospectiveCamposVaziosSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/ItemRetrospectiveCamposVazios.json");
        String itemType = "WORKED";
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("title: size must be between 3 and 60", "description: must not be blank", "title: must not be blank"));


        // Adição de login com e-mail em branco através do Json:
        ResponseErroDTO resultService = itemRetrospectiveService.criarItemRetrospectiveInexistenteSemSucesso(jsonBody, itemType);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
    }

    @Test
    public void criarItemRetrospectiveCaracteresInferioresSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/ItemRetrospectiveCaracteresInferiores.json");
        String itemType = "WORKED";
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("title: size must be between 3 and 60"));


        // Adição de login com e-mail em branco através do Json:
        ResponseErroDTO resultService = itemRetrospectiveService.criarItemRetrospectiveInexistenteSemSucesso(jsonBody, itemType);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
    }

    @Test
    public void criarItemRetrospectiveCaracteresSuperioresSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/ItemRetrospectiveCaracteresSuperiores.json");
        String itemType = "WORKED";
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("title: size must be between 3 and 60"));


        // Adição de login com e-mail em branco através do Json:
        ResponseErroDTO resultService = itemRetrospectiveService.criarItemRetrospectiveInexistenteSemSucesso(jsonBody, itemType);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
    }

    @Test
    public void consultarItemRetrospectivePorRetrospectiveComSucesso() throws IOException {

        Integer idRetrospective = 5;

        ItemRetrospectiveCreateDTO[] resultService = itemRetrospectiveService.consultarItemRetrospectivePorRetrospectiveComSucesso(idRetrospective);

        // Validação:
        Assert.assertEquals(resultService[0].getDescription(), "TESTE ITEM");
    }

    @Test
    public void consultarItemRetrospectivePorRetrospectiveSemSucesso() throws IOException {

        Integer idRetrospective = 1000;
        Object status = 400;

        ResponseErroDTO resultService = itemRetrospectiveService.consultarItemRetrospectivePorRetrospectiveSemSucesso(idRetrospective);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getMessage(), "Retrospectiva não encontrada");
        // Validação:
    }

    @Test
    public void consultarItemRetrospectivePorRetrospectiveSemNaoLogadoSemSucesso() {

        Integer idRetrospective = 1000;

        Response resultService = itemRetrospectiveService.consultarItemRetrospectivePorRetrospectiveNaoLogadoSemSucesso(idRetrospective);

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

    @Test
    public void deletarItemRetrospectiveComSucesso() throws IOException {

        // Adição:
        ItemRetrospectiveCreateDTO resulService = adicionarItemRetrospectiveJson();

        Response resultService = itemRetrospectiveService.deletarItemRetrospectiveComSucesso(Integer.parseInt(resulService.getIdItemRetrospective()));

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 200);
    }

    @Test
    public void deletarItemRetrospectiveNaoLogadoSemSucesso() throws IOException {

        // Adição:
        ItemRetrospectiveCreateDTO resulService = adicionarItemRetrospectiveJson();

        Response resultService = itemRetrospectiveService.deletarItemRetrospectiveNaoLogadoSemSucesso(Integer.parseInt(resulService.getIdItemRetrospective()));

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

    public ItemRetrospectiveCreateDTO adicionarItemRetrospectiveJson() throws IOException {

        String itemType = "WORKED";
        String jsonBody = lerJson("src/test/resources/data/ItemRetrospectiveCadastro.json");

        return itemRetrospectiveService.criarItemRetrospectiveComSucesso(jsonBody, itemType);
    }
}

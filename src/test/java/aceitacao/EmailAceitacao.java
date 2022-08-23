package aceitacao;

import aceitacao.service.EmailService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EmailAceitacao {

    EmailService emailService = new EmailService();

    // MASSA DE DADOS PARA O SERVICE
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test
    public void enviarEmailComSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/emailEnviar.json");
        Integer idRetrospective = 10;

        Response resultService = emailService.enviarEmailSucesso(idRetrospective, jsonBody);

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 200);
    }


    @Test
    public void enviarEmailNaoLogadoSemSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/emailEnviar.json");
        Integer idRetrospective = 10;

        Response resultService = emailService.enviarEmailNaoLogadoSemSucesso(idRetrospective, jsonBody);

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

}

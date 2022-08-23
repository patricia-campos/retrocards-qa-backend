package aceitacao;

import aceitacao.dto.*;
import aceitacao.service.AuthService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AuthAceitacao {

    AuthService authService = new AuthService();

    // MASSA DE DADOS PARA O SERVICE
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }


    @Test
    public void efetuarLoginSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/user/auth.json");

        // Adição de login através do Json:
        Response resultService = authService.efetuarLoginComSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatusCode(), 200);

    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void efetuarLoginSemSucessoEmailBranco() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/user/authEmailBranco.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("email: O campo de email não pode ser nulo/vazio."));

        // Adição de login com e-mail em branco através do Json:
        ResponseErroDTO resultService = authService.efetuarLoginEmailBranco(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void efetuarLoginSemSucessoSenhaBranco() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/user/authSenhaBranco.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("password: O campo de password não pode ser vazio/nulo."));

        // Adição de login com e-mail em branco através do Json:
        ResponseErroDTO resultService = authService.efetuarLoginSenhaBranco(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test

    public void efetuarLoginSemSucessoEmailInvalido() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/user/authEmailInvalido.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("email: Deve ser informado um e-mail válido!"));

        // Adição de login com e-mail em branco através do Json:
        ResponseErroDTO resultService = authService.efetuarLoginEmailInvalido(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void efetuarLoginSemSucessoEmailSemCadastro() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/user/authEmailSemCadastro.json");
        Object status = 400;
        //List<String> errors = new ArrayList<>(List.of( "Email não registrado!"));

        // Adição de login com e-mail em branco através do Json:
        ResponseErroDTO resultService = authService.efetuarLoginEmailSemCadastro(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        //Assert.assertEquals(resultService.getErrors(), errors);
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void efetuarLoginSemSucessoSenhaErrada() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/user/authSenhaErrada.json");
        Object status = 400;
        //List<String> errors = new ArrayList<>(List.of( "Email or password incorrect"));

        // Adição de login com e-mail em branco através do Json:
        ResponseErroDTO resultService = authService.efetuarLoginSemSucessoSenhaErrada(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getMessage(), "Email ou senha incorreta!");
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void acessarUsuarioLogado() throws IOException {

        // Adição:
        AuthUserLogadoDTO resultService = authService.usuarioLogado();

        // Validações:
        Assert.assertEquals(resultService.getName().toUpperCase(), "ADMIN");
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void acessarUsuarioLogadoSemUsuarioLogado() {

        Response resultService = authService.acessarUsuarioLogadoSemUsuarioLogado();

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void efetuarCadastroComSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/user/authCadastro.json");
        AuthUserRegisterDTO resultService = authService.efetuarCadastroComSucesso(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getName(), "Coraline Jones");

    }


    @Test
    public void efetuarCadastroSemCamposBrancoSucesso() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/user/authCadastroCamposBranco.json");

        Response resultService = authService.efetuarCadastroSemCamposBrancoSucesso(jsonBody);

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }


    @Test
    public void efetuarCadastroSemSucessoNomeCaracteresInferiores() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/user/authNomeCaracteresInferiores.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("name: size must be between 3 and 60"));

        // Adição de login com e-mail em branco através do Json:
        ResponseErroDTO resultService = authService.efetuarCadastroSemSucessoNomeCaracteresInferiores(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
    }

    @Test
    public void efetuarCadastroSemSucessoNomeCaracteresSuperiores() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/user/authNomeCaracteresSuperiores.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("name: size must be between 3 and 60"));

        // Adição de login com e-mail em branco através do Json:
        ResponseErroDTO resultService = authService.efetuarCadastroSemSucessoNomeCaracteresSuperiores(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
    }

    @Test
    public void efetuarCadastroSemSucessoEmailInvalido() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/user/authNomeEmailInvalido,json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("email: Deve ser informado um e-mail válido."));

        // Adição de login com e-mail em branco através do Json:
        ResponseErroDTO resultService = authService.efetuarCadastroSemSucessoEmailInvalido(jsonBody);

        // Validações:
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors(), errors);
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void consultarUsersPaginadoComSucesso() throws IOException {

        UserPaginadoDTO resultService = authService.consultarUserPaginadoComSucesso(0, 2);

        // Validação:
        Assert.assertEquals(resultService.getPage(), "0");
        Assert.assertEquals(resultService.getSize(), "2");
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void consultarUsersPaginadoSemSucesso() {

        Response resultService = authService.consultarUsersPaginadoSemSucesso(0, 0);

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 500);
    }

    @Test
    public void consultarUsersPaginadoMemberSemSucesso() {

        Response resultService = authService.consultarUsersPaginadoMemberSemSucesso(0, 20);

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

    @Test
    public void consultarUsersPaginadoUsuarioNaoLogadoSemSucesso() {

        Response resultService = authService.consultarUsersPaginadoUsuarioNaoLogadoSemSucesso(0, 20);

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

    @Test
    public void consultarUsersEmail() throws IOException {

        AuthEmailDTO[] resultService = authService.consultarUserEmailComSucesso();

        // Validação:
        Assert.assertEquals(resultService[0].getEmail(), "williann.valentim@gmail.com");
        Assert.assertEquals(resultService[0].getName(), "Willian");
    }

    @Test
    public void consultarUsersEmailNaoLogadoSemSucesso() {

        Response resultService = authService.consultarUsersEmailNaoLogadoSemSucesso();

        // Validação:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

    @Test
    public void changeRole() throws IOException {

        Integer idUser = 2;
        String userType = "MEMBER";

        Response resultService = authService.changeRole(idUser, userType);

        // Validações:
        Assert.assertEquals(resultService.getStatusCode(), 200);
    }

    @Test
    public void changeRoleSemSucessoMember() throws IOException {

        Integer idUser = 2;
        String userType = "MEMBER";

        Response resultService = authService.changeRoleSemSucessoMember(idUser, userType);

        // Validações:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }


    @Test
    public void changeRoleSemSucessoNaoLogado() throws IOException {

        Integer idUser = 1;
        String userType = "MEMBER";

        Response resultService = authService.changeRoleSemSucessoNaoLogado(idUser, userType);

        // Validações:
        Assert.assertEquals(resultService.getStatusCode(), 403);
    }

}

package aceitacao.service;

import aceitacao.dto.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthService {

    String tokenMember = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjoxMywicm9sZXMiOlsiUk9MRV9NRU1CRVIiXSwiaWF0IjoxNjYxMjAxMjE1LCJleHAiOjE2NjEyNDQ0MTV9.nWRJxxawBggvCCb1p6ZgBUojmpD72h_0PY___YAYC68";
    String tokenAdmin = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjoyLCJyb2xlcyI6WyJST0xFX0FETUlOIl0sImlhdCI6MTY2MTIwMTI1MywiZXhwIjoxNjYxMjQ0NDUzfQ.lKJvQj9Up_82oVGKusCQzGskLBjjB1S2VofooyzCqKc";

    //==================================================================================================================
    //
    //                                                  USER
    //
    //==================================================================================================================

    public Response efetuarLoginComSucesso(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/user/login";

        Response result = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public AuthUserLogadoDTO usuarioLogado() {

        String Url = "http://vemser-retrocards.herokuapp.com/user/get-logged";

        AuthUserLogadoDTO result = given()
                .header("Authorization", tokenAdmin)
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(AuthUserLogadoDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response acessarUsuarioLogadoSemUsuarioLogado() {

        String Url = "http://vemser-retrocards.herokuapp.com/user/get-logged";

        Response result = given()
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all()
                .statusCode(403)
                .extract().response();
        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public AuthUserRegisterDTO efetuarCadastroComSucessoAdmin(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/user/create-admin";

        AuthUserRegisterDTO result = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(AuthUserRegisterDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public AuthUserRegisterDTO efetuarCadastroComSucesso(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/user/create";

        AuthUserRegisterDTO result = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(AuthUserRegisterDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response efetuarCadastroSemCamposBrancoSucesso(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/user/create";

        Response result = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(400)
                .extract().response();
        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public ResponseErroDTO efetuarLoginEmailBranco(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/user/login";

        ResponseErroDTO result = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(400)
                .extract().as(ResponseErroDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public ResponseErroDTO efetuarLoginSenhaBranco(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/user/login";

        ResponseErroDTO result = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(400)
                .extract().as(ResponseErroDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public ResponseErroDTO efetuarLoginEmailInvalido(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/user/login";

        ResponseErroDTO result = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(400)
                .extract().as(ResponseErroDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public ResponseErroDTO efetuarLoginEmailSemCadastro(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/user/login";

        ResponseErroDTO result = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(400)
                .extract().as(ResponseErroDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public ResponseErroDTO efetuarCadastroSemSucessoNomeCaracteresSuperiores(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/user/create";

        ResponseErroDTO result = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(400)
                .extract().as(ResponseErroDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public ResponseErroDTO efetuarCadastroSemSucessoEmailInvalido(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/user/create";

        ResponseErroDTO result = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(400)
                .extract().as(ResponseErroDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public ResponseErroDTO efetuarCadastroSemSucessoNomeCaracteresInferiores(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/user/create";

        ResponseErroDTO result = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(400)
                .extract().as(ResponseErroDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public ResponseErroDTO efetuarLoginSemSucessoSenhaErrada(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/user/login";

        ResponseErroDTO result = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(400)
                .extract().as(ResponseErroDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public UserPaginadoDTO consultarUserPaginadoComSucesso(Integer pagina, Integer tamanhoDasPaginas) {

        String Url = "http://vemser-retrocards.herokuapp.com/user/list?page=" + pagina + "&quantityPerPage=" + tamanhoDasPaginas;

        UserPaginadoDTO result = given()
                .header("Authorization", tokenAdmin)
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all() //
                .statusCode(200)
                .extract().as(UserPaginadoDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response consultarUsersPaginadoSemSucesso(Integer pagina, Integer tamanhoDasPaginas) {

        String Url = "http://vemser-retrocards.herokuapp.com/user/list?page=" + pagina + "&quantityPerPage=" + tamanhoDasPaginas;

        Response result = given()
                .header("Authorization", tokenAdmin)
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all()
                .statusCode(500)
                .extract().response();
        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response consultarUsersPaginadoMemberSemSucesso(Integer pagina, Integer tamanhoDasPaginas) {

        String Url = "http://vemser-retrocards.herokuapp.com/user/list?pagina=" + pagina + "&registros=" + tamanhoDasPaginas;

        Response result = given()
                .header("Authorization", tokenMember)
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all()
                .statusCode(403)
                .extract().response();
        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response consultarUsersPaginadoUsuarioNaoLogadoSemSucesso(Integer pagina, Integer tamanhoDasPaginas) {

        String Url = "http://vemser-retrocards.herokuapp.com/user/list?pagina=" + pagina + "&registros=" + tamanhoDasPaginas;

        Response result = given()
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all()
                .statusCode(403)
                .extract().response();
        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public AuthEmailDTO[] consultarUserEmailComSucesso() {

        String Url = "http://vemser-retrocards.herokuapp.com/user/list-name-email";

        AuthEmailDTO[] result = given()
                .header("Authorization", tokenAdmin)
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all() //
                .statusCode(200)
                .extract().as(AuthEmailDTO[].class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response consultarUsersEmailNaoLogadoSemSucesso() {

        String Url = "http://vemser-retrocards.herokuapp.com/user/list-name-email";

        Response result = given()
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all()
                .statusCode(403)
                .extract().response();
        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response changeRole(Integer idUser, String userType) {

        String Url = "https://vemser-retrocards.herokuapp.com/user/change-role/" + idUser + "?userType=" + userType;

        Response result = given()
                .header("Authorization", tokenAdmin)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .put(Url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response changeRoleSemSucessoMember(Integer idUser, String userType) {

        String Url = "https://vemser-retrocards.herokuapp.com/user/change-role/" + idUser + "?userType=" + userType;

        Response result = given()
                .header("Authorization", tokenMember)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .put(Url)
                .then()
                .log().all()
                .statusCode(403)
                .extract().response();

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response changeRoleSemSucessoNaoLogado(Integer idUser, String userType) {

        String Url = "https://vemser-retrocards.herokuapp.com/user/change-role/" + idUser + "?userType=" + userType;

        Response result = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .put(Url)
                .then()
                .log().all()
                .statusCode(403)
                .extract().response();

        return result;
    }

}

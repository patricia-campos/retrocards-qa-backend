package aceitacao.service;

import aceitacao.dto.ResponseErroDTO;
import aceitacao.dto.SprintCreateDTO;
import aceitacao.dto.SprintListDTO;
import aceitacao.dto.SprintPaginadoDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SprintService {

    String tokenMember = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjoxMywicm9sZXMiOlsiUk9MRV9NRU1CRVIiXSwiaWF0IjoxNjYxMjAxMjE1LCJleHAiOjE2NjEyNDQ0MTV9.nWRJxxawBggvCCb1p6ZgBUojmpD72h_0PY___YAYC68";
    String tokenAdmin = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjoyLCJyb2xlcyI6WyJST0xFX0FETUlOIl0sImlhdCI6MTY2MTIwMjMxMiwiZXhwIjoxNjYxMjQ1NTEyfQ.gbZAMI9jiMsFUvf4D9UXNJzrpEqLUTqbHLuZdB4h_Ss";
    String tokenFacilitador = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjozLCJyb2xlcyI6WyJST0xFX0ZBQ0lMSVRBVE9SIl0sImlhdCI6MTY2MTIwMTgzMCwiZXhwIjoxNjYxMjQ1MDMwfQ.CRLz0GZmAv8k2U2KeeYCyI2QGdiBJ3-VSR4ruD1CItM";

    //==================================================================================================================
    //
    //                                                 SPRINT
    //
    //==================================================================================================================

    public SprintCreateDTO criarSprintComSucesso(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/sprint/create";

        SprintCreateDTO result = given()
                .header("Authorization", tokenFacilitador)
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(201)
                .extract().as(SprintCreateDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response criarSprintNaoLogadoSemSucesso(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/sprint/create";

        Response result = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(403)
                .extract().response();

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public ResponseErroDTO criarSprintInexistenteSemSucesso(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/sprint/create";

        ResponseErroDTO result = given()
                .header("Authorization", tokenFacilitador)
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

    public SprintPaginadoDTO consultarSprintPaginadoComSucesso(Integer pagina, Integer tamanhoDasPaginas) {

        String Url = "http://vemser-retrocards.herokuapp.com/sprint/list?page=" + pagina + "&quantityPerPage=" + tamanhoDasPaginas;

        SprintPaginadoDTO result = given()
                .header("Authorization", tokenFacilitador)
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all() //
                .statusCode(200)
                .extract().as(SprintPaginadoDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public SprintListDTO consultarSprintComSucesso(Integer idSprint) {

        String Url = "http://vemser-retrocards.herokuapp.com/sprint/check-progress/" + idSprint;

        SprintListDTO result = given()
                .header("Authorization", tokenFacilitador)
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all() //
                .statusCode(200)
                .extract().as(SprintListDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response consultarSprintNaoLogadoSemSucesso(Integer idSprint) {

        String Url = "http://vemser-retrocards.herokuapp.com/sprint/check-progress/" + idSprint;

        Response result = given()
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all() //
                .statusCode(403)
                .extract().response();

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response consultarSprintPaginadoNaoLogadoSemSucesso(Integer pagina, Integer tamanhoDasPaginas) {

        String Url = "http://vemser-retrocards.herokuapp.com/sprint/list?page=" + pagina + "&quantityPerPage=" + tamanhoDasPaginas;

        Response result = given()
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all() //
                .statusCode(403)
                .extract().response();

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response consultarSprintPaginadoSemSucesso(Integer pagina, Integer tamanhoDasPaginas) {

        String Url = "http://vemser-retrocards.herokuapp.com/sprint/list?page=" + pagina + "&quantityPerPage=" + tamanhoDasPaginas;

        Response result = given()
                .header("Authorization", tokenFacilitador)
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

    public SprintCreateDTO editarSprintComSucesso(Integer idSprint, String jsonBody) {

        String Url = "https://vemser-retrocards.herokuapp.com/sprint/update/" + idSprint;

        SprintCreateDTO result = given()
                .header("Authorization", tokenFacilitador)
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .put(Url)
                .then()
                .log().all() //
                .statusCode(201)
                .extract().as(SprintCreateDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response editarSprintNaoLogadoSemSucesso(Integer idSprint, String jsonBody) {

        String Url = "https://vemser-retrocards.herokuapp.com/sprint/update/" + idSprint;

        Response result = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .put(Url)
                .then()
                .log().all() //
                .statusCode(403)
                .extract().response();

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response deletarSprintComSucesso(Integer idSprint) {

        String Url = "http://vemser-retrocards.herokuapp.com/sprint/delete/" + idSprint;

        Response result = given()
                .header("Authorization", tokenFacilitador)
                .log().all()
                .when()
                .delete(Url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        return result;
    }


}

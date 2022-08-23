package aceitacao.service;


import aceitacao.dto.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class RetrospectiveService {

    String tokenMember = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjoxMywicm9sZXMiOlsiUk9MRV9NRU1CRVIiXSwiaWF0IjoxNjYxMjAxMjE1LCJleHAiOjE2NjEyNDQ0MTV9.nWRJxxawBggvCCb1p6ZgBUojmpD72h_0PY___YAYC68";
    String tokenAdmin = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjoyLCJyb2xlcyI6WyJST0xFX0FETUlOIl0sImlhdCI6MTY2MTIwMjMxMiwiZXhwIjoxNjYxMjQ1NTEyfQ.gbZAMI9jiMsFUvf4D9UXNJzrpEqLUTqbHLuZdB4h_Ss";
    String tokenFacilitador = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjozLCJyb2xlcyI6WyJST0xFX0ZBQ0lMSVRBVE9SIl0sImlhdCI6MTY2MTIwMTgzMCwiZXhwIjoxNjYxMjQ1MDMwfQ.CRLz0GZmAv8k2U2KeeYCyI2QGdiBJ3-VSR4ruD1CItM";

    //==================================================================================================================
    //
    //                                              RETROSPECTIVE
    //
    //==================================================================================================================

    public RetrospectivePaginadoDTO consultarRetrospectivePorIDSprintComSucesso(Integer idSprint, Integer pagina, Integer tamanhoDasPaginas) {

        String Url = "http://vemser-retrocards.herokuapp.com/retrospective/list/sprint/" + idSprint + "?page=" + pagina + "&quantityPerPage=" + tamanhoDasPaginas;

            RetrospectivePaginadoDTO result = given()
                .header("Authorization", tokenFacilitador)
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all() //
                .statusCode(200)
                .extract().as(RetrospectivePaginadoDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------


    public Response consultarRetrospectivePorIDSprintSemNaoLogadoSucesso(Integer idSprint, Integer pagina, Integer tamanhoDasPaginas) {

        String Url = "http://vemser-retrocards.herokuapp.com/retrospective/list/sprint/" + idSprint + "?pagina=" + pagina + "&registro=" + tamanhoDasPaginas;

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

    //------------------------------------------------------------------------------------------------------------------

    public RetrospectiveCreateDTO consultarRetrospectiveComSucesso(Integer idRestrospective) {

        String Url = "http://vemser-retrocards.herokuapp.com/retrospective/list/" + idRestrospective;

        RetrospectiveCreateDTO result = given()
                .header("Authorization", tokenFacilitador)
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all() //
                .statusCode(200)
                .extract().as(RetrospectiveCreateDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response consultarRetrospectiveNaoLogadoSemSucesso(Integer idRestrospective) {

        String Url = "http://vemser-retrocards.herokuapp.com/retrospective/list/" + idRestrospective;

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


    public RetrospectiveCreateDTO retrospectiveCreate(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/retrospective/create";

        RetrospectiveCreateDTO result = given()
                .header("Authorization", tokenFacilitador)
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(201)
                .extract().as(RetrospectiveCreateDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response retrospectiveCreateNaoLogadoSemSucesso(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/retrospective/create";

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

    public ResponseErroDTO criarRetrospectiveInexistenteSemSucesso(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/retrospective/create";

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

    public RetrospectiveCreateDTO editarRetrospectiveComSucesso(Integer idRestrospective, String jsonBody) {

        String Url = "https://vemser-retrocards.herokuapp.com/retrospective/update/" + idRestrospective;

        RetrospectiveCreateDTO result = given()
                .header("Authorization", tokenFacilitador)
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .put(Url)
                .then()
                .log().all() //
                .statusCode(200)
                .extract().as(RetrospectiveCreateDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response editarRetrospectiveNaoLogadoSemSucesso(Integer idRestrospective, String jsonBody) {

        String Url = "https://vemser-retrocards.herokuapp.com/retrospective/update/" + idRestrospective;

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

    public RetrospectiveCreateDTO updateStatus(Integer idRetrospective, String status) {

        String Url = "https://vemser-retrocards.herokuapp.com/retrospective/update-status/" + idRetrospective + "?status=" + status;

        RetrospectiveCreateDTO result = given()
                .header("Authorization", tokenFacilitador)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .put(Url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(RetrospectiveCreateDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response updateStatusNaoLogadoSemSucesso(Integer idRetrospective, String status) {

        String Url = "https://vemser-retrocards.herokuapp.com/retrospective/update-status/" + idRetrospective + "?status=" + status;

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

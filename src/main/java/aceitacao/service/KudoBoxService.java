package aceitacao.service;

import aceitacao.dto.KudoBoxCreateDTO;
import aceitacao.dto.KudoBoxPaginadoDTO;
import aceitacao.dto.ResponseErroDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class KudoBoxService {

    String tokenMember = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjoxMywicm9sZXMiOlsiUk9MRV9NRU1CRVIiXSwiaWF0IjoxNjYxMjAxMjE1LCJleHAiOjE2NjEyNDQ0MTV9.nWRJxxawBggvCCb1p6ZgBUojmpD72h_0PY___YAYC68";
    String tokenAdmin = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjoyLCJyb2xlcyI6WyJST0xFX0FETUlOIl0sImlhdCI6MTY2MTIwMjMxMiwiZXhwIjoxNjYxMjQ1NTEyfQ.gbZAMI9jiMsFUvf4D9UXNJzrpEqLUTqbHLuZdB4h_Ss";
    String tokenFacilitador = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjozLCJyb2xlcyI6WyJST0xFX0ZBQ0lMSVRBVE9SIl0sImlhdCI6MTY2MTIwMTgzMCwiZXhwIjoxNjYxMjQ1MDMwfQ.CRLz0GZmAv8k2U2KeeYCyI2QGdiBJ3-VSR4ruD1CItM";

    //==================================================================================================================
    //
    //                                              KUDOBOX
    //
    //==================================================================================================================

    public KudoBoxCreateDTO criarKudoBoxComSucesso(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudobox/create";

        KudoBoxCreateDTO result = given()
                .header("Authorization", tokenFacilitador)
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(201)
                .extract().as(KudoBoxCreateDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response criarKudoBoxNaoLogadoSemSucesso(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudobox/create";

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


    public ResponseErroDTO criarKudoBoxInexistenteSemSucesso(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudobox/create";

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

    public KudoBoxCreateDTO editarKudoBoxComSucesso(Integer idKudoBox, String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudobox/update/" + idKudoBox;

        KudoBoxCreateDTO result = given()
                .header("Authorization", tokenFacilitador)
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .put(Url)
                .then()
                .log().all() //
                .statusCode(200)
                .extract().as(KudoBoxCreateDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response editarKudoBoxNaoLogadoSemSucesso(Integer idKudoBox, String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudobox/update/" + idKudoBox;

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

    public KudoBoxPaginadoDTO consultarKudoBoxPorSprintComSucesso(Integer idSprint, Integer pagina, Integer tamanhoDasPaginas) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudobox/list/sprint/" + idSprint + "?page=" + pagina + "&quantityPerPage=" + tamanhoDasPaginas;

        KudoBoxPaginadoDTO result = given()
                .header("Authorization", tokenMember)
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all() //
                .statusCode(200)
                .extract().as(KudoBoxPaginadoDTO.class);


        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response consultarKudoBoxPorSprintNaoLogadoSemSucesso(Integer idSprint, Integer pagina, Integer tamanhoDasPaginas) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudobox/list/sprint/" + idSprint + "?page=" + pagina + "&quantityPerPage=" + tamanhoDasPaginas;

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

    public KudoBoxCreateDTO consultarKudoBoxPorKudoBoxComSucesso(Integer idKudoBox) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudobox/list/" + idKudoBox;

        KudoBoxCreateDTO result = given()
                .header("Authorization", tokenMember)
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all() //
                .statusCode(200)
                .extract().as(KudoBoxCreateDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response consultarKudoBoxPorKudoBoxNaoLogadoSemSucesso(Integer idKudoBox) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudobox/list/" + idKudoBox;

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

}


package aceitacao.service;

import aceitacao.dto.ItemRetrospectiveCreateDTO;
import aceitacao.dto.ResponseErroDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class ItemRetrospectiveService {

    String tokenMember = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjoxMywicm9sZXMiOlsiUk9MRV9NRU1CRVIiXSwiaWF0IjoxNjYxMjAxMjE1LCJleHAiOjE2NjEyNDQ0MTV9.nWRJxxawBggvCCb1p6ZgBUojmpD72h_0PY___YAYC68";

    //==================================================================================================================
    //
    //                                              ITEM RETROSPECTIVE
    //
    //==================================================================================================================

    public ItemRetrospectiveCreateDTO criarItemRetrospectiveComSucesso(String jsonBody, String itemType) {

        String Url = "http://vemser-retrocards.herokuapp.com/itemretrospective/create?itemType="+itemType;

        ItemRetrospectiveCreateDTO result = given()
                .header("Authorization", tokenMember)
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(201)
                .extract().as(ItemRetrospectiveCreateDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public ResponseErroDTO criarItemRetrospectiveInexistenteSemSucesso(String jsonBody, String itemType) {

        String Url = "http://vemser-retrocards.herokuapp.com/itemretrospective/create?itemType="+itemType;

        ResponseErroDTO result = given()
                .header("Authorization", tokenMember)
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

    public ItemRetrospectiveCreateDTO[] consultarItemRetrospectivePorRetrospectiveComSucesso(Integer idRetrospective) {

        String Url = "http://vemser-retrocards.herokuapp.com/itemretrospective/list/retrospective/" + idRetrospective;

        ItemRetrospectiveCreateDTO[] result = given()
                .header("Authorization", tokenMember)
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all() //
                .statusCode(200)
                .extract().as(ItemRetrospectiveCreateDTO[].class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public ResponseErroDTO consultarItemRetrospectivePorRetrospectiveSemSucesso(Integer idRetrospective) {

        String Url = "http://vemser-retrocards.herokuapp.com/itemretrospective/list/retrospective/" + idRetrospective;

        ResponseErroDTO result = given()
                .header("Authorization", tokenMember)
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all() //
                .statusCode(400)
                .extract().as(ResponseErroDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response consultarItemRetrospectivePorRetrospectiveNaoLogadoSemSucesso(Integer idRetrospective) {

        String Url = "http://vemser-retrocards.herokuapp.com/itemretrospective/list/retrospective/" + idRetrospective;

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

    public Response deletarItemRetrospectiveComSucesso(Integer idItemRetrospective) {

        String Url = "http://vemser-retrocards.herokuapp.com/itemretrospective/delete/" + idItemRetrospective;

        Response result = given()
                .header("Authorization", tokenMember)
                .log().all()
                .when()
                .delete(Url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        return result;
    }

    public Response deletarItemRetrospectiveNaoLogadoSemSucesso(Integer idItemRetrospective) {

        String Url = "http://vemser-retrocards.herokuapp.com/itemretrospective/delete/" + idItemRetrospective;

        Response result = given()
                .log().all()
                .when()
                .delete(Url)
                .then()
                .log().all()
                .statusCode(403)
                .extract().response();
        return result;
    }

}

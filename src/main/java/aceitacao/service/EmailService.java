package aceitacao.service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class EmailService {

    String tokenMember = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjoxMywicm9sZXMiOlsiUk9MRV9NRU1CRVIiXSwiaWF0IjoxNjYxMjAxMjE1LCJleHAiOjE2NjEyNDQ0MTV9.nWRJxxawBggvCCb1p6ZgBUojmpD72h_0PY___YAYC68";
    String tokenFacilitador = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjozLCJyb2xlcyI6WyJST0xFX0ZBQ0lMSVRBVE9SIl0sImlhdCI6MTY2MTIwMTgzMCwiZXhwIjoxNjYxMjQ1MDMwfQ.CRLz0GZmAv8k2U2KeeYCyI2QGdiBJ3-VSR4ruD1CItM";

    //==================================================================================================================
    //
    //                                                  EMAIL
    //
    //==================================================================================================================

    public Response enviarEmailSucesso(Integer idRetrospective, String jsonBody) {

        String Url = "https://vemser-retrocards.herokuapp.com/email/send?idRetrospective=" + idRetrospective;

        Response result = given()
                .header("Authorization", tokenFacilitador)
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all() //
                .statusCode(200)
                .extract().response();

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response enviarEmailNaoLogadoSemSucesso(Integer idRetrospective, String jsonBody) {

        String Url = "https://vemser-retrocards.herokuapp.com/email/send?idRetrospective=" + idRetrospective;

        Response result = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all() //
                .statusCode(403)
                .extract().response();

        return result;
    }
}

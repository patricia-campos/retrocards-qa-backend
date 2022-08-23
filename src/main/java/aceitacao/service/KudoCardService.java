package aceitacao.service;

import aceitacao.dto.KudoCardCreateDTO;
import aceitacao.dto.KudoCardPaginadoDTO;
import aceitacao.dto.ResponseErroDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class KudoCardService {

    String tokenMember = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjoxMywicm9sZXMiOlsiUk9MRV9NRU1CRVIiXSwiaWF0IjoxNjYxMjAxMjE1LCJleHAiOjE2NjEyNDQ0MTV9.nWRJxxawBggvCCb1p6ZgBUojmpD72h_0PY___YAYC68";
    String tokenAdmin = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjoyLCJyb2xlcyI6WyJST0xFX0FETUlOIl0sImlhdCI6MTY2MTIwMTI1MywiZXhwIjoxNjYxMjQ0NDUzfQ.lKJvQj9Up_82oVGKusCQzGskLBjjB1S2VofooyzCqKc";
    String tokenFacilitador = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyZXRyb2NhcmRzIiwianRpIjozLCJyb2xlcyI6WyJST0xFX0ZBQ0lMSVRBVE9SIl0sImlhdCI6MTY2MTIwMTgzMCwiZXhwIjoxNjYxMjQ1MDMwfQ.CRLz0GZmAv8k2U2KeeYCyI2QGdiBJ3-VSR4ruD1CItM";

    //==================================================================================================================
    //
    //                                              KUDOCARD
    //
    //==================================================================================================================


    public KudoCardCreateDTO criarKudoBoxComSucesso(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudocard/create";

        KudoCardCreateDTO result = given()
                .header("Authorization", tokenMember)
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(Url)
                .then()
                .log().all()
                .statusCode(201)
                .extract().as(KudoCardCreateDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response criarKudoBoxNaoLogadoSemSucesso(String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudocard/create";

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

        String Url = "http://vemser-retrocards.herokuapp.com/kudocard/create";

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

    public KudoCardPaginadoDTO consultarKudoCardPorKudoBoxComSucesso(Integer idKudoBox, Integer pagina, Integer tamanhoDasPaginas) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudocard/list/kudocards/" + idKudoBox + "?page=" + pagina + "&quantityPerPage=" + tamanhoDasPaginas;

        KudoCardPaginadoDTO result = given()
                .header("Authorization", tokenMember)
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all() //
                .statusCode(200)
                .extract().as(KudoCardPaginadoDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response consultarKudoCardPorKudoBoxSemSucesso(Integer idKudoBox, Integer pagina, Integer tamanhoDasPaginas) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudocard/list/kudocards/" + idKudoBox + "?page=" + pagina + "&quantityPerPage=" + tamanhoDasPaginas;

        Response result = given()
                .header("Authorization", tokenMember)
                .log().all()
                .when()
                .get(Url)
                .then()
                .log().all() //
                .statusCode(500)
                .extract().response();

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response consultarKudoCardPorKudoBoxNaoLogadoSemSucesso(Integer idKudoBox, Integer pagina, Integer tamanhoDasPaginas) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudocard/list/kudocards/" + idKudoBox + "?page=" + pagina + "&quantityPerPage=" + tamanhoDasPaginas;

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

    public Response deletarKudoCardComSucesso(Integer idKudoCard) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudocard/delete/" + idKudoCard;

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

    //------------------------------------------------------------------------------------------------------------------

    public ResponseErroDTO deletarKudoCardKudoBoxnaoProgressoSemSucesso(Integer idKudoCard) {

        String Url = "https://vemser-retrocards.herokuapp.com/kudocard/delete/" + idKudoCard;

        ResponseErroDTO result = given()
                .header("Authorization", tokenMember)
                .log().all()
                .when()
                .delete(Url)
                .then()
                .log().all() //
                .statusCode(400)
                .extract().as(ResponseErroDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public KudoCardCreateDTO editarKudoCardComSucesso(Integer idKudoCard, String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudocard/update/" + idKudoCard;

        KudoCardCreateDTO result = given()
                .header("Authorization", tokenMember)
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .put(Url)
                .then()
                .log().all() //
                .statusCode(200)
                .extract().as(KudoCardCreateDTO.class);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------

    public Response editarKudoCardNaoLogadoSemSucesso(Integer idKudoCard, String jsonBody) {

        String Url = "http://vemser-retrocards.herokuapp.com/kudocard/update/" + idKudoCard;

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
}

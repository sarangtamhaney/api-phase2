package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeSuite;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo_models.Token;
import utilities.AuthenticationToken;
import utilities.RestAssuredEngine;

public class BaseTest {

    public ResponseOptions<Response> response;
    protected RestAssuredEngine restAssuredEngine;
    protected String secureToken = "";
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeSuite
    public void beforeSuite() throws JsonProcessingException {
        AuthenticationToken authToken = new AuthenticationToken();
        Token token = mapper.readValue(authToken.getAuthenticationToken(), Token.class);
        //Token tokenstr = token.getAuthenticationToken();
        restAssuredEngine = new RestAssuredEngine(token.getAccessToken());
    }

    public RestAssuredEngine getRestEngine() {
        return restAssuredEngine;
    }

}

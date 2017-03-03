package org.js.assertj.jersey.response;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.js.assertj.jersey.Assertions.assertThat;

public class ResponseAssertTest {

    private WebTarget client;

    @ClassRule
    public static WireMockClassRule STUB_SERVER = new WireMockClassRule(0);

    @Before
    public void setup() throws Exception {
        client = JerseyClientBuilder.newBuilder().build().target("http://localhost:" + STUB_SERVER.port() + "/index");
    }

    @Test
    public void shouldX() {
        STUB_SERVER.stubFor(get(urlMatching("/index"))
                .willReturn(
                        aResponse()
                                .withStatus(200)
                                .withBody("{\n" +
                                        "  \"message\": \"Hello World\",\n" +
                                        "  \"details\": {\n" +
                                        "    \"code\": 1,\n" +
                                        "    \"num\": 10\n" +
                                        "  }\n" +
                                        "}")
                                .withHeader("Content-Type", "application/json")
                ));

        Response response = client.request().get();

        assertThat(response)
                .isSuccessful()
                .hasContentType("application/json")
                .matchesJson("message", "Hello World")
                .matchesJson(".details[0].code", 1);

    }
}

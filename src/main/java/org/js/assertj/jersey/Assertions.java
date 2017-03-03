package org.js.assertj.jersey;

import org.js.assertj.jersey.response.ResponseAssert;

import javax.ws.rs.core.Response;

public class Assertions {

    public static ResponseAssert assertThat(Response actual) {
        return new ResponseAssert(actual);
    }
}

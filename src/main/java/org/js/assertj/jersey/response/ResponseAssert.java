package org.js.assertj.jersey.response;

import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Comparables;
import org.assertj.core.internal.Numbers;
import org.assertj.core.internal.Objects;
import org.assertj.core.internal.Strings;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.Family.SUCCESSFUL;

public class ResponseAssert extends AbstractAssert<ResponseAssert, Response> {

    private Comparables comparables = Numbers.instance();
    private Strings strings = Strings.instance();
    private final Objects objects = Objects.instance();
    private String entity;

    public ResponseAssert(Response actual) {
        super(actual, ResponseAssert.class);
    }

    public ResponseAssert isOk() {
        comparables.assertEqual(info, actual.getStatus(), 200);
        return myself;
    }

    public ResponseAssert isSuccessful() {
        objects.assertEqual(info, actual.getStatusInfo().getFamily(), SUCCESSFUL);
        return myself;
    }

    public ResponseAssert hasContentType(String expected) {
        strings.assertEqualsIgnoringCase(info, actual.getHeaderString("Content-Type"), expected);
        return myself;
    }

    public ResponseAssert matchesJson(String path, Object expected) {
        JsonPath p = new JsonPath(path, new Filter[]{});
        Object actual = p.read(readEntity());
        comparables.assertEqual(info, actual, expected);
        return myself;
    }

    private String readEntity() {
        if (entity == null) {
            entity = this.actual.readEntity(String.class);
        }
        return entity;
    }

}

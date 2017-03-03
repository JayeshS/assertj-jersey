assertj-jersey
==============

Fluent AssertJ assertions for Jersey

        Response response = client.request().get();
        /**
        Say this is the response...
        {
          "message": "Hello World",
          "details": {
            "code": 1,
            "num": 10
          }
        }
        **/
        assertThat(response)
                .isSuccessful()
                .hasContentType("application/json")
                .matchesJson("message", "Hello World")
                .matchesJson(".details[0].code", 1);
License
-------

* [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0)

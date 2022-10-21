package org.acme;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RegisterRestClient(baseUri = "http://localhost:8081")
@Path("/hello")
public interface MyServiceClient {

    @GET
    String hello();
}

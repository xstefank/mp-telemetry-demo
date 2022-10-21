package org.acme;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    private static final Logger LOG = Logger.getLogger(GreetingResource.class);

    @Inject
    TracedBean tracedBean;

    @Inject
    Tracer tracer;

    @Inject
    @RestClient
    MyServiceClient myServiceClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOG.info(tracedBean.getHello("Martin"));

        Span span = tracer.spanBuilder("My custom span")
            .setAttribute("attr", "attr.value")
            .setParent(Context.current().with(Span.current()))
            .setSpanKind(SpanKind.INTERNAL)
            .startSpan();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        span.end();

//        myServiceClient.hello();

        return "Hello from RESTEasy Reactive";
    }
}
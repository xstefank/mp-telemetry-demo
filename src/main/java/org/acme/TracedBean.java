package org.acme;

import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TracedBean {

    @WithSpan(value = "My CDI span", kind = SpanKind.SERVER)
    public String getHello(@SpanAttribute("passed name") String name) {
        return "Hello " + name;
    }
}

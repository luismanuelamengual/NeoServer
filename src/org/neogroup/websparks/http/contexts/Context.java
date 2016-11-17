
package org.neogroup.websparks.http.contexts;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.neogroup.websparks.http.HttpRequest;
import org.neogroup.websparks.http.HttpResponse;
import org.neogroup.websparks.http.HttpResponseCode;

public abstract class Context {
    
    private final String path;

    public Context(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    
    public abstract HttpResponse onContext (HttpRequest request);
    
    public HttpResponse onError (HttpRequest request, Throwable throwable) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printer = new PrintStream(out);
        throwable.printStackTrace(printer);
        byte[] body = out.toByteArray();
        
        HttpResponse response = new HttpResponse();
        response.setResponseCode(HttpResponseCode.INTERNAL_SERVER_ERROR);
        response.setBody(body);
        return response;
    }
}
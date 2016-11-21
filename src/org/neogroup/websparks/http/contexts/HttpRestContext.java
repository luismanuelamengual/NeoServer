
package org.neogroup.websparks.http.contexts;

import java.util.List;
import org.neogroup.websparks.http.HttpMethod;
import org.neogroup.websparks.http.HttpRequest;
import org.neogroup.websparks.http.HttpResponse;

public abstract class HttpRestContext<T> extends HttpContext {

    public HttpRestContext(String path) {
        super(path);
    }

    @Override
    public HttpResponse onContext(HttpRequest request) {
        
        String method = request.getMethod();
        
        List<T> resources = null;
        switch (method) {
            case HttpMethod.GET:  
                resources = getResources(request);
                break;
            case HttpMethod.PUT:
                resources = createResources(request, decodeResources(request.getBody()));
                break;
            case HttpMethod.POST:
                resources = updateResources(request, decodeResources(request.getBody()));
                break;
            case HttpMethod.DELETE:  
                resources = deleteResources(request);
                break;
        }
        
        HttpResponse response = new HttpResponse();
        if (resources != null && !resources.isEmpty()) {
            response.setBody(encodeResources(resources));
        }
        return response;
    }
    
    protected abstract List<T> decodeResources (byte[] content);
    protected abstract byte[] encodeResources (List<T> resource);
    
    protected abstract List<T> getResources (HttpRequest request);
    protected abstract List<T> createResources (HttpRequest request, List<T> resource);
    protected abstract List<T> updateResources (HttpRequest request, List<T> resource);
    protected abstract List<T> deleteResources (HttpRequest request);
}
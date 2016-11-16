
package org.neogroup.websparks.http;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequest {
    
    private static final String URI_SEPARATOR = "/";
    
    private final HttpExchange exchange;
    private Map<String,String> parameters;
    private byte[] body;
    
    public HttpRequest(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public String getMethod() {
        return exchange.getRequestMethod();
    }

    public byte[] getBody() {
        
        if (body == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                InputStream inputStream = exchange.getRequestBody();
                int read = inputStream.read();
                while (read != -1) {
                    byteArrayOutputStream.write(read);
                    read = inputStream.read();
                }
            } catch (Exception ex) {};
            body = byteArrayOutputStream.toByteArray();
        }
        return body;
    }
    
    public URI getUri() {
        return exchange.getRequestURI();
    }

    public Headers getHeaders() {
        return exchange.getRequestHeaders();
    }
    
    public String getQuery() {
        return exchange.getRequestURI().getRawQuery();
    }
    
    public String getPath() {
        return exchange.getRequestURI().getRawPath();
    }
    
    public List<String> getPathParts() {
        String path = getPath();
        String[] pathTokens = path.split(URI_SEPARATOR);
        return Arrays.asList(pathTokens);
    }
    
    public Map<String,String> getParameters() {
        
        if (parameters == null) {
            parameters = new HashMap<>();
            try {
                String query = getQuery() ;
                if (query != null) {
                    String pairs[] = query.split("[&]");
                    for (String pair : pairs) {
                        String param[] = pair.split("[=]");
                        String key = null;
                        String value = null;
                        if (param.length > 0) {
                            key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
                        }
                        if (param.length > 1) {
                            value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
                        }
                        parameters.put(key, value);
                    }
                }
            }
            catch (Exception ex) {}
        }
        return parameters;
    }
    
    public String getParameter (String name) {
        return getParameters().get(name);
    }
}
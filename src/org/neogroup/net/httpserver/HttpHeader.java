
package org.neogroup.net.httpserver;

import java.util.List;

public class HttpHeader {

    public static final String ACCEPT = "Accept";
    public static final String ACCEPT_CHARSET = "Accept-Charset";
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String AUTHORIZATION = "Authorization";
    public static final String EXPECT = "Expect";
    public static final String FROM = "From";
    public static final String HOST = "Host";
    public static final String IF_MATCH = "If-Match";
    public static final String IF_MODIFIED_SINCE = "If-Modified-Since";
    public static final String IF_NONE_MATCH = "If-None-Match";
    public static final String IF_RANGE = "If-Range";
    public static final String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";
    public static final String MAX_FORWARDS = "Max-Forwards";
    public static final String PROXY_AUTHORIZATION = "Proxy-Authorization";
    public static final String RANGE = "Range";
    public static final String REFERER = "Referer";
    public static final String TE = "TE";
    public static final String USER_AGENT = "User-Agent";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String SERVER = "Server";
    public static final String DATE = "Date";
    public static final String LAST_MODIFIED = "Last-Modified";
    public static final String CONNECTION = "Connection";
    
    private static final String HEADER_ASSIGNATION = ":";
    private static final String HEADER_VALUES_SEPARATOR = ";";
    
    private final String name;
    private final String value;

    public HttpHeader(String name, List<String> values) {
        this.name = name;
        StringBuilder valueBuilder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            if (i > 0) {
                valueBuilder.append(HEADER_VALUES_SEPARATOR);
            }
            valueBuilder.append(values.get(i));
        }
        this.value = valueBuilder.toString();
    }
    
    public HttpHeader(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(name);
        result.append(HEADER_ASSIGNATION).append(" ");
        result.append(value);
        return result.toString();
    }
}

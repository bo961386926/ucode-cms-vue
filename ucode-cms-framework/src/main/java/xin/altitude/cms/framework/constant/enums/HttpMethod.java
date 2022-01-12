package xin.altitude.cms.framework.constant.enums;

import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求方式
 *
 * @author ucode
 */
public enum HttpMethod {
    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;
    
    private static final Map<String, HttpMethod> MAPPINGS = new HashMap<>(16);
    
    static {
        for (HttpMethod httpMethod : values()) {
            MAPPINGS.put(httpMethod.name(), httpMethod);
        }
    }
    
    @Nullable
    public static HttpMethod resolve(@Nullable String method) {
        return (method != null ? MAPPINGS.get(method) : null);
    }
    
    public boolean matches(String method) {
        return (this == resolve(method));
    }
}

package xin.altitude.cms.code.service.code;

import org.apache.velocity.VelocityContext;

/**
 * @author explore
 * @since 2021/12/03 16:58
 **/
public interface IXmlService {
    void writeToLocalFile(String tableName, String className);
    
    String realtimePreview(String tableName);
    
    VelocityContext createContext(String tableName);
}

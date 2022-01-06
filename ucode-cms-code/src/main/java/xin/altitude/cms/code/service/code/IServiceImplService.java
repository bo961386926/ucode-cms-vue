package xin.altitude.cms.code.service.code;

import org.apache.velocity.VelocityContext;

import java.util.List;

/**
 * @author explore
 * @since 2019/12/03 16:57
 **/
public interface IServiceImplService {
    void writeToLocalFile(String tableName, String className);
    
    String realtimePreview(String tableName);
    
    VelocityContext createContext(String tableName);
    
    List<String> getImportList(String tableName);
}

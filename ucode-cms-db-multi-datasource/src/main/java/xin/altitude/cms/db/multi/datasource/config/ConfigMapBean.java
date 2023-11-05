package xin.altitude.cms.db.multi.datasource.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import xin.altitude.cms.db.multi.datasource.entity.SourceEntity;

import java.util.Map;

@ConfigurationProperties(prefix = "spring.ucode")
public class ConfigMapBean {

    //参数名称要对上yml的名称
    public Map<String, DataSourceProperties> more;

    public Map<String, DataSourceProperties> getMore() {
        return more;
    }

    public void setMore(Map<String, DataSourceProperties> more) {
        this.more = more;
    }
}

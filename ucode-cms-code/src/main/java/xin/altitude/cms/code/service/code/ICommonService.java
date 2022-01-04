package xin.altitude.cms.code.service.code;

import xin.altitude.cms.code.domain.KeyColumnUsage;
import xin.altitude.cms.code.entity.vo.KeyColumnUsageVo;
import xin.altitude.cms.code.entity.vo.MetaColumnVo;
import xin.altitude.cms.code.domain.MetaColumn;
import xin.altitude.cms.code.domain.MetaTable;

import java.util.List;

/**
 * @author explore
 * @since 2021/12/03 16:55
 **/
public interface ICommonService {
    MetaTable getTableInfo(String tableName);
    
    List<MetaColumnVo> getColumnVos(String tableName);
    
    /**
     * 获取两表列名信息的差集
     *
     * @param tableNameA 表名A
     * @param tableNameB 表名B
     * @return 差集合
     */
    List<MetaColumn> listColumns(String tableNameA, String tableNameB);
    
    MetaColumnVo getPkColumn(String tableName);
    
    /**
     * 查询外键索引信息
     *
     * @param tableName 当前表名
     * @return 外键集合
     */
    List<KeyColumnUsage> listKeyColumns(String tableName);
    
    KeyColumnUsageVo toKeyColumnUsageVo(KeyColumnUsage keyColumnUsage);
}

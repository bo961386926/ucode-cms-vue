package xin.altitude.cms.code.service.core.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import xin.altitude.cms.code.entity.vo.MetaColumnVo;
import xin.altitude.cms.code.mapper.MetaColumnMapper;
import xin.altitude.cms.code.service.core.IKeyColumnUsage;
import xin.altitude.cms.code.domain.MetaColumn;
import xin.altitude.cms.code.service.core.IMetaColumnService;
import xin.altitude.cms.code.service.core.IThirdSqlSessionService;
import xin.altitude.cms.code.util.AutoCodeUtils;
import xin.altitude.cms.common.util.EntityUtils;

import java.util.List;

/**
 * 字段信息Service业务层处理
 *
 * @author explore
 * @since 2021-06-22
 */
// @Service
public class MetaColumnServiceImpl implements IMetaColumnService {
    @Autowired
    private IKeyColumnUsage keyColumnUsage;
    
    @Autowired
    private IThirdSqlSessionService sessionService;
    
    /**
     * 查询当前数据库所有列信息
     *
     * @return 列元数据集合
     */
    @Override
    public List<MetaColumn> listColumns() {
        try (SqlSession sqlSession = sessionService.getSqlSession()) {
            LambdaQueryWrapper<MetaColumn> wrapper = Wrappers.lambdaQuery(MetaColumn.class)
                    .apply("table_schema = database()");
            return sqlSession.getMapper(MetaColumnMapper.class).selectList(wrapper);
        }
    }
    
    /**
     * 查询表的列信息集合
     *
     * @param tableName 表名
     * @return 列信息集合
     */
    @Override
    public List<MetaColumn> listColumns(String tableName) {
        try (SqlSession sqlSession = sessionService.getSqlSession()) {
            LambdaQueryWrapper<MetaColumn> eq = Wrappers.lambdaQuery(MetaColumn.class)
                    .eq(MetaColumn::getTableName, tableName)
                    .apply("table_schema = database()");
            return sqlSession.getMapper(MetaColumnMapper.class).selectList(eq);
        }
    }
    
    
    /**
     * 根据表名、列名查询详细数据
     *
     * @param tableName  表名
     * @param columnName 列名
     * @return 列详细数据
     */
    @Override
    public MetaColumn getOneColumn(String tableName, String columnName) {
        try (SqlSession sqlSession = sessionService.getSqlSession()) {
            LambdaQueryWrapper<MetaColumn> eq = Wrappers.lambdaQuery(MetaColumn.class)
                    .eq(MetaColumn::getTableName, tableName)
                    .eq(MetaColumn::getColumnName, columnName)
                    .apply("table_schema = database()");
            return sqlSession.getMapper(MetaColumnMapper.class).selectOne(eq);
        }
    }
    
    @Override
    public List<MetaColumnVo> getColumnVos(String tableName) {
        try (SqlSession sqlSession = sessionService.getSqlSession()) {
            LambdaQueryWrapper<MetaColumn> eq = Wrappers.lambdaQuery(MetaColumn.class)
                    .eq(MetaColumn::getTableName, tableName)
                    .apply("table_schema = database()");
            List<MetaColumn> columns = sqlSession.getMapper(MetaColumnMapper.class).selectList(eq);
            List<MetaColumnVo> columnsVoList = EntityUtils.toList(columns, MetaColumnVo::new);
            columnsVoList.forEach(AutoCodeUtils::handleColumnField);
            columnsVoList.forEach(e -> e.setPkColumn(keyColumnUsage.isPk(tableName, e.getColumnName())));
            return columnsVoList;
        }
    }
}
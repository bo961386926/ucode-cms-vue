package xin.altitude.cms.code.service.core.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import xin.altitude.cms.code.domain.MetaTable;
import xin.altitude.cms.code.entity.vo.MetaTableBo;
import xin.altitude.cms.code.mapper.MetaTableMapper;
import xin.altitude.cms.code.service.core.IMetaTableService;
import xin.altitude.cms.code.service.core.IThirdSqlSessionService;
import xin.altitude.cms.common.util.ColUtils;
import xin.altitude.cms.common.util.EntityUtils;

import java.util.List;

/**
 * 信息Service业务层处理
 *
 * @author explore
 * @since 2021-06-22
 */
// @Service
public class MetaTableServiceImpl implements IMetaTableService {
    @Autowired
    private IThirdSqlSessionService sessionService;
    
    
    /**
     * 查询当前数据库所有列信息
     *
     * @return 列元数据集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<MetaTable> listTables() {
        try (SqlSession sqlSession = sessionService.getSqlSession()) {
            LambdaQueryWrapper<MetaTable> wrapper = Wrappers.lambdaQuery(MetaTable.class)
                    .apply("table_schema = database()");
            return sqlSession.getMapper(MetaTableMapper.class).selectList(wrapper);
        }
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<MetaTable> listTables(MetaTable metaTable) {
        try (SqlSession sqlSession = sessionService.getSqlSession()) {
            LambdaQueryWrapper<MetaTable> eq = Wrappers.lambdaQuery(metaTable)
                    .apply("table_schema = database()");
            return sqlSession.getMapper(MetaTableMapper.class).selectList(eq);
        }
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<MetaTableBo> selectTableList(String tableName) {
        try (SqlSession sqlSession = sessionService.getSqlSession()) {
            LambdaQueryWrapper<MetaTable> eq = Wrappers.lambdaQuery(MetaTable.class)
                    .select(MetaTable::getTableName, MetaTable::getTableComment)
                    .apply("table_schema = database()");
            List<MetaTable> list = sqlSession.getMapper(MetaTableMapper.class).selectList(eq);
            return EntityUtils.toList(list, e -> new MetaTableBo(e.getTableName(), e.getTableName().equalsIgnoreCase(tableName)));
        }
    }
    
    /**
     * 列表查询元数据表数据
     *
     * @param metaTable
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<MetaTableBo> selectTableList(MetaTable metaTable) {
        try (SqlSession sqlSession = sessionService.getSqlSession()) {
            LambdaQueryWrapper<MetaTable> eq = Wrappers.lambdaQuery(MetaTable.class)
                    .select(MetaTable::getTableName, MetaTable::getTableComment)
                    .apply("table_schema = database()");
            List<MetaTable> list = sqlSession.getMapper(MetaTableMapper.class).selectList(eq);
            return EntityUtils.toList(list, e -> new MetaTableBo(e.getTableName(), e.getTableName().equalsIgnoreCase(metaTable.getTableName())));
        }
    }
    
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<MetaTable> pageMetaTable(Page<MetaTable> page, MetaTable metaTable) {
        page.setOptimizeCountSql(false);
        try (SqlSession sqlSession = sessionService.getSqlSession()) {
            LambdaQueryWrapper<MetaTable> wrapper = Wrappers.lambdaQuery(metaTable)
                    .apply("table_schema = database()");
            return sqlSession.getMapper(MetaTableMapper.class).selectPage(page, wrapper);
        }
    }
    
    @Override
    public MetaTable getMetaTable(String tableName) {
        try (SqlSession sqlSession = sessionService.getSqlSession()) {
            LambdaQueryWrapper<MetaTable> queryWrapper = Wrappers.lambdaQuery(MetaTable.class)
                    .select(MetaTable::getTableName, MetaTable::getTableComment)
                    .eq(MetaTable::getTableName, tableName)
                    .apply("table_schema = database()");
            List<MetaTable> tables = sqlSession.getMapper(MetaTableMapper.class).selectList(queryWrapper);
            return ColUtils.toObj(tables);
        }
    }
}

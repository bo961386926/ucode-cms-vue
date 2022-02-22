/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.code.service.core.impl;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import xin.altitude.cms.code.constant.CodeConstant;
import xin.altitude.cms.code.service.core.IThirdSqlSessionService;
import xin.altitude.cms.common.util.SpringUtils;

/**
 * @author explore
 */
// @Service
public class ThirdSqlSessionServiceImpl implements IThirdSqlSessionService {
    /**
     * 通过连接ID获取SqlSession会话
     *
     * @return SqlSession
     */
    @Override
    public SqlSession getSqlSession() {
        SqlSessionFactory factory = SpringUtils.getBean(CodeConstant.CODE_SQL_SESSION_FACTORY);
        return factory.openSession(ExecutorType.REUSE, true);
    }
}

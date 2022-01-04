package xin.altitude.cms.code.service.core;

import org.apache.ibatis.session.SqlSession;

/**
 * @author explore
 * @since 2021/06/23 17:03
 **/
public interface IThirdSqlSessionService {
    
    SqlSession getSqlSession();
}

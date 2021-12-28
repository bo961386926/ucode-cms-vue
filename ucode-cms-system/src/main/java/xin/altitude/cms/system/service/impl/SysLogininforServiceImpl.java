package xin.altitude.cms.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.altitude.cms.system.domain.SysLogininfor;
import xin.altitude.cms.system.mapper.SysLogininforMapper;
import xin.altitude.cms.system.service.ISysLogininforService;

import java.util.Arrays;
import java.util.List;

/**
 * 系统访问日志情况信息 服务层处理
 *
 * @author ucode
 */
@Service
public class SysLogininforServiceImpl extends ServiceImpl<SysLogininforMapper, SysLogininfor> implements ISysLogininforService {
    
    @Autowired
    private SysLogininforMapper logininforMapper;
    
    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(SysLogininfor logininfor) {
        // logininforMapper.insertLogininfor(logininfor);
        save(logininfor);
    }
    
    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor) {
        // return logininforMapper.selectLogininforList(logininfor);
        return list(Wrappers.lambdaQuery(logininfor));
    }
    
    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     * @return
     */
    @Override
    public boolean deleteLogininforByIds(Long[] infoIds) {
        // return logininforMapper.deleteLogininforByIds(infoIds);
        return removeByIds(Arrays.asList(infoIds));
    }
    
    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLogininfor() {
        // logininforMapper.cleanLogininfor();
        remove(Wrappers.lambdaQuery());
    }
}

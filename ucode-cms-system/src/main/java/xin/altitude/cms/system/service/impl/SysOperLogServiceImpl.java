package xin.altitude.cms.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xin.altitude.cms.system.domain.SysOperLog;
import xin.altitude.cms.system.mapper.SysOperLogMapper;
import xin.altitude.cms.system.service.ISysOperLogService;

import java.util.Arrays;
import java.util.List;

/**
 * 操作日志 服务层处理
 *
 * @author ucode
 */
// @Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService {
    // @Autowired
    // private SysOperLogMapper operLogMapper;
    
    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog) {
        // operLogMapper.insertOperlog(operLog);
        save(operLog);
    }
    
    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        // return operLogMapper.selectOperLogList(operLog);
        return list(Wrappers.lambdaQuery(operLog));
    }
    
    /**
     * 批量删除系统操作日志
     *
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public boolean deleteOperLogByIds(Long[] operIds) {
        // return operLogMapper.deleteOperLogByIds(operIds);
        return removeByIds(Arrays.asList(operIds));
    }
    
    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLog selectOperLogById(Long operId) {
        // return operLogMapper.selectOperLogById(operId);
        return getById(operId);
    }
    
    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog() {
        // operLogMapper.cleanOperLog();
        remove(Wrappers.lambdaQuery());
    }
}

package xin.altitude.cms.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.constant.Constants;
import xin.altitude.cms.common.constant.UserConstants;
import xin.altitude.cms.common.core.text.Convert;
import xin.altitude.cms.common.exception.ServiceException;
import xin.altitude.cms.common.util.StringUtils;
import xin.altitude.cms.system.domain.SysConfig;
import xin.altitude.cms.system.mapper.SysConfigMapper;
import xin.altitude.cms.system.service.ISysConfigService;

import java.util.List;

/**
 * 参数配置 服务层实现
 *
 * @author ucode
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {
    // @Autowired
    // private SysConfigMapper configMapper;
    
    // @Autowired
    // private RedisCache redisCache;
    
    /**
     * 项目启动时，初始化参数到缓存
     */
    // @PostConstruct
    public void init() {
        loadingConfigCache();
    }
    
    /**
     * 查询参数配置信息
     *
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    @Override
    // @DataSource(DataSourceType.MASTER)
    public SysConfig selectConfigById(Long configId) {
        SysConfig config = new SysConfig();
        config.setConfigId(configId);
        // return configMapper.selectConfig(config);
        return getById(configId);
    }
    
    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数key
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey) {
        // String configValue = Convert.toStr(redisCache.getCacheObject(getCacheKey(configKey)));
        // if (StringUtils.isNotEmpty(configValue)) {
        //     return configValue;
        // }
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);
        // SysConfig retConfig = configMapper.selectConfig(config);
        SysConfig retConfig = getOne(Wrappers.lambdaQuery(config));
        if (StringUtils.isNotNull(retConfig)) {
            // redisCache.setCacheObject(getCacheKey(configKey), retConfig.getConfigValue());
            return retConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }
    
    /**
     * 获取验证码开关
     *
     * @return true开启，false关闭
     */
    @Override
    public boolean selectCaptchaOnOff() {
        String captchaOnOff = selectConfigByKey("sys.account.captchaOnOff");
        if (StringUtils.isEmpty(captchaOnOff)) {
            return true;
        }
        return Convert.toBool(captchaOnOff);
    }
    
    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    @Override
    public List<SysConfig> selectConfigList(SysConfig config) {
        // return configMapper.selectConfigList(config);
        return list(Wrappers.lambdaQuery(config));
    }
    
    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int insertConfig(SysConfig config) {
        // int row = configMapper.insertConfig(config);
        boolean row = save(config);
        if (row) {
            // redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        return row ? 1 : 0;
    }
    
    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int updateConfig(SysConfig config) {
    
        // int row = configMapper.updateConfig(config);
        boolean row = updateById(config);
        if (row) {
            // redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        return row ? 1 : 0;
    }
    
    /**
     * 批量删除参数信息
     *
     * @param configIds 需要删除的参数ID
     * @return 结果
     */
    @Override
    public void deleteConfigByIds(Long[] configIds) {
        for (Long configId : configIds) {
            SysConfig config = this.getById(configId);
            if (StringUtils.equals(UserConstants.YES, config.getConfigType())) {
                throw new ServiceException(String.format("内置参数【%1$s】不能删除 ", config.getConfigKey()));
            }
            removeById(configId);
            // configMapper.deleteConfigById(configId);
            // redisCache.deleteObject(getCacheKey(config.getConfigKey()));
        }
    }
    
    /**
     * 加载参数缓存数据
     */
    @Override
    public void loadingConfigCache() {
        // List<SysConfig> configsList = configMapper.selectConfigList(new SysConfig());
        List<SysConfig> configsList = list(Wrappers.lambdaQuery());
        // for (SysConfig config : configsList) {
        //     redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        // }
    }
    
    /**
     * 清空参数缓存数据
     */
    @Override
    public void clearConfigCache() {
        // Collection<String> keys = redisCache.keys(Constants.SYS_CONFIG_KEY + "*");
        // redisCache.deleteObject(keys);
    }
    
    /**
     * 重置参数缓存数据
     */
    @Override
    public void resetConfigCache() {
        clearConfigCache();
        loadingConfigCache();
    }
    
    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public String checkConfigKeyUnique(SysConfig config) {
        Long configId = StringUtils.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        // SysConfig info = configMapper.checkConfigKeyUnique(config.getConfigKey());
        SysConfig info = getOne(Wrappers.lambdaQuery(SysConfig.class).eq(SysConfig::getConfigKey, config.getConfigKey()));
        if (StringUtils.isNotNull(info) && info.getConfigId().longValue() != configId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
    
    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey) {
        return Constants.SYS_CONFIG_KEY + configKey;
    }
}

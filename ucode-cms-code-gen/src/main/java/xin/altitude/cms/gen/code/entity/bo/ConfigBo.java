package xin.altitude.cms.gen.code.entity.bo;

import lombok.Getter;
import lombok.Setter;
import xin.altitude.cms.gen.code.config.property.CodeProperties;
import xin.altitude.cms.gen.code.enums.DaoEnum;
import xin.altitude.cms.gen.code.enums.FileEnum;

import static java.util.Objects.nonNull;

/**
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
@Getter
@Setter
public class ConfigBo extends CodeProperties {
    /**
     * 内部使用
     */
    private final boolean useMybatisPlus = DaoEnum.mybatisPlus.equals(this.getDao());
    /**
     * 是否包含接口服务类
     */
    private boolean hasService;

    public ConfigBo() {
    }

    public ConfigBo(CodeProperties codeProperties) {
        super(codeProperties);
        this.hasService = super.getFiles().contains(FileEnum.service);
    }

    public boolean isHasService() {
        return hasService || super.getFiles().contains(FileEnum.service);
    }

    /**
     * 获取模块名
     *
     * @return 模块名
     */
    @Override
    public String getModuleName() {
        String packageName = super.getPackageName();
        String moduleName = super.getModuleName();
        final int index = packageName.lastIndexOf(".");
        if (index > 0) {
            return nonNull(moduleName) ? moduleName : packageName.substring(index + 1);
        }
        return nonNull(moduleName) ? moduleName : "front";
    }

}

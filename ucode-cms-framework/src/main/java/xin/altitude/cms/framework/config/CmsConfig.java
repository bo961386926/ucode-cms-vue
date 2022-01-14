package xin.altitude.cms.framework.config;

import lombok.Data;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author explore
 * @since 2019/12/22 17:09
 **/
@Data
// @Configuration
@ConfigurationProperties(prefix = "ucode")
public class CmsConfig {
    /* 接口访问统一前缀 */
    public final static String UNIFORM_PREFIX = "/cms-api";
    // public final static String UNIFORM_PREFIX = "";
    private Cms cms;
    private Token token;
    private Xss xss;
    private Swagger swagger;
    private Job job;
    
    @Data
    public static class Cms {
        /* 上传路径 */
        private String profile;
        /* 获取地址开关 */
        private boolean addressEnabled;
        /* 项目名称 */
        private String name;
        /* 版本 */
        private String version;
        /* 版权年份 */
        private String copyrightYear;
        /* 实例演示开关 */
        private boolean demoEnabled;
        /* 启用认证开关 */
        private boolean authEnabled;
        /* 是否开启验证码 */
        private boolean captchaEnabled;
        /* 验证码类型 */
        private String captchaType;
        
        /**
         * 获取导入上传路径
         */
        public String getImportPath() {
            return FilenameUtils.concat(getProfile(), "import");
        }
        
        /**
         * 获取头像上传路径
         */
        public String getAvatarPath() {
            return FilenameUtils.concat(getProfile(), "avatar");
        }
        
        /**
         * 获取下载路径
         */
        public String getDownloadPath() {
            return FilenameUtils.concat(getProfile(), "download");
        }
        
        /**
         * 获取上传路径
         */
        public String getUploadPath() {
            return FilenameUtils.concat(getProfile(), "upload");
        }
    }
    
    /**
     * Token全局配置文件
     */
    @Data
    public static class Token {
        /* 令牌自定义标识 */
        private String header;
        /* 令牌密钥 */
        private String secret;
        /* 令牌有效期 */
        private Integer expireTime = 30;
    }
    
    /**
     * xss全局配置文件
     */
    @Data
    public static class Xss {
        /* 过滤开关 */
        private Boolean enabled = true;
        /* 排除链接（多个用逗号分隔） */
        private String excludes;
        /* 匹配链接 */
        private String urlPatterns;
    }
    
    /**
     * Swagger全局配置文件
     */
    @Data
    public static class Swagger {
        /* 是否开启swagger */
        private Boolean enabled = true;
        /* 请求前缀 */
        private String pathMapping;
    }
    
    /**
     * 定时任务全局配置文件
     */
    @Data
    public static class Job {
        /* 是否开启定时任务 */
        private Boolean enabled = false;
    }
    
    /**
     * 线程池全局配置文件
     */
    @Data
    public static class Thread {
        /* 是否开启swagger */
        private Boolean enabled = true;
        /* 核心线程池大小 */
        private int corePoolSize = 10;
    }
}

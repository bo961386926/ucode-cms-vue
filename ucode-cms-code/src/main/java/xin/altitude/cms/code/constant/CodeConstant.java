package xin.altitude.cms.code.constant;

/**
 * 代码生成器部分常量
 *
 * @author explore
 * @since 2019/12/02 18:51
 **/
public class CodeConstant {
    /**
     * 第三方会话工厂
     */
    public final static String CODE_SQL_SESSION_FACTORY = "CODE_SQL_SESSION_FACTORY";
    /**
     * 当前运行的项目根目录
     */
    public static final String PROJECT_ROOT_ABSOLUTE_PATH = System.getProperty("user.dir");
    /**
     * 项目空间路径
     */
    public static final String PROJECT_RELATIVE_JAVA_PATH = "src/main/java";
    /**
     * mybatis空间路径
     */
    public static final String PROJECT_RELATIVE_MAPPER_PATH = "src/main/resources/mapper";
    /**
     * Mysql数据库元数据主键名称
     */
    public static final String PRIMARY = "PRIMARY";
    /**
     * 系统表前缀
     */
    public static final String SYS_TABLE_PREFIX = "sys_";
    /**
     * 任务表前缀
     */
    public static final String QRTZ_TABLE_PREFIX = "qrtz_";
}

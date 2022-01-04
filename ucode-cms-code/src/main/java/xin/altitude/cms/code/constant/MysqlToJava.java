package xin.altitude.cms.code.constant;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author explore
 * @since 2021/07/12 17:23
 **/
public class MysqlToJava {
    private static final Map<String, String> HASH_MAPS = new HashMap<>();
    
    
    static {
        HASH_MAPS.put("bigint", Long.class.getSimpleName());
        HASH_MAPS.put("int", Integer.class.getSimpleName());
        HASH_MAPS.put("tinyint", Integer.class.getSimpleName());
        HASH_MAPS.put("smallint", Integer.class.getSimpleName());
        HASH_MAPS.put("mediumint", Integer.class.getSimpleName());
        HASH_MAPS.put("year", Integer.class.getSimpleName());
        HASH_MAPS.put("char", String.class.getSimpleName());
        HASH_MAPS.put("varchar", String.class.getSimpleName());
        HASH_MAPS.put("json", String.class.getSimpleName());
        HASH_MAPS.put("tinytext", String.class.getSimpleName());
        HASH_MAPS.put("text", String.class.getSimpleName());
        HASH_MAPS.put("mediumtext", String.class.getSimpleName());
        HASH_MAPS.put("longtext", String.class.getSimpleName());
        HASH_MAPS.put("bit", Boolean.class.getSimpleName());
        HASH_MAPS.put("float", Float.class.getSimpleName());
        HASH_MAPS.put("date", LocalDate.class.getSimpleName());
        HASH_MAPS.put("datetime", LocalDateTime.class.getSimpleName());
        HASH_MAPS.put("timestamp", LocalDateTime.class.getSimpleName());
        HASH_MAPS.put("time", LocalTime.class.getSimpleName());
        HASH_MAPS.put("double", Double.class.getSimpleName());
        HASH_MAPS.put("decimal", BigDecimal.class.getSimpleName());
    }
    
    
    /**
     * 通过Mysql字段类型，返回Java类型
     *
     * @param dataType Mysql字段类型
     * @return Java数据类型
     */
    public static String getJavaType(String dataType) {
        return HASH_MAPS.get(dataType);
    }
    
    /**
     * 判断字段是否存在
     *
     * @param key 键值
     * @return Boolean
     */
    public static Boolean containsKey(String key) {
        return HASH_MAPS.containsKey(key);
    }
}

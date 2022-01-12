package xin.altitude.cms.framework.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author explore
 * @since 2019/05/28 23:51
 **/
// @Configuration
public class JacksonSerializerConfig {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * LocalDate
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer localDateCustomizer() {
        return builder -> {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            builder.serializerByType(LocalDate.class, new LocalDateSerializer(formatter));
            builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(formatter));
        };
    }
    
    /**
     * LocalDateTime
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer localDateTimeCustomizer() {
        return builder -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
        };
    }
    
    /**
     * Long
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer longCustomizer() {
        return builder -> builder.serializerByType(Long.class, ToStringSerializer.instance);
    }
}

package xin.altitude.cms.common.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author explore
 */
public class ResourceUtil {
    private static final PathMatchingResourcePatternResolver RESOLVER = new PathMatchingResourcePatternResolver();
    
    /**
     * 解析mapperLocations
     *
     * @param mapperLocations
     * @return
     */
    public static Resource[] resolveMapperLocations(String mapperLocations) {
        Optional<String> optional = Optional.ofNullable(mapperLocations);
        if (optional.isPresent()) {
            return resolveMapperLocations(StringUtils.split(mapperLocations, ","));
        }
        return new Resource[0];
    }
    
    /**
     * 解析mapperLocations
     *
     * @param mapperLocations
     * @return
     */
    public static Resource[] resolveMapperLocations(String[] mapperLocations) {
        List<Resource> resources = new ArrayList<>();
        if (mapperLocations != null) {
            Arrays.stream(mapperLocations).forEach(mapperLocation -> {
                try {
                    resources.addAll(Arrays.asList(RESOLVER.getResources(mapperLocation)));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        }
        return resources.toArray(new Resource[0]);
    }
    
    /**
     * configLocation
     *
     * @param configLocation
     * @return
     */
    public static Resource resolveConfigLocation(String configLocation) {
        return RESOLVER.getResource(configLocation);
    }
}
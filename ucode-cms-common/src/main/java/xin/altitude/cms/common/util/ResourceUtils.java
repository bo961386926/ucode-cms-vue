

/*
 *
 *  *
 *  *  Copyright (c) 2020-2022, Java知识图谱 (http://www.altitude.xin).
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *  *
 *
 */

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
public class ResourceUtils {
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
            return resolveMapperLocations(StringUtil.split(mapperLocations, ","));
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

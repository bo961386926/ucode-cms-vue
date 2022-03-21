/*
 *
 * Copyright (c) 2020-2022, Java知识图谱 (http://www.altitude.xin).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package xin.altitude.cms.framework.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import xin.altitude.cms.framework.config.AbstractSwaggerConfig;

/**
 * Swagger2的接口配置
 *
 * @author ucode
 */
@ConditionalOnProperty(value = "ucode.swagger.enabled", havingValue = "true")
public class DefaultSwaggerConfig extends AbstractSwaggerConfig {
    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi() {
        Docket docket = createBaseDocket();
        return docket;
    }

}

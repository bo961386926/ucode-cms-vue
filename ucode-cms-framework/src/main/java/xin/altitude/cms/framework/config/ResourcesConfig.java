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

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xin.altitude.cms.common.constant.Constants;
import xin.altitude.cms.common.util.SpringUtils;

/**
 * 通用配置
 *
 * @author ucode
 */
// @Configuration
public class ResourcesConfig implements WebMvcConfigurer {
    private final CmsConfig cmsConfig = SpringUtils.getBean(CmsConfig.class);
    // @Autowired
    // private RepeatSubmitInterceptor repeatSubmitInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /* 本地文件上传路径 */
        registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**")
            .addResourceLocations("file:" + cmsConfig.getCms().getProfile() + "/");

        /* swagger配置 */
        registry.addResourceHandler("/swagger-ui/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");

        /* cms-ui配置 */
        registry.addResourceHandler("/cms-web/**").addResourceLocations("classpath:/META-INF/resources/");
    }

    // /**
    //  * 自定义拦截规则
    //  */
    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     /* 添加全局防重复提交拦截器 */
    //     registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
    // }
}

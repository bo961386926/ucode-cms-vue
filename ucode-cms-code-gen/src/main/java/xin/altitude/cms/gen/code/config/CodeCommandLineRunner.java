/*
 *
 * Copyright (c) 2020-2022, 赛泰先生 (http://www.altitude.xin).
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

package xin.altitude.cms.gen.code.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;

import java.util.Optional;

/**
 * 快捷进入代码生成器控制台
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
public class CodeCommandLineRunner implements CommandLineRunner {
    private final Environment environment;

    public CodeCommandLineRunner(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(String... args) {
        String port = Optional.ofNullable(environment.getProperty("server.port")).orElse("8080");
        System.out.printf("========点击(http://localhost:%s/code.html)进入代码生成器控制台========%n", port);
    }

}

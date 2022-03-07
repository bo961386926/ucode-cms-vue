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

package xin.altitude.cms.common.util;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * RabbitMQ工具类
 *
 * @author explore
 * @since 2022/03/07 10:34
 **/
public class RabbitUtils extends org.springframework.amqp.rabbit.connection.RabbitUtils {

    /**
     * 设置消息的延迟时间（单位毫秒）
     *
     * @param delay 正整数
     * @return MessagePostProcessor
     */
    public static MessagePostProcessor setDelay(Integer delay) {
        Assert.isTrue(delay >= 0, "参数【delay】必须为正整数");
        return message -> {
            message.getMessageProperties().setDelay(delay);
            return message;
        };
    }

    /**
     * 创建CorrelationData工具方法
     *
     * @param id ID
     * @return CorrelationData
     */
    public static CorrelationData correlationData(Serializable id) {
        Assert.notNull(id, "参数【id】不能为空");
        if (id instanceof Number) {
            return new CorrelationData(String.valueOf(id));
        } else if (id instanceof String) {
            return new CorrelationData((String) id);
        }
        return null;
    }
}

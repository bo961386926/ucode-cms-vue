package xin.ucode.admin.task;

import org.springframework.stereotype.Component;

/**
 * 定时任务调度测试
 *
 * @author ucode
 */
@Component("ryTask")
public class RyTask {
    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i) {
        System.out.printf("执行多参方法： 字符串类型%s，布尔类型%d，长整型%d，浮点型%d，整形%d%n", s, b, l, d, i);
    }
    
    public void ryParams(String params) {
        System.out.println("执行有参方法：" + params);
    }
    
    public void ryNoParams() {
        System.out.println("执行无参方法");
    }
}

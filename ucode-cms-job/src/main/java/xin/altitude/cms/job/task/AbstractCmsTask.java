package xin.altitude.cms.job.task;

/**
 * 这里提供定时任务编写的演示示例，分别包含无参数方法、单一参数方法、多参数方法
 * 其中参数类型包含：字符串、整型、布尔、长整型、浮点型
 * 添加到此类或者子类中，并且加载到容器中的方法方能被正确调用
 *
 * @author explore
 * @since 2022/01/12 16:01
 **/
public abstract class AbstractCmsTask {
    public void mutiParams(String s, Boolean b, Long l, Double d, Integer i) {
        System.out.printf("执行多参方法： 字符串类型%s，布尔类型%d，长整型%d，浮点型%d，整形%d%n", s, b, l, d, i);
    }
    
    public void oneParams(String params) {
        System.out.println("执行有参方法：" + params);
    }
    
    public void noParams() {
        System.out.println("执行无参方法");
    }
}

package xin.altitude.cms.common.lang;

/**
 * 定义抽象类，单纯实现Cloneable接口
 * 将clone方法修饰符转化为public
 *
 * @author explore
 * @since 2022/03/24 19:57
 **/
public abstract class AbstractObject implements Cloneable {
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

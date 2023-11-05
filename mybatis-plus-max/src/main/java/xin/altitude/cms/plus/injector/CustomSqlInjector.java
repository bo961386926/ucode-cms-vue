package xin.altitude.cms.plus.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import xin.altitude.cms.plus.method.Decr;
import xin.altitude.cms.plus.method.Incr;

import java.util.List;

/**
 * 自定义Sql注入
 *
 * @author 赛泰先生
 */
@ConditionalOnClass(DefaultSqlInjector.class)
public class CustomSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        //增加自定义方法
        // 自增
        methodList.add(new Incr("incr"));
        // 自减
        methodList.add(new Decr("decr"));
        return methodList;
    }
}

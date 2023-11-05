package xin.altitude.cms.common.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 集合转换器
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
public class ListConverter implements Converter<String, List<String>> {
    @Override
    public List<String> convert(String source) {
        List<String> splits = Arrays.asList(source.split(","));
        return new ArrayList<>(splits);
    }
}

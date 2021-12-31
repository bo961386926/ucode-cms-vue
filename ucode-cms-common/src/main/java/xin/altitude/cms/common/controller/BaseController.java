package xin.altitude.cms.common.controller;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import xin.altitude.cms.common.entity.AjaxResult;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author explore
 * @since 2021/12/31 13:13
 **/
public interface BaseController {
    
    /**
     * 将前台传递过来的
     *
     * @param binder 数据绑定器
     */
    @InitBinder
    default void initBinder(WebDataBinder binder) {
        /* Date 类型转换 */
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                try {
                    setValue(DateUtils.parseDate(text));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        /* Date 集合类型转换 */
        binder.registerCustomEditor(List.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text != null) {
                    setValue(Arrays.stream(text.split(",")).filter(e -> e.length() > 0).collect(Collectors.toList()));
                }
            }
        });
    }
    
    /**
     * 返回成功
     *
     * @return AjaxResult
     */
    default AjaxResult success() {
        return AjaxResult.success();
    }
    
    /**
     * 返回成功
     *
     * @param data 具体响应体数据
     * @return AjaxResult
     */
    default AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }
}

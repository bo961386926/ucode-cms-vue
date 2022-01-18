package xin.ucode.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.common.entity.AjaxResult;

import java.util.List;

/**
 * @author explore
 * @since 2019/12/31 13:33
 **/
@RestController
public class TestController {
    // @ApiOperation("分页查询标签列表")
    @GetMapping("/test")
    public AjaxResult test(@RequestParam List<String> data) {
        return AjaxResult.success(data);
    }
    
    @GetMapping("/test2")
    public AjaxResult test2(String[] data) {
        return AjaxResult.success(data);
    }
    
    @GetMapping("/test3")
    public AjaxResult test3() {
        return AjaxResult.success(new PageDTO<String>());
    }
}

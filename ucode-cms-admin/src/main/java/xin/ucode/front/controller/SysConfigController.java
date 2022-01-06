package xin.ucode.front.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xin.ucode.front.service.ISysConfigService;
import java.util.Arrays;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.ucode.front.mapper.SysConfigMapper;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import xin.altitude.cms.common.entity.PageEntity;
import xin.ucode.front.domain.SysConfig;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
@RequestMapping("/front/sys/config")
public class SysConfigController{
    @Autowired
    private ISysConfigService sysConfigService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,SysConfig sysConfig){
        return AjaxResult.success(sysConfigService.page(pageEntity.toPage(), Wrappers.lambdaQuery(sysConfig)));
    }
    @GetMapping("/list")
    public AjaxResult list(SysConfig sysConfig){
        return AjaxResult.success(sysConfigService.list(Wrappers.lambdaQuery(sysConfig)));
    }
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysConfig sysConfig) {
        return AjaxResult.success(sysConfigService.save(sysConfig));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody SysConfig sysConfig) {
        return AjaxResult.success(sysConfigService.updateById(sysConfig));
    }
    @DeleteMapping("/delete/{configIds}")
    public AjaxResult delete(@PathVariable Integer[] configIds) {
        return AjaxResult.success(sysConfigService.removeByIds(Arrays.asList(configIds)));
    }
    @GetMapping(value = "/detail/{configId}")
    public AjaxResult detail(@PathVariable("configId") Integer configId) {
        return AjaxResult.success(sysConfigService.getById(configId));
    }
}

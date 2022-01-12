package xin.ucode.admin.task;

import org.springframework.stereotype.Component;
import xin.altitude.cms.job.constant.JobConstant;
import xin.altitude.cms.job.task.AbstractCmsTask;

/**
 * 定时任务调度测试
 *
 * @author ucode
 */
@Component(JobConstant.CMS_TASK_NAME)
public class CmsTask extends AbstractCmsTask {
    @Override
    public void mutiParams(String s, Boolean b, Long l, Double d, Integer i) {
        super.mutiParams(s, b, l, d, i);
    }
    
    @Override
    public void oneParams(String params) {
        super.oneParams(params);
    }
    
    @Override
    public void noParams() {
        super.noParams();
    }
}

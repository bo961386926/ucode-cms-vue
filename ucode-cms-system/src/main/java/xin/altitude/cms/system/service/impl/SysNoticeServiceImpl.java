package xin.altitude.cms.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xin.altitude.cms.system.domain.SysNotice;
import xin.altitude.cms.system.mapper.SysNoticeMapper;
import xin.altitude.cms.system.service.ISysNoticeService;

import java.util.Arrays;
import java.util.List;

/**
 * 公告 服务层实现
 *
 * @author ucode
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements ISysNoticeService {
    // @Autowired
    // private SysNoticeMapper noticeMapper;
    
    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId) {
        // return noticeMapper.selectNoticeById(noticeId);
        return getById(noticeId);
    }
    
    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice) {
        // return noticeMapper.selectNoticeList(notice);
        return list(Wrappers.lambdaQuery(notice));
    }
    
    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public boolean insertNotice(SysNotice notice) {
        // return noticeMapper.insertNotice(notice);
        return save(notice);
    }
    
    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public boolean updateNotice(SysNotice notice) {
        // return noticeMapper.updateNotice(notice);
        return updateById(notice);
    }
    
    /**
     * 删除公告对象
     *
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public boolean deleteNoticeById(Long noticeId) {
        // return noticeMapper.deleteNoticeById(noticeId);
        return removeById(noticeId);
    }
    
    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public boolean deleteNoticeByIds(Long[] noticeIds) {
        // return noticeMapper.deleteNoticeByIds(noticeIds);
        return removeByIds(Arrays.asList(noticeIds));
    }
}

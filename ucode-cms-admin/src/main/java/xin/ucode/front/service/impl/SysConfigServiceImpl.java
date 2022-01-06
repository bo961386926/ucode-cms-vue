package xin.ucode.front.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.ucode.front.domain.SysConfig;
import xin.ucode.front.mapper.SysConfigMapper;
import xin.ucode.front.service.ISysConfigService;
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper,SysConfig> implements ISysConfigService{
}

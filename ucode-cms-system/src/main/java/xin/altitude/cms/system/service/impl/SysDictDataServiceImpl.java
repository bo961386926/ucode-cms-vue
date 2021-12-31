package xin.altitude.cms.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xin.altitude.cms.common.util.EntityUtils;
import xin.altitude.cms.framework.core.domain.SysDictData;
import xin.altitude.cms.system.mapper.SysDictDataMapper;
import xin.altitude.cms.system.service.ISysDictDataService;

import java.util.List;

/**
 * 字典 业务层处理
 *
 * @author ucode
 */
// @Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements ISysDictDataService {
    // @Autowired
    // private SysDictDataMapper dictDataMapper;
    
    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData) {
        // return dictDataMapper.selectDictDataList(dictData);
        return list(Wrappers.lambdaQuery(dictData));
    }
    
    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        // return dictDataMapper.selectDictLabel(dictType, dictValue);
        LambdaQueryWrapper<SysDictData> wrapper = Wrappers.lambdaQuery(SysDictData.class).select(SysDictData::getDictLabel).eq(SysDictData::getDictType, dictType).eq(SysDictData::getDictValue, dictValue);
        return EntityUtils.toObj(getOne(wrapper), SysDictData::getDictLabel);
    }
    
    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictData selectDictDataById(Long dictCode) {
        // return dictDataMapper.selectDictDataById(dictCode);
        return getById(dictCode);
    }
    
    /**
     * 批量删除字典数据信息
     *
     * @param dictCodes 需要删除的字典数据ID
     */
    @Override
    public void deleteDictDataByIds(Long[] dictCodes) {
        for (Long dictCode : dictCodes) {
            SysDictData data = selectDictDataById(dictCode);
            // dictDataMapper.deleteDictDataById(dictCode);
            removeById(dictCode);
            // List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
            List<SysDictData> dictDatas = list(Wrappers.lambdaQuery(SysDictData.class).eq(SysDictData::getDictType, data.getDictType()));
            // DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
    }
    
    /**
     * 新增保存字典数据信息
     *
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public boolean insertDictData(SysDictData data) {
        // int row = dictDataMapper.insertDictData(data);
        boolean row = save(data);
        if (row) {
            // List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
            List<SysDictData> dictDatas = list(Wrappers.lambdaQuery(SysDictData.class).eq(SysDictData::getDictType, data.getDictType()));
            // DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
        return row;
    }
    
    /**
     * 修改保存字典数据信息
     *
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public boolean updateDictData(SysDictData data) {
        // int row = dictDataMapper.updateDictData(data);
        boolean row = updateById(data);
        if (row) {
            // List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
            List<SysDictData> dictDatas = list(Wrappers.lambdaQuery(SysDictData.class).eq(SysDictData::getDictType, data.getDictType()));
            // DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
        return row;
    }
}

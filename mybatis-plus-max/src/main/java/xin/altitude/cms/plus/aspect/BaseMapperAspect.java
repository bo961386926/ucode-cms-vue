package xin.altitude.cms.plus.aspect;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import xin.altitude.cms.common.util.EntityUtils;
import xin.altitude.cms.common.util.RedisBitMapUtils;
import xin.altitude.cms.plus.util.PlusUtils;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 定义{@link BaseMapper}AOP抽象类
 * <p>
 * 用以实现与Redis BitMap无缝拓展
 * <p>
 * 对于保存{@link BaseMapper#insert(Object)}方法，使用事务严格保证数据一致性，防止出现数据在表中存在却查不出来的情况
 * <p>
 * 对于删除方法，这里未使用事务，以业务优先为原则，当然用户端可自行添加事务约束
 *
 * @author 赛泰先生
 * @since 1.6.4
 */
public abstract class BaseMapperAspect<T> {
    /**
     * BitMap格式化字符串 用来格式化默认BitMap Key
     */
    private static final String BITMAP_FORMAT = "REDIS:BITMAP:KEY:%s";
    /**
     * 日志收集器
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 建议子类重写此方法，根据业务指定具体的Redis BitMap Key
     * <p>
     * 要求具有唯一性
     * <p>
     * 如果不重写，则使用默认值
     * <pre>
     *     BITMAP:KEY:User
     * </pre>
     *
     * @return BItMap Key字符串
     */
    protected String bitMapKey() {
        return String.format(BITMAP_FORMAT, entityClazz().getSimpleName());
    }


    /**
     * 定义抽象方法 子类实现
     *
     * @return DO实体类Class对象
     */
    protected abstract Class<T> entityClazz();


    /**
     * 拦截{@link BaseMapper#insert(Object)}方法
     */
    @Pointcut("execution(public * com.baomidou.mybatisplus.core.mapper.BaseMapper.insert(..))")
    public void insert() {
    }

    @Around("insert()")
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor = Exception.class)
    public Integer insert(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        T entity = (T) args[0];
        Integer result = (Integer) jp.proceed();
        if (result > 0) {
            // 取出主键ID 包含回填的主键ID
            Serializable pkVal = PlusUtils.pkVal(entity, entityClazz());
            // 向Redis BitMap中保存当前主键ID值
            RedisBitMapUtils.setBit(bitMapKey(), pkVal);
            logger.debug("[向Redis中添加BitMap数据，其中Key:{}，主键ID:{}，offset:{}]", bitMapKey(), pkVal, Objects.hash(pkVal));
            return result;
        }
        return 0;
    }


    /**
     * 拦截主键删除{@link BaseMapper#deleteById(Serializable)}或者{@link BaseMapper#deleteById(Object)}方法
     */
    @Pointcut("execution(public * com.baomidou.mybatisplus.core.mapper.BaseMapper.deleteById(*))")
    public void deleteById() {
    }

    @Around("deleteById()")
    @SuppressWarnings("unchecked")
    public Integer deleteById(ProceedingJoinPoint jp) throws Throwable {
        Object arg = jp.getArgs()[0];
        Serializable id;
        if (arg instanceof String || arg instanceof Number) {
            id = (Serializable) arg;
        } else {
            id = PlusUtils.pkVal((T) arg, entityClazz());
        }
        Integer result = (Integer) jp.proceed();
        if (result != null && result > 0) {
            RedisBitMapUtils.removeBit(bitMapKey(), id);
            logger.debug("[向Redis中移除BitMap数据，其中Key:{} 主键ID:{}]", bitMapKey(), id);
        }
        return result;
    }


    /**
     * 拦截删除{@link BaseMapper#deleteByMap(Map)}方法
     */
    @Pointcut("execution(public * com.baomidou.mybatisplus.core.mapper.BaseMapper.deleteByMap(*))")
    public void deleteByMap() {
    }

    @Around("deleteByMap()")
    @SuppressWarnings("unchecked")
    public Integer deleteByMap(ProceedingJoinPoint jp) throws Throwable {
        Object arg = jp.getArgs()[0];

        Map<String, Object> map = (Map<String, Object>) arg;
        List<T> ts = ((BaseMapper<T>) jp.getTarget()).selectByMap(map);
        return doDelete(jp, ts);
    }


    /**
     * 拦截删除{@link BaseMapper#delete(Wrapper)}方法
     */
    @Pointcut("execution(public * com.baomidou.mybatisplus.core.mapper.BaseMapper.delete(*))")
    public void delete() {
    }

    @Around("delete()")
    @SuppressWarnings("unchecked")
    public Integer delete(ProceedingJoinPoint jp) throws Throwable {
        Object arg = jp.getArgs()[0];

        Wrapper<T> queryWrapper = (Wrapper<T>) arg;
        List<T> ts = ((BaseMapper<T>) jp.getTarget()).selectList(queryWrapper);
        return doDelete(jp, ts);
    }


    /**
     * 重构消除重复代码
     */
    private Integer doDelete(ProceedingJoinPoint jp, List<T> ts) throws Throwable {
        List<Serializable> ids = EntityUtils.toList(ts, e -> PlusUtils.pkVal(e, entityClazz()));

        Integer result = (Integer) jp.proceed();
        if (result != null && result > 0) {
            RedisBitMapUtils.removeBit(bitMapKey(), ids);
            logger.debug("[向Redis中移除BitMap数据，其中Key:{} 主键ID:{}]", bitMapKey(), ids.toArray());
        }
        return result;
    }

    /**
     * 拦截主键ID批量删除{@link BaseMapper#deleteBatchIds(Collection)}方法
     */
    @Pointcut("execution(public * com.baomidou.mybatisplus.core.mapper.BaseMapper.deleteBatchIds(..))")
    public void deleteBatchIds() {
    }

    @Around("deleteBatchIds()")
    @SuppressWarnings("unchecked")
    public Integer deleteBatchIds(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        Collection<Serializable> ids = (Collection<Serializable>) args[0];
        Integer result = (Integer) jp.proceed();
        if (result != null && result > 0) {
            RedisBitMapUtils.removeBit(bitMapKey(), ids);
            logger.debug("[向Redis中移除BitMap数据，其中Key:{} 主键ID:{}]", bitMapKey(), ids.toArray());
        }
        return result;
    }

    /**
     * 拦截主键查询{@link BaseMapper#selectById(Serializable)}方法
     */
    @Pointcut("execution(public * com.baomidou.mybatisplus.core.mapper.BaseMapper.selectById(..))")
    public void selectById() {
    }

    @Around("selectById()")
    public Object selectById(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        Serializable id = (Serializable) args[0];
        boolean isExist = RedisBitMapUtils.checkId(bitMapKey(), id);
        if (isExist) {
            logger.debug("[向Redis中查询BitMap数据，其中Key:{}，主键ID:{}，offset:{}，结果:主键ID存在，行为：允许继续访问DB]", bitMapKey(), id, Objects.hash(id));
            Object proceed = jp.proceed();
            if (Objects.isNull(proceed)) {
                RedisBitMapUtils.removeBit(bitMapKey(), id);
                logger.debug("[数据库中主键ID:{}不存在，已向Redis中删除BitMap数据，其中Key:{}，offset:{}，结果:主键ID存在，行为：允许继续访问DB]", id, bitMapKey(), Objects.hash(id));
            }
            return proceed;
        }
        logger.debug("[向Redis中查询BitMap数据，其中Key:{}，主键ID:{}，offset:{}，结果:主键ID不存在，行为：快速返回]", bitMapKey(), id, Objects.hash(id));
        return null;
    }


    /**
     * 拦截主键查询{@link BaseMapper#selectBatchIds(Collection)}方法
     */
    @Pointcut("execution(public * com.baomidou.mybatisplus.core.mapper.BaseMapper.selectBatchIds(..))")
    public void selectBatchIds() {
        File file = null;
        Arrays.stream(file.listFiles());
        List<File> collect = Arrays.stream(file.listFiles()).sorted(Comparator.comparing(File::getName)).collect(Collectors.toList());
    }

    @Around("selectBatchIds()")
    @SuppressWarnings("unchecked")
    public Object selectBatchIds(ProceedingJoinPoint jp) throws Throwable {
        Object arg = jp.getArgs()[0];
        Collection<Serializable> ids = (Collection<Serializable>) arg;
        Collection<Serializable> newIds = RedisBitMapUtils.checkIds(bitMapKey(), ids);

        if (newIds.size() > 0) {
            logger.debug("[向Redis中查询BitMap数据，其中Key:{} 主键ID:{}，结果:主键ID存在，行为：允许继续访问DB]", bitMapKey(), newIds.toArray());
            Object[] objs = new Object[]{newIds};
            Collection<T> proceed = (Collection<T>) jp.proceed(objs);
            List<Serializable> reIds = EntityUtils.toList(proceed, e -> PlusUtils.pkVal(e, entityClazz()));
            if (reIds.size() < newIds.size()) {
                List<String> notExistIds = PlusUtils.subtract(newIds, reIds);
                RedisBitMapUtils.removeBit(bitMapKey(), notExistIds);
                logger.debug("[存在于Redis BitMap中却不存在于数据库中的主键ID:{}已被删除，其中Key:{}]", newIds.toArray(), bitMapKey());
            }
            return proceed;
        }
        logger.debug("[向Redis中查询BitMap数据，其中Key:{} 主键ID:{}，结果:主键ID不存在，行为：快速返回]", bitMapKey(), ids.toArray());
        return null;
    }

}

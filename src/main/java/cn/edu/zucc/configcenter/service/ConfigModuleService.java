package cn.edu.zucc.configcenter.service;

import cn.edu.zucc.configcenter.pojo.BeanConfigModule;
import cn.edu.zucc.configcenter.utils.exception.BaseException;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author crabxyj
 * @date 2020/5/13 22:07
 */
public interface ConfigModuleService {
    void add(BeanConfigModule configModule)throws BaseException;
    void addIfNotExist( BeanConfigModule configModule) throws BaseException;
    void delByIds(Collection<Serializable> ids)throws BaseException;
    void modify(BeanConfigModule configModule)throws BaseException;
//    List<BeanConfigModule> load(String moduleName, String configKey, int version);

    /**
     * 分页查询
     * @param moduleName 模块名
     * @param configKey 配置项名称
     * @param version 版本
     * @param curPage 当前页
     * @param pageSize 页大小
     * @return 查询结果
     */
    IPage<BeanConfigModule> loadPage(String moduleName, String configKey,int version, long curPage, long pageSize);

    List<String> loadModuleNames();
}

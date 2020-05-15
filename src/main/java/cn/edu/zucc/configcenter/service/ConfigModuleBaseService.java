package cn.edu.zucc.configcenter.service;

import cn.edu.zucc.configcenter.pojo.BeanConfigModuleBase;
import cn.edu.zucc.configcenter.utils.exception.BaseException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author crabxyj
 * @date 2020/5/15 18:20
 */
public interface ConfigModuleBaseService {
    BeanConfigModuleBase info(String moduleName,String version) throws BaseException;

    IPage<BeanConfigModuleBase> loadPage(String moduleName, String version, long curPage, long pageSize);

    void add(BeanConfigModuleBase moduleBase) throws BaseException;

    void modify(BeanConfigModuleBase moduleBase) throws BaseException;
}

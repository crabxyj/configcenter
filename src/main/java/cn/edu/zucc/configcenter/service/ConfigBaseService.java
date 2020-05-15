package cn.edu.zucc.configcenter.service;

import cn.edu.zucc.configcenter.pojo.BeanConfigBase;
import cn.edu.zucc.configcenter.utils.exception.BaseException;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author crabxyj
 * @date 2020/5/15 15:37
 */
public interface ConfigBaseService {
    void add(BeanConfigBase configBase)throws BaseException;
    void delByIds(Collection<Serializable> ids)throws BaseException;
    void modify(BeanConfigBase configBase)throws BaseException;

    IPage<BeanConfigBase> loadPage(String baseName,String type, String url, long curPage, long pageSize);
    List<BeanConfigBase> load();
}

package cn.edu.zucc.configcenter.dao;

import cn.edu.zucc.configcenter.pojo.BeanConfigBase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author crabxyj
 * @date 2020/5/13 22:28
 */
@Repository("configBaseDao")
public interface ConfigBaseDao extends BaseMapper<BeanConfigBase> {
}

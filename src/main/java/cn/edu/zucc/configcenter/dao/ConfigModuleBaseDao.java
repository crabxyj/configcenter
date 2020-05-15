package cn.edu.zucc.configcenter.dao;

import cn.edu.zucc.configcenter.pojo.BeanConfigModuleBase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author crabxyj
 * @date 2020/5/15 18:19
 */
@Repository("configModuleBaseDao")
public interface ConfigModuleBaseDao extends BaseMapper<BeanConfigModuleBase> {
    @Select("select base_id from config_module_base where moduleName=#{moduleName} and version=#{version}")
    List<Integer> getModuleBase(String moduleName,String version);
}

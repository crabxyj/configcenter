package cn.edu.zucc.configcenter.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Objects;

/**
 * @author crabxyj
 * @date 2020/5/13 22:04
 */
@Data
@Accessors(chain = true)
@TableName("config_module")
public class BeanConfigModule {
    @TableId(type = IdType.AUTO)
    private int id;

    @TableField
    @NotEmpty(message = "模块名称不能为空")
    private String moduleName;

    @TableField
    @NotEmpty(message = "配置项不能为空")
    private String configKey;

    @TableField
    @NotEmpty(message = "配置项值不能为空")
    private String configValue;

    @TableField
    @NotEmpty(message = "配置项版本不能为空")
    private String version;

    @TableField
    private Date createTime;

    @Override
    public int hashCode() {
        return id!=0?id:super.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof BeanConfigModule){
            BeanConfigModule one = (BeanConfigModule) obj;
            if (id!=0){
                return id==one.getId();
            }
            if (!Objects.equals(moduleName,one.getModuleName())){
                return false;
            }
            if (!Objects.equals(configKey,one.getConfigKey())){
                return false;
            }
            if (!Objects.equals(configValue,one.getConfigValue())){
                return false;
            }
            return Objects.equals(version,one.getVersion());
        }
        return false;
    }

}

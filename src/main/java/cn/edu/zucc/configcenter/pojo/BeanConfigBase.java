package cn.edu.zucc.configcenter.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author crabxyj
 * @date 2020/5/13 22:26
 */
@Data
@Accessors(chain = true)
@TableName("configdatabase")
public class BeanConfigBase {
    @TableId
    private int baseId;
    private String baseName;
    private String url;
    private String username;
    private String password;
}

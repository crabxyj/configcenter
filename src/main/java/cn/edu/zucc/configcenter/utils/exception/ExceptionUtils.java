package cn.edu.zucc.configcenter.utils.exception;

/**
 * @author crabxyj
 * @date 2020/5/14 18:45
 */
public class ExceptionUtils {
    public BaseException exBusinessfromMsg(String msg){
        return new BaseException(1,msg);
    }
}

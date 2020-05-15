package cn.edu.zucc.configcenter.utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author crabxyj
 * @date 2020/5/15 21:05
 */
public class StrUtils {
    public String rmDouble(String keys,String sep){
        return Stream.of(keys.split(sep))
                .filter(str->!"".equals(str))
                .distinct()
                .collect(Collectors.joining(sep));
    }
}

package com.boki.bokiapi.entity.vo;

import com.boki.bokiapi.execption.BusinessException;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LJF
 * @Date: 2020/3/6
 * @Description:
 */
@Data
@Accessors(chain = true)
@Slf4j
public class DataWithTotal<T> {
    private Integer totalCount;                         //总数
    private List list = new ArrayList<T>();;          //分页数据

    /**
     * 构造数据
     * @param result
     */
    public void input(List<List<?>> result,Class<T> kind){
        if(result.get(0) != null && result.get(0).size() != 0){
            this.totalCount = (Integer) result.get(0).get(0);
            if(result.get(1) != null && result.get(1).size() != 0){
                this.list = new ArrayList<T>();
                for(Object o : result.get(1)){
                    T t = getInstance(kind);
                    BeanUtils.copyProperties(o,t);
                    this.list.add(t);
                }
            }
        }
    }

    private T getInstance(Class<T> kind){
        T t;
        try {
            t = kind.newInstance();
        } catch (InstantiationException e) {
            log.warn("DataWithTotal.getInstance出错:InstantiationException");
            throw new BusinessException(e.getMessage()).setType(RequestResultCode.SERVER_ERROR);
        } catch (IllegalAccessException e) {
            log.warn("DataWithTotal.getInstance出错:IllegalAccessException");
            throw new BusinessException(e.getMessage()).setType(RequestResultCode.SERVER_ERROR);
        }
        return t;
    }
}

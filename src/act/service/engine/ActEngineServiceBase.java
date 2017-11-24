package com.thinkgem.jeesite.modules.act.service.engine;

import java.util.List;

/**
 * Created by hiden on 2017/11/20.
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
public interface ActEngineServiceBase<T> {
    public int saveBatch(List<T> data);
}

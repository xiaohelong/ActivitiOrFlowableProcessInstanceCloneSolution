package com.thinkgem.jeesite.modules.act.dao.engine;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.modules.act.entity.engine.ActHiComment;

import java.util.List;

/**
 * Created by xiaohelong on 2017/11/20.
 * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
public interface ActEngineDaoBase<T> extends CrudDao<T> {
    public int saveBatch(List<T> data);
}

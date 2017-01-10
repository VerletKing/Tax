package com.nsfw.complain.dao;

import java.util.List;

import com.core.dao.BaseDao;
import com.nsfw.complain.entity.Complain;

public interface ComplainDao extends BaseDao<Complain> {

	List<Object[]> getAnnualStatisticDataByYear(int year);
}

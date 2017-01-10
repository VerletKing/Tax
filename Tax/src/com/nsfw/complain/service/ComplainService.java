package com.nsfw.complain.service;

import java.util.List;
import java.util.Map;

import com.core.service.BaseService;
import com.nsfw.complain.entity.Complain;

public interface ComplainService extends BaseService<Complain> {
	public void autoProcess();

	public List<Map<String,String>> getAnnualStatisticDataByYear(int year);
}

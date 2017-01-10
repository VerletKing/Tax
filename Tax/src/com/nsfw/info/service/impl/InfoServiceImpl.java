package com.nsfw.info.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.core.service.impl.BaseServiceImpl;
import com.nsfw.info.dao.InfoDao;
import com.nsfw.info.entity.Info;
import com.nsfw.info.service.InfoService;

@Service("infoService")
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService {
	
	
	InfoDao infoDao;
	
	@Resource
	public void setInfoDao(InfoDao infoDao) {
		super.setBaseDao(infoDao);
		this.infoDao = infoDao;
	}
}

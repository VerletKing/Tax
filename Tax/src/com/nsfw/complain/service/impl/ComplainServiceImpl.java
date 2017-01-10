package com.nsfw.complain.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.core.service.impl.BaseServiceImpl;
import com.core.utils.QueryHelper;
import com.nsfw.complain.dao.ComplainDao;
import com.nsfw.complain.entity.Complain;
import com.nsfw.complain.service.ComplainService;

@Service("complainService")
public class ComplainServiceImpl extends BaseServiceImpl<Complain> implements
		ComplainService {

	ComplainDao complainDao;
	
	@Resource
	public void setComplainDao(ComplainDao complainDao) {
		super.setBaseDao(complainDao);
		this.complainDao = complainDao;
	}

	@Override
	public void autoProcess() {
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.DAY_OF_MONTH, 1);
		calender.set(Calendar.HOUR_OF_DAY, 0);
		calender.set(Calendar.MINUTE, 0);
		calender.set(Calendar.SECOND, 0);
		
		QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
		queryHelper.addWhere("c.state=?", Complain.COMPLAIN_STATE_UNDONE);
		queryHelper.addWhere("c.compTime<?", calender.getTime());
		
		List<Complain> list = complainDao.find(queryHelper);
		if(list!=null && list.size()>0){
			for(Complain complain : list){
				complain.setState(Complain.COMPLAIN_STATE_INVALID);
				complainDao.update(complain);
			}
		}
	}

	@Override
	public List<Map<String,String>> getAnnualStatisticDataByYear(int year) {
		List<Map<String,String>> returnList = new ArrayList<Map<String,String>>();
		Calendar calendar = Calendar.getInstance();
		boolean isCurYear = calendar.get(Calendar.YEAR)==year;
		int curMonth = calendar.get(Calendar.MONTH)+1;
		List<Object[]> list = complainDao.getAnnualStatisticDataByYear(year);
		if(list!=null && list.size()>0){
			Map<String,String> map;
			for(Object[] obj : list){
				map = new HashMap<String, String>();
				int tempMonth = Integer.valueOf(obj[0].toString());
				map.put("label", tempMonth+" 月");
				//当前年
				if(isCurYear){
					//以后的月
					if(tempMonth>curMonth){
						map.put("value", "");
					}else{
						map.put("value", obj[1]==null ? "0" : obj[1].toString());
					}
				}else{
					map.put("value", obj[1]==null ? "0" : obj[1].toString());
				}
				returnList.add(map);
			}
		}
		return returnList;
	}
}

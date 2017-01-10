package com.nsfw.complain.action;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;

import com.core.action.BaseAction;
import com.core.utils.QueryHelper;
import com.nsfw.complain.entity.Complain;
import com.nsfw.complain.entity.ComplainReply;
import com.nsfw.complain.service.ComplainService;
import com.opensymphony.xwork2.ActionContext;

public class ComplainAction extends BaseAction {
	
	@Resource
	private ComplainService complainService;
	
	private Complain complain;
	private String startTime;
	private String endTime;
	private ComplainReply reply;
	private String strTitle;
	private String strState;
	private Map<String,Object> map;
	
	public String listUI() throws Exception{
		ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
		QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
		if(StringUtils.isNotBlank(startTime)){
			startTime = URLDecoder.decode(startTime, "utf-8");
			queryHelper.addWhere("c.compTime>=?", DateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm"));
		}
		if(StringUtils.isNotBlank(endTime)){
			endTime = URLDecoder.decode(endTime, "utf-8");
			queryHelper.addWhere("c.compTime<=?", DateUtils.parseDate(endTime, "yyy-MM-dd HH:mm"));
		}
		if(complain!=null){
			if(StringUtils.isNotBlank(complain.getState())){
				queryHelper.addWhere("c.state=?", complain.getState());
			}
			if(StringUtils.isNotBlank(complain.getCompTitle())){
				complain.setCompTitle(URLDecoder.decode(complain.getCompTitle(), "utf-8"));
				queryHelper.addWhere("c.compTitle like ?", "%"+complain.getCompTitle()+"%");
			}
		}
		queryHelper.addOrder_by("c.state", QueryHelper.ORDER_BY_ASC);
		queryHelper.addOrder_by("c.compTime", QueryHelper.ORDER_BY_ASC);
		pageResult = complainService.find(queryHelper, getPageNo(), getPageSize());
		return "listUI";
	}
	
	public String dealUI(){
		ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
		if(complain!=null){
			strTitle = complain.getCompTitle();
			strState = complain.getState();
			complain = complainService.findById(complain.getCompId());
		}
		return "dealUI";
	}
	
	public String deal(){
		if(complain!=null){
			Complain tem = complainService.findById(complain.getCompId());
			if(!Complain.COMPLAIN_STATE_DONE.equals(tem.getState())){
				tem.setState(Complain.COMPLAIN_STATE_DONE);
			}
			if(reply!=null){
				reply.setComplain(tem);
				reply.setReplyTime(new Timestamp(new Date().getTime()));
				tem.getComplainReplies().add(reply);
			}
			complainService.update(tem);
		}
		return "list";
	}
	
	public String annualStatisticChartUI(){
		return "annualStatisticChartUI";
	}
	
	public String getAnnualStatisticData(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		if(request.getParameter("year")!=null){
			year = Integer.parseInt(request.getParameter("year"));
		}
		map = new HashMap<String, Object>();
		map.put("msg", "success");
		map.put("chartData", complainService.getAnnualStatisticDataByYear(year));
		return "getAnnualStatisticData";
	}
	
	public Complain getComplain() {
		return complain;
	}

	public void setComplain(Complain complain) {
		this.complain = complain;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public ComplainReply getReply() {
		return reply;
	}

	public void setReply(ComplainReply reply) {
		this.reply = reply;
	}

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	public String getStrState() {
		return strState;
	}

	public void setStrState(String strState) {
		this.strState = strState;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Map<String, Object> getMap() {
		return map;
	}
}

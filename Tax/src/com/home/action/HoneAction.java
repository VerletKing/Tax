package com.home.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.core.constant.Constant;
import com.core.page.PageResult;
import com.core.utils.QueryHelper;
import com.nsfw.complain.entity.Complain;
import com.nsfw.complain.service.ComplainService;
import com.nsfw.info.entity.Info;
import com.nsfw.info.service.InfoService;
import com.nsfw.user.entity.User;
import com.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HoneAction extends ActionSupport{
	
	@Resource
	private UserService userService;
	@Resource
	private InfoService infoService;
	@Resource
	private ComplainService complainService;
	private Complain comp;
	private Info info;
	
	private Map<String,Object> return_map;

	@Override
	public String execute() throws Exception {
		ActionContext.getContext().getContextMap().put("infoType", Info.INFO_TYPE_MAP);
		QueryHelper infoQueryHelper = new QueryHelper(Info.class, "i");
		infoQueryHelper.addOrder_by("i.createTime", QueryHelper.ORDER_BY_DESC);
		List<Info> infoList = infoService.find(infoQueryHelper, 1, 7).getItems();
		ActionContext.getContext().getContextMap().put("infoList", infoList);
		
		ActionContext.getContext().getContextMap().put("complainState", Complain.COMPLAIN_STATE_MAP);
		QueryHelper compQueryHelper = new QueryHelper(Complain.class, "c");
		User user = (User) ActionContext.getContext().getSession().get(Constant.USER);
		compQueryHelper.addWhere("c.compName=?", user.getUserName());
		//根据投诉时间升序排序
		compQueryHelper.addOrder_by("c.compTime", QueryHelper.ORDER_BY_ASC);
		//根据投诉状态降序排序
		compQueryHelper.addOrder_by("c.state", QueryHelper.ORDER_BY_DESC);
		List<Complain> complainList = complainService.find(compQueryHelper, 1, 8).getItems();
		ActionContext.getContext().getContextMap().put("complainList", complainList);
		return "home";
	}
	
	public String complainAddUI(){
		return "complainAddUI";
	}
	
	public void getUserJson(){
		try {
			String dept = ServletActionContext.getRequest().getParameter("dept");
			if(StringUtils.isNotBlank(dept)){
				QueryHelper queryHelper = new QueryHelper(User.class,"u");
				queryHelper.addWhere("u.dept = ?", dept);
				List<User> userList = userService.find(queryHelper);
				
				JSONObject json = new JSONObject();
				json.put("msg", "success");
				json.accumulate("userList", userList);
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(json.toString().getBytes("utf-8"));
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getUserJson2(){
		try {
			String dept = ServletActionContext.getRequest().getParameter("dept");
			if(StringUtils.isNotBlank(dept)){
				QueryHelper queryHelper = new QueryHelper(User.class,"u");
				queryHelper.addWhere("u.dept = ?", dept);
				
				return_map = new HashMap<String,Object>();
				return_map.put("msg", "success");
				return_map.put("userList", userService.find(queryHelper));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void submit(){
		try {
			if(comp!=null){
				comp.setState(Complain.COMPLAIN_STATE_UNDONE);
				comp.setCompTime(new Timestamp(new Date().getTime()));
				complainService.save(comp);
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write("success".getBytes("utf-8"));
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String infoViewUI(){
		if(info!=null){
			ActionContext.getContext().getContextMap().put("infoType", Info.INFO_TYPE_MAP);
			info = infoService.findById(info.getInfoId());
		}
		return "infoViewUI";
	}
	
	public String complainViewUI(){
		if(comp!=null){
			ActionContext.getContext().getContextMap().put("complainState", Complain.COMPLAIN_STATE_MAP);
			comp = complainService.findById(comp.getCompId());
		}
		return "complainViewUI";
	}
	
	public Map<String, Object> getReturn_map() {
		return return_map;
	}

	public void setReturn_map(Map<String, Object> return_map) {
		this.return_map = return_map;
	}

	public void setComp(Complain comp) {
		this.comp = comp;
	}

	public Complain getComp() {
		return comp;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Info getInfo() {
		return info;
	}
	
}

package com.nsfw.info.action;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.core.action.BaseAction;
import com.core.utils.QueryHelper;
import com.nsfw.info.entity.Info;
import com.nsfw.info.service.InfoService;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class InfoAction extends BaseAction {
	
	@Resource
	InfoService infoService;
	
	List<Info> infoList;
	Info info;
	String[] privileges;

	public String listUI() throws Exception{
		Map<String,String> map = Info.INFO_TYPE_MAP;
		ActionContext.getContext().getContextMap().put("infoTypeMap", map);
		QueryHelper queryHelper = new QueryHelper(Info.class,"i");
		//queryHelper.addWhere("i.state = ?", "1");
		if(info!=null){
			if(StringUtils.isNoneBlank(info.getTitle())){
				info.setTitle(URLDecoder.decode(info.getTitle(),"utf-8"));
				queryHelper.addWhere("i.title like ?", "%"+info.getTitle()+"%");
			}
		}
		
		queryHelper.addOrder_by("i.createTime", QueryHelper.ORDER_BY_DESC);
		pageResult = infoService.find(queryHelper,getPageNo(),getPageSize());
		return "listUI";
	}
	
	public String addUI(){
		Map<String,String> map = Info.INFO_TYPE_MAP;
		info = new Info();
		info.setCreateTime(new Timestamp(new Date().getTime()));
		ActionContext.getContext().getContextMap().put("infoTypeMap", map);
		return "addUI";
	}
	
	public String add(){
		if(info!=null){
			infoService.save(info);
		}
		return "list";
	}
	
	public String editUI(){
		strTitle = info.getTitle();
		info = infoService.findById(info.getInfoId());
		Map<String,String> map = Info.INFO_TYPE_MAP;
		ActionContext.getContext().getContextMap().put("infoTypeMap", map);
		return "editUI";
	}
	
	public String edit(){
		if(info!=null){
			infoService.update(info);
		}
		return "list";
	}
	
	public String delete(){
		strTitle = info.getTitle();
		infoService.delete(info.getInfoId());
		return "list";
	}
	
	public String deleteSelected(){
		if(selectedRow!=null){
			strTitle = info.getTitle();
			for(String id : selectedRow){
				infoService.delete(id);
			}
		}
		return "list";
	}
	
	public void publicInfo(){
		try {
			Info nowInfo = infoService.findById(info.getInfoId());
			nowInfo.setState(info.getState());
			infoService.update(nowInfo);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write("更新状态成功".getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public List<Info> getInfoList() {
		return infoList;
	}

	public void setPrivileges(String[] privileges) {
		this.privileges = privileges;
	}

	public String[] getPrivileges() {
		return privileges;
	}

	
}

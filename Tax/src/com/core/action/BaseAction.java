package com.core.action;

import com.core.page.PageResult;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport{
	protected String[] selectedRow;
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
	
	
	protected PageResult pageResult;
	private int pageNo;
	private int pageSize;
	protected String strTitle;

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		if(pageSize<1)
			pageSize=PageResult.PAGESIZE_NUM;
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public PageResult getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}
	
}

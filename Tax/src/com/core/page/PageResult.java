package com.core.page;

import java.util.ArrayList;
import java.util.List;

public class PageResult {
	//总记录数
	private long totalCount;
	//当前页号
	private int pageNo;
	//总页数
	private int totalPageCount;
	//行数
	private int pageSize;
	//列表记录
	private List items;
	
	public static Integer PAGESIZE_NUM = 5;
	
	public PageResult(long totalCount, int pageNo, int pageSize, List items) {
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.items = (items==null ? new ArrayList() : items);
		if(totalCount!=0){
			int temp = (int) (totalCount/pageSize);
			this.totalPageCount = (totalCount%pageSize==0?temp:temp+1);
			this.pageNo = pageNo;
		}else{
			this.pageNo = 0;
		}
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
}

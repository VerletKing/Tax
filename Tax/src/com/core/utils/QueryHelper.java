package com.core.utils;

import java.util.ArrayList;
import java.util.List;

public class QueryHelper {
	private String fromStr = "";
	private String whereStr = "";
	private String order_byStr= "";
	private List<Object> parameters;
	
	public static String ORDER_BY_ASC = "asc";
	public static String ORDER_BY_DESC ="desc";
	
	public QueryHelper(Class<?> clazz, String aligs){
		fromStr += "FROM "+clazz.getSimpleName() + " "+aligs;
	}
	
	/**
	 * @param condition 查询条件语句 ； 例如：info.title like ?
	 * @param params  查询条件语句中?对应的查询条件值； 例如：%标题%
	 */
	public void addWhere(String condition, Object... params){
		if(whereStr.length()>0){
			whereStr += " AND " + condition;
		}else{
			whereStr = " WHERE " + condition; 
		}
		
		if(parameters == null){
			parameters = new ArrayList<Object>();
		}
		
		if(params!=null){
			for(Object param : params){
				parameters.add(param);
			}
		}
	}
	
	public void addOrder_by(String property, String order){
		if(order_byStr.length()>0){
			order_byStr += " , " + property;
		}else{
			order_byStr = " ORDER BY " + property + " " + order;
		}
	}
	
	public String getQueryListHql(){
		return fromStr+whereStr+order_byStr;
	}
	
	public String getQueryCountHql(){
		return "SELECT COUNT(*)" +fromStr+whereStr;
	}
	
	public List<Object> getParameters(){
		return parameters;
	}
}

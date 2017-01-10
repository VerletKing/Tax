package com.nsfw.complain.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;

import com.core.dao.impl.BaseDaoImpl;
import com.nsfw.complain.dao.ComplainDao;
import com.nsfw.complain.entity.Complain;

public class ComplainDaoImpl extends BaseDaoImpl<Complain> implements ComplainDao {

	@Override
	public List<Object[]> getAnnualStatisticDataByYear(int year) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ");
		sb.append("    imonth, ");
		sb.append("    c2 ");
		sb.append("FROM ");
		sb.append("    tmonth ");
		sb.append("LEFT JOIN ( ");
		sb.append("    SELECT ");
		sb.append("        MONTH (comp_time) c1, ");
		sb.append("        count(comp_id) c2 ");
		sb.append("    FROM ");
		sb.append("        complain ");
		sb.append("    WHERE ");
		sb.append("        YEAR (comp_time) = ? ");
		sb.append("    GROUP BY ");
		sb.append("        MONTH (comp_time) ");
		sb.append(")t ON imonth = c1 ");
		sb.append("ORDER BY ");
		sb.append("    imonth; ");
		SQLQuery query = getSession().createSQLQuery(sb.toString());
		query.setInteger(0, year);
		return query.list();
	}

}

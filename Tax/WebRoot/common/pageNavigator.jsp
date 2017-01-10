<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="pageResult.totalCount>0">
	<div class="c_pate" style="margin-top: 5px;">
		<table width="100%" class="pageDown" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td align="right">总共<s:property value="pageResult.totalCount" />条记录，
					当前第<s:property value="pageResult.pageNo" /> 页， 共<s:property
						value="pageResult.totalPageCount" /> 页 &nbsp;&nbsp; <s:if
						test="pageResult.pageNo > 1">
						<a
							href="JavaScript:doGoPage(<s:property value='pageResult.pageNo-1'/>,<s:property value="pageResult.totalPageCount"/> )">上一页</a>&nbsp;&nbsp;
								</s:if> <s:if test="pageResult.pageNo < pageResult.totalPageCount">
						<a
							href="JavaScript:doGoPage(<s:property value='pageResult.pageNo+1'/>,<s:property value="pageResult.totalPageCount"/> )">下一页</a> 到&nbsp;
								</s:if> <input id="pageNo" type="text" style="width: 30px;"
					name="pageNo"
					onkeypress="if(event.keyCode == 13){doGoPage(this.value,<s:property value="pageResult.totalPageCount"/>);}"
					min="1" max="" value="<s:property value='pageResult.pageNo'/>" />
					&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</div>
</s:if>
<s:else>
	没有你要查找数据。
</s:else>

<script type="text/javascript">
					function doGoPage(pageNo,totalPageCount){
						if(pageNo>totalPageCount)
							pageNo = totalPageCount;
						document.getElementById("pageNo").value = pageNo;
						document.forms[0].action = list_ui;
						document.forms[0].submit();
					}
				</script>
</div>
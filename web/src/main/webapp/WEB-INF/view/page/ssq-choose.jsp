<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>双色球精选</title>
<%
    String contextPath = request.getContextPath();
%>
<script type="text/javascript" src="<%=contextPath%>/resources/util/com-submit.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources/foundation/js/ssq/ssq-choose.js"></script>	
</head>
<body>
<label for="ssqform"><Strong>双色球精选</Strong></label>
   <table class="table table-bordered" id ='ssqform'>
   <tr class="active">
      <td >
  <div class="row">
  <div class="col-lg-6">
    <div class="input-group">
      <span class="input-group-addon" >红：
      </span>
      <input type="text" class="form-control" placeholder="input for red" id="red">
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-4">
    <div class="input-group">
     <span class="input-group-addon">蓝：
      </span>
      <input type="text" class="form-control" placeholder="input for blue" id="blue">
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
</div><!-- /.row -->
      </td>
     </tr>
   <tr class="active"> <td>
   <div class="row">
  <div class="col-lg-2">
    <div class="input-group">
      <span class="input-group-addon" >注数：
      </span>
      <input type="text" class="form-control" placeholder="number" id="num">
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-3">
    <div class="input-group">
     <span class="input-group-addon">和值范围：
      </span>
      <input type="text" class="form-control" placeholder=":xx-xx" id="sum">
    </div>
</div>
 <div class="col-lg-3">
    <div class="input-group">
     <span class="input-group-addon">期号：
      </span>
      <input type="text" class="form-control" placeholder="NumberSort" id="sort">
    </div>
</div>
<div class="col-lg-4">
    <button type="button" class=" btn btn-primary" onclick="findNumsOfChoose()"> 查询</button>
     &nbsp;&nbsp;&nbsp;&nbsp;
       <button type="button" class=" btn btn-primary" onclick="getNumOfChoose()"> 确定</button>
   &nbsp;&nbsp;&nbsp;&nbsp;
    <button type="button" class=" btn btn-primary" onclick="deleteNumsOfChoose()"> 删除</button>
</div>
</div>
</td></tr>
<tr  height="720px">
    <td>
      <table class="table"  id ="table-data">
    <tr style="border:0px solid #ff0000;">
      <th># &nbsp;&nbsp;<input type='checkbox' name='check'  id="check" onclick="selectAll();"/></th>
     <th>Red</th>
     <th>Blue</th>
     <th>SUM</th>
     <th>SourceType</th>
    </tr>
  </table>
    </td>
</tr>
  </table>
</body>
<script type="text/javascript">
//复选框事件
//全选、取消全选的事件
function selectAll(){
	var isChecked =$("#check").get(0).checked;
	var elements_all=document.getElementsByName('chk_list');      
    for(i=0;i<elements_all.length;i++) {      
        var element=elements_all[i];      
        if(element.type=="checkbox")  {      
            element.checked=isChecked;      
        }      
    }  
	/* if ($("#check").get(0).checked) {
		$(":checkbox").attr("checked", true);
	} else {
		$(":checkbox").attr("checked", false);
	} */
}
//子复选框的事件
function setSelectAll($this){
	//当没有选中某个子复选框时，SelectAll取消选中
	if (!$(this).prop('checked')) {
		$("#check").get(0).checked= false;
	}
	var chsub = $("input[type='checkbox'][name=chk_list]").length; //获取subcheck的个数
	var checkedsub = $("input[type='checkbox'][name=chk_list]:checked").length; //获取选中的subcheck的个数
	if (checkedsub == chsub) {
		$("#check").get(0).checked= true;
	}
}
</script>
      
</body>
</html>
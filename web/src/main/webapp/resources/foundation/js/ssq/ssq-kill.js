
var type="kill";

function getNum(){
	$("#check").get(0).checked=false;
	var red =$("#red").val();
	var blue =$("#blue").val();
	var num =$("#num").val();
	var sum =$("#sum").val();
	var sort=$("#sort").val();
	
	var params={
			red:red,
			blue:blue,
			num:num,
			sum:sum,
			sort:sort
	};
	var url=contextPath+'/foundation/ssq/kill/getNums';
	myAjax("post", url, params,callSSQ,"json");
}
function callSSQ(data){
	var item ;
	var res =data['data'];
	var tab_rows =$('#table-data').find('tr').length;
	for(var i =1 ;i<tab_rows;i++){
		 $("tr[id='"+i +"']").remove();
	}
	$.each(res,function(i,result){ 
		if(result!=null){
			item = "<tr id ="+(i+1)+"><td> <input type='checkbox' name='chk_list' value="+result['id']+" onclick='setSelectAll()'/>"+
			(i+1)+"</td><td>"+result['red']+"</td><td>"+result['blue']+"</td><td>"+result['redSum']+"</td> <td>"+result['dataSourceType']+"</td></tr>"; 
			if($("tr[id='"+(i+1)+"']")!=null){
				$("tr[id='"+(i+1)+"']").remove();
			}
			
			$('#table-data').append(item); 
		}else{
			
		}

	}); 
	if(res.length==0){
		$('#table-data').append("<tr> id='1'<td>未查询到数据。</td></tr>"); 
	}
}


function findNums(){
	$("#check").get(0).checked=false;
	var sort=$("#sort").val();
	if(sort==''||sort==null){
		alert("期号未填写！");
		return;
	}
	var params={sort:sort,type:type};
	var url=contextPath+'/foundation/ssq/kill/findNums';
	myAjax("get", url, params,callSSQ,"json");
	
}

function deleteNums(){
	$("#check").get(0).checked=false;
	var ids=[];
	var checkedsub = $("input[type='checkbox'][name=chk_list]:checked");
	for(var i=0;i<checkedsub.length;i++){
		ids.push(checkedsub[i].value);
	}
	if(ids.length==0){
		alert("请选择要删除的记录");
	   return;
	}
	var params={ids:ids.join()};
	var url=contextPath+'/foundation/ssq/kill/deleteNums';
	myAjax("post", url, params,callSSQ,"json");
	
}
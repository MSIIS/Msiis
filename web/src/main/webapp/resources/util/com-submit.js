/*$.post("Ajax.aspx", { Action: "post", Name: "lulu" },
		function (data, textStatus){
			// data 可以是 xmlDoc, jsonObj, html, text, 等等.
			//this; // 这个Ajax请求的选项配置信息，请参考jQuery.get()说到的this
			alert(data.result);
		}, "json");*/

function myAjax(type,url,params,callback,dataType){
	$.ajax({
		  type: type,
		  cache:false,
		  url: url,
		  data: params,
		  success: callback,
		  error: function(XMLHttpRequest, error, errorThrown){
			  alert(errorThrown);
			  },
		  dataType: dataType
	});
}


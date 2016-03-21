<%@ page language="java" contentType="text/html;" pageEncoding="utf-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
    
    <title>添加学生信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>
<body>
	<center>
		<div id="crossband">
  			<div></div>
  			<ul id="ul">
  				<li>
  					<a href="Student/getAll.asp">
  						<span style="background-image: url('images/base/add_new.gif')"></span>
  							返回
  					</a>
  				</li>
  			</ul>
  	   </div>

<div id="nav">
  	   		<span id="navSpan">
  	   			信息管理<span> > </span><span><a href="<%= basePath %>">新增学生信息</a></span>
  	   		</span>
  	   </div>
		<form id = "formStu" action = "<%=basePath %>Student/modify.asp" method = "post">
			<table>			
				<tr>
					<td><label class = "infoLabel">姓名</label></td>
					<td>
						<input type = "text" name = "name" value = "${student.name}" class = "un_null" />
						<input type = "hidden" id = "id" name = "id" value = "${student.id}"/>
					</td>
				</tr>
				<tr>
					<td><label class = "infoLabel">课程</label></td>
					<td><input type = "text" name = "course" value = "${student.course}" class = "un_null" /></td>
				</tr>
				<tr>
					<td><label class = "infoLabel">成绩</label></td>
					<td><input type = "text" name = "score" value = "${student.score}" class = "un_null" /></td>
				</tr>
				<tr>
					<td></td>
					<td>
					<input type = "button"  value = "重置"  onclick = "reset()"/>&nbsp;&nbsp;
					<input type = "button"  value = "提交" onclick = "commit()" />
					</td>
				</tr>
			</table>
		
		</form>
	</center>
	
	<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.5.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/zDialog/zDialog.js"></script>
	
	<script type="text/javascript">
	
	function commit()
	{
		check_allData();
			if($(".un_null_alert").size()>0){
			Dialog.alert("请认真填写信息");
		}
		else{
			var id = $("#id").val();
			if(null == id || "" == id)
				{										
					$("#id").remove();
				}			
			$("#formStu").submit();
		}
	}
	
	function check_allData(){
		var length1 = $(".un_null").size();
		
		for(var i=0;i<length1;i++)
		{
			$(".un_null")[i].focus();
		}
		$(".un_null")[length1-1].blur();
	}
	
	function reset(){
		$(".un_null").attr("value","");
	}
	
	//校验
	   $(function(){
		 	$(".un_null").focusout(function(){
					if($(this).val()==null||$(this).val()==""){
						if($(this).next().attr("class")!="un_null_alert"){
							$(this).after("<label class='un_null_alert'>该属性不能为空！</label>");
							$(this).next().css("font-size","12px").css("color","red");				
						}
					}else{
						if($(this).next().attr("class")=="un_null_alert")
						$(this).next().remove();			
					}		
				});
			$(".only_num").focusout(function(){
				var reg= /^\d+(\.{1}\d+)?$/;
				if($(this).val()==null||$(this).val()==""||reg.test($(this).val())==false){
					if($(this).next().attr("class")!="only_num_alert"){		
						$(this).after("<label class='only_num_alert'>该属性只能填写数字！</label>");
						$(this).next().css("font-size","12px").css("color","red");		
					}			
				}else{			
					if($(this).next().attr("class")=="only_num_alert")
					$(this).next().remove();
				}
			});
		 
		 $(".tel_number").focusout(function(){
				var reg= /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
				if($(this).val()==null||$(this).val()==""||reg.test($(this).val())==false){
					if($(this).next().attr("class")!="tel_number_alert"){		
						$(this).after("<label class='tel_number_alert'>填写正确的电话号码！</label>");
						$(this).next().css("font-size","12px").css("color","red");		
					}				
				}else{
					if($(this).next().attr("class")=="tel_number_alert")
					$(this).next().remove();			
				}		
			});
		 });
	
	
	
	</script>
</body>
</html>
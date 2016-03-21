<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import = "com.travelsky.entity.Student" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		.info{		    
			width : 150px;	
			height : 50px;
		}		
	</style>
		<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.5.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/zDialog/zDialog.js"></script>
	<script type="text/javascript">
		function comfirm(url,id)
		{
			Dialog.confirm('警告：删除后数据将不能恢复！您确认要删除吗？',
				function(){
				post(url,id);
			});	
		}
		function post(url,id,name,course,score) {        
	 	    var temp = document.createElement("form");        
	 	    temp.action = url;        
	 	    temp.method = "post";        
	 	    temp.style.display = "none"; 
	 	   var opt = document.createElement("textarea");        
	        opt.name = "id";        
	        opt.value = id;          
	        temp.appendChild(opt);
	        opt = document.createElement("textarea");        
	        opt.name = "name";        
	        opt.value = name;          
	        temp.appendChild(opt);
	        opt = document.createElement("textarea");        
	        opt.name = "course";        
	        opt.value = course;          
	        temp.appendChild(opt);
	        opt = document.createElement("textarea");        
	        opt.name = "score";        
	        opt.value = score;          
	        temp.appendChild(opt);
	 	    document.body.appendChild(temp);        
	 	    temp.submit();                
	 	}   
		
		function findByParams()
		{
			var id = $("#id").val();
			var name = $("#name").val();
			var course = $("#course").val();
			var score = $("#score").val();
			if (null == id || "" == id)
				{
					id = 0;
				}
			var url = "<%=basePath%>"+ "Student/findByParams.asp";
			post(url,id,name,course,score);
		}
		
		
		
	</script>

  </head>
  
  
  
  <body>
  <%
			String message=(String)request.getAttribute("message");
			if( message != null && !"".equals(message)){
				%>
				<script language="javascript">
				Dialog.alert("<%= message%>");
				</script>
				<%
			}
		%>	
		<div id="crossband">
  			<div></div>
  			<ul id="ul">
  				<li>
  					<a href="Student/goModify.asp">
  						<span style="background-image: url('../images/base/add_new.gif')"></span>
  							新增
  					</a>
  				</li>
  				<li>
  					<a href="Student/getAll.asp">
  						<span style="background-image: url('images/base/add_new.gif')"></span>
  							刷新
  					</a>
  				</li>
  			</ul>
  	   </div>
  	   
  	   <div id="nav">
  	   		<span id="navSpan" >
  	   			信息管理<span> > </span><span><a href="<%= basePath %>Student/getAll.asp">学生信息</a></span>
  	   		</span>
  	   	
  	   </div> 	   		  
		      <table border='0' cellpadding='0' cellspacing='0'>
		        <tr>
		          <td width='90' align='center'>搜索条件：</td>		         
		        <td width='50'>
		          学号：
		        </td>
		        <td >
		          	<input type='text'  id='id'   style='width:100px' />
		        </td>
		        <td width='50'>
		          姓名：
		        </td>
		        <td >
		          	<input type='text'  id='name'  style='width:100px' />
		        </td>
		        <td width='50'>
		          课程：
		        </td>
		        <td >
		          	<input type='text'  id='course'  style='width:100px' />
		        </td>
		        <td width='50'>
		          成绩：
		        </td>
		        <td >
		          	<input type='text'  id='score'  style='width:100px' />
		        </td>	
		        
		        	         
		        
		        <td>&nbsp;
		        <span >
		        <input type="button" onclick="findByParams()" value="搜索"  style = "cursor:hand;width:70px;height:22px"/>&nbsp;
		        </span>
		          
		        </td>		       
		       </tr>
		      </table>		    
  	<table >		
  		<tr style= "height:10px"></tr>
  		<tr  align = "center" height = "22px" onMouseMove = "javascript:this.bgColor='#E8EFF5'" onMouseOut = 
  		"javascript:this.bgColor='#FCFCFC'">
  			<td>学生学号</td>
  			<td>学生名字</td>
  			<td>学生课程</td>
  			<td>学生成绩</td>
  			<td>操作</td>
  		</tr>
 		<% 		
			List<Student> students=(List <Student>)request.getAttribute("students");
			if( students != null && !"".equals(students)){
				for(Student stu : students)
				{
				%>
					<tr align = "center" onMouseMove = "javascript:this.bgColor='#E8EFF5'" onMouseOut = 
  		"javascript:this.bgColor='#FCFCFC'">
  						<td class="info" ><%= stu.getId() %></td>
  						<td class="info"><%= stu.getName() %></td>
  						<td class="info"><%= stu.getCourse() %></td>
  						<td class="info"><%= stu.getScore() %></td>
  						<td class="info"> <a href = "<%=basePath%>Student/goModify.asp?id=<%=stu.getId()%>">修改</a>  | 
  								 <a href = "javascript:void(0)" onclick = "comfirm('<%=basePath%>Student/delete.asp',<%= stu.getId() %>)">删除</a>
  						 </td>
  					</tr>			
				<% 
				}
			}		
			%> 
 </table>  
  </body>
</html>

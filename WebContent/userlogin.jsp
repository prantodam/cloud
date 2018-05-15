<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
           "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>SDS</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="stylegreen.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

function valid()
{
var a=document.s.un.value;
if(a=="")
{
alert("Plz Enter the User Name!!!");
document.s.un.focus();
return false;
}
var b=document.s.pass.value;
if(b=="")
{
alert("Plz Enter the Password!!!");
document.s.pass.focus();
return false;
}
}

</script>
</head>
<body>
<div id="outerwrapper">

<div id="head">
    
    <div id="logo">
      <div style="font-size:35px;color:white;font-family-verdana;padding:30px 40px 30px 20px;line-height:1.2em;font-weight:bold;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Efficient data storage in cloud computing using 
	   <div align="center"> Algebraic signature based auditing and ROTP</div> 
	  </div>
   </div><!-- end of logo -->
   
   
   
   <div id="navigation">
      <div id="navbar_link">
      <ul>
           <li ><a href="index.html">HOME</a></li>
           <li class="currentpage"><a href="userlogin.jsp">SIGNIN</a></li>
            <li ><a href="register.jsp">SIGNUP</a></li>
            
        </ul>
      </div><!-- end of navbar_link-->
      </div><!-- end of navigation-->
      
  </div><!-- end of header-->
  
  
<div id="innerwrapper">
<table align="center" width="1020">
	<tr>
		<td width="364" valign="top">
		<table align="center" width="336">
			<tr>
				<td width="328" height="268" colspan="2"><img
					src="img/login3.jpg" width="300" /></td>
			</tr>
		</table>
		</td>
		<td width="644" valign="top">
		<form action="Login" name="s" method="post"
			onsubmit="return valid()">
		
		<table align="center" style="width: 581px; height: 198px">
			<div align="center" class="paragraping1"><font color="#006600"
				size="3"> Login Here</font></div>
			<tr>
				<td><img src="img/login1.png" /></td>
				<td><font face="Courier New" size="+1"><strong>User
				Name</strong></font></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="un"
					class="b" /></td>
			</tr>
			<tr>
				<td><img src="img/login.png" /></td>
				<td><font face="Courier New" size="+1"><strong>Password</strong></font></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp; <input type="password" name="pass"
					class="b" /></td>
			</tr>
			
			
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" name="sub" value="" class="submit" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="reset" name="clear" value="" class="clear" /></td>
			</tr>
			
		</table>
		<table><tr> 
                <td colspan="2" align="center"><font size="2">
                <%
					String error=(String)request.getAttribute("error");
					if(error !=null){%>
					<font color="red">Invalid User. </font>	
						&nbsp;<%}
				%>
                  </font></td>
        </tr></table>
	</form>
	</td>
	</tr>
</table>

</div>
<div id="footer">
<p align="center"><span style="float:center;">&nbsp;&nbsp;&nbsp;</span>
</p>
</div>
</div>
</body>
</html>
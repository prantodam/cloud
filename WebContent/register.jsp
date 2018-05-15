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
var aa=document.s.un.value;
if(aa=="")
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
var k = document.s.mob.value;
if(k=="")
{
alert("Enter the Mobile Number!!!");
document.s.mob.focus();
return false;
}
if(isNaN(k))
{
alert("Plz Enter the Mobile Number in Numbers!!!");
document.s.mob.focus();
return false;
}
if (!(k.charAt(0)=="9" || k.charAt(0)=="8" || k.charAt(0)=="7"))
{
alert("Mobile No. should start with 9 ,8 or 7 ");
document.s.mob.focus();
return false;
}
if(k.length!=10)
{
alert("Plz Enter 10 digits Number");
document.s.mob.focus();
return false;
}

var emailfilter=/^\w+[\+\.\w-]*@([\w-]+\.)*\w+[\w-]*\.([a-z]{2,4}|\d+)$/i;
var m=emailfilter.test(document.s.eid.value);
if(m==false)
{
alert("Plz Enter a Valid Email Id!!!");
document.s.eid.focus();
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
	   <div align="center">Algebraic signature based auditing and ROTP</div> 
	  </div>
   </div><!-- end of logo -->
   
   
   <div id="navigation">
      <div id="navbar_link">
      <ul>
           <li ><a href="index.html">Home</a></li>
           <li ><a href="userlogin.jsp">SIGN IN</a></li>
            <li class="currentpage"><a href="register.jsp">SIGN UP</a></li>
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
					src="img/signUp (2).jpg" width="300" /></td>
			</tr>
		</table>
		</td>
		<td width="644" valign="top">
		<form action="Register" name="s" method="post"
			onsubmit="return valid()">
		
		<table align="center" style="width: 602px; height: 237px">
			<div align="center" class="paragraping1"><font color="#006600"
				size="3">Registration Here</font></div>
			<tr>
				
				<td><font face="Courier New" size="+1"><strong>User
				Name</strong></font></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="un"
					class="b" /></td>
			</tr>
			<tr>
				
				<td><font face="Courier New" size="+1"><strong>Password</strong></font></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp; <input type="password" name="pass"
					class="b" /></td>
			</tr>
			<tr>
			
			<td><font face="Courier New" size="+1"><strong>Type of User</strong></font></td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;
			
			<select name="typeofusers" id="typeofusers" class="b">
			  <option value="0">Type of Users</option>
			  <option value="1">Data Owner</option>
			  <option value="2">Data Consumer</option>
			<!--   <option value="3">Server</option> -->
			</select>
			
			</td>
			</tr>
			
			<tr>
			<td><font face="Courier New" size="+1"><strong>Mobile</strong></font></td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="mob" class="b" maxlength = "10"/></td>
			</tr>
			<tr>
				<td><font face="Courier New" size="+1"><strong>Email
				ID</strong></font></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="eid" class="b" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="sub" value="" class="submit" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="reset" name="clear" value="" class="clear" /></td>
			</tr>
			
		</table>
	
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
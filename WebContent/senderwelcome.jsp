<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
           "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>SDS</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="stylegreen.css" rel="stylesheet" type="text/css" />

</head>
<body>
<script type="text/javascript">
function upload(aform)
{
	 var choose_file = aform.file.value;
     var ext = choose_file.split(".").pop();

	    if( !choose_file)
	    {
	        alert("Please Choose a file !");
	        return false;
	        
	    }
}

</script>


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
	<li><a href="index.html">HOME</a></li>
	<li class="currentpage"><a href="senderwelcome.jsp">UPLOAD</a></li>
	<li><a href="userlogin.jsp">SIGNOUT</a></li>
</ul>
</div>
<!-- end of navbar_link--></div>
<!-- end of navigation--></div>
<!-- end of header-->


<div id="innerwrapper">
<table align="center" width="1020">
	<tr>
		<td width="364" valign="top">
		<table align="center" width="336">
			<tr>
				<td width="328" height="268" colspan="2"><img
					src="img/upload12.png" width="300" /></td>
			</tr>
		</table>
		</td>


		<td width="644" valign="top">
		<form onsubmit="return upload(this)" name="aform"
			enctype="multipart/form-data" method="post" action="Upload">
		<table align="center" style="width: 581px; height: 198px">
			<div align="center" class="paragraping1"><font color="#006600"
				size="3">File Upload Here</font></div>
			
			<tr>
			<td></td>
				<td>Choose the file :</td>
				<td><input name="file" type="file" /></td>
			</tr>
			
			<tr>
			<td></td>
				<td></td>
				<td colspan="2"><input type="submit" value="Upload"
					style="width: 161px;" /></td>
			</tr>
		</table>
	
		</form>
		</td>
	</tr>
</table>

</div>
<div id="footer">
<p align="center"><span style="float: center;">&nbsp;&nbsp;&nbsp;</span>
</p>
</div>
</div>

</body>
</html>
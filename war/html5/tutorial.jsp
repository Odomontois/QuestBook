<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>First HTML5 tutorial</title>
</head>
<body>
	<canvas id="tutorial" width="500" height="500"></canvas>
<script type="text/javascript">
	//<!CDATA[
	var canvas = document.getElementById("tutorial");

	var ctx = canvas.getContext("2d");
	ctx.rotate(0.3);
	ctx.fillStyle = "rgba(200,0,0,0.5)";
	ctx.fillRect (50, 150, 150, 150);

	ctx.fillStyle = "rgba(0, 0, 200, 0.5)";
	ctx.fillRect (125, 225, 150, 150);


	alert("hello");
	//]!>
</script>
</body>

</html>
<%@page import="ru.nol.qbook.model.ChatSubscription"%>
<%@page import="ru.nol.qbook.model.ChatRegistry"%>
<%@page import="ru.nol.qbook.persistence.PMF"%>
<%@page import="javax.jdo.PersistenceManager"%>
<%@page import="ru.nol.qbook.persistence.Chat"%>
<%@page import="ru.nol.qbook.greet.Greeter"%>
<%@page import="ru.nol.qbook.greet.GentlemanGreeter"%>
<%@page import="ru.nol.qbook.greet.ClojureGreeter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	Greeter greeter = new ClojureGreeter();

// 	ChatSubscription subscription = ChatRegistry.getInstance()
// 			.getChat("Uuu").addSubscription();
// 	subscription.setJID("odo@olo");
// 	ChatRegistry.getInstance().save();

%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=greeter.getGreeting()%></title>

</head>
<body>
	<b><%=greeter.getProposal()%></b>
	<script type="text/javascript">
		service = XMLHttpRequest();
		service.open("POST","/serv/good",true);
		service.onreadystatechange = function() {
			  if (this.readyState == 4) {
			     if(this.status == 200) {
			       obj = JSON.parse(this.responseText);
			       alert(obj.value);
			     }
			  }
			};
		service.send(JSON.stringify({"value" : 2}));
	</script>
</body>
</html>
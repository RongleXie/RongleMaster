<%@
        page contentType="text/html;charset=UTF-8" language="java"
%>
<%@
        page import="org.apache.axis2.Constants,
                     org.apache.axis2.description.AxisOperation,
                     org.apache.axis2.description.AxisService,
                     java.util.Collection,
                     java.util.HashMap,
                     java.util.Iterator"
%>
<html>
<head><title>List Services</title>
    <style>
        h2 {
            margin: 20 0 5 0;
        }

        ul {
            margin-top: 5;
        }
    </style>
</head>
<body>
<h1>Available services</h1>
<%
    HashMap serviceMap = (HashMap) request.getSession().getAttribute(Constants.SERVICE_MAP);
    Collection servicecol = serviceMap.values();
    if (servicecol.size() == 0) {%>Available services is Empty.<%
    }
    for (Iterator iterator = servicecol.iterator(); iterator.hasNext(); ) {
        AxisService axisService = (AxisService) iterator.next();
        Iterator opItr = axisService.getOperations();
        String serviceName = axisService.getName();

%>

<h2><font color="blue"><a href="<%=serviceName %>?wsdl" target="_blank"><%=serviceName%>
</a></font></h2>
<i>Available Operations</i>
<ul>
    <%
        while (opItr.hasNext()) {
            AxisOperation axisOperation = (AxisOperation) opItr.next();
    %>
    <li><%=axisOperation.getName().getLocalPart()%>
    </li>
    <%
        }
    %>
</ul>

<%
    }
%>
</body>
</html> 

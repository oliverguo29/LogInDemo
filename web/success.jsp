<%@ page import="com.ao.domain.User" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ao</title>
</head>
<body>
    <%User user = (User) request.getSession().getAttribute("User"); %>
    <h1><%=user.getUsername() %>,welcome</h1>

    <%
        //show time
        boolean flag =false;
        Cookie[] cookies = request.getCookies();

        if(cookies!=null&&cookies.length!=0) {
            for (Cookie c : cookies) {
                if (c.getName().equals("LastTime")) {
                    //响应
                    flag = true;
                    String decode_value = URLDecoder.decode(c.getValue(), "utf-8");

                    out.print("<h1>welcome back, your last log in time is: " + decode_value + "</h1>");

                    //重新设置
                    Date dd = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd hh mm ss");//有空格
                    String date_value = sdf.format(dd);
                    String encode_value = URLEncoder.encode(date_value, "utf-8");
                    c.setValue(encode_value);
                    //发送
                    int month = 60 * 60 * 24 * 30;
                    c.setMaxAge(month);
                    response.addCookie(c);
                    break;
                }
            }
        }
        if (cookies == null || cookies.length == 0 || flag == false) {

            Date dd = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd hh mm ss");//有空格
            String date_value = sdf.format(dd);
            String encode_value = URLEncoder.encode(date_value, "utf-8");
            //创建于设置cookie
            int month = 60 * 60 * 24 * 30;
            Cookie c_first = new Cookie("LastTime", encode_value);
            c_first.setMaxAge(month);
            //发送
            response.addCookie(c_first);


            out.print("welcome first time");

        }
    %>

</body>
</html>

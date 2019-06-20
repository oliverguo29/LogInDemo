package com.ao.web;

import com.ao.dao.UserDao;
import com.ao.domain.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/Servlet_LogIn")
public class Servlet_LogIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.encoding
        request.setCharacterEncoding("UTF-8");
        //2.get username and password from user
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkcode = request.getParameter("checkCode");
        //3. info correct
            //3.1 checkcode correct?
        HttpSession session = request.getSession();
        String checkCode_session = (String)session.getAttribute("checkCode_session");
        session.removeAttribute("checkCode_session");//destory

        if(checkCode_session.equalsIgnoreCase(checkcode)){
            //checkcode correct,user info correct?
            //3.2encapsulate those info as a user object
            User login_user = new User(username,password);
            //4.call userDao
            UserDao dao = new UserDao();
            User user = dao.login(login_user);

            //5.if success put data into Servlet_Success else Servlet_Fail and also storage success user
            if(user!=null){ //success
                session.setAttribute("User",user);
                //redirect
                response.sendRedirect(request.getContextPath()+"/success.jsp");

            }else{  //fail
                request.setAttribute("login_error","wrong username or password ");
                String path=request.getContextPath();
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }else{
                //checkcode wrong;
            request.setAttribute("cc_error","checkcode wrong");
            //String path=request.getContextPath();
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }



         /* //2.use BeanUtils to get all parameters;
        Map<String, String[]> parameterMap = request.getParameterMap();

       //3.create a null login_user object;
        //从parameterMap中读取数据，挨个存入login_user对象中，方便，不需要手动处理参数
        User login_user = new User();

        //3.1 put into BeanUtils
        try {
           BeanUtils.populate(login_user,parameterMap);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
*/




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}

package com.ao.web;

import com.ao.dao.UserDao;
import com.ao.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/Servlet_LogIn")
public class Servlet_LogIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.encoding
        request.setCharacterEncoding("UTF-8");
        //2.get username and password from user
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");

        //3 info correct
        //3.1 checkCode correct?
        HttpSession session = request.getSession();
        String check_code_session = (String)session.getAttribute("checkCode_session");
        session.removeAttribute("checkCode_session");
        if(check_code_session!=null&&check_code_session.equalsIgnoreCase(checkCode)){
            //3.2 encapsulate those info as a user object
            User login_user = new User(username,password);
            //3.3call userDao
            UserDao dao = new UserDao();
            User user = dao.login(login_user);

            //3.4if success put data into Servlet_Success else Servlet_Fail and also storage success user
            if(user!=null){ //success
                session.setAttribute("User",user);
                //redirect
                response.sendRedirect(request.getContextPath()+"/success.jsp");
            }else{  // user info wrong
                request.setAttribute("login_error","wrong username or password");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }

        }else{
            //check code wrong
            request.setAttribute("cc_error","wrong checkcode");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}

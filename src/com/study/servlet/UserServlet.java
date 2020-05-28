package com.study.servlet;

import com.study.dao.impl.UserDAOImpl;
import com.study.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lxy
 * @date 2020-05-10-11:54
 * @function
 **/
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String submitType = req.getParameter("submitType");

        UserDAOImpl userDAO = new UserDAOImpl();

        if (username==null || password==null || submitType==null){
            out.println("你没有权限直接访问该url...");
            resp.setHeader("refresh","3;url=index.jsp");
        }



        if ("注册".equals(submitType)){
            //先判断该用户名是否被注册
            if (userDAO.queryByUsername(username)==null){
                //未被注册，允许此次被注册
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                userDAO.add(user);
                out.println("恭喜你...注册成功！");
                resp.setHeader("refresh","3;url=index.jsp");
            }else {
                //若已经被注册，则返回错误信息给用户
                out.println("对不起,"+username+"已被注册...请你换一个用户名重新注册");
                resp.setHeader("refresh","3;url=index .jsp");
            }

        }else if("登录".equals(submitType)){
            //先判断该用户名是否存在
            if (userDAO.queryByUsername(username)!=null){
                //用户存在,再判断两次输入的密码是否一致
                if (!password.equals(confirmPassword)){
                    out.println("两次输入的密码不一致，请重新输入！！");
                    resp.setHeader("refresh","3;url=login.jsp");
                }else {
                    //两次密码一致，再判断账户密码是否正确
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    boolean login = userDAO.login(user);
                    if (login){
                        out.println("恭喜你...登录成功！");
                        req.getSession().setAttribute("login","successful");
                        req.getSession().setAttribute("username",username);
                        resp.setHeader("refresh","3;url=index.jsp");
                    }else {
                        out.println("用户名或密码有误...请重新输入");
                        resp.setHeader("refresh","3;url=index.jsp");
                    }
                }

            }else {
                out.println("该用户名不存在....请您注册");
                resp.setHeader("refresh","3;url=index.jsp");
            }

        }



    }
}

package com.study.servlet;

import com.study.dao.IShoppingTrolleyDAO;
import com.study.dao.impl.ShoppingTrolleyDAOImpl;
import com.study.entity.ShoppingTrolley;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author lxy
 * @date 2020-05-26-8:03
 * @function
 **/
public class CheckShoppingTrolleyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String username = (String) req.getSession().getAttribute("username");
        if (username!=null){
            IShoppingTrolleyDAO shoppingTrolleyDAO = new ShoppingTrolleyDAOImpl();

            String SItemId = req.getParameter("itemId");
            String submitType = req.getParameter("submitType");
            if (SItemId!=null){
                if ("delete".equals(submitType)){
                    Integer delItemId = Integer.parseInt(SItemId);
                    if (delItemId!=null){//删除购物车里的指定商品
                        shoppingTrolleyDAO.delete(delItemId,username);
                    }
                }


            }


            List<ShoppingTrolley> shoppingTrolleyList = shoppingTrolleyDAO.queryByUsername(username);
            req.getSession().setAttribute("shoppingTrolleyList",shoppingTrolleyList);

                req.getRequestDispatcher("shoppingTrolley.jsp").forward(req, resp);
        }else {
            out.println("请先登录账户...");
            resp.setHeader("refresh","3;url=login.jsp");
        }

    }
}

package com.study.servlet;

import com.study.dao.IShoppingTrolleyDAO;
import com.study.dao.impl.ItemDAOImpl;
import com.study.dao.impl.ShoppingTrolleyDAOImpl;
import com.study.entity.ShoppingTrolley;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lxy
 * @date 2020-05-26-8:03
 * @function
 **/
public class CUDShoppingTrolleyServlet extends HttpServlet {
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
            String Sitemid = req.getParameter("itemId");
            if (Sitemid!=null){
                Integer itemId = Integer.parseInt(Sitemid);
                ItemDAOImpl itemDAO = new ItemDAOImpl();
                IShoppingTrolleyDAO shoppingTrolleyDAO = new ShoppingTrolleyDAOImpl();

                String submitType = req.getParameter("submitType");
                ShoppingTrolley oldShoppingTrolley = shoppingTrolleyDAO.queryByItemId(itemId,username);
                if ("add".equals(submitType)){ //通过add一个一个添加商品数量至购物车
                    if (oldShoppingTrolley==null){
                        ShoppingTrolley newShoppingTrolley = new ShoppingTrolley();
                        newShoppingTrolley.setItem_id(itemId);
                        newShoppingTrolley.setItem_name(itemDAO.queryByItemId(itemId).getName());
                        newShoppingTrolley.setItem_singlePrice(itemDAO.queryByItemId(itemId).getPrice());
                        newShoppingTrolley.setItem_num(1);
                        newShoppingTrolley.setUsername(username);
                        shoppingTrolleyDAO.add(newShoppingTrolley);
                    }else {
                        oldShoppingTrolley.setItem_num(oldShoppingTrolley.getItem_num()+1);
                        shoppingTrolleyDAO.update(oldShoppingTrolley);
                    }

                    req.getRequestDispatcher("/ItemServlet").forward(req, resp);
                }else if ("update".equals(submitType)){ //直接修改购物车数量
                    req.getSession().setAttribute("shoppingTrolley",oldShoppingTrolley);
                    req.getRequestDispatcher("shoppingTrolley/item.jsp").forward(req, resp);
                }else if ("confirmUpdate".equals(submitType)||"addMore".equals(submitType)){
                    Integer itemNum = Integer.parseInt(req.getParameter("itemNum"));
                    oldShoppingTrolley.setItem_num(itemNum);
                    shoppingTrolleyDAO.update(oldShoppingTrolley);
                    req.getRequestDispatcher("/CheckShoppingTrolleyServlet").forward(req, resp);
                }

            }else {
                out.println("此次操作无效，请重新加购商品");
                resp.setHeader("refresh","3;url=/experiment/ItemServlet");
            }
        }else {
            out.println("请先登录账户...");
            resp.setHeader("refresh","3;url=login.jsp");
        }



    }
}

package com.study.servlet;

import com.study.dao.impl.OrderFormDAOImpl;
import com.study.dao.impl.ShoppingTrolleyDAOImpl;
import com.study.entity.OrderForm;
import com.study.entity.ShoppingTrolley;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author lxy
 * @date 2020-05-27-8:55
 * @function
 **/
public class OrderFormServlet extends HttpServlet {
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

            OrderFormDAOImpl orderFormDAO = new OrderFormDAOImpl();
            ShoppingTrolleyDAOImpl shoppingTrolleyDAO = new ShoppingTrolleyDAOImpl();
            List<ShoppingTrolley> shoppingTrolleyList = shoppingTrolleyDAO.queryByUsername(username);
            String uuid = UUID.randomUUID().toString(); //同一用户在同一时间结算的商品订单号一致
            if (shoppingTrolleyList!=null){
                Date create_time = new Date();
                for (ShoppingTrolley shoppingTrolley : shoppingTrolleyList) {
                    OrderForm orderForm = new OrderForm();
                    orderForm.setSerial_number(uuid);
                    orderForm.setItem_name(shoppingTrolley.getItem_name());
                    orderForm.setItem_singlePrice(shoppingTrolley.getItem_singlePrice());
                    orderForm.setItem_num(shoppingTrolley.getItem_num());
                    orderForm.setItem_totalPrice(shoppingTrolley.getItem_num()*shoppingTrolley.getItem_singlePrice());
                    orderForm.setUsername(username);
                    orderForm.setCreate_time(create_time);
                    if (orderFormDAO.create(orderForm)){
                        //订单添加成功的同时，删除对应购物车里的商品
                        shoppingTrolleyDAO.delete(shoppingTrolley.getItem_id(),username);
                    }else {
                        out.println("id为"+shoppingTrolley.getId()+"的购物车里的商品生成订单失败，请您对此商品重新操作！");
                    }
                }
                req.getSession().setAttribute("serial_number",uuid);
                req.getSession().setAttribute("create_time",create_time);
            }

            List<OrderForm> orderFormList = orderFormDAO.queryByUsernameAndSerialNumber(username,uuid);
            req.getSession().setAttribute("orderFormList",orderFormList);

            req.getRequestDispatcher("orderForm.jsp").forward(req, resp);

        }else {
            out.println("请先登录账户...");
            resp.setHeader("refresh","3;url=login.jsp");
        }
    }
}

package com.study.servlet;

import com.study.dao.impl.ItemDAOImpl;
import com.study.entity.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author lxy
 * @date 2020-05-12-16:44
 * @function
 **/
public class ItemServlet extends HttpServlet {
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
        if (username==null){//用户未登录账户
            out.println("请你先登录账户，再进行商品查看");
            resp.setHeader("refresh","3;url=login.jsp");
        }else {//用户已登录账户
            ItemDAOImpl itemDAO = new ItemDAOImpl();

            //获取商品总条数
            int totalCount = itemDAO.getTotalCount();
            req.setAttribute("totalCount",totalCount);

            //对商品分页
//        int currentPage = 0;
            //获取页码
            String page = req.getParameter("currentPage");
            if (page==null){//第一次访问ItemServlet，请求为空
                page="0";
            }
            Integer currentPage = Integer.parseInt(page);

            //获取页面大小
            Integer pageSize=4; //默认为4
            if (req.getSession().getAttribute("pageSize")!=null){
                pageSize= (Integer) req.getSession().getAttribute("pageSize");
            }
            String size = req.getParameter("pageSize");
            if (size!=null){//第一次访问ItemServlet，请求为空
                if ("1".equals(size)){
                    pageSize=1;
                }else if ("2".equals(size)){
                    pageSize=2;
                }else if ("3".equals(size)){
                    pageSize=3;
                }else if ("5".equals(size)){
                    pageSize=5;
                }else if ("10".equals(size)){
                    pageSize=10;
                }
                req.getSession().setAttribute("pageSize",pageSize);
            }
//        pageSize = Integer.parseInt(size);

            //对商品进行分页处理
            List<Item> items = itemDAO.queryItemByPage(currentPage, pageSize);
            req.setAttribute("currentPage",currentPage);
            req.setAttribute("pageSize",pageSize);
            req.setAttribute("items",items);




            req.getRequestDispatcher("item.jsp").forward(req, resp);
        }



    }
}

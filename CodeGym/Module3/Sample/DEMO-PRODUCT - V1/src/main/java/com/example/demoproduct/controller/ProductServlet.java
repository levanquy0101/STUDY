package com.example.demoproduct.controller;

import com.example.demoproduct.common.ValidationUtil;
import com.example.demoproduct.model.Product;
import com.example.demoproduct.services.ImlProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    ImlProductService productService = new ImlProductService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                request.getRequestDispatcher("/product/create.jsp").forward(request, response);
                break;
            case "update":
                Integer id = Integer.parseInt(request.getParameter("id"));
                Product productFindId = productService.findById(id);
                request.setAttribute("product",productFindId);
                request.getRequestDispatcher("/product/edit.jsp").forward(request, response);
                break;
            case "delete":
                int idDelete = Integer.valueOf(request.getParameter("id"));
                productService.delete(idDelete);
                response.sendRedirect("/ProductServlet?ms=1");
                break;
            case "search":
                String name = request.getParameter("name");
                List<Product> productListSearch = productService.findName(name);
                request.setAttribute("productList",productListSearch);
                request.getRequestDispatcher("/product/list.jsp").forward(request,response);
                break;
            default:
                List<Product> productList = productService.findAll();
                request.setAttribute("productList",productList);
                request.getRequestDispatcher("/product/list.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
//                int idNew = Integer.parseInt(request.getParameter("id"));
                int idNew = -1;
                String nameNew = request.getParameter("name");
                Double priceNew = Double.valueOf(request.getParameter("price"));
                String aboutNew = request.getParameter("about");
                if(!ValidationUtil.isValidNamePhone(nameNew)){
                    request.setAttribute("messError","Định dạng không hợp lệ !");
                    request.getRequestDispatcher("product/create.jsp").forward(request,response);
                    return;
                }
                Product productNew = new Product(idNew,nameNew,priceNew,aboutNew);
                productService.save(productNew);
                response.sendRedirect("/ProductServlet?ms=1");
                break;
            case "update":
                int idUpdate = Integer.parseInt(request.getParameter("id"));
                String nameUpdate = request.getParameter("name");
                Double priceUpdate = Double.valueOf(request.getParameter("price"));
                String aboutUpdate = request.getParameter("about");
                Product productUpdate = new Product(idUpdate,nameUpdate,priceUpdate,aboutUpdate);
                productService.update(productUpdate);
                response.sendRedirect("/ProductServlet?ms=1");
                break;
            case "delete":
                break;
            default:

        }
    }
}

package com.example.demo.controller;

import com.example.demo.common.ValidationUtil;
import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;
import com.example.demo.model.TypeProduct;
import com.example.demo.services.IProductService;
import com.example.demo.services.ITypeProductService;
import com.example.demo.services.ImlProductService;
import com.example.demo.services.ImlTypeProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    IProductService productService = new ImlProductService();
    ITypeProductService typeProductService = new ImlTypeProductService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                request.setAttribute("typeList",typeProductService.findAll());
                request.getRequestDispatcher("/product/create.jsp").forward(request, response);
                break;
            case "update":
                request.setAttribute("typeList",typeProductService.findAll());
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
                List<ProductDTO> productListSearch = productService.findName(name);
                request.setAttribute("productList",productListSearch);
                request.getRequestDispatcher("/product/list.jsp").forward(request,response);
                break;
            case "sort":
                String sortType = request.getParameter("sortType");
                List<ProductDTO> productSortList = productService.sort(sortType);
                request.setAttribute("productList",productSortList);
                request.getRequestDispatcher("/product/list.jsp").forward(request,response);
                break;
            default:
                List<ProductDTO> productList = productService.findAll();
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
                int idNew = -1;
                String nameNew = request.getParameter("name");
                String codeNameNew = request.getParameter("codeName");
                Integer idTypeNew = Integer.valueOf(request.getParameter("typeName"));
                Double priceNew = Double.valueOf(request.getParameter("price"));
                LocalDate dateSxNew = LocalDate.parse(request.getParameter("date"));
                String aboutNew = request.getParameter("about");
                if(!ValidationUtil.isValidNamePhone(codeNameNew)){
                    request.setAttribute("messError","Định dạng không hợp lệ !");
                    request.setAttribute("typeList",typeProductService.findAll());
                    request.getRequestDispatcher("product/create.jsp").forward(request,response);
                    return;
                }
                Product productNew = new Product(idNew,nameNew,codeNameNew,idTypeNew,priceNew,dateSxNew,aboutNew);
                productService.save(productNew);
                response.sendRedirect("/ProductServlet?ms=1");
                break;
            case "update":
                int idUpdate = Integer.parseInt(request.getParameter("id"));
                String nameUpdate = request.getParameter("name");
                String codeNameUpdate = request.getParameter("codeName");
                Integer idTypeUpdate = Integer.valueOf(request.getParameter("typeName"));
                Double priceUpdate = Double.valueOf(request.getParameter("price"));
                LocalDate dateSxUpdate = LocalDate.parse(request.getParameter("date"));
                String aboutUpdate = request.getParameter("about");
                Product productUpdate = new Product(idUpdate,nameUpdate,codeNameUpdate,idTypeUpdate,priceUpdate,dateSxUpdate,aboutUpdate);
                productService.update(productUpdate);
                response.sendRedirect("/ProductServlet?ms=1");
                break;
            case "delete":
                break;
            default:

        }
    }
}

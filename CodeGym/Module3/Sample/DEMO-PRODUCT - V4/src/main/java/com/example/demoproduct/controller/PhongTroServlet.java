package com.example.demoproduct.controller;

import com.example.demoproduct.common.ValidationUtil;
import com.example.demoproduct.model.PhongTro;
import com.example.demoproduct.services.ImlPhongTroService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "PhongTroServlet", value = "/PhongTroServlet")
public class PhongTroServlet extends HttpServlet {
    ImlPhongTroService productService = new ImlPhongTroService();
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
                PhongTro productFindId = productService.findById(id);
                request.setAttribute("data",productFindId);
                request.getRequestDispatcher("/product/edit.jsp").forward(request, response);
                break;
            case "delete":
                String codeDelete = request.getParameter("deleteList");
                productService.delete(codeDelete);
                response.sendRedirect("/PhongTroServlet?ms=1");
                break;
            case "search":
                String name = request.getParameter("nameSearch");
                List<PhongTro> productListSearch = productService.findName(name);
                request.setAttribute("dataList",productListSearch);
                request.getRequestDispatcher("/product/list.jsp").forward(request,response);
                break;
            default:
                List<PhongTro> productList = productService.findAll();
                request.setAttribute("dataList",productList);
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
                String codeNew = request.getParameter("codeThue");
                String nameNew = request.getParameter("nameThue");
                String phoneNew = request.getParameter("phone");
                LocalDate dateNew = LocalDate.parse(request.getParameter("dateThue"));
                Integer idThueNew = Integer.valueOf(request.getParameter("typeThue"));
                String aboutNew = request.getParameter("aboutThue");

                if(!ValidationUtil.isValidPositonInterger(phoneNew)){
                    request.setAttribute("messError","Định dạng không hợp lệ ! Vui lòng nhập lại.");
                    request.getRequestDispatcher("product/create.jsp").forward(request,response);
                    return;
                }

                PhongTro product = new PhongTro(idThueNew,codeNew,nameNew,phoneNew,dateNew,aboutNew);
                productService.save(product);
                response.sendRedirect("/PhongTroServlet?ms=1");
                break;
            default:

        }
    }
}

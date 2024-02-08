package com.example.demoproduct.controller;

import com.example.demoproduct.common.ValidationUtil;
import com.example.demoproduct.model.BenhAn;
import com.example.demoproduct.model.BenhNhan;
import com.example.demoproduct.services.IBenhAnService;
import com.example.demoproduct.services.IBenhNhanService;
import com.example.demoproduct.services.ImlBenhAnService;
import com.example.demoproduct.services.ImlBenhNhanService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "BenhAnServlet", value = "/BenhAnServlet")
public class BenhAnServlet extends HttpServlet {
    IBenhAnService benhAnService = new ImlBenhAnService();
    IBenhNhanService benhNhanService = new ImlBenhNhanService();
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
                Integer idBa = Integer.parseInt(request.getParameter("idBa"));
                BenhAn benhAnFindId = benhAnService.findById(idBa);
                request.setAttribute("data",benhAnFindId);
                request.getRequestDispatcher("/product/edit.jsp").forward(request, response);
                break;


            case "delete":
                int idDelete = Integer.valueOf(request.getParameter("id"));
                benhAnService.delete(idDelete);
                response.sendRedirect("/BenhAnServlet?ms=1");
                break;
            case "search":
                String name = request.getParameter("name");
                List<BenhAn> benhAnListSearch = benhAnService.findName(name);
                request.setAttribute("dataList",benhAnListSearch);
                request.getRequestDispatcher("/product/list.jsp").forward(request,response);
                break;
            default:
                List<BenhAn> benhAnList = benhAnService.findAll();
                request.setAttribute("dataList",benhAnList);
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

                String codeBaNew = request.getParameter("codeBa");
                String codeBnNew = request.getParameter("codeBn");
                String nameBnNew = request.getParameter("nameBn");
                LocalDate dateInNew = LocalDate.parse(request.getParameter("dateIn"));
                LocalDate dateOutNew = LocalDate.parse(request.getParameter("dateOut"));
                String reasonNew = request.getParameter("reason");

                if(!ValidationUtil.isValidBenhAn(codeBaNew)){
                    request.setAttribute("messError","Định dạng không hợp lệ ! Vui lòng nhập lại");
                    request.getRequestDispatcher("product/create.jsp").forward(request,response);
                    return;
                }

                BenhNhan benhNhanNew = new BenhNhan(codeBnNew,nameBnNew);
                Integer idBnNew = benhNhanService.save(benhNhanNew);
                if(idBnNew != -1){
                    BenhAn benhAnNew = new BenhAn(codeBaNew,idBnNew,dateInNew,dateOutNew,reasonNew);
                    benhAnService.save(benhAnNew);
                }

                response.sendRedirect("/BenhAnServlet?ms=1");
                break;
            case "update":
                Integer idBaUpdate = Integer.valueOf(request.getParameter("idBa"));
                Integer idBnUpdate = Integer.valueOf(request.getParameter("idBn"));
                String codeBaUpdate = request.getParameter("codeBa");
                String codeBnUpdate = request.getParameter("codeBn");
                String nameBnUpdate = request.getParameter("nameBn");
                LocalDate dateInUpdate = LocalDate.parse(request.getParameter("dateIn"));
                LocalDate dateOutUpdate = LocalDate.parse(request.getParameter("dateOut"));
                String reasonUpdate = request.getParameter("reason");
                BenhNhan benhNhanUpdate = new BenhNhan(idBnUpdate,codeBnUpdate,nameBnUpdate);
                benhNhanService.update(benhNhanUpdate);
                BenhAn benhAnUpdate = new BenhAn(idBaUpdate,codeBaUpdate,dateInUpdate,dateOutUpdate,reasonUpdate);
                benhAnService.update(benhAnUpdate);
                response.sendRedirect("/BenhAnServlet?ms=1");
                break;
            case "delete":
                break;
            default:

        }
    }
}

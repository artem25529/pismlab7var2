package com.es.servlets;

import com.es.service.Producer;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/mdb")
public class MdbServlet extends HttpServlet {
    @EJB
    private Producer producer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Arrays.stream(req.getParameterValues("msg")).forEach(msg -> producer.sendJmsMessage(msg));

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}

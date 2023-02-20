package com.example.servlet;

import com.example.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("/login.jsp");
        } else {
            resp.sendRedirect("/user/hello.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Users.getInstance().getUsers().forEach(e -> {
            if (e.equals(login)) {
                req.getSession().setAttribute("user", login);
            }
        });
        if (req.getSession().getAttribute("user") != null) {
            RequestDispatcher dd = req.getRequestDispatcher("/user/hello.jsp");
            dd.forward(req, resp);
        }
        resp.sendRedirect("/login.jsp");
    }
}

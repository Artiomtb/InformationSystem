package com.servlet;

import com.model.DatabaseHandler;
import com.model.Item;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ItemServlet extends HttpServlet {
    private ServletContext context;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idP = req.getParameter("id");
        if (idP != null) {

            int id = Integer.parseInt(idP.trim());

            Item item = DatabaseHandler.initialize().getItemById(id);
            if (item != null) {
                req.setAttribute("item", item);
                context.getRequestDispatcher("/item.jsp").forward(req, resp);
            }

        } else {
            //error
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            if ("update".equals(action)) {
                String idP = req.getParameter("id");
                if (idP != null) {
                    String firstName = req.getParameter("fn");
                    String lastName = req.getParameter("ln");
                    String description = req.getParameter("dc");
                    int id = Integer.parseInt(idP.trim());
                    DatabaseHandler.initialize().updateItem(id, new Item(id, firstName, lastName, description));
                    String xml ="<message><action>update</action><result>ok</result></message>";
                    resp.setContentType("text/xml");
                    resp.setHeader("Cache-Control", "no-cache");
                    PrintWriter pw = resp.getWriter();
                    pw.print(xml);
                    pw.close();
                }

            }
        }
    }

        @Override
        public void init (ServletConfig config)throws ServletException {
            this.context = config.getServletContext();
        }
    }

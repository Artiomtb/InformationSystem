package com.servlet;

import com.model.DatabaseHandler;
import com.model.Item;
import com.model.XmlHandler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class SearchServlet extends HttpServlet {
    ServletContext context;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        if (action != null) {
            if ("autocomplete".equals(action)) {
                String text = req.getParameter("text");
                System.out.println(text);
                if (text != null) {
                    if (!text.trim().isEmpty()) {
                        Collection<Item> items = DatabaseHandler.initialize().getItemByPattern(text);
                        String xml = XmlHandler.makeAutoCompleteSearchXml(items);
                        resp.setContentType("text/xml");
                        resp.setHeader("Cache-Control", "no-cache");
                        PrintWriter pw = resp.getWriter();
                        pw.print(xml);
                        pw.close();
                    }
                }
            }
        } else {
            //404
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }
}

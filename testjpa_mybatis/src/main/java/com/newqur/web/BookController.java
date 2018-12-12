package com.newqur.web;

import com.newqur.entities.Book;
import com.newqur.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping("/book")
@Controller
public class BookController {
    private static final long serialVersionUID = 1L;

    @Autowired
    BookService bookservice;

    // 图书列表Action
    @RequestMapping("/list")
    public ModelAndView listbook(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/listbook");
        mav.addObject("books", bookservice.getAllBooks());
        return mav;
    }

    // 删除图书Action
    @RequestMapping("/delete")
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("listbook");
        int id = Integer.parseInt(request.getParameter("id"));
        mav.addObject("message", bookservice.delete(id) > 0 ? "删除成功！" : "删除失败！");
        mav.addObject("books", bookservice.getAllBooks());
        return mav;
    }

    // 多删除图书Action
    @RequestMapping("/deletemore")
    public ModelAndView Deletes(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("listbook");
        String[] ids = request.getParameterValues("ids");
        if (ids != null && ids.length > 0) {
            mav.addObject("message", bookservice.delete(ids) > 0 ? "删除成功！" : "删除失败！");
        } else {
            mav.addObject("message", "请选择删除项！");
        }
        mav.addObject("books", bookservice.getAllBooks());
        return mav;
    }

    // 添加图书Action
    @RequestMapping("/addbook")
    public ModelAndView AddBook(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/addbook");
        mav.addObject("model", new Book());
        return mav;
    }

    // 保存添加图书Action
    @RequestMapping("/addbookpost")
    public ModelAndView AddBookPost(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/addbook");
        try {
            String title = request.getParameter("title");
            double price = Double.parseDouble(request.getParameter("price"));

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date publishDate = simpleDateFormat.parse(request.getParameter("publishDate"));

            Book entity = new Book(0, title, price, publishDate);
            if (bookservice.add(entity) > 0) {
                mav.addObject("model", new Book());
                mav.addObject("message", "添加成功！");
            } else {
                mav.addObject("model", entity);
                mav.addObject("message", "添加失败！");
            }
        } catch (Exception exp) {
            request.setAttribute("message", exp.getMessage());
            exp.printStackTrace();
        }
        return mav;
    }

    //编辑图书Action
    @RequestMapping("/editbook")
    public ModelAndView editBook(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Book model = bookservice.getBookById(id);
        ModelAndView mav = new ModelAndView("/edit");
        mav.addObject("model", model);
        return mav;
    }

    // 保存编辑图书Action
    @RequestMapping("/editbookpost")
    public ModelAndView EditBookPost(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/edit");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            double price = Double.parseDouble(request.getParameter("price"));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date publishDate = simpleDateFormat.parse(request.getParameter("publishDate"));
            Book entity = new Book(id, title, price, publishDate);
            mav.addObject("message", bookservice.update(entity) > 0 ? "更新成功！" : "更新失败！");
            mav.addObject("model", entity);
        } catch (Exception exp) {
            mav.addObject("message", exp.getMessage());
            exp.printStackTrace();
        }
        return mav;
    }
}

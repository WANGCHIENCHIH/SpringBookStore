package com.example.springbookstore.Controller;

import com.example.springbookstore.DAO.BookRepository;
import com.example.springbookstore.Entity.Book;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BoolController {
    //initialize
    private final BookRepository bookRepository;

    //constr

    BoolController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/BookList")
    public String getBookList(Model model) {

        Iterable<Book> bookList = bookRepository.findAll();
        bookList = Streamable.of(bookList).toList();

        model.addAttribute("bookList", bookList);
        return "BookList";
    }


}

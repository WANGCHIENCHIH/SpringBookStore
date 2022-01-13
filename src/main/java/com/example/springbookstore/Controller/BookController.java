package com.example.springbookstore.Controller;

import com.example.springbookstore.DAO.BookRepository;
import com.example.springbookstore.Entity.Book;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class BookController {
    //initialize
    private final BookRepository bookRepository;

    //constr

    BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/BookList")
    public String getBookList(Model model) {
        //get all book
        Iterable<Book> bookList = bookRepository.findAll();
        bookList = Streamable.of(bookList).toList();

        model.addAttribute("bookList", bookList);
        return "BookList";
    }

    @GetMapping("/Book/{id}")
    public String getBookById(@PathVariable long id, Model model) {
        //getbook by id
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
        }
        //not do exception
        return "Book";
    }

    @GetMapping("/NewBook")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());

        return "NewBook";
    }

    @PostMapping("/NewBook")
    public String AddNewBook(@ModelAttribute Book book, Model model) {

        bookRepository.save(book);
        return "redirect:BookList";
    }

    @GetMapping("/UpdateBook/{id}")
    public String updateBookInfo(@PathVariable long id, Model model) {
        //getbook by isbn
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
        } else {
            return "redirect:BookList";
        }

        return "UpdateBook";
    }

    @PostMapping("/UpdateBook")
    public String updateBookInfo(@Valid Book book, Model model) {
        bookRepository.save(book);

        return "redirect:BookList";
    }


}

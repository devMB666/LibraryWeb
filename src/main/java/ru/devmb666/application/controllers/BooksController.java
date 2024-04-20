package ru.devmb666.application.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.devmb666.application.dao.BooksDAO;
import ru.devmb666.application.dao.PersonDAO;
import ru.devmb666.application.models.Book;
import ru.devmb666.application.models.Person;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksDAO booksDAO;

    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BooksDAO booksDAO, PersonDAO personDAO) {
        this.booksDAO = booksDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", booksDAO.getListOfBooks());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksDAO.getBookById(id));
        return "books/bookpage";
    }

    @GetMapping("/new")
    public String newBookForm(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping("/new")
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/new";
        }
        booksDAO.saveBook(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        booksDAO.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable("id") int id){
        model.addAttribute("book", booksDAO.getBookById(id));
        return "/books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book updatedBook, @PathVariable("id") int id){
        booksDAO.updateBook(id, updatedBook);
        return "redirect:/books";
    }

    @GetMapping("/appoint/{id}") //ModelAttribute - то что передается сюда
    public String appointBookPage(Model model, @ModelAttribute("person") Person person, @PathVariable("id") int book_id){
        model.addAttribute("people", personDAO.index());
        model.addAttribute("book_id", book_id);
        return "/books/appointPage";
    }

    @PatchMapping("/{id}/app")
    public String appoint(@ModelAttribute("person") Person person, @PathVariable("id") int book_id){
        booksDAO.appointBook(person.getId(),book_id);
        return "redirect:/books";
    }
    //state

}

package ru.devmb666.application.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.devmb666.application.models.Book;
import ru.devmb666.application.models.Person;
import ru.devmb666.application.services.BooksService;
import ru.devmb666.application.services.PeopleService;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final PeopleService peopleService;
    private final BooksService booksService;

    @Autowired
    public BooksController(PeopleService peopleService, BooksService booksService) {
        this.peopleService = peopleService;
        this.booksService = booksService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", booksService.getAllBooks());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksService.getBookById(id));
        model.addAttribute("isBookFree", booksService.isBookFree(id));
        model.addAttribute("order", peopleService.getPersonByBookId(id));
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
        booksService.saveBook(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        booksService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable("id") int id){
        model.addAttribute("book", booksService.getBookById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book updatedBook, @PathVariable("id") int id){
        booksService.updateBook(id, updatedBook);
        return "redirect:/books";
    }

    @GetMapping("/{id}/appoint") //ModelAttribute - то что передается сюда
    public String appointBookPage(Model model, @ModelAttribute("person") Person person,
                                  @PathVariable("id") int book_id){
        model.addAttribute("people", peopleService.getAllPeople());
        model.addAttribute("book_id", book_id);
        return "books/appointPage";
    }

    @PatchMapping("/app/{id}")
    public String appoint(@ModelAttribute("person") Person person,
                          @ModelAttribute("book") Book book,
                          @PathVariable("id") int book_id, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/appointPage";
        }
        booksService.appointBook(person.getId(),book_id);
        return "redirect:/books";
    }
}

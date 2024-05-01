package ru.devmb666.application.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.devmb666.application.dao.PersonDAO;
import ru.devmb666.application.models.Book;
import ru.devmb666.application.models.Person;
import ru.devmb666.application.services.PeopleService;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {


    private final PeopleService peopleService;
    private final PersonDAO personDAO;
    private List<Book> list;
    //private final BooksService booksService;
    //private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PeopleService peopleService, PersonDAO personDAO) {
        this.peopleService = peopleService;
        this.personDAO = personDAO;
        //this.booksService = booksService;
    }


    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", peopleService.getAllPeople());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable(value = "id") int id, Model model){
        model.addAttribute("person", peopleService.getPeopleById(id));
        model.addAttribute("bookList", peopleService.getAllBooks(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "people/new";
        peopleService.save(person);
        //personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", peopleService.getPeopleById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "people/edit";
        }

        peopleService.update(id, person);
        //personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        //personDAO.delete(id);
        return "redirect:/people";
    }

    @PatchMapping("/release/{id}")
    public String release(@PathVariable("id") int id){
        peopleService.releaseBook(id);
        return "redirect:/people";
    }
}

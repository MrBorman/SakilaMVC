package org.example.demo1.Controllers;

import org.example.demo1.dao.FilmDAO;
import org.example.demo1.models.Actor;
import org.example.demo1.models.Film;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/films")
public class FilmController {
    private final FilmDAO filmDAO;

    public FilmController(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("films", filmDAO.index());
        return "films/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("film", filmDAO.show(id));
        return "films/show";
    }

    @GetMapping("/new")
    public String newFilm(@ModelAttribute("film") Film film) {
        return "films/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("film") Film film) {
        filmDAO.save(film);
        return "redirect:/films";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("film", filmDAO.show(id));
        return "films/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("film") Film film, @PathVariable("id") int id) {
        filmDAO.update(id, film);
        return "redirect:/films";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        filmDAO.delete(id);
        return "redirect:/films";
    }
}

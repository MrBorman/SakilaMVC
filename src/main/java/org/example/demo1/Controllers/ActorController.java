package org.example.demo1.Controllers;

import org.example.demo1.dao.ActorDAO;
import org.example.demo1.models.Actor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/actors")
public class ActorController{

    private final ActorDAO actorDAO;

    public ActorController(ActorDAO actorDAO) {
        this.actorDAO = actorDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("actors", actorDAO.index());
        return "actors/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("actor", actorDAO.show(id));
        return "actors/show";
    }

    @GetMapping("/new")
    public String newActor(@ModelAttribute("actor") Actor actor) {
        return "actors/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("actor") Actor actor) {
        actorDAO.save(actor);
        return "redirect:/actors";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("actor", actorDAO.show(id));
        return "actors/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("actor") Actor actor, @PathVariable("id") int id) {
        actorDAO.update(id, actor);
        return "redirect:/actors";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        actorDAO.delete(id);
        return "redirect:/actors";
    }

}

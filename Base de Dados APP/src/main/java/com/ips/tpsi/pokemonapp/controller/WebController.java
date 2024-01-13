package com.ips.tpsi.pokemonapp.controller;

import com.ips.tpsi.pokemonapp.bc.WebBc;
import com.ips.tpsi.pokemonapp.entity.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private WebBc bc;

    @GetMapping("/select")
    public ModelAndView getSelectForm() {
        List<Pokemon> pokemonList = bc.getAllPokemon();
        ModelAndView mv = new ModelAndView("selectForm");
        mv.addObject("pokemonList", pokemonList);
        mv.addObject("selectedPokemon", new Pokemon());
        return mv;
    }

    @GetMapping("/add-form")
    public ModelAndView getAddForm() {
        ModelAndView mv = new ModelAndView("addForm");
        mv.addObject("newPokemon", new Pokemon());
        return mv;
    }

    @PostMapping("/add")
    public ModelAndView addPokemon(@ModelAttribute Pokemon newPokemon) {
        bc.addPokemon(newPokemon);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/edit-form/{id}")
    public ModelAndView getEditForm(@PathVariable Integer id) {
        Pokemon pokemonToEdit = bc.getPokemonById(id);
        ModelAndView mv = new ModelAndView("editForm");
        mv.addObject("editedPokemon", pokemonToEdit);
        return mv;
    }


    @PostMapping("/edit")
    public ModelAndView editPokemon(@ModelAttribute Pokemon editedPokemon) {
        bc.editPokemon(editedPokemon);
        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/delete")
    public ModelAndView deletePokemon(Integer idPokemon) {
        bc.deletePokemon(idPokemon);
        return new ModelAndView("redirect:/home");
    }
}

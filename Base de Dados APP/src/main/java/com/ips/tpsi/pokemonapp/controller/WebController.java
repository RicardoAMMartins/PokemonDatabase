package com.ips.tpsi.pokemonapp.controller;

import com.ips.tpsi.pokemonapp.bc.WebBc;
import com.ips.tpsi.pokemonapp.entity.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/add")
    public ModelAndView addPokemon(@ModelAttribute Pokemon newPokemon) {
        bc.addPokemon(newPokemon);
        return new ModelAndView("redirect:/home");
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

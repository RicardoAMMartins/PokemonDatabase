package com.ips.tpsi.pokemonapp.controller;

import com.ips.tpsi.pokemonapp.bc.WebBc;
import com.ips.tpsi.pokemonapp.entity.Pokemon;
import com.ips.tpsi.pokemonapp.entity.TypePokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private WebBc bc;

    @GetMapping("/select")
    public ModelAndView getSelectForm() {
        List<Pokemon> pokemonList = bc.getAllPokemonWithTypes();
        ModelAndView mv = new ModelAndView("selectForm");
        mv.addObject("pokemonList", pokemonList);
        mv.addObject("selectedPokemon", new Pokemon());
        return mv;
    }

    @GetMapping("/add-form")
    public ModelAndView getAddForm() {
        List<TypePokemon> allTypes = bc.getAllTypes();
        ModelAndView mv = new ModelAndView("addForm");
        mv.addObject("newPokemon", new Pokemon());
        mv.addObject("allTypes", allTypes);
        return mv;
    }

    @PostMapping("/add")
    public ModelAndView addPokemon(@ModelAttribute Pokemon newPokemon,
                                @RequestParam("type1") Integer type1Id,
                                @RequestParam("type2") Integer type2Id) {
        try {
            System.out.println("Type 1 ID: " + type1Id);
            System.out.println("Type 2 ID: " + type2Id);
    
            bc.addPokemon(newPokemon, type1Id, type2Id);
            ModelAndView modelAndView = new ModelAndView("addForm"); 
            modelAndView.addObject("newPokemon", new Pokemon()); 
            modelAndView.addObject("addedPokemon", newPokemon); 
            modelAndView.addObject("pokemonList", bc.getAllPokemonWithTypes());
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("errorPage");
        }
    }

    @GetMapping("/edit-form/{id}")
    public ModelAndView getEditForm(@PathVariable Integer id) {
        List<TypePokemon> allTypes = bc.getAllTypes();
        Pokemon pokemonToEdit = bc.getPokemonById(id);
        ModelAndView mv = new ModelAndView("editForm");
        mv.addObject("editedPokemon", pokemonToEdit);
        mv.addObject("allTypes", allTypes);
        return mv;
    }

    @PostMapping("/edit")
    public ModelAndView editPokemon(@ModelAttribute Pokemon editedPokemon,
                                    @RequestParam("type1") Integer type1Id,
                                    @RequestParam("type2") Integer type2Id) 
    {
        try {
            System.out.println("Type 1 ID: " + type1Id);
            System.out.println("Type 2 ID: " + type2Id);
            bc.editPokemon(editedPokemon, type1Id, type2Id);

            ModelAndView modelAndView = new ModelAndView("editForm"); 
            modelAndView.addObject("editedPokemon", new Pokemon()); 
            modelAndView.addObject("newEditedPokemon", editedPokemon); 
            modelAndView.addObject("pokemonList", bc.getAllPokemonWithTypes());
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("errorPage");
        }
    }

    @PostMapping("/delete/{id}")
    public String deletePokemon(@PathVariable Integer id) {
        bc.deletePokemon(id);
        return "redirect:/select";
    }
}

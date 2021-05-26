package com.empresa.suporte.controller;

import com.empresa.suporte.model.Sala;
import com.empresa.suporte.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SalaController {

    @Autowired
    private SalaRepository salaRepository;

    @GetMapping("/sala/list")
    public String listSala(Model model) {
        model.addAttribute("sala", salaRepository.findAll(Sort.by("id")));
        return "sala/list";
    }

    @GetMapping("/sala/add")
    public String addSala(Model model) {
        model.addAttribute("sala", new Sala());
        return "sala/add";
    }

    @PostMapping("/sala/save")
    public String saveSala(Sala sala) {
        try {
            if (sala != null && sala.getLimite() > 0) {
                salaRepository.save(sala);
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
        return "redirect:/sala/list";
    }

    @GetMapping("/sala/view/{id}/{salvo}")
    public String viewSala(@PathVariable long id, @PathVariable boolean salvo, Model model) {
        model.addAttribute("sala", salaRepository.findById(id));
        model.addAttribute("salvo", salvo);
        return "sala/view_modal";
    }

    @GetMapping("/sala/edit/{id}")
    public String editSala(@PathVariable long id, Model model) {

        model.addAttribute("sala", salaRepository.findById(id));
        return "sala/edit_modal";

    }

    @GetMapping("/sala/delete/{id}")
    public String deleteSala(@PathVariable long id) {
        try {
            salaRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //tratar o erro de deletar uma sala cadastrada com o usuario
        }
        return "redirect:/sala/list";
    }
}

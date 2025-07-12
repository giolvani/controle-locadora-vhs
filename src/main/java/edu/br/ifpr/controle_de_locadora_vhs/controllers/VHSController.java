package edu.br.ifpr.controle_de_locadora_vhs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.br.ifpr.controle_de_locadora_vhs.entities.Categoria;
import edu.br.ifpr.controle_de_locadora_vhs.entities.VHS;
import edu.br.ifpr.controle_de_locadora_vhs.services.CategoriaService;
import edu.br.ifpr.controle_de_locadora_vhs.services.VHSService;

@Controller
@RequestMapping("/vhs")
public class VHSController {

    @Autowired
    VHSService vhsService;

    @Autowired
    CategoriaService categoriaService;

    // lista de todas as fitas VHS
    @GetMapping
    public String findAll(Model model) {
        List<VHS> vhsList = vhsService.findAll();
        model.addAttribute("vhsList", vhsList);
        return "vhs/lista";
    }

    // formulario para nova fita VHS
    @GetMapping("/adicionar")
    public String novaFita(Model model) {
        model.addAttribute("vhs", new VHS());
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);
        return "vhs/form";
    }

    // salvar fita VHS
    @PostMapping("/salvar")
    public String salvarFita(@ModelAttribute VHS vhs, RedirectAttributes attributes) {
        try {
            vhsService.save(vhs);
            attributes.addFlashAttribute("mensagemSucesso", "Fita VHS salva com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("mensagemErro", "Erro ao salvar fita VHS: " + e.getMessage());
        }
        return "redirect:/vhs";
    }

    // editar fita VHS
    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model, RedirectAttributes attributes) {
        return vhsService.findById(id).map(vhs -> {
            model.addAttribute("vhs", vhs);
            List<Categoria> categorias = categoriaService.findAll();
            model.addAttribute("categorias", categorias);
            return "vhs/form";
        }).orElseGet(() -> {
            attributes.addFlashAttribute("mensagemErro", "Fita VHS não encontrada.");
            return "redirect:/vhs";
        });
    }

    // Processa a exclusão de uma fita VHS
    @GetMapping("/deletar/{id}")
    public String deletarFita(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            vhsService.deleteById(id);
            attributes.addFlashAttribute("mensagemSucesso", "Fita VHS excluída com sucesso!");
        } catch (RuntimeException e) {
            attributes.addFlashAttribute("mensagemErro", "Erro ao excluir fita VHS: " + e.getMessage());
        }
        return "redirect:/vhs";
    }
}

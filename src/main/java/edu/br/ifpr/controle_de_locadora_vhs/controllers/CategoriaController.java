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
import edu.br.ifpr.controle_de_locadora_vhs.services.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // lista de todas as categorias
    @GetMapping
    public String findAll(Model model) {
        List<Categoria> categoriaList = categoriaService.findAll();
        model.addAttribute("categoriaList", categoriaList);
        return "categoria/lista";
    }

    // formulario para nova categoria
    @GetMapping("/adicionar")
    public String novaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/form";
    }

    // salvar categoria
    @PostMapping("/salvar")
    public String salvarCategoria(@ModelAttribute Categoria categoria, RedirectAttributes attributes) {
        try {
            categoriaService.save(categoria);
            attributes.addFlashAttribute("mensagemSucesso", "Categoria salva com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("mensagemErro", "Erro ao salvar categoria: " + e.getMessage());
        }
        return "redirect:/categorias";
    }

    // editar categoria
    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model, RedirectAttributes attributes) {
        return categoriaService.findById(id).map(categoria -> {
            model.addAttribute("categoria", categoria);
            return "categoria/form";
        }).orElseGet(() -> {
            attributes.addFlashAttribute("mensagemErro", "Categoria não encontrada.");
            return "redirect:/categorias";
        });
    }

    // Processa a exclusão de uma categoria
    @GetMapping("/excluir/{id}")
    public String excluirCategoria(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            categoriaService.deleteById(id);
            attributes.addFlashAttribute("mensagemSucesso", "Categoria excluída com sucesso!");
        } catch (RuntimeException e) {
            attributes.addFlashAttribute("mensagemErro", "Erro ao excluir categoria: " + e.getMessage());
        }
        return "redirect:/categorias";
    }
}

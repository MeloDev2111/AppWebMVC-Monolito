package com.sistemas.monolito.controlador;

import com.sistemas.monolito.dominio.Empleado;
import com.sistemas.monolito.dominio.Fase;
import com.sistemas.monolito.servicio.FaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/fase")
public class FaseController {
    @Autowired private FaseService faseService;

    @GetMapping({"/","/index"})
    public String getIndex(Model model){
        model.addAttribute("listaFases",faseService.listarTodos());

        return "fase/faseIndex";
    }

    @GetMapping("/nuevo")
    public String getFaseNuevoForm(Model model){
        model.addAttribute("fase", new Fase());

        return "fase/faseForm";
    }

    @PostMapping("/nuevo")
    public String postFaseNuevoForm(
            @Valid @ModelAttribute("fase") Fase fase,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs){

        if(bindingResult.hasErrors()){
            return "fase/faseForm";
        }

        faseService.agregar(fase);
        redirectAttrs.addFlashAttribute("flash", "Agregado Correctamente");
        return "redirect:/fase/index";
    }

    @GetMapping("/editar/{id}")
    public String getFaseEditForm(
            @PathVariable("id") Long id,
            Model model){

        Optional<Fase> buscado = faseService.buscar(id);

        model.addAttribute("fase",
                buscado.isPresent() ? buscado.get() : new Fase());

        return "fase/faseForm";
    }

    @PostMapping("/editar")
    public String postFaseEditForm(
            @Valid @ModelAttribute("fase") Fase fase,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs){

        if(bindingResult.hasErrors()){
            return "fase/faseForm";
        }

        faseService.actualizar(fase);
        redirectAttrs.addFlashAttribute("flash", "Actualizado Correctamente");
        return "redirect:/fase/index";
    }

    @GetMapping("/eliminar/{id}")
    public String getFaseEliminar(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttrs){

        faseService.eliminar(id);
        redirectAttrs.addFlashAttribute("flash", "Eliminado Correctamente");
        return "redirect:/fase/index";
    }

}

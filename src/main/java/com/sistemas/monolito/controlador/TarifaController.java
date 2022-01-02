package com.sistemas.monolito.controlador;

import com.sistemas.monolito.dominio.Tarifa;
import com.sistemas.monolito.servicio.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/tarifa")
public class TarifaController {
    @Autowired private TarifaService tarifaService;

    @GetMapping({"/","/index"})
    public String getIndex(Model model){
        model.addAttribute("listaTarifas", tarifaService.listarTodos());

        return "tarifa/tarifaIndex";
    }

    @GetMapping("/nuevo")
    public String getTarifaFormNew(Model model){
        model.addAttribute("tarifa", new Tarifa());

        return "tarifa/tarifaForm";
    }

    @PostMapping("/nuevo")
    public String postTarifaFormNew(
            @Valid @ModelAttribute("tarifa") Tarifa tarifa,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "tarifa/tarifaForm";
        }

        tarifaService.agregar(tarifa);
        redirectAttributes.addFlashAttribute("flash", "Agregado Correctamente");
        return "redirect:/tarifa/index";
    }

    @GetMapping("/editar/{id}")
    public String getTarifaFormEdit(@PathVariable("id") Long id,
                                    Model model){
        Optional<Tarifa> buscado = tarifaService.buscar(id);

        model.addAttribute("tarifa",
                buscado.isPresent() ? buscado.get() : new Tarifa());

        return "tarifa/tarifaForm";
    }

    @PostMapping("/editar")
    public String postTarifaFormEdit(
            @Valid @ModelAttribute("tarifa") Tarifa tarifa,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "tarifa/tarifaForm";
        }

        tarifaService.actualizar(tarifa);
        redirectAttributes.addFlashAttribute("flash", "Actualizado Correctamente");
        return "redirect:/tarifa/index";
    }

    @GetMapping("/eliminar/{id}")
    public String getTarifaEliminar(@PathVariable("id") Long id,
                                    RedirectAttributes redirectAttributes){
        tarifaService.eliminar(id);
        redirectAttributes.addFlashAttribute("flash", "Eliminado Correctamente");
        return "redirect:/tarifa/index";
    }
}

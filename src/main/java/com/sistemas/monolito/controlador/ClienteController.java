package com.sistemas.monolito.controlador;

import com.sistemas.monolito.dominio.Asignacion;
import com.sistemas.monolito.dominio.Cliente;
import com.sistemas.monolito.dominio.Orden;
import com.sistemas.monolito.servicio.ClienteService;
import com.sistemas.monolito.servicio.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired private ClienteService clienteService;
    @Autowired private OrdenService ordenService;

    @GetMapping({"/","/index"})
    public String getIndex(Model model){
        model.addAttribute("listaClientes", clienteService.listarTodos());

        return "cliente/clienteIndex";
    }

    @GetMapping("/nuevo")
    public String getClienteFormNew(Model model){
        model.addAttribute("cliente",new Cliente());

        return "cliente/clienteForm";
    }

    @PostMapping("/nuevo")
    public String postClienteFormNew(
            @Valid @ModelAttribute("cliente") Cliente cliente,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs){

        if(bindingResult.hasErrors()){
            //si hay errores vuelve a mostrar el formulario
            return "cliente/clienteForm";
        }

        clienteService.agregar(cliente);
        redirectAttrs.addFlashAttribute("flash", "Agregado Correctamente");

        return "redirect:/cliente/index";
    }

    @GetMapping("/editar/{id}")
    public String getClienteFormEdit(@PathVariable("id") Long id,
                                     Model model){
        Optional<Cliente> buscado = clienteService.buscar(id);
        model.addAttribute("cliente",
                buscado.isPresent() ? buscado.get() : new Cliente());

        return "cliente/clienteForm";
    }

    @PostMapping("/editar")
    public String postClienteFormEdit(
            @Valid @ModelAttribute("Cliente") Cliente cliente,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs){

        if(bindingResult.hasErrors()){
            return "cliente/clienteForm";
        }

        clienteService.actualizar(cliente);
        redirectAttrs.addFlashAttribute("flash", "Actualizado Correctamente");

        return "redirect:/cliente/index";
    }

    @GetMapping("/eliminar/{id}")
    public String getClienteEliminar( @PathVariable("id") Long id,
                                      RedirectAttributes redirectAttrs){
        clienteService.eliminar(id);
        redirectAttrs.addFlashAttribute("flash", "Eliminado Correctamente");
        return "redirect:/cliente/index";
    }

    @GetMapping("/monitorear")
    public String getClienteMonitorear(Model model){
        // muestra el cuadro de seleccion de clientes
        model.addAttribute("listaClientes", clienteService.listarTodos());
        model.addAttribute("cliente",new Cliente());

        return "cliente/clienteMonitorForm";
    }

    @PostMapping("/monitorear/lista")
    public String getMonitorearLista(
            @ModelAttribute("cliente") Cliente cliente,
            Model model){

        Optional<Cliente> buscado = clienteService.buscar(cliente.getId());

        if(buscado.isPresent()){
            model.addAttribute("cliente", buscado.get());
            model.addAttribute("listaOrdenes", buscado.get().getOrdenes());
        }

        return "cliente/clienteMonitorIndex";
    }

    @GetMapping("/monitorear/orden/{id}")
    public String getMonitorearDetalle(
            @PathVariable("id") Long id,
            Model model){

        Optional<Orden> buscado = ordenService.buscar(id);

        if(buscado.isPresent()){
            Orden orden = buscado.get();
            List<Asignacion> asignaciones = orden.getAsignaciones();

            Collections.sort(asignaciones,
                    (x,y) -> x.getId().compareTo( y.getId() )
            );

            model.addAttribute("orden",orden);
            model.addAttribute("listaAsignaciones", asignaciones);
        }

        return "cliente/clienteMonitorDetalle";
    }
}

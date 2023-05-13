package br.com.csi.musician_plataform.controller;

import br.com.csi.musician_plataform.model.InstrumentoDTO;
import br.com.csi.musician_plataform.service.InstrumentoService;
import br.com.csi.musician_plataform.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/instrumentos")
public class InstrumentoController {

    private final InstrumentoService instrumentoService;

    public InstrumentoController(final InstrumentoService instrumentoService) {
        this.instrumentoService = instrumentoService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("instrumentos", instrumentoService.findAll());
        return "instrumento/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("instrumento") final InstrumentoDTO instrumentoDTO) {
        return "instrumento/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("instrumento") @Valid final InstrumentoDTO instrumentoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "instrumento/add";
        }
        instrumentoService.create(instrumentoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("instrumento.create.success"));
        return "redirect:/instrumentos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("instrumento", instrumentoService.get(id));
        return "instrumento/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("instrumento") @Valid final InstrumentoDTO instrumentoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "instrumento/edit";
        }
        instrumentoService.update(id, instrumentoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("instrumento.update.success"));
        return "redirect:/instrumentos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = instrumentoService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            instrumentoService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("instrumento.delete.success"));
        }
        return "redirect:/instrumentos";
    }

}

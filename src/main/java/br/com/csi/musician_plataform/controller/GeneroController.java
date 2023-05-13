package br.com.csi.musician_plataform.controller;

import br.com.csi.musician_plataform.model.GeneroDTO;
import br.com.csi.musician_plataform.service.GeneroService;
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
@RequestMapping("/generos")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(final GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("generos", generoService.findAll());
        return "genero/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("genero") final GeneroDTO generoDTO) {
        return "genero/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("genero") @Valid final GeneroDTO generoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "genero/add";
        }
        generoService.create(generoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("genero.create.success"));
        return "redirect:/generos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("genero", generoService.get(id));
        return "genero/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("genero") @Valid final GeneroDTO generoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "genero/edit";
        }
        generoService.update(id, generoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("genero.update.success"));
        return "redirect:/generos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = generoService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            generoService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("genero.delete.success"));
        }
        return "redirect:/generos";
    }

}

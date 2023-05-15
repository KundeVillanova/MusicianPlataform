package br.com.csi.musician_plataform.controller;

import br.com.csi.musician_plataform.domain.Genero;
import br.com.csi.musician_plataform.domain.Usuario;
import br.com.csi.musician_plataform.model.PostShowDTO;
import br.com.csi.musician_plataform.repos.GeneroRepository;
import br.com.csi.musician_plataform.repos.UsuarioRepository;
import br.com.csi.musician_plataform.service.PostShowService;
import br.com.csi.musician_plataform.util.CustomCollectors;
import br.com.csi.musician_plataform.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/postShows")
public class PostShowController {

    private final PostShowService postShowService;
    private final UsuarioRepository usuarioRepository;
    private final GeneroRepository generoRepository;

    public PostShowController(final PostShowService postShowService,
            final UsuarioRepository usuarioRepository, final GeneroRepository generoRepository) {
        this.postShowService = postShowService;
        this.usuarioRepository = usuarioRepository;
        this.generoRepository = generoRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("idUserValues", usuarioRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Usuario::getId, Usuario::getNome)));
        model.addAttribute("idGeneroValues", generoRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Genero::getId, Genero::getGeneroMusical)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("postShows", postShowService.findAll());
        return "postShow/list";
    }

    @GetMapping("/add")
    public String add(
            @ModelAttribute("postShow") final PostShowDTO postShowDTO) {
        return "postShow/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("postShow") @Valid final PostShowDTO postShowDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "postShow/add";
        }
        postShowService.create(postShowDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("postShow.create.success"));
        return "redirect:/postShows";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("postShow", postShowService.get(id));
        return "postShow/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("postShow") @Valid final PostShowDTO postShowDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "postShow/edit";
        }
        postShowService.update(id, postShowDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("postShow.update.success"));
        return "redirect:/postShows";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        postShowService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("postShow.delete.success"));
        return "redirect:/postShows";
    }

}

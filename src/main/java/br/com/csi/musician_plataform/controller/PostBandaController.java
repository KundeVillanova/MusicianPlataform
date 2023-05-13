package br.com.csi.musician_plataform.controller;

import br.com.csi.musician_plataform.domain.Instrumento;
import br.com.csi.musician_plataform.domain.Usuario;
import br.com.csi.musician_plataform.model.PostBandaDTO;
import br.com.csi.musician_plataform.repos.InstrumentoRepository;
import br.com.csi.musician_plataform.repos.UsuarioRepository;
import br.com.csi.musician_plataform.service.PostBandaService;
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
@RequestMapping("/postBandas")
public class PostBandaController {

    private final PostBandaService postBandaService;
    private final UsuarioRepository usuarioRepository;
    private final InstrumentoRepository instrumentoRepository;

    public PostBandaController(final PostBandaService postBandaService,
            final UsuarioRepository usuarioRepository,
            final InstrumentoRepository instrumentoRepository) {
        this.postBandaService = postBandaService;
        this.usuarioRepository = usuarioRepository;
        this.instrumentoRepository = instrumentoRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("idUserValues", usuarioRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Usuario::getId, Usuario::getNome)));
        model.addAttribute("idInstrumentoValues", instrumentoRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Instrumento::getId, Instrumento::getNomeInstrumento)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("postBandas", postBandaService.findAll());
        return "postBanda/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("postBanda") final PostBandaDTO postBandaDTO) {
        return "postBanda/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("postBanda") @Valid final PostBandaDTO postBandaDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "postBanda/add";
        }
        postBandaService.create(postBandaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("postBanda.create.success"));
        return "redirect:/postBandas";
    }

    @GetMapping("/edit/{idBanda}")
    public String edit(@PathVariable final Long idBanda, final Model model) {
        model.addAttribute("postBanda", postBandaService.get(idBanda));
        return "postBanda/edit";
    }

    @PostMapping("/edit/{idBanda}")
    public String edit(@PathVariable final Long idBanda,
            @ModelAttribute("postBanda") @Valid final PostBandaDTO postBandaDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "postBanda/edit";
        }
        postBandaService.update(idBanda, postBandaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("postBanda.update.success"));
        return "redirect:/postBandas";
    }

    @PostMapping("/delete/{idBanda}")
    public String delete(@PathVariable final Long idBanda,
            final RedirectAttributes redirectAttributes) {
        postBandaService.delete(idBanda);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("postBanda.delete.success"));
        return "redirect:/postBandas";
    }

}

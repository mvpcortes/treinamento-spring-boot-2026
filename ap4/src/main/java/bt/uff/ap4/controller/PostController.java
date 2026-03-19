package bt.uff.ap4.controller;


import bt.uff.ap4.service.PostService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;


    @GetMapping("{id}")
    public ModelAndView get(@PathVariable Long id){
        val mv = new ModelAndView("post/get");

        mv.addObject("post", postService.findObyComUsuarioById(id));

        return mv;
    }


    @GetMapping
    public ModelAndView list(){
        val mv = new ModelAndView("post/get");
        mv.addObject("post", postService.findAll(Pageable.ofSize(50)));
        return mv;
    }
}

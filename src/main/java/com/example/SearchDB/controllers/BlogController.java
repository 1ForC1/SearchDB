package com.example.SearchDB.controllers;

import com.example.SearchDB.models.Post;
import com.example.SearchDB.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Post post, Model model){
        return "blog-add";
    }

//    @PostMapping("/blog/add")
//    public String blogPostAdd(@RequestParam String title,
//                              @RequestParam String anons,
//                              @RequestParam String full_text, Model model){
//        Post post = new Post(title, anons, full_text);
//        postRepository.save(post);
//        return "redirect:/blog";
//    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@ModelAttribute ("post") @Valid Post post, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "blog-add";
        }
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/filter")
    public String blogFilter(Model model){ return "blog-filter"; }

    @PostMapping("/blog/filter/result")
    public String blogResult(@RequestParam String title, Model model)
    {
        List<Post> result = postRepository.findByTitleContains(title);
        model.addAttribute("result", result);
        return "blog-filter";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        if(!postRepository.existsById(id))
        {
            return "redirect:/blog";
        }
        return "blog-details";
    }
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable("id")long id,
                           Model model)
    {
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "blog-edit";
    }
    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable("id")long id,
                                 @RequestParam String title,
                                 @RequestParam String anons,
                                 @RequestParam String full_text,
                                 Model model)
    {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }
    @PostMapping("/blog/{id}/remove")
    public String blogBlogDelete(@PathVariable("id") long id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }
}

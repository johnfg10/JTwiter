package io.github.johnfg10.jtweeter.controllers;

import io.github.johnfg10.jtweeter.database.model.Twit;
import io.github.johnfg10.jtweeter.database.repositories.TwitRepository;
import io.github.johnfg10.jtweeter.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class TwitController {
    static Logger log = Logger.getLogger(TwitController.class.getName());

    @Autowired
    private TwitRepository twitRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/twit/create")
    public String CreateTwit(Model model){
        model.addAttribute("twits", twitRepository.findAll());
        return "/twits/createtwits";
    }
    //todo add checks to ensure accounts enabled
    @PostMapping("/twit/create")
    public String CreateTwitPost(@RequestParam("content") String content){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //User user = (User)authentication.getDetails();
        Twit twit = Twit.builder().content(content).author(userRepository.findByUsername(authentication.getName())).build();
        System.out.println(content);
        twitRepository.save(twit);
        return "redirect:/index?addedtwit=true";
    }

    @GetMapping(value = "/twit/{twitId}")
    public String ViewTwit(@PathVariable int twitId, Model model){
        Optional<Twit> twitOptional = twitRepository.findById(twitId);
        if (twitOptional.isPresent()){
            Twit twit = twitOptional.get();
            model.addAttribute("twit", twit);
            //model.addAttribute("authorName", twit.author.getUsername());
            return "twits/viewTwit";
        }else {
            //twit not found
            return "twits/twitNotFound";
        }
    }

    @GetMapping(value = "/twit/{twitId}/edit")
    public String EditTwit(@PathVariable int twitId, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        io.github.johnfg10.jtweeter.database.model.User user = userRepository.findByUsername(authentication.getName());
        Optional<Twit> twitOptional = twitRepository.findById(twitId);
        if (twitOptional.isPresent()){
            Twit twit = twitOptional.get();
            if (twit.author == user){
                model.addAttribute("twit", twit);
                return "twits/edit";
            }else{
                throw new AccessDeniedException("This is not your twit!");
            }
        } else {
            //todo show warnings etc
            return "redirect:/twit/"+twitId;
        }
    }

    @PostMapping(value = "/twit/{twitId}/edit")
    public String EditTwitPost(@PathVariable int twitId, @RequestParam("content") String content){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        io.github.johnfg10.jtweeter.database.model.User user = userRepository.findByUsername(authentication.getName());
        Optional<Twit> twitOptional = twitRepository.findById(twitId);
        if (twitOptional.isPresent()){
            Twit twit = twitOptional.get();
            if (twit.author == user){
                twit.setContent(content);
                twitRepository.save(twit);
                return "redirect:/twit/" + twitId;
            }else{
                throw new AccessDeniedException("This is not your twit!");
            }
        } else {
            //todo show warnings etc
            return "redirect:/twit/"+twitId;
        }
    }

    @GetMapping(value = "/twit/{twitId}/delete")
    public String DeleteTwit(@PathVariable int twitId, @RequestParam(defaultValue = "false") boolean accept, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        io.github.johnfg10.jtweeter.database.model.User user = userRepository.findByUsername(authentication.getName());
        Optional<Twit> twitOptional = twitRepository.findById(twitId);
        if (twitOptional.isPresent()){
            Twit twit = twitOptional.get();
            System.out.println(twit.author.id);
            if (twit.author == user){
                if (accept){
                    System.out.println("deleting");
                    twitRepository.delete(twit);
                    return "redirect:/index";
                }else {
                    model.addAttribute("twit", twit);
                    return "twits/delete.html";
                }
            }else{
                //todo replace this with a custom error screen that looks nice
                throw new AccessDeniedException("This is not your twit!");
            }
        } else {
            //todo show warnings etc
            return "redirect:/twit/"+twitId;
        }
    }

/*    @PostMapping(value = "/twit/{twitId}/delete")
    public String DeleteTwitPost(@PathVariable int twitId, @RequestParam("id") int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        io.github.johnfg10.jtweeter.database.model.User user = userRepository.findByUsername(authentication.getName());
        Optional<Twit> twitOptional = twitRepository.findById(id);
        if (twitOptional.isPresent()){
            Twit twit = twitOptional.get();
            System.out.println(twit.author.id);
            if (twit.author == user){
                twitRepository.delete(twit);
            }else{
                //todo replace this with a custom error screen that looks nice
                throw new AccessDeniedException("This is not your twit!");
            }
        }
        return "redirect:/";
    }*/




/*    @PostMapping(value = "/twit/{twitId}/delete")
    public String DeleteTwitPost(@PathVariable int twitId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        io.github.johnfg10.jtweeter.database.model.User user = userRepository.findByUsername(authentication.getName());
        Optional<Twit> twitOptional = twitRepository.findById(twitId);
        if (twitOptional.isPresent()){
            Twit twit = twitOptional.get();
            if (twit.author == user){
                twitRepository.delete(twit);
                return "redirect:/index";
            }else{
                throw new AccessDeniedException("This is not your twit!");
            }
        } else {
            //todo show warnings etc
            return "redirect:/twit/"+twitId;
        }
    }*/

}

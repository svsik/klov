package com.aventstack.klov.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aventstack.klov.domain.User;
import com.aventstack.klov.domain.UserSafe;
import com.aventstack.klov.repository.UserRepository;
import com.aventstack.klov.security.PasswordAuthentication;

@Controller
@Scope("session")
public class UserController {

    @Autowired
    private UserRepository<User, String> userRepo;
    
    private PasswordAuthentication auth = new PasswordAuthentication();
    
    @GetMapping("/auth/signon")
    public String signon(HttpSession session) {
        return "signon";
    }
    
    @PostMapping("/auth/authenticate")
    public String authenticate(String username, String password, HttpSession session, Map<String, String> model, RedirectAttributes redirectAttributes) {
        User user = userRepo.findOneByName(username);
        
        if (user != null 
                && user.getPassword() != null 
                && !user.getPassword().isEmpty()) {
            
            boolean b = false;
            try {
                b = auth.authenticate(password.toCharArray(), user.getPassword());
                
                if (b) {
                    user.setLastSignOn(DateTime.now().toDate());
                    userRepo.save(user);

                    session.setAttribute("user", new UserSafe(user));
                    
                    return "redirect:/account/settings";
                }
            } catch (Exception e) { }
        }
        
        redirectAttributes.addFlashAttribute("message",
                "Invalid username or password");
        return "redirect:/auth/signon";
    }
    
    @GetMapping("/auth/signout")
    public String signout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/auth/signon";
    }
    
    @PostMapping("/auth/changePassword")
    public Map<String, Object> changePassword(String current, String updated, String updatedMatch, Map<String, Object> model, HttpSession session) {
        model.put("status", false);
        model.put("message", "Passwords do not match");
        
        if (updated.equals(updatedMatch)) {
            UserSafe sessionUser = (UserSafe) session.getAttribute("user");
            if (sessionUser == null || sessionUser.getName() == null || sessionUser.getName().isEmpty()) {
                model.put("message", "Something went wrong. Please signon again to change your password");
                session.removeAttribute("user");                
            } else {
                User user = userRepo.findOneByName(sessionUser.getName());
                boolean b = false;
                
                try {
                    b = auth.authenticate(current.toCharArray(), user.getPassword());
                    
                    if (b) {
                        user.setPassword(auth.hash(updatedMatch.toCharArray()));
                        userRepo.save(user);
                        
                        model.put("status", true);
                        model.put("message", "Password updated successfully");
                    }
                } catch (Exception e) {
                    model.put("message", e);
                }
                
            }
        }
        
        return model;
    }
    
}

package com.example.viewWithSecurity.controller;

import com.example.viewWithSecurity.entity.User;
import com.example.viewWithSecurity.exception.UserExistException;
import com.example.viewWithSecurity.exception.UserInfoIncorrectException;
import com.example.viewWithSecurity.exception.UserNotFoundException;
import com.example.viewWithSecurity.service.SecurityService;
import com.example.viewWithSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

//    // Convert a predefined exception to an HTTP Status code :optional
//    @ResponseStatus(value= HttpStatus.CONFLICT,reason="Data integrity violation")  // 409
//    @ExceptionHandler(UserNotFoundException.class)
//    public ModelAndView handleException(UserNotFoundException ex) {
//        //Do something additional if required
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("error");
//        modelAndView.addObject("message", ex.getMessage());
//        return modelAndView;
//    }

    @GetMapping({"/", "/welcome"})
    public String home(HttpServletRequest request) {
        HttpSession session=request.getSession();
        session.setAttribute("user","Sxdxxx");
        return "welcome";
    }

    @GetMapping("/error")
    public String noAccess() {
        return "error";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "usermanagement/registration";
    }
    @GetMapping("/checkexc")
    public String chechExceptionHanding() {
        throw new UserNotFoundException("to check exception handler");
    }

    @PostMapping("/register")
    public String registerPage(@ModelAttribute User user) throws UserInfoIncorrectException, UserExistException {//it gets data from form-data

        if(user.getUsername()==""||user.getPassword()==""||user.getPassConfirm()=="")
            throw new UserInfoIncorrectException("Please Enter Data");

        if(!user.getPassConfirm().equals(user.getPassword()))
            throw new UserInfoIncorrectException("pass and its confirm are not the same");

        userService.save(user);
        securityService.autoLogin(user.getUsername(), user.getPassword());
        return "redirect:/welcome";
    }

    //We don't define /login POST controller, it is provided by Spring Security!!!!!!!!!!!!!s
    @GetMapping("/login")
    public String login() {
        System.out.printf("login get");
        return "usermanagement/login";
    }

    @GetMapping("/logout")
    public String logout() {
        System.out.printf("logout get");
        return "usermanagement/login";
    }
}

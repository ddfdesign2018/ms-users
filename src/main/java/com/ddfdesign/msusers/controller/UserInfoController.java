package com.ddfdesign.msusers.controller;

import com.ddfdesign.msusers.entity.UserInfo;
import com.ddfdesign.msusers.hello.Infouser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ddfdesign.msusers.service.IUserInfoService;

@Controller
@RequestMapping("app")
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping("login")
    public ModelAndView login() {
        System.out.println("Get Mapping /login");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("welcome-login");
        return mav;
    }

    @GetMapping("secure/logged")
    public ModelAndView getInitialpage() {
        System.out.println("GetMapping /secure/logged");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("logged");
        return mav;
    }

    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("access-denied");
        return mav;
    }

    @GetMapping("/infouser")
    public String infoUserForm(Model model) {
        System.out.println("/Infouser get");
        model.addAttribute("infouser", new Infouser());
        return "infouser";
    }


     /**
     * Se comprueba que no existe un username que coincida con el que se introduce,
     * y si no hay ningún username se crea en bbdd a partir de los datos del formulario
     * @param infouser
     * @return indica si el usuario se ha podido crear correctamente
     */
    @PostMapping("/infouser")
    public String infoUserSubmit(@ModelAttribute Infouser infouser) {
        boolean check = false;
        System.out.println(infouser.getPassword());
        System.out.println(infouser.getUsername());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(infouser.getUsername());
        userInfo.setPassword(bCryptPasswordEncoder.encode(infouser.getPassword()));
        userInfo.setEmail(infouser.getEmail());
        userInfo.setName(infouser.getName());
        userInfo.setSurnames(infouser.getSurnames());
        check = userInfoService.createUser(userInfo);
        if (check) {
            return "result";
        }
        else{
            return "errorcreation";
        }
    }

    //Get method que devuelve un eco del usuario que se quiere crear
    @GetMapping("/result")
    public ModelAndView newresult(){
        System.out.println("/Result get");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("result");
        return mav;
    }

    @GetMapping("/secure/createAnimal")
    public ModelAndView createAnimal(){
        System.out.println("/Result get create Animal");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("createAnimal");
        return mav;
    }


}


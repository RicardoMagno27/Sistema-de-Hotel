package System.Hotel.Hotel.System.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import System.Hotel.Hotel.System.execption.ServiceException;
import System.Hotel.Hotel.System.model.Client;
import System.Hotel.Hotel.System.model.Users;
import System.Hotel.Hotel.System.repository.UsersRepository;
import System.Hotel.Hotel.System.service.UsersService;
import System.Hotel.Hotel.System.util.Util;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/")
    public ModelAndView loginView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/login");
        modelAndView.addObject("users", new Users());
        return modelAndView;

    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home/index");
        modelAndView.addObject("client", new Client());
        return modelAndView;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/cadastro");
        modelAndView.addObject("users", new Users());
        return modelAndView;
    }

    @PostMapping("/salvarUsuario")
    public ModelAndView cadastrar(Users users) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        usersService.SalveUser(users);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid Users users, BindingResult br, HttpSession session)
            throws NoSuchAlgorithmException, ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", new Users());
        if (br.hasErrors()) {
            modelAndView.setViewName("login/login");
        }
        Users userlogin = usersService.loginUsers(users.getUsername(), Util.md5(users.getPassword()));
        if (userlogin == null) {
            modelAndView.addObject("msg", "Usuario invalido, Tente novamente");
        } else {
            session.setAttribute("usuarioLogado", userlogin);
            return index();
        }
        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return loginView();
    }

}

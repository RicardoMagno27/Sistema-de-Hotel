package System.Hotel.Hotel.System.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import System.Hotel.Hotel.System.model.Client;
import System.Hotel.Hotel.System.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/inserirClients")
    public ModelAndView addClientes() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cliente/formCliente");
        modelAndView.addObject("client", new Client());
        return modelAndView;

    }

    @PostMapping("InsertClient")
    public ModelAndView insertCliente(@Valid Client client, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("cliente/formCliente");
            modelAndView.addObject("client");
        } else {
            modelAndView.setViewName("redirect:/cliente-adicionado");
            clientRepository.save(client);
        }

        return modelAndView;

    }

    @GetMapping("cliente-adicionado")
    public ModelAndView listarClient() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cliente/listClientes");
        modelAndView.addObject("listasClientes", clientRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cliente/editar");
        Optional<Client> client = clientRepository.findById(id);
        modelAndView.addObject("client", client);
        return modelAndView;
    }

    @PostMapping("/editar")
    public ModelAndView editar(Client client) {
        ModelAndView modelAndView = new ModelAndView();
        clientRepository.save(client);
        modelAndView.setViewName("redirect:/cliente-adicionado");

        return modelAndView;
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable("id") Long id) {
        clientRepository.deleteById(id);
        return "redirect:/cliente-adicionado";

    }

    @GetMapping("filtro-clientes")
    public ModelAndView filtros() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cliente/filtro-clientes");
        return modelAndView;
    }

    @GetMapping("clientes-aprovados")
    public ModelAndView clientesAprovados() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cliente/clientes-aprovados");
        modelAndView.addObject("clienteAprovados", clientRepository.FindByAprovado());
        return modelAndView;
    }

    @GetMapping("clientes-aguarde")
    public ModelAndView clientesAguarde() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cliente/clientes-aguarde");
        modelAndView.addObject("clienteAguarde", clientRepository.FindByAguarde());
        return modelAndView;
    }

    @GetMapping("clientes-negados")
    public ModelAndView clientesNegados() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cliente/clientes-negados");
        modelAndView.addObject("clienteNegado", clientRepository.findByNegado());
        return modelAndView;
    }

    @PostMapping("/pesquisar-cliente")
    public ModelAndView pesquisar(@RequestParam(required = false) String nome) {
        ModelAndView modelAndView = new ModelAndView();
        List<Client> listaClientes;
        if (nome == null || nome.trim().isEmpty()) {
            listaClientes = clientRepository.findAll();
        } else {
            listaClientes = clientRepository.findByNomeContainingIgnoreCase(nome);
        }
        modelAndView.setViewName("cliente/pesquisar-clientes");
        modelAndView.addObject("listaDeClientes", listaClientes);
        return modelAndView;

    }

}

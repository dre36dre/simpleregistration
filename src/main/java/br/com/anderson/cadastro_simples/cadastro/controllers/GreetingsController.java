package br.com.anderson.cadastro_simples.cadastro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.anderson.cadastro_simples.cadastro.repository.UsuarioRepository;
import br.com.anderson.model.Usuario;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
@Autowired  // IC/ CD OU CDI =Injeção de dependência
private UsuarioRepository usuarioRepository;
	
	
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Eu sou capaz " + name + "!";
    }
    
    @RequestMapping(value = "/sucesso/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaSucesso(@PathVariable String nome) {
    	
    	Usuario usuario=new Usuario();
    	usuario.setNome(nome);
       
    	usuarioRepository.save(usuario); //grava no BD
       
    	return "Estou progredindo " + nome ;
}
//    método que retorna JSON
    @GetMapping(value= "listatodos")
    @ResponseBody //retorna os dados para o corpo da resposta
    public ResponseEntity<List<Usuario>> listaUsuario(){
    
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	
    	return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
    }
}
package br.com.anderson.cadastro_simples.cadastro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping(value= "listatodos") //Método de api
    @ResponseBody //retorna os dados para o corpo da resposta
    public ResponseEntity<List<Usuario>> listaUsuario(){
    
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	
    	return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
    }
    
    @PostMapping(value="salvar") //mapeia a url
    @ResponseBody //descrição da resposta
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){//recebe os dados para salvar 
    	
    	Usuario user= usuarioRepository.save(usuario);    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);    }
    
//    Método atualizar
    @PutMapping(value="atualizar") //mapeia a url
    @ResponseBody //descrição da resposta
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){//recebe os dados para atualizar 
    	if(usuario.getId()==null) {
    		return new ResponseEntity<String>("Id não foi informado para atualização.", HttpStatus.OK);
    	}
    	Usuario user= usuarioRepository.saveAndFlush(usuario);    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);    }
    
    
    @DeleteMapping(value="deletar") //mapeia a url
    @ResponseBody //descrição da resposta
    public ResponseEntity<String> deletar(@RequestParam Long userId){//recebe os dados para deletar 
    	
    	usuarioRepository.deleteById(userId);    	
    	return new ResponseEntity<String>("User deletado", HttpStatus.OK);    }

// método pesquisar
@GetMapping(value="buscaruserid") //mapeia a url
@ResponseBody //descrição da resposta
public ResponseEntity<Usuario> buscaruserid(@RequestParam(name="iduser") Long iduser){//recebe os dados para deletar 
	
	Usuario usuario=usuarioRepository.findById(iduser).get();    	
	return new ResponseEntity<Usuario>(usuario,  HttpStatus.OK);    }
}
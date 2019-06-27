package org.generation.brazil.gfood.cliente;


import com.sun.xml.internal.bind.v2.TODO;
import org.generation.brazil.gfood.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository repository;


    @GetMapping("/clientes")
    public List<Cliente> findAll (){
        // "SELECT * FROM clientes";
        return repository.findAll();

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/clientes")
    public Cliente save(@RequestBody Cliente cliente){

        System.out.println("Nome:" + cliente.getNome());
        System.out.println("Endereço:" + cliente.getEndereco());

        return repository.save(cliente);
    }

    @PutMapping("/clientes/{id}")
    public Cliente update (@PathVariable Long id, @RequestBody Cliente cliente)
        throws ResourceNotFoundException {

     return repository.findById(id).map(c -> {
         c.setNome(cliente.getNome());
         c.setEndereco(cliente.getEndereco());
         return repository.save(c);
     }).orElseThrow(() ->
                new ResourceNotFoundException("Não existe cliente cadastrado com o id: " + id));
     }


    @DeleteMapping("/clientes/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}


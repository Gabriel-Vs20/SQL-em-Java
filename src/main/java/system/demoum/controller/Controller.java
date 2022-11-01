package system.demoum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import system.demoum.entity.Cliente;
import system.demoum.dto.ClienteDTO;
import system.demoum.repository.Repository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cliente/v1/")
public class Controller {

    @Autowired
    Repository repository;

    @PostMapping
    public Cliente cliente(@RequestBody @Valid Cliente cliente){
        Cliente clienteSaved = repository.save(cliente);
        return clienteSaved;
    }
    @GetMapping("{id}")
    @ResponseBody
    public Optional<Cliente> getClienteById(@PathVariable @Validated Long id){
        Optional<Cliente> clienteReturned = repository.findById(id);
        return clienteReturned;
    }
    @DeleteMapping("/{id}")
    public String deleteClienteById(@PathVariable Long id){
        try{
            Optional<Cliente> cliente = Optional.of(repository.getById(id));
            if (cliente != null){
                repository.deleteById(id);
                return "Cliente de " + id + " deletado com sucesso! ";
            }
            else{
                throw new Exception("Cliente inexistente!");

            }
        }catch(Exception e){
            System.out.println("Excessão ocorreu!");
            e.printStackTrace();
            return  "O cliente de "+ id + " não existe para ser deletado! ";
        }

    }
    @GetMapping
    public List<Cliente> listClientes(){
        return repository.findAll();
    }
    @PutMapping("/atualize/{id}")
    public String updateClienteById(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id){
        Optional<Cliente> velhoCliente = repository.findById(id);
        if (velhoCliente.isPresent()){
            Cliente cliente = velhoCliente.get();
            cliente.setEndereco(clienteDTO.getEndereco());
            repository.save(cliente);
            return "Cliente de id "+ cliente.getId() + "Atualizado com sucesso! ";
        }else{
            return "Cliente de id "+ id + "não existe! ";

        }
    }

}

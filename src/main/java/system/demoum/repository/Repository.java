package system.demoum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import system.demoum.entity.Cliente;

public interface Repository extends JpaRepository<Cliente, Long> {



}

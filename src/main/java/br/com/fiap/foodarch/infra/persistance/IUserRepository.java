package br.com.fiap.foodarch.infra.persistance;

import br.com.fiap.foodarch.infra.gateways.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {

  List<UserEntity> findByEmailOrCpf(String email, String cpf);

}

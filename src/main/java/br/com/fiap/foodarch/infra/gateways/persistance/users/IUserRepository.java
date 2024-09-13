package br.com.fiap.foodarch.infra.gateways.persistance.users;

import br.com.fiap.foodarch.infra.external.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {

  List<UserEntity> findByEmailOrCpf(String email, String cpf);

}

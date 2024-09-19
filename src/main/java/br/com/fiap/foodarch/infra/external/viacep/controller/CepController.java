package br.com.fiap.foodarch.infra.external.viacep.controller;

import br.com.fiap.foodarch.infra.external.viacep.services.ViaCepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/utils")
@Tag(name = "Utils")
public class CepController {

  private final ViaCepService viaCepService;

  @Autowired
  public CepController(ViaCepService viaCepService) {
    this.viaCepService = viaCepService;
  }

  @GetMapping("/cep/{cep}")
  @Operation(summary = "Get CEP", description = "Brazilian CEP consultation.")
  public Mono<String> getCepInfo(@PathVariable String cep) {
    return viaCepService.getCepInfo(cep);
  }
}
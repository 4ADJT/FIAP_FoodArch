package br.com.fiap.foodarch.infra.external.viacep.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ViaCepService {
  private final WebClient webClient;

  public ViaCepService(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<String> getCepInfo(String cep) {
    String url = String.format("https://viacep.com.br/ws/%s/json/", cep);

    return webClient
        .get()
        .uri(url)
        .retrieve()
        .bodyToMono(String.class);
  }
}

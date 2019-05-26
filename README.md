# Pessoas Interview

Projeto criado para processo seletivo para a vaga de desenvolvedor Java da empresa Softplan

## Rodando o projeto rojeto

- gradlew clean build
- docker build -t pessoas .
- docker tag <inserir tag> tadashi/pessoasinterview:latest
- docker push tadashi/pessoasinterview
- docker run -p 8080:8080 pessoas
  
## Subindo a imagem no docker

- docker pull tadashiwakita/pessoasinterview
- docker run -p 8080:8080 tadashiwakita/pessoasinterview
- acessar localhost:8080/index.html

## Tecnologias utilizadas

- Java 8
- Spring boot
- HTML5
- CSS3
- Javascript

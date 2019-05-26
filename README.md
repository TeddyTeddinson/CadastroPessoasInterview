# Pessoas Interview

Projeto criado para processo seletivo para a vaga de desenvolvedor Java da empresa Softplan

## Rodando Projeto Docker

- gradlew clean build
- docker build -t pessoas .
- docker tag <inserir tag> tadashi/pessoasinterview:latest
- docker push tadashi/pessoasinterview
- docker run -p 8080:8080 pessoas

## Tecnologias utilizadas

- Java 8
- Spring boot
- HTML5
- CSS3
- Javascript
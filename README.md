# pwr-aplikacje-mobilne
Projekt aplikacji do zarządzania wydatkami. Aplikacja na kurs "Aplikacje Mobilne" na PWr.


## Opis aplikacji
Aplikacja składa się z dwóch modułów.

1. Front-end stworzony w TypeScript + Angular 7
2. Back-end stworzony w Javie 11 + Spring Boot 2

Jako warstwę danych aplikacja wykorzystuje wbudowaną w JAR relacyjną bazę H2.

Aplikacja uruchamiana jest z poziomu obrazu dockera będącego połączeniem dwóch powyższych modułów.

## Wymagania wstępne

Do uruchomienia aplikacji niezbędny jest zainstalowany Docker.


## Uruchomienie 

W celu uruchomienia aplikacji należy wywołać polecenie:

```
docker run -p 8080:8080 mdados/pwr-aplikacje-mobilne
```
Spowoduje to pobranie obrazu z rejestru Docker a następnie uruchomienie kontenera z wystawionymi na zewnątrz portami.

Następnie można przejść w przeglądarce pod adres `localhost:8080`

## Budowanie

W celu własnoręcznego stworzenia obrazu należy najpierw sklonować repozytorium a następnie w jego głównym katalogu wykonać polecenie: 

```
docker build --tag=nazwa-obrazu .
```

Spowoduje to uruchomienie procesu budowy obrazu.

Obraz budowany jest na podstawie Dockerfile w typie _multi-stage_. Podczas budowy można wyróżnić 3 etapu:

1. Zbudowanie fontendu 

```Dockerfile
FROM node:10.15.1-alpine as front-end-builder
WORKDIR /client
COPY client/package*.json ./
RUN npm install
COPY /client ./
RUN npx ng build --prod
```

2. Zbudowanie części backendowej

Należy przed tym również skopiować zbudowane pliki frontendowe do folderu public w celu ich serwowania przez backend. Po tym można zbudować projekt korzystając z mavena. 

```Dockerfile
FROM openjdk:11.0.2-jdk-slim as back-end-builder
WORKDIR /server
COPY server/ ./
COPY --from=front-end-builder client/dist/ ./src/main/resources/public/
RUN apt-get --yes update
RUN apt-get --yes --force-yes install maven
```

3. Uruchomienie częsci backendowej
```Dockerfile
FROM openjdk:11.0.2-jdk-slim as runner
EXPOSE 8080
COPY --from=back-end-builder server/target/expense-manager-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "expense-manager-0.0.1-SNAPSHOT.jar"]
```


Wykorzystanie _multi-stage Dockerfile_ pozwala na przeprowadzenie całego procesu budowy projektu w izolowanym i stałym środowisku a także na zmninimalizowanie wynikowego rozmiaru obrazu, ponieważ wcześniejsze etapy Dockerfile są ostatecznie odrzucane.


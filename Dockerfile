FROM node:10.15.1-alpine as front-end-builder

WORKDIR /client
COPY client/package*.json ./
RUN npm install
COPY /client ./
RUN npx ng build --prod

FROM openjdk:11.0.2-jdk-slim as back-end-builder
WORKDIR /server
COPY server/ ./
COPY --from=front-end-builder client/dist/ ./src/main/resources/public/
RUN apt-get --yes update
RUN apt-get --yes --force-yes install maven

FROM openjdk:11.0.2-jdk-slim as runner
EXPOSE 8080
COPY --from=back-end-builder server/target/expense-manager-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "expense-manager-0.0.1-SNAPSHOT.jar"]
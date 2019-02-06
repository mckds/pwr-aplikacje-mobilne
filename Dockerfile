FROM node:v10.15.1-alpine as front-end-builder

WORKDIR /client
COPY client/package.json .
RUN npm install && ng build --prod

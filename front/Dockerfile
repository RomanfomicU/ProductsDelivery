FROM node:latest AS builder

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY ./package.json .
COPY ./vite.config.js .
RUN npm install

COPY . .

CMD ["npm", "run", "dev"]
FROM node:latest AS builder

WORKDIR /app

COPY ./package.json .
COPY ./vite.config.js .
RUN npm install

COPY . .

CMD ["npm", "run", "dev"]
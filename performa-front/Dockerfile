FROM node:20 AS build
WORKDIR /usr/src/app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build --prod

FROM nginx:alpine
COPY --from=build /usr/src/app/dist/performa-app /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
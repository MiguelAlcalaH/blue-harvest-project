# Stage 1: Build the application
FROM node:18-alpine AS build

# Set working directory
WORKDIR /app

# Copy package.json and install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy all files and build the app
COPY . .
RUN npm run build

# Stage 2: Serve the build
FROM nginx:alpine

# Copy the build from the previous stage
COPY --from=build /app/build /usr/share/nginx/html

# Expose port 80 for the application
EXPOSE 80

# Nginx runs automatically when the container starts


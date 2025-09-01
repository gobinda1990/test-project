# Stage 1: Build React app
FROM node:20-alpine AS builder

WORKDIR /app

# Copy only package files first to install dependencies (caching)
COPY package*.json ./
RUN npm install

# Copy the rest of the source code and build
COPY . .
RUN npm run build

# Stage 2: Serve with Nginx
FROM nginx:alpine

# Remove default Nginx static content
RUN rm -rf /usr/share/nginx/html/*

# Copy build output from builder
COPY --from=builder /app/build /usr/share/nginx/html

# Expose default HTTP port
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]

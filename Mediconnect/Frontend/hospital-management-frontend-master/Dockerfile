# Build Stage
FROM node:22.11.0-bullseye AS build

# Set working directory
WORKDIR /app

# Copy package.json and install dependencies
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install

# Copy the rest of the project files
COPY . .

# Build the React application
RUN npm run build

# Serve Stage (Using Nginx for better performance)
FROM nginx:latest

# Set working directory for serving static files
WORKDIR /usr/share/nginx/html

# Copy the built React app from the previous stage
COPY --from=build /app/build .

# Expose port 80 for Nginx
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
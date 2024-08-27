# Use a Node.js image for development
FROM node:18

# Set working directory
WORKDIR /app

# Copy the package.json and package-lock.json files
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the source code
COPY . .

# Add local binaries to the PATH
ENV PATH /app/node_modules/.bin:$PATH

# Set environment variable to disable Angular CLI analytics (otherwise the server will not be able to continue because it is waiting for a response)
ENV NG_CLI_ANALYTICS=false

# Define CHOKIDAR_USEPOLLING to force the use of polling (for development on Windows otherwise no update of the view on a modification)
ENV CHOKIDAR_USEPOLLING=true

# Expose the Angular development port
EXPOSE 4200

# Launch the application in development mode
CMD ["ng", "serve", "--poll" , "2000" ,"--host", "0.0.0.0"]


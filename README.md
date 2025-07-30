# Error Cause AI Analyzer - Help

## Overview

This application uses Spring Boot and Spring AI to analyze error causes using AI models. It integrates with [Ollama](https://ollama.com/) for local AI inference.

---

## Dependencies

- **Spring Boot 3.5.4**
  - spring-boot-starter-web
  - spring-boot-starter-actuator
  - spring-boot-configuration-processor
  - spring-boot-starter-test
- **Spring AI 1.0.0**
  - spring-ai-starter-model-ollama
  - spring-ai-bom
- **Lombok**
- **Java 17 or higher** (Java 21 recommended)

---

## Prerequisites

- Java 17 or 21 (JDK)
- Maven 3.9.x or newer
- Ollama (for local AI model inference)

---

## Installing Ollama on Linux

  Go to release page to pick a version to download (https://github.com/ollama/ollama/releases)

1. **Install Ollama:**
   ```sh
   curl -fsSL https://ollama.com/install.sh | OLLAMA_VERSION=0.9.6 sh
   ```

2. **Start Ollama:**
   ```sh
   ollama serve
   ```

3. **Verify Ollama is Running:**
   - You should see:
     ```
     The Ollama API is now available at 127.0.0.1:11434.
     ```
   - Or check with:
     ```sh
     ollama --version
     ```
   - If installed, you will see:
     ```
     Install complete. Run "ollama" from the command line.
     ```

---

## Models Tested

---
  - A lightweight model which is very fast 1.7GB (https://ollama.com/library/starcoder2)
  - A lightweight model which is very fast 815MB https://ollama.com/library/gemma3
    ```
    ollama run starcoder2:latest
    ollama run gemma3:1b
    ollama run llama3:latest
    ```
---

##### Available Commands

  ```
  serve       Start ollama
  create      Create a model from a Modelfile
  show        Show information for a model
  run         Run a model
  pull        Pull a model from a registry
  push        Push a model to a registry
  list        List models
  ps          List running models
  cp          Copy a model
  rm          Remove a model
  help        Help about any command
  ```

#### Stop/Kill Ollama
  ```
    lsof -i :11434
    kill -9 <PID>
  ```

## Running the Application

1. **Build the project:**
   ```sh
   mvn clean install
   ```

2. **Run the application:**
   ```sh
   mvn spring-boot:run
   ```

3. **Ensure Ollama is running before starting the application.**

---

## Reference Documentation

- [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.4/maven-plugin)
- [Create an OCI image](https://docs.spring.io/spring-boot/3.5.4/maven-plugin/build-image.html)
- [Spring Configuration Processor](https://docs.spring.io/spring-boot/3.5.4/specification/configuration-metadata/annotation-processor.html)
- [Spring Web](https://docs.spring.io/spring-boot/3.5.4/reference/web/servlet.html)
- [Spring Boot Actuator](https://docs.spring.io/spring-boot/3.5.4/reference/actuator/index.html)
- [OpenAI](https://docs.spring.io/spring-ai/reference/api/chat/openai-chat.html)
- [Ollama](https://docs.spring.io/spring-ai/reference/api/chat/ollama-chat.html)

---

## Guides

- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
- [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)


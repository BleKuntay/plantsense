# PlantSense: IoT Plant Monitoring and Irrigation System

**PlantSense** is an IoT project designed to monitor plant conditions in real-time and perform automatic or manual irrigation. This project uses ESP8266 and various sensors to read environmental data, and it provides a web interface to monitor plant conditions.

## Key Features
- **Sensor Monitoring:** Collect temperature, humidity, and soil moisture data from sensors and store them on the server.
- **Automatic Irrigation:** Automatically waters the plants when soil moisture is below 40%.
- **Manual Irrigation:** Users can trigger manual watering through a web interface.
- **Sensor Data Summary:** Displays a summary of sensor data for the last 24 hours and 7 days.
- **Real-time Updates:** Uses WebSocket for real-time communication between the server and web interface.

## Project Structure
This project is divided into three main parts:
1. **API** - Backend using Spring Boot and MongoDB to store sensor data and manage irrigation.
2. **UI** - Web interface to display sensor data and manually control irrigation.
3. **ESP32 Code** - Code for ESP8266 that reads sensor data and sends it to the API server.

## Tech Stack
- **Backend:** 
  - **Spring Boot (Java)** - Manages the API and business logic.
  - **MongoDB** - NoSQL database for storing sensor data.
  - **WebSocket** - Real-time communication between server and client.
  - **REST API** - Provides endpoints for sensor data and irrigation control.

## API Endpoint
- **POST** /api/sensors - Sends sensor data to the server.
- **GET** /api/sensors - Retrieves all sensor data.
- **GET** /api/sensors/latest - Retrieves the latest sensor data.
- **POST** /api/irrigation/start - Triggers manual irrigation.

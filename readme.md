# PlantSense: Smart Plant Monitoring and Watering System

**PlantSense** is a smart system for monitoring and controlling plant conditions using IoT sensors and devices. This project integrates **ESP8266/ESP32** for sensor data collection, a **Spring Boot backend** for data processing and device control, and a **Vue.js frontend** for the user interface.

---

## **Features**
1. Real-time monitoring of **temperature**, **air humidity**, and **soil moisture**.
2. Manual or automated plant watering via a web interface.
3. Daily summary data for the past 7 days.
4. Automatic deletion of sensor data older than 7 days.
5. LED indicators for system and pump status.

---

## **Project Structure**

---

## **Requirements**
### **Hardware:**
- **ESP8266** or **ESP32**
- **DHT11 Sensor** (for temperature and humidity)
- **Soil Moisture Sensor**
- **Relay Module** to control the pump
- LED for status indication

### **Software:**
- **Arduino IDE** (for firmware)
- **Node.js** (for Vue.js frontend)
- **JDK 21** (for Spring Boot backend)
- **PostgreSQL** (for the database)

---

## **Setup and Usage**

### **1. Firmware (ESP8266/ESP32)**
1. Open the `firmware/` folder in the Arduino IDE.
2. Ensure the following libraries are installed:
   - `ESP8266WiFi` or `WiFi.h` (for ESP32)
   - `DHT` (for reading the DHT11 sensor)
3. Configure the `config.h` file:
   ```cpp
   #define WIFI_SSID "Your_SSID"
   #define WIFI_PASSWORD "Your_PASSWORD"
   #define SERVER_URL "http://<your-server-ip>:8080"
   ```
4. Upload the main.ino file to your ESP8266 or ESP32 board.

### **2. Backend (Spring Boot)**
1. Open the backend/ folder in an IDE like IntelliJ IDEA or Visual Studio Code.
2. Update the application.properties file:
   ```properties
    server.port=8080
    spring.datasource.url=jdbc:postgresql://localhost:5432/plantsense
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password
   ```
4. Start the Spring Boot backend:
    ```bash
    mvn spring-boot:run
    ```

### **3. Frontend (Vue.js)**
1. Navigate to the frontend/ folder in your terminal.
2. Install dependencies:
    ```bash
    npm install
    ```
3. Start the Vue.js application

---

## **How It Works**
1. The ESP8266/ESP32 reads data from the DHT11 and soil moisture sensors and sends the data to the backend via REST API or WebSocket.
2. The Spring Boot backend processes the sensor data, saves it in a PostgreSQL database, and provides endpoints for monitoring and control.
3. The Vue.js frontend displays the real-time data and allows users to control the pump manually.

## **Additional Notes**
- Ensure that all devices (ESP and server) are on the same WiFi network.
- Test API endpoints with tools like Postman or curl before integrating with the ESP.
- Properly secure your backend and WiFi credentials to prevent unauthorized access.

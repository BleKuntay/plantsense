#include <WiFi.h>
#include <WebSocketsClient.h>
#include "DHT.h"

#define WIFI_SSID "" 
#define WIFI_PASSWORD ""
#define WS_SERVER ""
#define WS_PORT 8080         
#define WS_PATH "/ws/sensors"

#define LED_PIN 17          
#define DHT_PIN 16          
#define SOIL_MOISTURE_PIN 32
#define DHTTYPE DHT11

DHT dht(DHT_PIN, DHTTYPE);
WebSocketsClient webSocket;

unsigned long lastSensorRead = 0;
const unsigned long sensorInterval = 5000;

void connectToWiFi();
void setupWebSocket();
void webSocketEvent(WStype_t type, uint8_t *payload, size_t length);
void readSensorsAndSend();

void connectToWiFi() {
  Serial.print("Connecting to WiFi: ");
  Serial.println(WIFI_SSID);
  
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("\nConnected to WiFi!");

  digitalWrite(LED_PIN, HIGH);
}

void setupWebSocket() {
  webSocket.begin(WS_SERVER, WS_PORT, WS_PATH);
  webSocket.onEvent(webSocketEvent);
}

void webSocketEvent(WStype_t type, uint8_t *payload, size_t length) {
  switch (type) {
    case WStype_DISCONNECTED:
      Serial.println("WebSocket disconnected!");
      break;
    case WStype_CONNECTED:
      Serial.println("WebSocket connected!");
      break;
    case WStype_TEXT:
      Serial.printf("Message received: %s\n", payload);
      break;
  }
}

void readSensorsAndSend() {
  float temperature = dht.readTemperature();
  float humidity = dht.readHumidity();
  
  int soilMoistureValue = analogRead(SOIL_MOISTURE_PIN);
  int soilMoisturePercent = map(soilMoistureValue, 930, 2555, 100, 0);

  if (isnan(temperature) || isnan(humidity)) {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

  String payload = "{\"temperature\":" + String(temperature) +
                   ",\"humidity\":" + String(humidity) +
                   ",\"soilMoisture\":" + String(soilMoisturePercent) + "}";
  webSocket.sendTXT(payload);
  Serial.println("Sent to WebSocket: " + payload);
}

void setup() {
  Serial.begin(115200);
  pinMode(LED_PIN, OUTPUT);
  digitalWrite(LED_PIN, LOW); 

  dht.begin();
  pinMode(SOIL_MOISTURE_PIN, INPUT);

  connectToWiFi();

  setupWebSocket();
}

void loop() {
  webSocket.loop();

  unsigned long currentMillis = millis();
  if (currentMillis - lastSensorRead >= sensorInterval) {
    readSensorsAndSend();
    lastSensorRead = currentMillis;
  }
}

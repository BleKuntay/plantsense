#include <WiFi.h>
#include <WebSocketsClient.h>
#include "DHT.h"

#define WIFI_SSID "SIMPATI KOS 1  -4G"          // Ganti dengan SSID WiFi Anda
#define WIFI_PASSWORD "simpatikos" // Ganti dengan Password WiFi Anda
#define WS_SERVER "192.168.18.124"    // IP Server WebSocket
#define WS_PORT 8080                  // Port WebSocket
#define WS_PATH "/ws/sensors"         // Path WebSocket

#define LED_PIN 17                    // GPIO17 untuk LED indikator WiFi
#define DHT_PIN 16                    // GPIO16 untuk DHT11
#define SOIL_MOISTURE_PIN 32          // GPIO32 untuk Soil Moisture Sensor
#define DHTTYPE DHT11                 // Tipe sensor DHT

DHT dht(DHT_PIN, DHTTYPE);
WebSocketsClient webSocket;

unsigned long lastSensorRead = 0;     // Waktu terakhir pembacaan sensor
const unsigned long sensorInterval = 5000; // Interval pembacaan sensor (5 detik)

// Fungsi untuk menghubungkan ke WiFi
void connectToWiFi() {
  Serial.print("Connecting to WiFi: ");
  Serial.println(WIFI_SSID);
  
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("\nConnected to WiFi!");

  // Nyalakan LED sebagai indikator
  digitalWrite(LED_PIN, HIGH);
}

// Fungsi untuk mengatur WebSocket
void setupWebSocket() {
  webSocket.begin(WS_SERVER, WS_PORT, WS_PATH);
  webSocket.onEvent(webSocketEvent);
}

// Callback untuk WebSocket
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

// Fungsi untuk membaca data sensor
void readSensorsAndSend() {
  // Baca suhu dan kelembapan dari DHT11
  float temperature = dht.readTemperature();
  float humidity = dht.readHumidity();
  
  // Baca nilai kelembapan tanah dari Soil Moisture Sensor
  int soilMoistureValue = analogRead(SOIL_MOISTURE_PIN);
  int soilMoisturePercent = map(soilMoistureValue, 930, 2555, 100, 0); // Konversi ke %

  // Periksa apakah pembacaan sensor valid
  if (isnan(temperature) || isnan(humidity)) {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

  // Buat payload JSON untuk WebSocket
  String payload = "{\"temperature\":" + String(temperature) +
                   ",\"humidity\":" + String(humidity) +
                   ",\"soilMoisture\":" + String(soilMoisturePercent) + "}";
  webSocket.sendTXT(payload);
  Serial.println("Sent to WebSocket: " + payload);
}

void setup() {
  // Inisialisasi Serial dan LED
  Serial.begin(115200);
  pinMode(LED_PIN, OUTPUT);
  digitalWrite(LED_PIN, LOW); // Matikan LED sebelum koneksi WiFi

  // Inisialisasi sensor
  dht.begin();
  pinMode(SOIL_MOISTURE_PIN, INPUT);

  // Hubungkan ke WiFi
  connectToWiFi();

  // Setup WebSocket
  setupWebSocket();
}

void loop() {
  // Jalankan WebSocket
  webSocket.loop();

  // Kirim data sensor setiap interval tertentu
  unsigned long currentMillis = millis();
  if (currentMillis - lastSensorRead >= sensorInterval) {
    readSensorsAndSend();
    lastSensorRead = currentMillis;
  }
}

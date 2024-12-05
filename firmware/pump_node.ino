#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

const char* ssid = "SIMPATI KOS 1  -4G";
const char* password = "simpatikos";
const char* serverUrl = "http://192.168.18.124:8080/api/control";

WiFiClient wifiClient;
const int pumpPin = D1;
String lastCommand = "TURN_OFF";

void setup() {
  Serial.begin(115200);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi...");
  }
  Serial.println("Connected to WiFi");

  pinMode(pumpPin, OUTPUT);
  digitalWrite(pumpPin, LOW);
}

void loop() {
  if (WiFi.status() == WL_CONNECTED) {
  HTTPClient http;
  http.begin(wifiClient, serverUrl);
  http.addHeader("Content-Type", "application/json");

  int httpResponseCode = http.GET();

  if (httpResponseCode > 0) {
    String command = http.getString();
    Serial.println("Received raw command: " + command);

    command.replace("\"", "");
    Serial.println("Processed command: " + command);

    if (command != lastCommand) {
      lastCommand = command;

      if (command == "TURN_ON") {
        digitalWrite(pumpPin, HIGH);
        Serial.println("LED ON");
      } else if (command == "TURN_OFF") {
        digitalWrite(pumpPin, LOW);
        Serial.println("LED OFF");
      }

      Serial.print("GPIO state: ");
      Serial.println(digitalRead(pumpPin));
    }
  } else {
    Serial.print("HTTP error: ");
    Serial.println(httpResponseCode);
  }

  http.end();
  } else {
    Serial.println("WiFi Disconnected");
  }


  delay(300);
}

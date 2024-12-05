<script lang="ts">
import SensorValue from '@/components/SensorView.vue'
import WaterItButton from '@/components/WaterItButton.vue'
import TemperatureCard from '@/components/TemperatureCard.vue'

export default {
  name: 'SensorView',
  components: { TemperatureCard, WaterItButton, SensorValue },
  data() {
    return {
      humidity: 0,
      soilMoisture: 0,
      temperature: 0,
    }
  },
  mounted() {
    const apiBaseUrl = import.meta.env.VITE_BASE_URL;
    const socket = new WebSocket(`ws://${apiBaseUrl}/ws/sensors`);

    socket.onopen = () => {
      console.log('WebSocket connection established.');
    };

    socket.onmessage = (event) => {
      try {
        const message = event.data.startsWith('Echo: ')
          ? event.data.slice(6)
          : event.data;

        const data = JSON.parse(message);

        this.humidity = Math.max(data.humidity || 0, 0);
        this.soilMoisture = Math.max(data.soilMoisture || 0, 0);
        this.temperature = Math.max(data.temperature || 0, 0);

        console.log('Received data:', {
          humidity: this.humidity,
          soilMoisture: this.soilMoisture,
          temperature: this.temperature,
        });
      } catch (error) {
        console.error('Error parsing WebSocket message:', error, event.data);
      }
    };


    socket.onerror = (error) => {
      console.error('WebSocket error:', error);
    };

    socket.onclose = () => {
      console.log('WebSocket connection closed.');
    };
  },
};
</script>


<template>
  <div class="text-center mb-12">
    <h1 class="text-3xl font-semibold tracking-wide pb-3">PlantSense</h1>
    <div class="grid grid-cols-2 gap-3">
      <!-- Tampilkan data dari WebSocket -->
      <SensorValue :sensorValue="humidity" sensorName="Humidity" />
      <SensorValue :sensorValue="soilMoisture" sensorName="Soil Moisture" />
      <TemperatureCard :sensorValue="temperature" />
      <WaterItButton />
    </div>
  </div>
</template>


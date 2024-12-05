<script setup lang="ts">
import axios from 'axios';
import { ref, onMounted } from 'vue';

const buttonStatus = ref("TURN_OFF");
const apiBaseUrl = import.meta.env.VITE_BASE_URL;

onMounted(async () => {
  try {
    const response = await axios.get(`http://${apiBaseUrl}/api/control`);
    buttonStatus.value = response.data;
    console.log('Initial button status:', buttonStatus.value);
  } catch (error) {
    console.error('Error fetching initial status:', error);
  }
});

const toggleButtonStatus = async () => {
  try {
    const command = buttonStatus.value === "TURN_OFF" ? "TURN_ON" : "TURN_OFF";

    const response = await axios.post(`http://${apiBaseUrl}/api/control`, command, {
      headers: { 'Content-Type': 'application/json' },
    });
    console.log('Command sent successfully:', response.data);

    buttonStatus.value = command;
  } catch (error) {
    console.error('Error sending command:', error);
  }
};
</script>


<template>
  <button
    class="water-button"
    :class="{ 'water-button-on': buttonStatus === 'TURN_ON', 'water-button-off': buttonStatus === 'TURN_OFF' }"
    @click="toggleButtonStatus"
  >
    <h2 class="text-5xl bg-white rounded-full aspect-square p-2 flex items-center justify-center">💧</h2>
    <p>{{ buttonStatus === 'TURN_ON' ? 'Turn Off' : 'Turn On' }}</p>
  </button>
</template>

<style scoped>
.water-button {
  width: 100%;
  transition: 200ms;
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -4px rgba(0, 0, 0, 0.1);
  aspect-ratio: 1 / 1;
  border-radius: 0.5rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
}

.water-button-on {
  background-color: #40A2D8;
}

.water-button-off {
  background-color: #A9A9A9;
}

.water-button:active {
  transition: 200ms;
  transform: scale(0.95);
}
</style>

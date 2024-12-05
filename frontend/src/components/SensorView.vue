<script setup lang="ts">
import RadialProgress from '@/components/RadialProgress.vue'

defineProps({
  sensorValue: {
    type: Number,
    required: false,
  },
  sensorName: {
    type: String,
    required: true,
  },
})
</script>
<script lang="ts">
export default {
  name: 'SensorValue',
}
</script>

<template>
  <div
    class="sensor-card">
    <RadialProgress :value=sensorValue :max="100" :size="120" />
    <p class="font-[500]">{{ sensorName }}</p>
  </div>
</template>

<style scoped>
  .sensor-card {
    width: 100%;
    background-color: #26282B;
    box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -4px rgba(0, 0, 0, 0.1);
    aspect-ratio: 1 / 1;
    border-radius: 0.5rem;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 0.5rem;
  }
</style>

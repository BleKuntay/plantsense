<script setup lang="ts">
  import RadialProgress from '@/components/RadialProgress.vue'

  defineProps({
    sensorValue: {
      type: Number,
      required: false,
    }
  });
</script>
<script lang="ts">
export default {
  name: 'TemperatureCard',
}
</script>

<template>
  <div
    class="sensor-card">
    <RadialProgress :value="sensorValue" type="temperature" />
    <p class="font-[500]">Temperature</p>
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

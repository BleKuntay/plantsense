<script setup lang="ts">
  defineProps({
    sensorName: {
      type: String,
      required: true
    },
    sensorValue: {
      type: Number,
      required: true
    }
  })
</script>

<script lang="ts">
  export default {
    name: 'summaryCard',
  }
</script>

<template>
  <div
    class="bg-primary w-full h-fit p-3 shadow-xl rounded-lg
           flex flex-row items-center justify-between">
    <h2 class="font-bold text-md">{{ sensorName }}</h2>
    <p>{{ sensorValue }}</p>
  </div>
</template>

<style scoped>

</style>

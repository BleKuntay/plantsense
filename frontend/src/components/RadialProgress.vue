<script setup lang="ts">
import { computed } from "vue";


const props = defineProps({
  value: {
    type: Number,
    default: 100,
  },
  max: {
    type: Number,
    default: 100,
  },
  size: {
    type: Number,
    default: 100,
  },
  strokeWidth: {
    type: Number,
    default: 10,
  },
  color: {
    type: String,
    default: "#3498db",
  },
  background: {
    type: String,
    default: "#e5e7eb",
  },
  type: {
    type: String,
    default: "default",
  },
});

const radius = computed(() => (props.size - props.strokeWidth) / 2);
const circumference = computed(() => 2 * Math.PI * radius.value);
const offset = computed(() => {
  if (props.type === "temperature") {
    return 0;
  }
  return circumference.value - (props.value / props.max) * circumference.value;
});


const progressColor = computed(() => {
  if (props.type === "temperature") {

    if (props.value >= 30) return "#f44336";
    if (props.value >= 20) return "#ffc107";
    return "#3498db";
  }

  if (props.type !== "temperature") {
    if (props.value >= 80) return "#4caf50";
    if (props.value >= 40 ) return "#ffc107";
    return "#f44336";
  }
});
</script>

<template>
  <div
    class="relative flex items-center justify-center text-white"
    :style="{ width: props.size + 'px', height: props.size + 'px' }"
  >
    <svg
      :width="props.size"
      :height="props.size"
      :viewBox="`0 0 ${props.size} ${props.size}`"
      class="absolute"
    >
      <!-- Lingkaran Latar Belakang -->
      <circle
        :cx="props.size / 2"
        :cy="props.size / 2"
        :r="radius"
        :stroke-width="props.strokeWidth"
        :stroke="props.background"
        fill="transparent"
      />
      <!-- Lingkaran Progres -->
      <circle
        :cx="props.size / 2"
        :cy="props.size / 2"
        :r="radius"
        :stroke-width="props.strokeWidth"
        :stroke="progressColor"
        :stroke-dasharray="circumference"
        :stroke-dashoffset="offset"
        stroke-linecap="round"
        fill="transparent"
        class="transition-all"
        :transform="`rotate(-90 ${props.size / 2} ${props.size / 2})`"
      />
    </svg>

    <span class="text-xl font-bold">
      {{ props.type === "temperature" ? `${props.value}°C` : `${Math.round((props.value / props.max) * 100)}%` }}
    </span>
  </div>
</template>

<style scoped>
circle {
  transition: stroke-dashoffset 0.5s ease, stroke 0.5s ease;
}
</style>

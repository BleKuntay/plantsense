<script lang="ts">
import { ref, onMounted } from "vue";
import SummaryCard from "@/components/SummaryCard.vue";

export default {
  name: "SummaryView",
  components: { SummaryCard },
  setup() {
    const weeklySummary = ref([]);
    const apiBaseUrl = import.meta.env.VITE_BASE_URL;

    const fetchWeeklySummary = async () => {
      try {
        const response = await fetch(`http://${apiBaseUrl}/api/sensors/summary`);
        const data = await response.json();
        weeklySummary.value = data.map((summary: any) => {
          const date = new Date(summary.day);
          const formattedDate = new Intl.DateTimeFormat("en-US", { month: "long", day: "numeric", year: "numeric" }).format(date);

          return {
            date: formattedDate,
            humidity: summary.avgHumidity || 0,
            soilMoisture: summary.avgSoilMoisture || 0,
            temperature: summary.avgTemperature || 0,
          };
        });
      } catch (error) {
        console.error("Error fetching weekly summary:", error);
      }
    };

    onMounted(fetchWeeklySummary);

    return {
      weeklySummary,
    };
  },
};
</script>


<template>
  <div class="text-center">
    <h1 class="text-3xl font-semibold tracking-wide pb-3">Summary</h1>
    <div class="flex flex-col gap-3">
      <div
        class="summary-card"
        v-for="(daySummary, index) in weeklySummary"
        :key="index"
      >
        <h2>{{ daySummary.date }}</h2>
        <div class="grid grid-cols-1 gap-3 w-full">
          <SummaryCard :sensor-value="daySummary.humidity" sensor-name="Humidity" />
          <SummaryCard :sensor-value="daySummary.soilMoisture" sensor-name="Soil Moisture" />
          <SummaryCard :sensor-value="daySummary.temperature" sensor-name="Temperature" />
        </div>
      </div>
    </div>
  </div>
</template>


<style scoped>
  .summary-card {
    width: 100%;
    height: fit-content;
    padding: 1rem;
    background-color: #26282B;
    box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -4px rgba(0, 0, 0, 0.1);
    border-radius: 0.5rem;
    display: flex;
    flex-direction: column;
    justify-content: start;
    align-items: center;
    gap: 0.5rem;
  }
</style>

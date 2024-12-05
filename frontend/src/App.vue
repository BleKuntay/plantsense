<script setup lang="ts">
  // import { RouterView } from 'vue-router'
  // import HomeView from '@/views/SensorView.vue'
  import SideBar from '@/components/NavigationBar.vue'
  import SensorView from '@/views/SensorView.vue'
  import SummaryView from '@/views/SummaryView.vue'
</script>

<template>
  <div class="app poppins bg-[#353941] min-h-screen text-white flex flex-col lg:flex-row">
    <SideBar />
    <div class="content p-3">
      <SensorView />
      <SummaryView />
    </div>
  </div>
</template>

<style scoped>
  .poppins {
    font-family: "Poppins", sans-serif;
  }
</style>

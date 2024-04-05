<template>
  <div class="app-container">
    <h1>链路跟踪界面</h1>
    <div class="trace-list">
      <div v-for="trace in traces" :key="trace.id" class="trace-item">
        <div class="trace-span-panel-box" :style="'margin-left:' + trace.percentage">
          <div class="trace-info">
            <div class="trace-id">ServiceA Trace ID: {{ trace.id }}</div>
            <div class="trace-duration">Duration: {{ trace.duration }}ms</div>
          </div>
          <div class="trace-spans">
            <div 
              v-for="span in trace.spans" 
              :key="span.id" 
              class="span-item" 
              :style="{ width: (span.duration / trace.duration) * 100 + '%' }"
            >
              <div class="span-name">{{ span.name }}</div>
              <div class="span-duration">Duration: {{ span.duration }}ms</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const traces = ref([
  {
    id: 'trace-1',
    duration: 100,
    percentage: '0%' ,
    spans: [
      { id: 'span-1', name: 'Span 1', duration: 50 },
      { id: 'span-2', name: 'Span 2', duration: 30 },
      { id: 'span-3', name: 'Span 3', duration: 20 },
    ],
  },
  {
    id: 'trace-2',
    duration: 200,
    percentage: '33.33%' ,
    spans: [
      { id: 'span-4', name: 'Span 4', duration: 70 },
      { id: 'span-5', name: 'Span 5', duration: 60 },
    ],
  },
  {
    id: 'trace-3',
    duration: 100,
    percentage: '50%' ,
    spans: [
      { id: 'span-1', name: 'Span 1', duration: 50 },
      { id: 'span-2', name: 'Span 2', duration: 30 },
      { id: 'span-3', name: 'Span 3', duration: 20 },
    ],
  },
  {
    id: 'trace-4',
    duration: 200,
    percentage: '73.33%' ,
    spans: [
      { id: 'span-4', name: 'Span 4', duration: 100 },
      { id: 'span-5', name: 'Span 5', duration: 100 },
    ],
  },
]);

let totalDuration = 0;
traces.value.forEach(trace => {
  totalDuration += trace.duration;
});

console.log("Total duration: ", totalDuration);

traces.value.forEach(trace => {
    let percentage = (trace.duration / totalDuration) * 100;
    console.log(`Span ${trace.id} duration percentage: ${percentage.toFixed(2)}%`);
});


</script>

<style>
.app {
  margin: 20px;
}

.trace-list {
  margin-top: 20px;
}

.trace-item {
  padding: 10px;
  float: left;
  width: 100%;
}

.trace-info {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.trace-id {
  font-weight: bold;
}

.trace-spans {
  display: flex;
  gap: 5px;
}

.span-item {
  background-color: #eee;
  padding: 5px;
  box-sizing: border-box;
  height: 5px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.span-name {
  font-weight: bold;
}

.span-duration {
  color: #888;
  font-size: 12px;
}
</style>

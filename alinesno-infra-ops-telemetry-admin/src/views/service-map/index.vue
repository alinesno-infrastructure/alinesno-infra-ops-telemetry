<template>
  <div class="schedule2">
    <div class="schedule2-canvas" ref="dagCanvas"></div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { Canvas } from 'butterfly-dag';
import mockData from './data.js';

import './index.scss';
import './iconfont.css';
import 'butterfly-dag/dist/index.css';

const dagCanvas = ref(null);
let canvas;

onMounted(() => {
  canvas = new Canvas({
    root: dagCanvas.value,
    disLinkable: true, // 可删除连线
    linkable: true, // 可连线
    draggable: true, // 可拖动
    zoomable: true, // 可放大
    moveable: true, // 可平移
    theme: {
      edge: {
        shapeType: 'AdvancedBezier',
        defaultAnimate: true  , 
        dur: '1s'
      }
    }
  });
  canvas.draw(mockData);
});
</script>

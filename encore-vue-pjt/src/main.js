import { createApp } from 'vue'
import router from './router/index.js'

// 서버기동 : npm run serve

// import App from './App.vue'
// createApp(App).mount('#app')

// import App from './AppSecond.vue'
// createApp(App).mount('#app')

// import App from './AppHome.vue'
// createApp(App).mount('#app')

import App from './AppRead.vue'
createApp(App).use(router).mount('#app')
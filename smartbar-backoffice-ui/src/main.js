import './assets/main.css'

import {createApp} from 'vue'
import App from './App.vue'
import KeycloackService from "@/KeycloackService.js";

const keyclock = new KeycloackService()
keyclock.authenticate(() => {
    const app = createApp(App)
    app.provide('keyclock', keyclock)
    app.mount('#app')
})


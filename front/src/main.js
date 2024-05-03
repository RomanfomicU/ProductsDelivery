import { createApp, } from 'vue'
import VueCookies from 'vue-cookies'
import App from './App.vue'
import router from '@/router/router'

createApp(App)
    .use(router)
    .use(VueCookies, {expires: '1d'})
    .mount('#app')

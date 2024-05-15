import {createRouter, createWebHistory} from "vue-router";
import MainPage from "../pages/MainPage.vue";
import LoginPage from "../pages/LoginPage.vue";
import OrderPage from "../pages/OrderPage.vue";
import ProfilePage from "../pages/ProfilePage.vue";
import RegPage from "../pages/RegPage.vue";
import UserPage from "../pages/UserPage.vue";


const routes = [
    {
        path: '/',
        component: MainPage
    },
    {
        path: '/login',
        component: LoginPage
    },
    {
        path: '/register',
        component: RegPage
    },
    {
        path: '/order',
        component: OrderPage
    },
    {
        path: '/users',
        component: UserPage
    },
    {
        path: '/profile',
        component: ProfilePage
    }
]

const router = createRouter({
    routes,
    history: createWebHistory(import.meta.env.BASE_URL)
});

export default router;
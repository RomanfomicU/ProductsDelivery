<template>
    <div>
        <div class="nav-bar">
            <div class="logo">
                <router-link class="nav-link" to="/">ProDel</router-link>
            </div>
            <div class="links">
                <router-link v-if="user" class="nav-link" to="/order">Заказы</router-link>
                <router-link v-if="!user" class="nav-link" to="/login">Авторизация</router-link>
                <router-link v-if="user" class="nav-link" to="/profile">Профиль</router-link>
                <button class="logout-btn" v-if="user" @click="logout">Logout</button>
            </div>
        </div>
    </div>
</template>

<script setup>
    import { ref, onMounted } from 'vue';
    import { useRouter } from 'vue-router';

    const user = ref('');

    const router = useRouter();

    const logout = () => {
        $cookies.remove('username');
        $cookies.remove('password');
        $cookies.remove('id');
        user.value = '';
        router.push('/')
    }
    onMounted(() => {
        if($cookies.get('username')) {
            user.value = $cookies.get('username')._value
        }
    })
</script>

<style scoped>
    .nav-bar {
        border-bottom: 1px solid black;
        display: flex;
        justify-content: space-between;
        padding: 15px;
    }
    .nav-link {
        margin: 0 10px;
        font-size: 20px;
        color: dodgerblue;
        text-decoration: none;
        font-weight: bold;
    }
    .nav-link:hover {
        color: coral;
    }
    .logout-btn {
        margin: 0 10px;
        font-size: 20px;
        color: coral;
        text-decoration: none;
        font-weight: bold;
        background-color: white;
        border: none;
    }
    .logout-btn:hover {
        color: red;
        cursor: pointer;
    }
</style>
<template>
    <div>
        <div class="nav-bar">
            <div class="logo">
                <router-link class="nav-link" to="/">ProDel</router-link>
            </div>
            <div class="links">
                <router-link v-if="user && idRole == '3'" class="nav-link" to="/users">Пользователи</router-link>
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
    import axios from "axios";

    const user = ref('');
    const idRole = ref('');
    const id = ref('');

    const router = useRouter();

    const getPermissions = async () => {
        try {
            let password = $cookies.get('password')._value;
            const response = await axios.get('http://localhost:8081/api/users/' + id.value, {
                auth: {
                    username: user.value,
                    password: password
                }
            });
            idRole.value = response.data.idRole;
        } catch (error) {
            console.error('The request failed: ', error);
        }
    }
    const logout = () => {
        $cookies.remove('username');
        $cookies.remove('password');
        $cookies.remove('id');
        user.value = '';
        router.push('/')
    }
    onMounted(() => {
        if($cookies.get('username') || $cookies.get('password') || $cookies.get('id')) {
            user.value = $cookies.get('username')._value;
            id.value = $cookies.get('id');
            getPermissions();
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
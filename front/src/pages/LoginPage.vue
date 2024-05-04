<template>
    <div>
        <form class="login-form" @submit.prevent="login" v-if="responseData === 'False' ||responseData === null">
            <input class="login-el" type="text" v-model="username" placeholder="Логин">
            <input class="login-el" type="password" v-model="password" placeholder="Пароль">
            <button class="login-el" type="submit">Войти</button>
        </form>
        <div class="msg-login" id="msg-login-success" v-if="responseData !== null && responseData !== 'False'">
            Авторизация пройдена успешно!
        </div>
        <div class="msg-login" id="msg-login-error" v-else-if="responseData === 'False'">
            Логин или пароль неверны
        </div>
    </div>
</template>

<script setup>
    import axios from 'axios';
    import { ref, onMounted } from 'vue';
    import { useRouter } from 'vue-router';

    const router = useRouter();
    const responseData = ref(null);
    const username = ref('');
    const password = ref('');

    const login = async () => {
        try {
            const response = await axios.post('http://localhost:8081/api/users/login', {
                    login: username.value,
                    password: password.value
            });
            responseData.value = response.data;
            if(responseData.value !== "False") {
                $cookies.set('username', username);
                $cookies.set('password', password);
                $cookies.set('id', responseData.value);
                window.location.reload();
            }
        } catch (error) {
            console.error('Ошибка при аутентификации:', error);
        }
    };
    onMounted(() => {
        if($cookies.get('username')) {
            router.push('/profile')
        }
    })
</script>

<style scoped>
    .login-form {
        display: flex;
        justify-content: center;
        margin: 10px;
        flex-direction: column;
        align-items: center;
    }
    .login-el {
        width: 300px;
        height: 30px;
        display: flex;
        justify-content: center;
        margin: 5px 0;
        border: 1px solid grey;
        border-radius: 10px;
    }
    .login-form button {
        background-color: dodgerblue;
        color: white;
        text-align: center;
        line-height: 28px;
        font-weight: bold;
        font-size: 15px;
        border: none;
        transition: all 0.3s ease;
    }
    .login-form button:hover {
        cursor: pointer;
        transform: scale(1.05);
    }

    .msg-login {
        margin-top: 10px;
        display: flex;
        justify-content: center;
        font-weight: bold;
        font-size: 15px;
    }
    #msg-login-error { color: darkred }
    #msg-login-success { color: forestgreen }
</style>
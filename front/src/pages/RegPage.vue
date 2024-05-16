<template>
    <div>
        <form class="reg-form" @submit.prevent="register">
            <input class="reg-el" type="text" v-model="username" placeholder="Логин">
            <input class="reg-el" type="password" v-model="password" placeholder="Пароль">
            <input class="reg-el" type="text" v-model="phone" placeholder="Телефон">
            <button class="reg-el reg-button" type="submit">Зарегистрироваться</button>
        </form>
        <div class="msg-reg" id="msg-reg-success" v-if="responseData !== null && responseData !== 'False'">
            Регистрация пройдена успешно!
        </div>
        <div class="msg-reg" id="msg-reg-error" v-else-if="responseData === 'False'">
            Такой пользователь уже существует!
        </div>
    </div>
</template>

<script setup>
    import axios from 'axios';
    import { ref, onMounted } from 'vue';
    import { useRouter } from 'vue-router';
    import { BASE_URL } from '../config';

    const router = useRouter();
    const responseData = ref(null);
    const username = ref('');
    const password = ref('');
    const phone = ref('');

    const register = async () => {
        try {
            const response = await axios.post(`${BASE_URL}/api/users`, {
                username: username.value,
                password: password.value,
                phone: phone.value
            });
            responseData.value = response.data;
            if(responseData.value !== "False") {
                $cookies.set('username', username);
                $cookies.set('password', password);
                $cookies.set('id', responseData.value);
                window.location.reload();
            }
        } catch (error) {
            console.error('Ошибка при регистрации:', error);
        }
    };

    onMounted(() => {
        if($cookies.get('username')) {
            router.push('/profile')
        }
    })
</script>

<style scoped>
    .reg-form {
        display: flex;
        justify-content: center;
        margin: 10px;
        flex-direction: column;
        align-items: center;
    }
    .reg-el {
        text-decoration: none;
        width: 300px;
        height: 30px;
        display: flex;
        justify-content: center;
        margin: 5px 0;
        border: 1px solid grey;
        border-radius: 10px;
    }
    .reg-button {
        background-color: dodgerblue;
        color: white;
        text-align: center;
        line-height: 28px;
        font-weight: bold;
        font-size: 15px;
        border: none;
        transition: all 0.3s ease;
    }
    .reg-button:hover {
        cursor: pointer;
        transform: scale(1.05);
    }

    .msg-reg {
        margin-top: 10px;
        display: flex;
        justify-content: center;
        font-weight: bold;
        font-size: 15px;
    }
    #msg-reg-error { color: darkred }
    #msg-reg-success { color: forestgreen }
</style>
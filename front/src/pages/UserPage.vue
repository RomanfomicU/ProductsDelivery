<template>
    <div class="users">
        <div class="users-body">
            <div class="user-body">
                <div class="user-id">id</div>
                <div class="user-field">username</div>
                <div class="user-field">phone</div>
                <div class="user-field">role</div>
            </div>
            <hr>
            <div class="user-body" v-for="user in usersData">
                <div class="user-id">#{{ user.id }}</div>
                <div class="user-field">{{ user.username }}</div>
                <div class="user-field">{{ user.phone }}</div>
                <div class="user-field">{{ user.idRole }}</div>
                <button v-on:click="roleUp(user.id, user.idRole)">+</button>
                <button v-on:click="roleDown(user.id, user.idRole)">-</button>
            </div>
        </div>
    </div>
</template>

<script setup>
    import axios from 'axios';
    import { ref, onMounted } from 'vue';
    import { BASE_URL } from '../config';

    const username = ref('');
    const password = ref('');
    const idRole = ref('');
    const id = ref('');
    const usersData = ref(null);

    const roleUp =  async (userId, userIdRole) => {
        if (userIdRole == 3) {
            return;
        }
        try {
            const response = await axios.put(`${BASE_URL}/api/users/${userId}`, {
                idRole: Number(userIdRole + 1)
            },
                {
                auth: {
                    username: username.value,
                    password: password.value
                }
            });
            window.location.reload();
        } catch (error) {
            console.error('The request failed: ', error);
        }
    }
    const roleDown =  async (userId, userIdRole) => {
        if (userIdRole == 1) {
            return;
        }
        try {
            const response = await axios.put(`${BASE_URL}/api/users/${userId}`, {
                    idRole: Number(userIdRole - 1)
                },
                {
                    auth: {
                        username: username.value,
                        password: password.value
                    }
                });
            window.location.reload();
        } catch (error) {
            console.error('The request failed: ', error);
        }
    }

    const getPermissions = async () => {
        try {
            const response = await axios.get(`${BASE_URL}/api/users/${id.value}`, {
                auth: {
                    username: username.value,
                    password: password.value
                }
            });
            idRole.value = response.data.idRole;
        } catch (error) {
            console.error('The request failed: ', error);
        }
    }
    const getUsers = async () => {
        await getPermissions();
        if(idRole.value != 3) return;
        try {
            const response = await axios.get(`${BASE_URL}/api/users`, {
                auth: {
                    username: username.value,
                    password: password.value
                }
            });
            usersData.value = response.data;
        } catch (error) {
            console.error('The request failed: ', error);
        }
    }
    onMounted(() => {
        if(!$cookies.get('username') || !$cookies.get('password') || !$cookies.get('id')){
            return;
        }
        username.value = $cookies.get('username')._value;
        password.value = $cookies.get('password')._value;
        id.value = $cookies.get('id');
        getUsers();
    });
</script>

<style scoped>
    .users {
        margin-top: 20px;
        margin-left: 10px;
        margin-right: 10px;
    }
    .users-body {
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    .user-body {
        display: flex;
        margin: 10px;
    }
    .users-title {
        font-size: 20px;
        font-weight: bold;
        margin-left: 10px;
        margin-bottom: 10px;
    }
    .user-id {
        font-weight: bold;
        margin-right: 10px;
    }
    .user-field {
        font-style: italic;
        width: 100px;
    }
</style>
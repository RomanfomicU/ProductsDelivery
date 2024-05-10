<template>
    <div>
        <div v-if="userData" class="user-data">
            <div class="user-data-title">
                {{ userData.username }}
            </div>
            <div class="user-data-body">
                <div>Логин: {{ userData.username }}</div>
                <div>Телефон: {{ userData.phone }}</div>
                <div>
                    <div v-if="userData.idRole == '1'">Роль доступа: Пользователь(1)</div>
                    <div v-if="userData.idRole == '2'">Роль доступа: Курьер(2)</div>
                    <div v-if="userData.idRole == '3'">Роль доступа: Администратор(3)</div>
                </div>
            </div>
        </div>
        <div class="user-orders" v-if="userOrders">
            <div class="user-orders-title">
                История заказов
            </div>
            <div class="user-orders-body">
                <div class="user-order-body" v-for="order in userOrders">
                    <div class="user-order-id">#{{ order.id }}</div>
                    <div class="user-order-status pending" v-if="order.status === 'Pending'">В пути</div>
                    <div class="user-order-status delivered" v-if="order.status === 'Delivered'">Доставлен</div>
                    <div class="user-order-status" v-if="order.status === 'Created'">Создан</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
    import axios from 'axios'
    import { ref, onMounted } from 'vue'

    const userData = ref(null);
    const userOrders = ref(null);
    const username = ref('');
    const password = ref('');
    const id = ref('');

    const getUser = async () => {
        if(!$cookies.get('username') || !$cookies.get('password') || !$cookies.get('id')){
            return;
        }
        username.value = $cookies.get('username')._value;
        password.value = $cookies.get('password')._value;
        id.value = $cookies.get('id');
        try {
            const response = await axios.get('http://localhost:8081/api/users/' + id.value, {
                auth: {
                    username: username.value,
                    password: password.value
                }
            });
            userData.value = response.data;
        } catch (error) {
            console.error('The request failed: ', error);
        }
        if (id.value == 2) {
            return;
        }
        try {
            const response = await axios.get('http://localhost:8081/api/orders/user/' + id.value, {
                auth: {
                    username: username.value,
                    password: password.value
                }
            });
            userOrders.value = response.data;
        } catch (error) {
            console.error('The request failed: ', error);
        }
    };
    onMounted(() => {
        getUser();
    })
</script>

<style scoped>
    .user-data {
        margin: 10px;
    }
    .user-data-title {
        font-size: 20px;
        font-weight: bold;
        margin-bottom: 10px;
        margin-left: 10px;
    }
    .user-data-body {
        border: 1px solid #ccc;
        padding: 10px;
        border-radius: 5px;
    }
    .user-data-body > div {
        margin-bottom: 5px;
    }
    .user-data-body > div:last-child {
        margin-bottom: 0;
    }

    .user-orders {
        margin-top: 20px;
        margin-left: 10px;
        margin-right: 10px;
    }
    .user-orders-body {
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    .user-order-body {
        display: flex;
        margin: 10px;
    }
    .user-orders-title {
        font-size: 20px;
        font-weight: bold;
        margin-left: 10px;
        margin-bottom: 10px;
    }
    .user-order-id {
        font-weight: bold;
        margin-right: 10px;
    }
    .user-order-status {
        font-style: italic;
    }
    .user-order-status.pending {
        color: coral;
    }
    .user-order-status.delivered {
        color: green;
    }

</style>
<template>
    <div v-if="ordersData">
        <div class="user-orders" v-if="idRole == '1'">
            <div class="user-orders-title">
                Ваши заказы
            </div>
            <div class="user-orders-body">
                <div class="user-order-body" v-for="order in ordersData">
                    <div class="user-order-id">#{{ order.id }}</div>
                    <div class="user-order-status delivered" v-if="order.status === 'Pending'">В пути</div>
                    <div class="user-order-status pending" v-if="order.status === 'Created'">Создан</div>
                </div>
            </div>
            <div class="user-orders-title">
                Создать заказ
            </div>
            <form class="create-order-form" @submit.prevent="createOrder">
                <input class="create-order-el" type="text" v-model="orderAddress" placeholder="Адрес">
                <input class="create-order-el" type="text" v-model="orderIdStock" placeholder="Склад">
                <button class="create-order-el" type="submit">Заказать</button>
            </form>
        </div>
        <div class="user-orders" v-if="idRole == '2'">
            <div class="user-orders-title">
                Доставки
            </div>
            <div class="user-orders-body">
                <div class="user-order-body" v-for="order in ordersData">
                    <div class="user-order-id">#{{ order.id }}</div>
                    <div class="user-order-status pending" v-if="order.status === 'Pending'">В пути</div>
                    <div class="user-order-status delivered" v-if="order.status === 'Delivered'">Доставлен</div>
                    <div class="user-order-status delivered" v-if="order.status === 'Created'">Создан</div>
                    <button class="courier-button" v-if="order.status === 'Created'" v-on:click="takeOrder(order.id)">Взять</button>
                    <button class="courier-button" v-if="order.status === 'Pending'" v-on:click="finishOrder(order.id)">Завершить</button>
                </div>
            </div>
        </div>
        <div class="user-orders" v-if="idRole == '3'">
            <div class="user-orders-title">
                Все заказы
            </div>
            <div class="user-orders-body">
                <div class="user-order-body" v-for="order in ordersData">
                    <div class="user-order-id">#{{ order.id }}</div>
                    <div class="user-order-status pending" v-if="order.status === 'Pending'">В пути</div>
                    <div class="user-order-status delivered" v-if="order.status === 'Delivered'">Доставлен</div>
                    <div class="user-order-status" v-if="order.status === 'Created'">Создан</div>
                    <div class="user-order-status" v-if="order.idCourier">Курьер: {{ order.idCourier }}</div>
                    <div class="user-order-status" v-else>Курьер: -</div>
                    <div class="user-order-status">Заказчик: {{ order.idUser }}</div>
                    <div class="user-order-status">Склад: {{ order.idStock }}</div>
                    <div class="user-order-address">Адресс: {{ order.endPoint }}</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
    import axios from 'axios'
    import { ref, onMounted } from 'vue'

    const username = ref('');
    const password = ref('');
    const id = ref('');
    const idRole = ref('');
    const ordersData = ref(null);
    const orderAddress = ref('');
    const orderIdStock = ref('');


    const getPermissions = async () => {
        try {
            const response = await axios.get('http://localhost:8081/api/users/' + id.value, {
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
    const getOrders = async () => {
        await getPermissions();
        let request = 'http://localhost:8081/api/orders';
        if (idRole.value == 1) {
            request += '/user/' + id.value;
        } else if (idRole.value == 2) {
            request += '/courier/' + id.value;
        }
        let data = null;
        try {
            const response = await axios.get(request, {
                auth: {
                    username: username.value,
                    password: password.value
                }
            });
            data = response.data;
        } catch (error) {
            console.error('The request failed: ', error);
        }
        if (idRole.value == 1 || idRole.value == 2) {
            ordersData.value = data.filter(order => order.status !== 'Delivered');
        } else {
            ordersData.value = data;
        }
    }
    const createOrder = async () => {
        try {
            const response = await axios.post('http://localhost:8081/api/orders', {
                endPoint: orderAddress.value,
                idUser: id.value,
                idStock: orderIdStock.value
            }, {
                auth: {
                    username: username.value,
                    password: password.value
                }
            });

        } catch (error) {
            console.error('Ошибка при аутентификации:', error);
        }
        window.location.reload();
    }
    const takeOrder = async (idOrder) => {
        try {
            const response = await axios.get(`http://localhost:8081/api/orders/take/${idOrder}/${id.value}`, {
                auth: {
                    username: username.value,
                    password: password.value
                }
            });
        } catch (error) {
            console.error('The request failed: ', error);
        }
        window.location.reload();
    }
    const finishOrder = async (idOrder) => {
        try {
            const response = await axios.get(`http://localhost:8081/api/orders/finish/${idOrder}`, {
                auth: {
                    username: username.value,
                    password: password.value
                }
            });
        } catch (error) {
            console.error('The request failed: ', error);
        }
        window.location.reload();
    }
    onMounted(() => {
        if(!$cookies.get('username') || !$cookies.get('password') || !$cookies.get('id')){
            return;
        }
        username.value = $cookies.get('username')._value;
        password.value = $cookies.get('password')._value;
        id.value = $cookies.get('id');
        getOrders();
    });
</script>

<style scoped>
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
        width: 100px;
    }
    .user-order-address {
        width: 50%;
    }
    .user-order-status.pending {
        color: coral;
    }
    .user-order-status.delivered {
        color: green;
    }

    .create-order-form {
        display: flex;
        justify-content: center;
        margin: 10px;
        flex-direction: column;
        align-items: center;
    }
    .create-order-el {
        width: 300px;
        height: 30px;
        display: flex;
        justify-content: center;
        margin: 5px 0;
        border: 1px solid grey;
        border-radius: 10px;
    }
    .create-order-form button {
        background-color: dodgerblue;
        color: white;
        text-align: center;
        line-height: 28px;
        font-weight: bold;
        font-size: 15px;
        border: none;
        transition: all 0.3s ease;
    }
    .create-order-form button:hover {
        cursor: pointer;
        transform: scale(1.05);
    }

    .courier-button {
        background-color: dodgerblue;
        color: white;
        text-align: center;
        font-weight: bold;
        border: none;
        transition: all 0.3s ease;
        border-radius: 10px;
        width: 100px;
    }
    .courier-button:hover {
        cursor: pointer;
        transform: scale(1.05);
    }
</style>
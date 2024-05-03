<template>
    <div>
        {{ responseData }}
    </div>
</template>

<script setup>
    import axios from 'axios'
    import { ref, onMounted } from 'vue'

    const responseData = ref(null);

    const fetchData = async () => {
        try {
            //test response
            const response = await axios.get('http://localhost:8081/api/orders', {
                auth: {
                    username: 'admin',
                    password: 'pass'
                }
            });
            responseData.value = response.data;
        } catch (error) {
            console.error('Ошибка при получении данных:', error);
        }
    }
    onMounted(() => {
        fetchData();
        console.log(responseData);
    });
</script>

<style scoped>

</style>
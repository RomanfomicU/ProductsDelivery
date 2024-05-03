<template>
    <div id="app">
        <HeaderComponent></HeaderComponent>
        <router-view></router-view>
    </div>
</template>

<script setup>
    import HeaderComponent from '/src/components/HeaderComponent.vue'
</script>

<style>
    body {
        margin: 0;
    }
</style>
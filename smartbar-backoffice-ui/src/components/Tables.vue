<script setup>
import {onMounted, ref} from "vue";
import {OAuth2Client} from "@badgateway/oauth2-client";
import axios from "axios";

const tables = ref({});

onMounted(() => {
  new OAuth2Client({
    server: '',
    clientId: '...'
  })

  axios.get("/api/tables").then(response => (tables.data = response.data))
})
</script>

<template>
  <h1>Smartbar - Tables overview</h1>
</template>

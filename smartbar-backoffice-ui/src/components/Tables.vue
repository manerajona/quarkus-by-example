<script setup>
import {onMounted, ref} from "vue";
import {OAuth2Client} from "@badgateway/oauth2-client";
import axios from "axios";

const tables = ref([]);

onMounted(() => {
  new OAuth2Client({
    server: '',
    clientId: '...'
  })

  axios.get("/api/tables").then(response => {
    console.log(response.data)
    console.log(tables)
    response.data.forEach(element => tables.value.push(element))
  })
})
</script>

<template>
  <h1>Smartbar - Tables overview</h1>

  <table>
    <th>
      <td>Name</td>
      <td>Anzahl Plätze</td>
    </th>
    <tr v-for="table in tables">
      <td>{{ table.name }}</td>
      <td>{{ table.seatCount }}</td>
    </tr>
  </table>
</template>

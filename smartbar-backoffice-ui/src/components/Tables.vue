<script setup>
import {inject, onMounted, ref} from "vue";
import axios from "axios";

const tables = ref([]);

onMounted(() => {
  const keyclock = inject('keyclock')
  const token = keyclock.keycloak.token
  axios.get("/api/tables", {
    headers: {
      Authorization: `Bearer ${token}`
    }
  }).then(response => {
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

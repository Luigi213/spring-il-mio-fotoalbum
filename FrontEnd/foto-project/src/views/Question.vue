<script>
import axios from 'axios';
const BASE_URL_QUESTION = "http://localhost:8080/api/v1/question";

export default {
  data() {
    return {
      question:{
        email: "",
        messaggio: ""
      }
    }
  },
  methods:{
    postMessage(){
      axios.post(BASE_URL_QUESTION, this.question)
      .then(res => {
        this.$router.push('/');
      })
      .catch(err => console.log(err));
    }
  }
}
</script>
<template lang="">
  <div>
    <router-link  to="/">Torna Indietro</router-link>
    <form @submit.prevent="postMessage">
        <div>
            <label for="email">Email: </label>
            <input type="email" name="email" v-model="question.email" id="email">
        </div>
        <div>
            <label for="messaggio">Messaggio: </label>
            <input type="text" name="messaggio" v-model="question.messaggio" id="messaggio">
        </div>
        <input type="submit" value="CREATE">
    </form>
  </div>
</template>
<style lang="">
  
</style>
<script>
import axios from 'axios';
import Photos from './Photos.vue';
const BASE_URL_QUESTION = "http://localhost:8080/api/v2";
export default {
    components:{
        Photos
    },
    data() {
        return {
            photos:[],
            search: ""
        }
    },
    methods:{
       getPhoto(){
        axios.get(BASE_URL_QUESTION + "/photos?titolo=" + this.search)
            .then(res => {
						const photos = res.data;

						this.photos = photos;
					})
                    .catch(err => console.log(err));
       } 
    },
    mounted(){
        this.getPhoto();
    }
}
</script>
<template lang="">
    <div>
        <router-link to="/question">Contatti</router-link>
        <br>
        <input type="text" v-model="search" @keyup="getPhoto()">
        <Photos v-for="photo in photos" :photo="photo"/>
    </div>
</template>
<style lang="">
    
</style>
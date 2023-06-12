<script>
export default {
    props:{
        photo:{}
    }
}
</script>
<template lang="">
    <ul v-if="photo.visibile">
        <li>
            {{ photo.titolo }}
            <img :src="photo.url">
        </li>
    </ul>
</template>
<style lang="scss">

</style>
<template>
    <div>
        <div class="header">
            <Header/>
        </div>
        <div class="searchBasicMain">
            <SearchBasic />
        </div>
        <div class="user">
            <User />
        </div>
    </div>
</template>

<script>

    import Header from '@/view/header/Header.vue'
    import SearchBasic from '@/view/search/SearchBasic.vue'
    import User from '@/view/user/User.vue'

    export default {
        name: 'MainUser',
        components: {
            Header,
            SearchBasic,
            User
        }
    }
</script>
<style scoped>
    .searchBasicMain {
        margin-top: 10px;
        margin-bottom: 10px;
        display: flex;
        justify-content: center;
    }

    

    
</style>
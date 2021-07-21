<template>
    <div>
        <div class="header">
            <Header/>
            <div>
                <ul>
                    <li><button @click="change('user')" :class="{selected:compare()}">Quản lý người dùng</button></li>
                    <li><button @click="change('team')" :class="{selected:!compare()}">Quản lý đơn vị </button></li>
                </ul>
            </div>
        </div>
        <div>
           <div v-if="compare()"><UserManager /></div> 
            <div v-else><TeamManager /></div>
        </div>



    </div>
</template>

<script scoped>

    import Header from '@/view/header/Header.vue'
    import TeamManager from '@/view/manager/TeamManager.vue'
    import UserManager from '@/view/manager/UserManager.vue'

    export default {
        components: {
            Header,
            TeamManager,
            UserManager,
        },
        data() {
            return {
                name: 'user'
            }
        },
        methods: {
            change(z){
                this.name = z
            },
            compare(){
             return this.name == "user"? true: false
            }
        },

    }
</script>
<style>
    .searchBasicMain {
        margin-top: 5px;
        display: flex;
        justify-content: center;
    }

    .center {
        display: flex;
        justify-content: space-between;
        margin-top: 10px;
    }

    .center .searchAdvantedMain {
        width: 20%;
    }

    .center .user {
        width: 80%;
    }

    .header ul {
        display: inline-flex;
    }

    .header  li {
        padding: 0;
        margin: 0;
        list-style-type: none;
    }

    .header li button {
        padding: 10px;
        margin: 10px;
    }
    .header li button:hover{
        background-color: cadetblue;
    }
    .selected{
        background-color:cadetblue;
    }
</style>
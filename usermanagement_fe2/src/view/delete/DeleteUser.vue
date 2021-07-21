<template>
    <div class="deleteUser">
        <div class="confirm">
            <h4 class="title">Bạn có chắc chắn muốn xóa " {{user.fullName}} " không ???</h4>
            <div>
                <div class="row mb-3">
                    <div class="col-md-12 text-center">
                        <button @click="confirmDeleteUser()" class="confirmDelete">Đồng ý</button>&nbsp;
                        <button @click="deleteDestroy()" class="delete">Hủy</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
    import User from '@/model/user.js'
    import AdminService from '@/service/admin.service.js'
    export default {
        data() {
            return {
                user: new User()
            }
        },
        mounted() {
            this.user = this.$store.state.user.transfreDataDelete;
        },
        methods: {
            deleteDestroy(){
                this.$store.state.user.buttonShowDelete = false;
            },
            confirmDeleteUser(){
                AdminService.deleteUser(this.user).then(
                    () =>{
                        this.loadingData();
                    },
                    error =>{
                        console.log(error);
                    }
                )
            },
            loadingData(){
                AdminService.getAllUser().then(
                    response => {
                        this.$store.state.user.dataUser = response.data;
                        this.$store.state.user.buttonShowDelete = false;
                    }, error => {
                        console.log(error.response);
                    }
                )
            }
        },
    }
</script>

<style scoped>
    .deleteUser {
        position: relative;
    }

    .title {
        text-align: center;
    }
    button{
        padding: 10px;
        margin-top: 50px;
        width: 100px;
        border-radius: 5px ;
    }
    .confirmDelete{
        color: white;
        background-color: red;
    }
     .delete{
        color: white;
        background-color: #0099CC;
    }
</style>
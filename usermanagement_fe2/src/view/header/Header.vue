<template>
    <div class="header">
        <div><img src="https://image.thanhnien.vn/1024/uploaded/duyphuc/2021_06_04/hinh4_1_eynn.jpeg" height="50px"
                width="50px"></div>
        <div class="right">
            <button @click="logoutAccout"><i class="fas fa-sign-in-alt"></i>&nbsp;&nbsp;Đăng xuất</button>
        </div>
    </div>
</template>

<script>
    export default{
        methods: {
           logoutAccout(){
            //Kiểu như là đăng xuất xong rồi thì mới cho nó về trang login, nếu không thực hiện được thì thông báo có lỗi 
            this.$store.dispatch('auth/logout')
            .then(
                ()=>this.$router.replace('/'),
                ()=>alert("Có lỗi")//cái này chắc nghĩ không xảy ra được đâu
            )
               
           }
        },
    }

</script>
<style scoped>
    .header {
        display: flex;
        justify-content: space-between;
        margin: auto 0;
        background-color: royalblue;
        align-items: center;
        height: 70px;
        padding: 10px;
    }

    div .right button {
        color: white;
        background-color: black;

        border-radius: 4px;
        padding: 10px;
    }
</style>
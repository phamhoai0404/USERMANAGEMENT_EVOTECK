<template>
    <div>
        <table>
            <tr class="title">
                <td>STT</td>
                <td>Họ tên</td>
                <td>Mô tả</td>
                <td>Ngày sinh</td>
                <td>Ngày tạo</td>
                <td>Địa chỉ</td>
                <td>Người tạo</td>
                <td>Ngày cập nhật</td>
                <td>Người cập nhật</td>
                <td>Tên Đơn vị</td>

            </tr>
            <tr class="content" v-for="(user, index) in this.$store.state.user.dataUser" :key="index">
                <td>{{index+1}}</td>
                <td>{{user.fullName}}</td>
                <td>{{user.userDescribe}}</td>
                <td>{{user.dateOfBirth}}</td>
                <td>{{user.dateCreated}}</td>
                <td>{{user.address}}</td>
                <td>{{user.maker}}</td>
                <td>{{user.updateDay}}</td>
                <td>{{user.updater}}</td>
                <td v-if="user.team !=null">{{user.team.teamName}}</td>
                <td v-if="user.team == null"></td>
            </tr>
        </table>
        <div></div>
    </div>
    

</template>
<script>

    import UserService from '@/service/user.service.js'
    export default {
        mounted() {
            UserService.getAllUser().then(
                response =>{
                    this.$store.state.user.dataUser = response.data;
                },error =>{
                    console.log(error.response);
                }
            )
        }
    }
</script>

<style scoped>
    table {
        font-family: 'Times New Roman', Times, serif;
        font-size: 15px;
        text-align: center;
        width: 95%;
        margin: 0 auto;

    }

    table,
    td,
    tr {
        border: 1px solid grey;
    }

    table .title td {
        font-size: 16px !important;
        font-weight: 700;
        padding: 15px ;
        background-color: #999999;
    }

    table .content td {
        padding: 5px;

    }

    table .content:hover {
        background-color: #EEEEEE;

    }
</style>
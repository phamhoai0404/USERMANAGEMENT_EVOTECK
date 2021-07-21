<template>
    <div>
        <div class="searchBasicMain">
            <SearchBasic />
        </div>
        <div class="center">
            <table>
                <tr class="addUser">
                    <td colspan="12">
                        <button @click="clickAdd()"><i class="fas fa-plus"></i>Thêm người dùng</button>
                        <BaseDialog :active.sync="this.$store.state.user.buttonShowAdd">
                            <AddUser />
                        </BaseDialog>
                    </td>
                </tr>
                <tr class="titleUser">
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
                    <td colspan="2">Các thao tác</td>
                </tr>
                <tr class="contentUser" v-for="(user, index) in this.$store.state.user.dataUser" :key="index">
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
                    <td>
                        <button class="repair" @click="clickRepair(user)"><i class="fas fa-hammer"></i>Sửa</button>

                    </td>
                    <td>
                        <button class="delete" @click="clickDelete(user)"><i class="fas fa-trash-alt"></i>Xóa</button>

                    </td>
                </tr>
            </table>
            <BaseDialog :active.sync="this.$store.state.user.buttonShowDelete">
                <DeleteUser />
            </BaseDialog>
            <BaseDialog :active.sync="this.$store.state.user.buttonShowRepair">
                <RepairUser />
            </BaseDialog>
        </div>
    </div>
</template>
<script>
    import SearchBasic from '@/view/search/SearchBasic.vue'
    import BaseDialog from '@/view/basic/BaseDialog.vue'
    import RepairUser from '@/view/repair/RepairUser.vue'
    import DeleteUser from '@/view/delete/DeleteUser.vue'
    import AddUser from '@/view/add/AddUser.vue'

    import AdminService from '@/service/admin.service.js'

    export default {
        components: {
            SearchBasic,
            BaseDialog,
            RepairUser,
            DeleteUser,
            AddUser
        },
        data() {
            return {
                isShowAdd: false,
                isShowDelete: false,
            }
        },
        beforeCreate() {
            AdminService.getAllUser().then(
                response => {
                    this.$store.state.user.dataUser = response.data;
                }, error => {
                    console.log(error.response);
                }
            )
        },
        methods: {
            clickRepair(user) {
                this.$store.state.user.transferDataRepair = user;
                this.$store.state.user.buttonShowRepair = true;
            },
            clickDelete(user) {
                this.$store.state.user.transfreDataDelete = user;
                this.$store.state.user.buttonShowDelete = true;
            },
            clickAdd(){
                this.$store.state.user.buttonShowAdd = true;
            }
        },
    }
</script>

<style scoped>
    table {
        font-family: 'Times New Roman', Times, serif;
        font-size: 15px;
        text-align: center;
        width: 95%;
        margin: 0px auto;
    }

    table,
    td,
    tr {
        border: 1px solid grey;
    }

    table .titleUser td {
        font-size: 16px !important;
        font-weight: 700;
        padding: 15px;
        background-color: #999999;
    }

    table .contentUser td {
        max-height: 10px !important;
        padding: 5px;

    }

    table .contentUser:hover {
        background-color: #EEEEEE;

    }

    table td .repair {
        color: #0099CC;

    }

    table td .repair:hover {
        border-color: #0099CC;

    }

    table td .delete {
        color: red !important;

    }

    table td .delete:hover {
        border-color: red;
    }

    table .addUser td {
        border: 1px solid white !important;
        text-align: right;
        /* padding: 5px; */
    }

    table .addUser td button {
        padding: 3px;
        color: yellowgreen;
    }

    table .addUser td button:hover {
        border-color: yellowgreen;
    }

</style>
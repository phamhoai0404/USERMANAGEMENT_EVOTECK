<template>
    <div>
        <table>
            <tr class="addTeam">
                <td colspan="11">
                    <button @click="addTeam()"><i class="fas fa-plus"></i>Thêm Đơn vị</button>
                    <BaseDialog :active.sync="this.$store.state.team.buttonShowAdd">
                        <AddTeam />
                    </BaseDialog>
                </td>
            </tr>
            <tr class="title">
                <td>STT</td>
                <td>Mã đơn vị</td>
                <td>Tên đơn vị</td>
                <td>Đơn vị cha</td>
                <td>Mô tả</td>
                <td>Ngày tạo</td>
                <td>Người tạo</td>
                <td>Ngày cập nhật</td>
                <td>Người cập nhật</td>
                <td colspan="2">Các thao tác</td>
            </tr>
            <tr class="content" v-for="(team, index) in this.$store.state.team.dataTeam" :key="index">
                <td>{{index + 1 }}</td>
                <td>{{team.teamId}}</td>
                <td>{{team.teamName}}</td>

                <td v-if="team.teamGroup != null">{{team.teamGroup.teamName}}</td>
                <td v-if="team.teamGroup == null"> </td>

                <td>{{team.teamDescribe}}</td>
                <td>{{team.teamDateCreated}}</td>
                <td>{{team.teamMaker}}</td>
                <td>{{team.teamUpdateDay}}</td>
                <td>{{team.teamUpdater}}</td>
                <td>
                    <button @click="repairTeam(team)" class="repair"><i class="fas fa-hammer"></i>Sửa</button>
                </td>
                <td>
                    <button @click="deleteTeam(team)" class="delete"><i class="fas fa-trash-alt"></i>Xóa</button>
                </td>
            </tr>
        </table>
        <BaseDialog :active.sync="this.$store.state.team.buttonShowRepair">
            <RepairTeam />
        </BaseDialog>
        <BaseDialog :active.sync="this.$store.state.team.buttonShowDelete">
            <DeleteTeam />
        </BaseDialog>

    </div>
</template>
<script>
    import BaseDialog from '@/view/basic/BaseDialog.vue'
    import RepairTeam from '@/view/repair/RepairTeam.vue'
    import DeleteTeam from '@/view/delete/DeleteTeam.vue'
    import AddTeam from '@/view/add/AddTeam.vue'

    import AdminService from '@/service/admin.service.js'

    export default {
        components: {
            BaseDialog,
            RepairTeam,
            DeleteTeam,
            AddTeam
        },
        data() {
            return {

            }
        },
        beforeCreate() {
            AdminService.getAllTeam().then(
                response => {
                    this.$store.state.team.dataTeam = response.data;
                }, error => {
                    console.log(error.response);
                }
            )
        },
        methods: {
            repairTeam(team) {
                this.$store.state.team.transferDataRepair = team;
                this.$store.state.team.buttonShowRepair = true;
            },
            deleteTeam(team) {
                this.$store.state.team.transfreDataDelete = team;
                this.$store.state.team.buttonShowDelete = true;
            },
            addTeam() {
                this.$store.state.team.buttonShowAdd = true;
            }
        },
    }
</script>

<style scoped>
    table {
        font-family: 'Times New Roman', Times, serif;
        text-align: center;
        width: 95%;
        margin: auto;

    }

    table,
    td,
    tr {
        border: 1px solid grey;
    }

    table .title td {
        font-size: 16px;
        font-weight: 700;
        padding: 15px;
        background-color: #999999;
    }

    table .content td {
        padding: 5px;
    }

    table .content:hover {
        background-color: #EEEEEE;
        border-color: red;
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
        border-color: red !important;
    }

    table .addTeam td {
        border: 1px solid white !important;
        text-align: right;
        padding: 10px;
    }

    table .addTeam td button {

        padding: 3px;
        color: yellowgreen;
    }

    table .addTeam td button:hover {
        border-color: yellowgreen;
    }
</style>
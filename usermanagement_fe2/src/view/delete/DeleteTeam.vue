<template>
    <div class="deleteTeam">
        <div class="confirm">
            <h4 class="title">Bạn có chắc chắn muốn xóa đơn vị này không " {{team.teamName}} " ???</h4>
            <div>
                <div class="row mb-3">
                    <div class="col-md-12 text-center">
                        <button @click="deleteTeam()" class="confirmDelete">Đồng ý</button>&nbsp;
                        <button @click="deleteDestroy()" class="delete">Hủy</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>

    import Team from '@/model/team.js'
    import AdminService from '@/service/admin.service.js'
    export default {
        data() {
            return {
                team: new Team()
            }
        },
        mounted() {
            this.team = this.$store.state.team.transfreDataDelete;
        },
        methods: {
            deleteDestroy() {
                this.$store.state.team.buttonShowDelete = false;
            },
            deleteTeam() {
                AdminService.deleteTeam(this.team).then(
                    () => {
                        this.loadData();
                    },
                    error => {
                        console.log(error);
                    }
                )
            },
            loadData() {
                AdminService.getAllTeam().then(
                    response => {
                        this.$store.state.team.dataTeam = response.data;
                        this.$store.state.team.buttonShowDelete = false;
                    }, error => {
                        console.log(error.response);
                    }
                )
            }

        },
    }
</script>

<style scoped>
    .deleteTeam {
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
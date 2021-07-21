<template>
    <form @submit.prevent = "">
        <div class="row mb-3">
            <div class="col-md-3"></div>
            <div class="col-md-6 title">Sửa đơn vị</div>
            <div class="col-md-3"></div>
        </div>
        <div class="row mb-3">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 text-right changeScreenSmall">
                        <label>Mã đơn vị <span>*</span></label>
                    </div>
                    <div class="col-md-7">
                        <input type="text" class="form-control" v-model="team.teamId">
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 text-right changeScreenSmall">
                        <label>Tên đơn vị <span>*</span></label>
                    </div>
                    <div class="col-md-7">
                        <input type="text" class="form-control" v-model="team.teamName">
                    </div>
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 text-right changeScreenSmall">
                        <label>Đơn vị cha</label>
                    </div>
                    <div class="col-md-7">
                        <b-form-select v-model="selectedTeam" class="selectNameTeam">
                            <b-form-select-option :value="null">Chọn</b-form-select-option>
                            <b-form-select-option :value="team.id" v-for="(team, index) in dataTeam" :key="index">
                                {{team.teamName}}
                            </b-form-select-option>
                        </b-form-select>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 text-right changeScreenSmall">
                        <label>Ngày tạo:</label>
                    </div>
                    <div class="col-md-7">
                        <input type="date" class="form-control" v-model="team.teamDateCreated">
                    </div>
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 text-right changeScreenSmall">
                        <label>Người tạo: </label>
                    </div>
                    <div class="col-md-7">
                        <input type="text" class="form-control" v-model="team.teamUpdater">
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 text-right changeScreenSmall">
                        <label>Ngày cập nhật: </label>
                    </div>
                    <div class="col-md-7">
                        <input type="date" class="form-control" v-model="team.teamUpdateDay">
                    </div>
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 text-right changeScreenSmall">
                        <label>Người cập nhật:</label>
                    </div>
                    <div class="col-md-7">
                        <input type="text" class="form-control" v-model="team.teamUpdater">
                    </div>
                </div>
            </div>

        </div>
        <div class="row mb-3">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-2 text-right changeScreenSmall">
                        <label>Mô tả: </label>
                    </div>
                    <div class="col-md-9">
                        <textarea type="text" class="form-control" v-model="team.teamDescribe" />
                    </div>
                </div>
            </div>

        </div>

        <div class="row mb-3">
            <div class="col-md-12 text-center">
                <button type="submit" @click="repairTeam()" class="repairTeam">Sửa</button>&nbsp;
                <button @click="deleteRepair()" class="delete">Hủy</button>
            </div>
        </div>
    </form>

</template>
<script>
    import Team from '@/model/team.js'
    import AdminService from '@/service/admin.service.js'
    import HandleTeam from '@/util/handleTeam.js'
    import ValidateTeam from '@/util/validateTeam.js'

    export default {
        data() {
            return {
                team: new Team(),
                selectedTeam: null,
                dataTeam: null
            }
        },
        mounted() {

            AdminService.getAllTeam().then(
                response => {
                    this.team = this.$store.state.team.transferDataRepair;

                    if (this.team.teamGroup == null) {
                        this.selectedTeam = null;
                    } else {
                        var intermediaryTeam = new Team();
                        intermediaryTeam = this.team.teamGroup;
                        this.selectedTeam = intermediaryTeam.id;
                    }
                    
                    var dataTeamAll = response.data;
                    this.dataTeam = HandleTeam.remove(dataTeamAll, this.team);
                }, error => {
                    console.log(error.response);
                }
            )
        },
        methods: {
            deleteRepair() {
                this.loadData();
            },
            repairTeam() {
                let temporaryTeam = new Team();
                if (this.selectedTeam != null) {
                    temporaryTeam.id = this.selectedTeam;
                } else {
                    temporaryTeam = null;
                }
                this.team.teamGroup = temporaryTeam;

                if (ValidateTeam.checkTeam(this.team)) {
                    AdminService.editTeam(this.team).then(
                        () => {
                            this.loadData();
                        },
                        error => {
                            console.log(error);
                        }
                    )
                }
            },
            loadData() {
                AdminService.getAllTeam().then(
                    response => {
                        this.$store.state.team.dataTeam = response.data;
                        this.$store.state.team.buttonShowRepair = false;
                    }, error => {
                        console.log(error.response);
                    }
                )
            }
        },

    }
</script>

<style scoped>
    form {
        width: 100%;
        margin: 0 auto;
        font-family: 'Times New Roman', Times, serif;
    }

    .title {
        font-weight: 800;
        font-size: 20px;
        text-align: center;
    }

    form button {
        border-radius: 4px;
        padding: 10px;
        width: 100px;
    }

    form .repairTeam {
        color: white;
        background-color: #0099CC;
    }

    form .delete {
        color: white;
        background-color: red;
    }
    form span{
        color: red;
    }

    /* Extra small devices (portrait phones, less than 576px) */
    @media (max-width: 575.98px) {
        form .changeScreenSmall {
            text-align: left !important;

            font-weight: 700;
        }

        form input {
            width: 90%;
        }

    }

    /* Small devices (landscape phones, 576px and up)*/
    @media (min-width: 576px) and (max-width: 767.98px) {
        form .changeScreenSmall {
            text-align: left !important;
            font-weight: 700;
        }

        form input {
            width: 90%;
        }

    }
</style>
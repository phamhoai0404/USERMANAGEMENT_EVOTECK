<template>
    <form @submit.prevent="">
        <div class="row mb-3">
            <div class="col-md-3"></div>
            <div class="col-md-6 title">Thêm đơn vị</div>
            <div class="col-md-3"></div>
        </div>
        <div class="row mb-3">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 text-right changeScreenSmall">
                        <label>Mã đơn vị<span>*</span>: </label>
                    </div>
                    <div class="col-md-7">
                        <input type="text" class="form-control" v-model="team.teamId">
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 text-right changeScreenSmall">
                        <label>Tên đơn vị<span>*</span>:</label>
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
                        <label>Đơn vị cha:</label>
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
                        <input type="date" class="form-control"  v-model="team.teamDateCreated">
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
                        <input type="text" class="form-control" v-model="team.teamMaker">
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
                        <textarea type="text" class="form-control"  v-model="team.teamDescribe" />
                    </div>
                </div>
            </div>

        </div>

        <div class="row mb-3">
            <div class="col-md-12 text-center">
                <button @click="handleAddTeam()" class="addTeam">Thêm</button>&nbsp;
                <button @click="deleteAdd()" class="delete">Hủy</button>
            </div>
        </div>

    </form>

</template>
<script>
    import AdminService from '@/service/admin.service.js'
    import ValidateTeam from '@/util/validateTeam.js'
    import Team from '@/model/team.js'

    export default {
        data() {
            return {
                team: new Team(),
                dataTeam: null,
                selectedTeam: null
            }
        },
        mounted() {
            AdminService.getAllTeam().then(
                response => {
                    this.dataTeam = response.data;
                }, error => {
                    console.log(error.response);
                }
            )
        },
        methods: {
            deleteAdd() {
                this.$store.state.team.buttonShowAdd = false;
            },

            handleAddTeam() {
                var intermediaryTeam = new Team();
                if (this.selectedTeam != null) {
                    intermediaryTeam.id = this.selectedTeam;
                } else {
                    intermediaryTeam = null;
                }

                this.team.teamGroup = intermediaryTeam;

                if (ValidateTeam.checkTeam(this.team)) {
                    AdminService.addTeam(this.team).then(
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
                        this.$store.state.team.buttonShowAdd = false;
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
        font-size: 17px !important;
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

    form .addTeam {
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
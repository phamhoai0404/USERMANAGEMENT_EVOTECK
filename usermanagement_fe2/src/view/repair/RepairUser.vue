<template>
    <form @submit.prevent>
        <div class="row mb-3">
            <div class="col-md-3"></div>
            <div class="col-md-6 title">Sửa người dùng</div>
            <div class="col-md-3"></div>
        </div>
        <div class="row mb-3">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 text-right changeScreenSmall">
                        <label>Họ và tên <span>*</span>:</label>
                    </div>
                    <div class="col-md-7">
                        <input type="text" class="form-control" v-model="user.fullName">
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 text-right changeScreenSmall">
                        <label>Ngày sinh <span>*</span>:</label>
                    </div>
                    <div class="col-md-7">
                        <input type="date" class="form-control" v-model="user.dateOfBirth">
                    </div>
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 text-right changeScreenSmall">
                        <label>Ngày tạo:</label>
                    </div>
                    <div class="col-md-7">
                        <input type="date" class="form-control" v-model="user.dateCreated">
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 text-right changeScreenSmall">
                        <label>Địa chỉ <span>*</span>:</label>
                    </div>
                    <div class="col-md-7">
                        <input type="text" class="form-control" v-model="user.address">
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
                        <input type="text" class="form-control" v-model="user.maker">
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 text-right changeScreenSmall">
                        <label>Người cập nhật: </label>
                    </div>
                    <div class="col-md-7">
                        <input type="text" class="form-control" v-model="user.updater">
                    </div>
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 text-right changeScreenSmall">
                        <label>Ngày cập nhật:</label>
                    </div>
                    <div class="col-md-7">
                        <input type="date" class="form-control" v-model="user.updateDay">
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 text-right changeScreenSmall">
                        <label>Đơn vị: </label>
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
        </div>
        <div class="row mb-3">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-2 text-right changeScreenSmall">
                        <label>Mô tả: </label>
                    </div>
                    <div class="col-md-9">
                        <textarea type="text" class="form-control" v-model="user.userDescribe" />
                    </div>
                </div>
            </div>

        </div>

        <div class="row mb-3">
            <div class="col-md-12 text-center">
                <button type="submit" @click="handleRepairUser()" class="repairUser">Sửa</button>&nbsp;
                <button type="button" @click="deleteRepair()" class="delete">Hủy</button>
            </div>
        </div>
    </form>

</template>
<script>

    import User from '@/model/user.js'
    import Team from '@/model/team.js'
    import AdminService from '@/service/admin.service.js'
    import ValidateUser from '@/util/validateUser.js'

    export default {
        data() {
            return {
                selectedTeam: null,
                user: new User(),
                team: new Team(),
                dataTeam: null,
            }
        },
        mounted() {
            AdminService.getAllTeam().then(
                response => {
                    this.dataTeam = response.data;
                    this.user = this.$store.state.user.transferDataRepair;
                    this.team = this.user.team;

                    if (this.team == null) {
                        this.selectedTeam = null ;
                    } else {
                        this.selectedTeam = this.team.id;
                    }

                }, error => {
                    console.log(error.response);
                }
            )
        },
        methods: {
            deleteRepair() {
                this.loadingData();
            },
            handleRepairUser() {

                let temporaryTeam = new Team();
                if (this.selectedTeam != null) {
                    temporaryTeam.id = this.selectedTeam;
                }else{
                    temporaryTeam = null;
                }
                this.user.team = temporaryTeam;

                if(ValidateUser.checkUser(this.user)){
                    AdminService.editUser(this.user).then(
                    () => {
                        this.loadingData();
                    }, error => {
                        console.log(error);
                    }
                )
                }
               
            },
            loadingData(){
                AdminService.getAllUser().then(
                    response => {
                        this.$store.state.user.dataUser = response.data;
                        this.$store.state.user.buttonShowRepair = false;
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

    form .repairUser {
        color: white;
        background-color: #0099CC;
    }
    form span{
        color: red;
    }
    form .delete {
        color: white;
        background-color: red;
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

        form .selectNameTeam {
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

        form .selectNameTeam {
            width: 90%;
        }
    }
</style>
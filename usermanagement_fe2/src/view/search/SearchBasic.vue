<template>
        <form action="" @submit.prevent ="" class="container ">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-3 search">
                        <div class="row">
                            <div class="col-md-12">
                                <label>Tìm kiếm: </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <input type="text" placeholder="Họ tên, mô tả, địa chỉ, . . ." class="form-control" v-model="user.searchBasic">
                            </div>
                        </div>
                       
                    </div>
                    <div class="col-md-2 date" >
                        <div class="row">
                            <div class="col-md-12">
                                <label>Ngày sinh từ: </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <input type="date" class="form-control" v-model="user.fromDOB">
                            </div>
                        </div>
                        
                    </div>
                    <div class="col-md-2 date">
                        <div class="row">
                            <div class="col-md-12">
                                <label>Đến: </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <input type="date" class="form-control" v-model="user.toDOB">
                            </div>
                        </div>
                       
                    </div>
                    <div class="col-md-3 reds">
                        <div class="row">
                            <div class="col-md-12">
                                <label>Tên đơn vị: </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 selectNameTeam">
                                <b-form-select v-model="selectedTeam" class="sss">
                                    <b-form-select-option :value="null">Chọn</b-form-select-option>
                                    <b-form-select-option :value="team.teamName" v-for="(team, index) in this.$store.state.team.dataTeam" :key="index">
                                        {{team.teamName}}
                                    </b-form-select-option>
                                </b-form-select>
                            </div>
                        </div>
                        
                    </div>
                    <div class="col-md-2 buttonSearch">
                        <button @click="searchUserss()"><i class="fas fa-search"></i>Tìm kiếm </button>
                        <div class="reload" @click="reloadPage()"><i class="fas fa-sync-alt"></i></div>
                    </div>
                </div>
            </div>
        </form>
</template>
<script>
     import UserService from '@/service/user.service.js'
     import Search from '@/model/search.js'
    export default{
        data() {
            return {
                selectedTeam: null,
                user : new Search()
            }
        },
        mounted() {
            //Nay dung UserService cũng được, hay dùng AdminService cũng được
            UserService.getAllTeam().then(
                response => {
                    this.$store.state.team.dataTeam = response.data;
                }, error => {
                    console.log(error.response);
                }
            )
           
        },
        methods: {
            searchUserss(){
                this.user.teamName = this.selectedTeam;

                //Cái phần này phải có 
                if(this.user.fromDOB =="")
                    this.user.fromDOB = null;
                if(this.user.toDOB =="")
                    this.user.toDOB = null;
                if(this.user.searchBasic!=null) 
                    this.user.searchBasic = this.user.searchBasic.trim();

                UserService.getSearchUser(this.user).then(     
                    response => {
                        this.$store.state.user.dataUser = response.data;
                    },
                    error => {
                        console.log(error.response);
                    }
                )
            },
            reloadPage(){
                UserService.getAllUser().then(
                    response =>{
                        this.$store.state.user.dataUser = response.data;
                    },error =>{
                        console.log(error.response);
                    }
                ),

                this.user = new Search();
                this.selectedTeam=null;
            },
           
           
        },
    }
</script>





<style scoped>
.main{
    width: 100%;
}
    
    form {
        width: 100%;
        border: 4px solid black;
        padding: 10px 0px;
        border-radius: 10px;
    }
    form .date input{
        width: 100%;
        margin: 0;
         padding: 0;
         padding-left: 10px; 
    }
    form .search input{
        width: 100%;
    }
    form .selectNameTeam{
        width: 90%;
    }
    form .selectNameTeam .sss:hover{
        border: 2px solid #CEF6F5;
    }
    form input:hover{
        border: 2px solid #CEF6F5;
    }
    form .buttonSearch{
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        align-items: center;
       
    }
    form .buttonSearch button{
        width: 100px;
        padding: 10px 5px;
        border-radius: 4px;
        color: white;
        background-color: #00BFFF;
        border: 1px solid blue;
        
    }
    form .buttonSearch button:hover{
        background-color: #0080FF;
        /* color: w; */
    }
    form .reload{
        color: red;
    }
</style>
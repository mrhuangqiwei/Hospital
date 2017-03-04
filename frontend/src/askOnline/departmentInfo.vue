<style lang="scss" scoped> 
    #department{
        height: 100%;
        background: white;
        li{
            background: white;
            height: 4rem;
            line-height: 4rem;
            text-indent: 2rem;
            border-bottom: 1px solid #d3d3d3;
        }
        .dialog-content{
            section{
                padding: 0 1rem 1rem 1rem;
            }
            p{
                text-align: left;
                text-indent: 2rem;
            }
            p.title{
                text-indent: 0;
                line-height:5rem;
                text-align: center;
            }
            p.department_floor{
                margin-top:1rem;
            }
        }
    }
</style>

<template>
    <div id='department'>
        <p class='TITLE'>科室列表</p>
        <loader v-if='loading'/>
        <ul v-else>
            <li v-for='item in departmentInfo' @click="showDetail(item)">
                <span>{{item.ksmc}}</span>
            </li>
        </ul>
        <!--通用-->
        <my-dialog :show='showDialog' :cbClose='closeDialog'>
            <p slot="title" class='title'>{{info.ksmc}}</p>
            <div slot='content' class='content'>
                <section>
                    <p class='department_introduce'>
                        介绍:{{info.ksjj}}
                    </p>
                    <p class='department_floor'>
                        地址:{{info.ksms}}
                    </p>
                </section>
            </div>
            <div slot='button' class='button'>
                <button @click='closeDialog'>确定</button>
            </div>
        </my-dialog>
    </div>
</template>

<script>
    import chipItem from '../component/chipItem';
    import api from '../backend/api';
    import { Toast } from 'mint-ui';
    import routerManager from '../routerManager';
 
    export default { 
        data: function (){
            return {
                loading:true,
                departmentInfo:[],
                info:{},
                showDialog:false
            }
        },
        methods:{
            showDialogFunc(){
                this.showDialog = true;
            },
            closeDialog(){
                this.showDialog = false;
            },
            getDepartmentList(){
                this.departmentInfo = this.$store.getters.departmentInfo;
                this.loading = false;
            },
            showDetail(item){
                this.info = item;
                this.showDialogFunc();
            }
        },
        mounted(){
            if(this.$store.getters.departmentInfo && this.$store.getters.departmentInfo.length == 0){
                api.getDepartmentInfo().then((data)=>{
                    var info = JSON.parse(data);
                    this.$store.commit('SET_DEPARTMENT_INFO',info);
                    this.getDepartmentList();
                })
            } else{
                this.getDepartmentList();
            }
        }
    }
</script>
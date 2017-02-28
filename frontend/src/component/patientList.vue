<style lang="scss" scoped>
    div.patientList{    
        height: 100%;
        font-size: 1.8rem;
        i.avater{
            position: absolute;
            top: 0.9rem;
            left: 2rem;
            display:inline-block;
            height:2rem;
            width:2rem;
            background-size:cover !important; 
            background: url('../img/avater.png') no-repeat center center;
        }
        span.name{
            text-align: center;
            flex:2;
        }
        span.cell{
            flex:3;
        }
        li{
            position: relative;
            text-indent: 1rem;
            display:flex;
            background: white;
            min-height:4rem;
            line-height: 4rem;    
            border-bottom: 1px solid #dcd8d8;     
        }
        li.darkBlue{
            color:#295286;
        }
    }
</style>

<template>
    <div class="patientList">
        <p class='TITLE'>常用就诊人</p>
        <loader v-if='loading'/>
        <ul v-else>
            <li><i></i><span class='name'>姓名</span><span class='cell'>电话号码</span></li>
            <li v-for='item in commonPatient' @click='outPutPatientList(item)' class='darkBlue'>
                <i class="avater"></i><span class='name'>{{item.brxm}}</span><span class='cell'>{{item.brdh}}</span>
            </li>
        </ul>
    </div>
  
</template>

<script>
    import api from '../backend/api';
    import loader from './loader';
    import routerManager from '../routerManager';
    export default {
        props:['doSomething'],
        data: function () {
            return {
               loading:true,
               commonPatient:[]
            }
        },
        components:{
            loader
        },
        methods:{
            outPutPatientList(item){
                if(typeof this.doSomething == 'function'){
                    this.doSomething(item);
                }
            },
            setCommonPatient(item){
                this.commonPatient = item;
                this.$nextTick(function(item){});
                this.loading = false;
            },
            goBack(){
                routerManager.goBack();
            }
        },
        mounted(){
            var getters = this.$store.getters;
            if(getters.commonPatient.length!=0){
                this.setCommonPatient(getters.commonPatient);
            } else{
//                api.getCommonPatient('owEWzwQKO7G_uy4C0X_Wn2boPVI4').then((data)=>{
                api.getCommonPatient(getters.weChatInfo.openid).then((data)=>{
                    this.setCommonPatient( JSON.parse(data) );
                    this.$store.commit('SET_COMMON_PATIENT',JSON.parse(data));
                });
            }
        }
    }
</script>
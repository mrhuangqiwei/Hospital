<style lang="scss" scoped>
    div.patientDetailInfo{    
        height: 100%;
  
        span.name{
            text-align: center;
            flex:2;
        }
        span.cell{
            flex:3;
            text-align: center;
        }
        li{
            text-indent: 1rem;
            display:flex;
            background: white;
            height:4rem;
            line-height: 4rem;    
            border-bottom: 1px solid #838383;     
        }
        li:not(:first-child){
            color:#295286;
        }
        li:last-child{
            border: none;
        }

    }
</style>

<template>
    <div class="patientDetailInfo">
        <p class='TITLE'>就诊信息</p>
        <loader v-if='loading'/>
        <ul v-else>
            <li><span class='name'>就诊人</span><span class='cell'>就诊日期</span></li>
            <li v-for='item in patientDetailInfo' @click='doSomething(item)' v-if='item.ylklxbm==khStyle'>
                <span class='name'>{{item.brxm}}</span><span class='cell'>{{item.ghxh.substr(0,8)}}</span>
            </li>
            <li>
                <button @click='goBack' class='GOBACK'>返 回</button>
            </li>
         </ul>
    </div>
</template>

<script>
    import api from '../backend/api';
    import loader from './loader';
    import routerManager from '../routerManager';
    export default {
        props:['doSomething','sfzh','ylkh','khStyle'],
        data: function () {
            return {
               loading:true,
               patientDetailInfo:[]
            }
        },
        components:{
            loader
        },
        methods:{
            outPutPatientListDetailInfo(item){
                if(typeof this.doSomething == 'function'){
                    this.doSomething(item);
                }
            },
            setPatientDetailInfo(item){
                this.patientDetailInfo = item;
                this.$nextTick(function(item){});
                this.loading = false;
            },
            goBack(){
                routerManager.goBack();
            }

        },
        mounted(){
            var getters = this.$store.getters;
            if(getters.patientDetailInfo.length!=0){
                this.setPatientDetailInfo(getters.patientDetailInfo);
            } else{
                api.getPatientDetailInfo(this.sfzh,this.ylkh).then((data)=>{
                    this.setPatientDetailInfo( JSON.parse(data) );
                    this.$store.commit('SET_PATIENT_DETAILINFO',JSON.parse(data));
                });
            }
            
        }
    }
</script>
<style lang="scss" scoped>
    #departmentNO{
        li{
            height: 4rem;
            line-height: 4rem;
            font-size: 1.7rem;
            text-indent: 3rem;
            border-bottom: 1px solid #838383;
            background-color: white;
        }
        span.arrow_icon{
            background: url('../img/arrow.png') no-repeat center center;
            height: 2rem;
            width: 2rem;
            background-size:cover !important;
            display: inline-block;
            float: right;
            top: 1rem;
            right: 1rem;
            position: relative;
        }
    }
</style>

<template>
    <div id='departmentNO'>
        <ul>
            <li>科室名称</li>
            <li v-for='item in departmentInfo' :ksbm='item.ksbm' @click='getDoctorSchedule(item.ksbm)'>
                {{item.ksmc}}
                <span class="arrow_icon"></span>
            </li>
        </ul>
    </div>
</template>

<script>
    import chipItem from '../component/chipItem';
    import api from '../backend/api';
    import routerManager from '../routerManager';
    export default {
        data: function () {
            return {
                departmentInfo:[]
            }
        },
        components:{
            chipItem
        },
        methods:{
            getDoctorSchedule(ksbm){
               api.getDoctorSchedule(ksbm).then((data)=>{
                    this.$store.commit('SET_DOCTORS_SCHEDULE',JSON.parse(data));
                    routerManager.routerTo('singel/doctorsSchedule');
               })
            }
        },
        mounted(){
           this.departmentInfo = this.$store.getters.departmentNO;
        }
    }   
</script>
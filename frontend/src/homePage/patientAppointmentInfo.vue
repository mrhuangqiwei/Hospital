<style lang="scss" scoped>
    #patientAppointmentInfo{
        height: 100%;
        background: white;  
        font-size:1.8rem;
        p.empty{
            margin-top:1rem;
            text-align:center;
        }
        ul li{
            border-bottom:1px solid #838383;
            margin-bottom: 1px;
            div{
                display:flex;
                background: white;
                box-sizing: border-box;
                padding:1rem;
                border-bottom:1px solid #dcd8d8;
                span{
                    flex:1;
                }
            }
        }
        .height100{
            height: 100%;
        }
    }
</style>

<template>
    <div id='patientAppointmentInfo'>
        <div v-if='step=="ONE"'>
            <patientList :doSomething='getAppointInfo'/>
        </div>  
        <ul v-else class='height100'>
            <p class='TITLE'>已约查询</p>
            <li v-for='info in infos'>
                <div>
                    <span><i>姓名:</i><i class='darkBlue'>{{info.brxm}}</i></span>
                    <span><i>科室:</i><i class='darkBlue'>{{info.ksmc}}</i></span>
                </div>
                <div>
                    <span><i>预约医生:</i><i class='darkBlue'>{{info.czyxm}}</i></span>
                    <span><i>地址:</i><i class='darkBlue'>{{info.mzsbdd}}</i></span>
                </div>
                <div>
                    <span><i>挂号序号:</i><i class='darkBlue'>{{info.ghxh}} (发票领取凭证)</i></span>
                </div>
                  <div>
                    <span><i>预约挂号id:</i><i class='darkBlue'>{{info.yyghid}}</i></span>
                </div>
                <div>
                    <span><i>预约日期:</i><i class='darkBlue'>{{getDate(info)}}</i></span>
                </div>
                <div>
                    <span><i>手机号:</i><i class='darkBlue'>{{info.sj}}</i></span>
                </div>
            </li>
            <p v-if='infos.length==0' class="empty">暂无数据!</li>
        </ul>
    </div>
</template>

<script>
    import api from '../backend/api';
    export default {
        data: function () {
            return {
                infos:[],
                step:'ONE'
            }
        },
        components:{
        },
        methods:{
            getDate(item){
                if(!item.ghrq){
                    return '-';
                }
                var date = new Date(+item.ghrq);
                var day = date.getDay();
                var month = date.getMonth()+1;
                var year = date.getFullYear();

                return `${year}-${month > 9 ? month:('0'+month)}-${day>9?day:('0'+day)}`;
            },

            getAppointInfo(item){
                api.gethasAppointedList('513427196907132818',item.ylkh).then((data)=>{
                    //挂号查询
                    this.infos = JSON.parse(data);
                    this.step = 'TWO';
                })
            }
        },
        mounted(){
            // this.infos = this.$store.getters.patientAppointmentInfo;
        }
    }   
</script>
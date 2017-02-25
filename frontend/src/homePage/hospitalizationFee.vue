<style lang="scss" scoped>
    #hospitalizationFee{
        background-color: white;
        box-sizing: border-box;
        font-size:1.7rem;
        height: 100%;
        div.height100{
            height:100%;
        }
        .flex1{
            flex: 1;
        }
        li{
            min-height:3rem;
            display:flex;
            border-bottom:1px solid #838383;
            line-height: 3rem;
            text-indent: 1rem;
            background-color: white;
            span{
                flex:2;
            }
        }
        li.listHeader{
            span{
                display:inline-block;
            }
            i{
                text-indent: 0;
                text-align: center;
            }
            i.item{
                width: 25%;
            }
            i.gg,i.sl{
                width:17%;
            }
            i.dj,i.je{
                width: 20%;
            }
        }
        span.gg{
            text-align: center;
        }
        div.header{
            div{
                min-height:3rem;
                width:100%;
                display:flex;
                span{
                    flex:2;
                }
                span:first-child{
                    text-indent: 1rem;
                }
            }
        }
    }
</style>

<template>
    <div id='hospitalizationFee'>
        <div v-if='step=="ONE"' class='height100'>
            <patientList :doSomething='getPatient'/>
        </div>  
        <div v-else-if='step=="TWO"' class='height100'>
            <patientDetailInfo :doSomething='getHospitalizationFee' :sfzh='sfzh' :ylkh='ylkh' :khStyle='02'/>
        </div>
        <div v-else-if='step=="THREE"'>
            <p class="TITLE">住院费清单</p>
            <div class="header">
                <div>
                    <span><i>住院号:</i><i class='darkBlue'>{{fee.zyh}}</i></span>
                    <span><i>姓名:</i><i class='darkBlue'>{{fee.brxm}}</i></span>
                </div>
                <div>
                    <span><i>科室:</i><i class='darkBlue'>{{fee.ksmc}}</i></span>
                </div>
                <div>
                    <span><i>入院日期:</i><i class='darkBlue'>{{fee.ryrq.substr(0,10)}}</i></span>
                    <span><i>出院日期:</i><i class='darkBlue'>{{fee.cyrq.substr(0,10)}}</i></span>
                </div>
                <div>
                     <span>
                         <i>费用合计:</i><i class='gold'>{{(+fee.fyje).toFixed(2)}}</i>
                     </span>
                     <span>
                         <i>预交费用:</i><i class='gold'>{{(+fee.yjje).toFixed(2)}}</i>
                     </span>
                </div>
                <div>
                    <span><i>费别:</i><i class='darkBlue'>{{fee.fbmc}}</i></span>
                    <span><i>家庭住址:</i><i class='darkBlue'>{{fee.jtzz}}</i></span>
                </div>
                <ul>
                    <li class='listHeader'>
                        <i class='item'>项目名称</i>
                        <i class='gg'>规格</i>
                        <i class='sl'>数量</i>
                        <i class='dj'>单价</i>
                        <i class='je'>金额</i>
                    </li>
                    <li v-for='item in fee.userfymx'>
                        <span class='item darkBlue'>{{item.ypmc}}</span>
                        <span class='gg  darkBlue'>{{item.fygg}}</span>
                        <span class='sl  darkBlue'>{{item.fysl}}</span>
                        <span class='dj  darkBlue'>{{(+item.fydj).toFixed(2)}}</span>
                        <span class='je gold'>{{(+item.fyje).toFixed(2)}}</span>
                    </li>
                </ul>
            </div>
        </div>
      </div>
</template>

<script>
    import chipItem from '../component/chipItem';
    import api from '../backend/api';
    export default {
        data: function () {
            return {
                step:'ONE',
                patientInfo:{},
                commonPatient:[],
                fee:{},
            }
        },
        components:{
            chipItem
        },
        methods:{
            getPatient(item){
                this.sfzh = item.sfzh;
                this.ylkh = item.ylkh;
                this.$nextTick(function(item){});
                this.step = 'TWO';
            },
        
            getHospitalizationFee(item){
                // if(item.ylklxbm=='02'){
                api.getHospitalizationFee('2016003850').then((data)=>{
                    this.fee = JSON.parse(data);
                    this.$nextTick(function(item){
                    })
                    this.step = 'THREE';
                })
                // }
            }
        },
        mounted(){
             this.commonPatient = this.$store.getters.commonPatient;
            // 医疗明细        
            // this.hospitalizationFee = this.$store.getters.hospitalizationFee;
        }
    }   
</script>
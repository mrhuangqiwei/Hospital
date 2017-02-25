<style lang="scss" scoped>
    #shouldPay{
        height: 100%;
        background:white;
        .flex2{
            flex:2;
        }
        button.doPay{
            background: #58b5af;
            color: white;
            width: 5rem;
            margin: 0.5rem 2rem 0 0;
            height: 3rem;
        }
        ul{
            box-sizing: border-box;
        }
        li{
            text-indent: 1rem;
            background: white;
            display:flex;
            min-height:4rem;
            line-height: 4rem;    
            border-bottom: 1px solid #838383;        
            span{
                flex:1;
            }
        }
        li:last-child{
            border: none;
        }
        i{
            margin-right: 1rem;
        }
        .half{
            background: #58b5af;
            color: white;
            height: 3rem;
            width: 12rem;
        }
    }
</style>

<template>
    <div id='shouldPay'>
        <div v-if='step=="ONE"'>
            <patientList :doSomething='getPatient'/>
        </div>  
        <div v-else-if='step=="TWO"'>
            <patientDetailInfo :doSomething='getShouldPay' :sfzh='sfzh' :ylkh='ylkh' :khStyle='01'/>
        </div>
        <ul v-else-if='step=="THREE"' class='stepThree'>
            <p class='TITLE'>门诊费用应缴清单</p>
            <li>
                <span><i>姓名:</i><i class='darkBlue'>{{total.patName}}</i></span>
                <span><i>门诊医生:</i><i class='darkBlue'>{{total.docName}}</i></span>
            </li>
            <li>
                <span><i>总费用:</i><i class='gold'>{{total.pay}}</i></span>
            </li>
            <li><span class='flex2'>项目名称</span><span>数量</span><span>单价</span><span>金额</span></li>
            <li v-for='item in paylist'>
                <span class='flex2 darkBlue'>{{item.mxfyxmmc}}</span>
                <span class='darkBlue'>{{item.fysl}}</span>
                <span class='darkBlue'>{{(+item.fydj).toFixed(2)}}</span>
                <span class='gold'>{{(+item.fyje).toFixed(2)}}</span>
            </li>
            <li>
                <button class='half' @click='goBack'>返回</button>
                <button class='half' @click='doPay'>付款</button>
            </li>
        </ul>
    </div>
</template>

<script>
    import api from '../backend/api';
    import patientList from '../component/patientList';
    import patientDetailInfo from '../component/patientDetailInfo';
    import routerManager from '../routerManager';

    export default {
        data: function () {
            return {
                step:'ONE',
                patient:{},
                detailInfo:{},
                paylist:{},
                total:{
                    pay:0
                }
            }
        },

        methods:{

             getPatient(item){
                this.sfzh = item.sfzh;
                this.ylkh = item.ylkh;
                this.$nextTick(function(item){});
                this.step = 'TWO';
            },

    
            getShouldPay(item){
                api.getShouldPay('20170216004865').then((data)=>{
                    this.paylist = JSON.parse(data);
                    if(this.paylist.length>0){
                        this.total.patName = this.paylist[0].brxm;
                        this.total.docName = this.paylist[0].mzysxm;
                        this.total.pay = (+this.paylist[0].fyhj).toFixed(2);
                    }
                    this.step = 'THREE';
                })
            },
            goBack(){
                routerManager.goBack();
            },
            doPay(){

            }
        },

        mounted(){
        }
    }   
</script>

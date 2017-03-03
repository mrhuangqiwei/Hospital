<style lang="scss" scoped>
    #doctorsSchedule{
        font-size:1.5rem;
        height: 100%;
        background: white;
        .flex1{
            flex:1;
        }
        .flex2{
            flex:2;
        }
        .flex4{
            flex:4;
        }
        span.icon{
            width: 6rem;
        }
        .TITLE{
            font-size: 2rem;
        }
        span.photo{
            position: relative;
            top: 1.5rem;
            left: 1rem;
            border-radius: 4px;
            height: 4rem; 
            width: 4rem;
            display: inline-block;
            background-size: contain !important;
            background: url('../img/doc.png') no-repeat center center;
        }
        span.detailBtn{
            width:6rem;
        }
        span.docDetail{
            width: calc(100% - 12rem);
        }
        li{
            display:flex;
            min-height: 7rem;
            border-bottom: 1px solid #dcd8d8;
        }
        .dot{
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .btn{
            height: 2rem;
            border: 1px solid #31B6AA;
            width: 4rem;
            display: inline-block;
            text-align: center;
            border-radius: 4px;
            line-height: 2rem;
            background: #31B6AA;
            position: relative;
            top: 2rem;
            left: 0.5rem;
            color: white;
        }
        .doctorList{
            .name{
                height: 3rem;
                line-height: 3rem;
                font-size: 2rem;
            }
        }
        div.header{
            padding: 1rem;
            p{
                font-size:2rem;
            }
            .name{
                height: 3rem;
                line-height: 3rem;
                font-size: 2.5rem;
                margin-bottom: 5px;
            }
        }
        div.middleBtn{
            display: flex;
            padding: 0 3rem;
            span{
                text-align:center;
                flex:1;
                height: 4rem;
                line-height: 4rem;
                font-size: 1.8rem;
            }
            span.switchBtn{
                color:#27A798;
                border-bottom:2px solid #27A798;
            }
        }
        div.body{
            border-top: 1px solid #838383;
            li{
                background:white;
                flex-direction: column;
                div{
                    display: flex;
                    height: 6rem;
                    line-height: 6rem;
                    border-bottom:1px solid #dcd8d8;
                    padding: 1rem;
                    span.double{
                        line-height: 3rem;
                    }
                }
                span{
                    font-size: 1.7rem;
                    flex:1;
                }
            }

            .introduce{
                padding: 1rem;
                height: 100%;
                text-indent: 2rem;
                font-size: 1.7rem;
            }
        }
        ul.patientList li{
            min-height:  4rem;
            line-height: 4rem;
            text-align: center;
            font-size: 1.8rem;
            padding-left: 3rem;
        }
        div.showAppointInfo{
            li{
                min-height: 4rem;
                line-height: 4rem;
                padding-left: 2rem;
                font-size: 1.7rem;
                padding-right: 2rem;
                span{
                    flex:1;
                }
                span:first-child{
                    text-align: left;
                }
                span:last-child{
                    text-align: right;
                }
            }
        }
    }
</style>

<template>
    <div id='doctorsSchedule'>
        <ul class='doctorList' v-if='step=="ONE"'>
            <p class='TITLE'>医生列表</p>
            <li v-for='item in docIntroduce'>
                <span class="icon">
                    <span class='photo'></span>
                </span>
                <span class='docDetail'>
                    <p class='name'>{{item.czyxm}}</p>
                    <p class='fontGray'>职称:{{item.zcmc}}</p>
                    <p class='fontGray dot'>介绍:{{item.czyjj.substring(0,22)}}</p>
                </span>
                <span class='detailBtn'><i class='btn' @click='lookDetail(item)'>详情</i></span>
            </li>
            <p v-if='docIntroduce.length==0' class='center'>暂无数据！</p>
        </ul>
        <div v-else-if='step=="TWO"'>
            <div class='header'>
                <p class='name'>{{docDetail.czyxm}}</p>
                <p class='fontGray'>职称:{{docDetail.zcmc}}</p>
                <p class='fontGray'>科室:{{docDetail.ksmc}}</p>
            </div>
            <div class='middleBtn'>
                <span :class='[{switchBtn : switchSide}]' @click='lookSchedule'>门诊排班</span>
                <span :class='[{switchBtn : !switchSide}]' @click='lookIntroduce'>医生简介</span>
            </div>
            <div class='body'>
                <ul v-if='switchSide' >
                    <li v-for='item in scheduleDetail'>
                        <div v-if='item.am' @click='showDetailDig(item.am)'>
                            <span class='double'>
                                <p>{{item.am.yzrq.substring(0,10)}}</p>
                                <i>{{item.am.xq}}</i>
                                <i>上午</i>
                            </span>
                            <span><i class='gold'>¥{{item.am.ghfy}}</i></span>
                            <span><i class='red'>余{{item.am.xhzs}}</i></span>
                        </div>
                        <div v-if='item.pm' @click='showDetailDig(item.pm)'>
                            <span class='double'>
                                <p>{{item.pm.yzrq.substring(0,10)}}</p>
                                <i>{{item.pm.xq}}</i>
                                <i>下午</i>
                            </span>
                            <span><i class='gold'>¥{{item.pm.ghfy}}</i></span>
                            <span><i class='red'>余{{item.pm.xhzs}}</i></span>
                        </div>
                    </li>
                </ul>
                <div v-else class='introduce'>
                    {{docDetail.czyjj}}
                </div>
            </div>
        </div>
        <!--通用-->
        <my-dialog :show='showDialogPatient' :cbClose='closeDialogPatient'>
            <p slot="title" class='TITLE'>选择就诊人</p>
            <div slot='content' class='content'>
                <ul class="patientList">
                    <li v-for='item in patient' @click='choosePatient(item)'><p class='center'>姓名:</p><p>{{item.brxm}}</p></li>
                </ul>
            </div>
        </my-dialog>
        <my-dialog :show='showDialog' :cbClose='closeDialog'>
            <p slot="title" class='TITLE'>挂号提醒</p>
            <div slot='content' class='content showAppointInfo'>
                <li><span>就诊人:</span><span class='darkGreen' @click='showPaient'>{{selectedPatient.brxm}}</span></li>
                <li><span>就诊科室:</span><span class='darkGreen'>{{chooseOne.ksmc}}</span></li>
                <li><span>就诊医生:</span><span class='darkGreen'>{{chooseOne.czyxm}}</span></li>
                <li><span>费用:</span><span class='gold'>{{chooseOne.ghfy}}</span></li>
                <li><span>就诊日期:</span><span class='darkGreen'>{{chooseOne.yydjsj&&chooseOne.yydjsj.substr(0,10)}}</span></li>
            </div>
            <div slot='button' class='button'>
                <button @click='confirmToPay'>确定挂号</button>
            </div>
        </my-dialog>
    </div>
</template>

<script>
    import api from '../backend/api';
    import routerManager from '../routerManager';
    import { Toast } from 'mint-ui';
    import wx from 'weixin-js-sdk';

    var scheduleItem = {
        props:['schedule','title'],
        template: `<div class='scheduleItem' v-on:click='doShowDetail'>
                        <div class='left' style='flex:1'>
                            <p class='date'>{{schedule.yydjsj.substr(0,10)}}</p>
                            <p class='time'>{{title}}</p>
                        </div>
                        <div class='middle' style='flex:1'>
                            <p class='name'>医生:{{schedule.czyxm}}</p>
                            <p class='apartment'>科室:{{schedule.ksmc}}</p>
                        </div>
                        <div class='right' style='flex:1'>
                            <p class='remain'>余:{{schedule.xhzs}}</p>
                            <p class='price'>单价:{{schedule.fydj}}</p>
                        </div>
                    </div>`,
        methods:{
           doShowDetail(){
               this.$emit('showDetail',this.schedule);
           }
        }
    }

    export default {
        data: function () {
            return {
                showDialog:false,
                scheduleAll:{},
                docIntroduce:[],
                chooseOne:{},
                showDialogPatient:false,
                patient:[],
                selectedPatient:{},
                step:'ONE',
                docDetail:{},
                switchSide:true,
                scheduleDetail:{}
            }
        },
        components:{
            scheduleItem
        },
        methods:{
            lookDetail(item){
                this.scheduleDetail = this.scheduleAll[item.czybm];
                this.docDetail = item;
                this.step = 'TWO';
            },
            lookSchedule(){
                this.switchSide = true;
            },
            lookIntroduce(){
                this.switchSide = false;
            },
            showDetailDig(data){
                 this.chooseOne = data;
                 api.getCommonPatient(this.$store.getters.weChatInfo.openid).then((data)=>{
                    var ret = JSON.parse(data);
                    if(ret.length == 0){
                         Toast({
                            message: '请先绑卡!',
                            duration: 2000,
                            className:'zIndex11000'
                        });
                        return;
                    }
                    this.$store.commit('SET_COMMON_PATIENT',ret);
                    this.patient = ret;
                    this.showDialogPatient = true;
                });
            },
            parseDocDate(data){
                var _docList = [];
                var docIntr = [];
                for(var id in data){
                    _docList.push = id;
                    for(var item in data[id]){
                        var amOrPm = data[id][item].am || data[id][item].pm;
                        docIntr.push(amOrPm);
                        break;
                    }
                }
                this.docIntroduce = docIntr;
                this.$nextTick(function(item){});
            },
            closeDialog(){
                this.showDialog = false;
            },
            showPaient(){
                this.showDialogPatient = true;
            },
            closeDialogPatient(){
                this.showDialogPatient = false;
            },
            choosePatient(item){
                this.selectedPatient = item;
                this.closeDialogPatient();
                this.showDialog = true;
            },
            initWeixin() {
                var curUrl = "/Hospital/homepage?from=singlemessage#/singel/doctorsSchedule";
                api.getWeChatPayParams(curUrl).then((data)=>{
                    var ret = JSON.parse(data);
                    wx.config({
                        debug: false,
                        appId: ret.appid,
                        timestamp: parseInt(ret.timeStamp),
                        nonceStr: ret.nonceStr,
                        signature: ret.signature,
                        jsApiList: ['chooseWXPay']
                    });
                });
            },
            confirmToPay() {
                this.showDialog = false;
                var openId = this.$store.getters.weChatInfo.openid;
                api.requestWechatOrder(openId, this.chooseOne.ghfy).then((data)=>{
                    var ret = JSON.parse(data);
                    wx.chooseWXPay({
                        timestamp: parseInt(ret.timeStamp),
                        nonceStr: ret.nonceStr,
                        package: ret.packageWithPrepayId,
                        signType: ret.signType,
                        paySign: ret.paySign,
                        success: function (res) {
                            alert("支付成功！")
                        }
                    });
                });
            }
        },
        mounted(){
            alert("mounted!");
            this.initWeixin();
            this.scheduleAll = this.$store.getters.doctorSchedule;
            this.parseDocDate(this.scheduleAll);
        }
    }   
</script>
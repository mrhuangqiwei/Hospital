<style lang="scss" scoped>
    #lisreport{
        height: 100%;
        font-size:1.8rem;
        background: white;
        .flex2{
            flex: 2;
        }
        .flex3{
            flex:3;
        }
        .state2{
            background: url('../img/stateUp.png') no-repeat center center;
        }
        .state1{
            background: url('../img/stateUp.png') no-repeat center center;
        }
        .state1,.state2{
            display: inline-block !important;
            background-size: cover;
            height: 1.5rem;
            width: 1.5rem;
            vertical-align: text-bottom;
        }
        li{
            min-height: 4rem;
            border-bottom: 1px solid #838383;
            box-sizing: border-box;
            padding-left:1rem;
            line-height: 4rem;
            background-color: white;
            display:flex;
            flex-direction:column;
            div{
                display:flex;
            }
            span{
                flex:1;
                min-height: 3rem;
                line-height: 3rem;
            }
        }
        ul.showArea{
            max-height: 30rem;
            overflow: scroll;
            box-sizing: border-box;
            font-size:1.6rem;
            li{
                flex-direction:row;
                min-height:3rem;
                line-height:3rem;
                span{
                    text-align: left;
                }
                i{
                    display: inline;
                }
            }
            li:nth-child(5){
                border-bottom: 2px solid #838383;
            }
        }
        ul.detailList{
            border-bottom: 2px solid #838383;
        }
     
        ul.detailList:nth-child(odd){
            li{
                background-color:#e3e3e3;
            }
        }
        ul.detailList:nth-child(even){
            li{
                background-color:white;
            }
        }
        .empty{
            border: none;
            span{
              text-align: center;
            }
        }
        
    }
</style>

<template>
    <div id='lisreport'>
        <ul class="reportList">
            <p class='TITLE'>检查记录</p>
            <li v-for='item in lisreport' @click="showDetail(item)">
                <div>
                    <span><i>姓名:</i><i class='darkBlue'>{{item.brxm}}</i></span>
                    <span><i>科室:</i><i class='darkBlue'>{{item.ksmc}}</i></span>
                </div>
                <div>
                    <span><i>检查日期:</i><i class='darkBlue'>{{item.cyrq.substr(0,10)}}</i></span>
                </div>
            </li>
            <li v-if='lisreport.length == 0' class='empty'><span>暂无数据！</span></li>
        </ul>
        <!--通用-->
        <my-dialog :show='showDialog' :cbClose='closeDialog'>
            <div slot="title"><p class='TITLE'>检查报告</p></div>
            <ul slot='content' v-if='showItem' class='showArea'>
                <li>
                    <span class='name'>
                        <i>姓名:</i><i class='darkBlue'>{{showItem.brxm}}</i>
                    </span>
                    <span class='sex'>
                        <i>性别:</i><i class='darkBlue'>{{showItem.brxb=='1'?'男':'女'}}</i>
                    </span>
                    <span class='age'>
                        <i>年龄:</i><i class='darkBlue'>{{showItem.brnl}}岁</i>
                    </span>
                </li>
                <li>
                    <span class='label flex2'>
                         <i>医生:</i><i class='darkBlue'>{{showItem.zxysxm}}</i>
                    </span>
                    <span class='flex3'>
                        <i>病案号:</i><i class='darkBlue'>{{showItem.bah}}</i>
                    </span>
                </li>
                <li>
                    <span class='label'>
                        <i>住院号:</i><i class='darkBlue'>{{showItem.cwh}}</i>
                    </span>
                    <span class='label'>
                        <i>科室:</i><i class='darkBlue'>{{showItem.ksmc}}</i>
                    </span>
                </li>
                <li>
                    <span class='label'>
                        <i>项目:</i><i class='darkBlue'>{{showItem.mc}}</i>
                    </span>
                </li>
                <li>
                    <span class='label'>
                        <i>仪器:</i><i class='darkBlue'>{{showItem.sbmc}}</i>
                    </span>
                </li>
                <ul v-for='jg in showItem.jyjg' class='detailList'>
                    <li>
                        <span class='jg_label jg_zwmc'>
                            <i>项目:</i><i class='darkBlue'>{{jg.zwmc}}</i>
                        </span>
                        <span class='jg_label jg_ywmc'>
                            <i>简称:</i><i class='darkBlue'>{{jg.ywmc}}</i>
                        </span>
                    </li>
                    <li>
                        <span class='jg_label'>
                            <i>结果:</i><i class='gold'>{{jg.jg}}</i>
                        </span>
                        <span class='jg_label'>
                            <i>单位:</i><i class='darkBlue'>{{jg.dw}}</i>
                        </span>
                    </li>
                    <li>
                        <span class='jg_label flex2'>
                            <i>参考范围:</i><i class='darkBlue'>{{jg.ckz_t}}</i>
                        </span>
                        <span class='jg_label jg_zt'>
                            <i>状态:</i><i v-bind:class='["state"+jg.zt]'></i>
                        </span>
                    </li>
                </ul>
                <li>
                    <span class='label'>
                        <i>样本编码:</i><i class='darkBlue'>{{showItem.ybbm}}</i>
                    </span>
                    <span class='label'>
                        <i>审核人员:</i><i class='darkBlue'>{{showItem.shry}}</i>
                    </span>
                </li>
                <li>
                    <span class='label'>
                         <i>审核日期:</i><i class='darkBlue'>{{showItem.shrq&&showItem.shrq.substr(0,10)}}</i>
                    </span>
                </li>
            </ul>
            <div slot='button' class='button'>
                <button @click='closeDialog'>确定</button>
            </div>
        </my-dialog>
    </div>
</template>

<script>
    import api from '../backend/api';

    export default {
        data: function () {
            return {
                showDialog:false,
                showItem:{},
                lisreport:[]
            }
        },
        computed:{
            stateIcon(){

            }
        },
        components:{
        },
        methods:{
            closeDialog(){
                this.showDialog = false;
            },
            showDetail(item){
                var self=this;
                this.showItem = item;
                this.$nextTick(function(item){
                   
                })
                this.showDialog = true;
            }
        },
        mounted(){
            this.lisreport = this.$store.getters.lisreport;
        }
    }   
</script>
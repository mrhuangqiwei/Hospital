<style lang="scss" scoped>
    #risreport{
        font-size: 1.8rem;
        background: white;
        height: 100%;
        .flex2{
            flex:2;
        }
        .flex3{
            flex:3;
        }
        .textLeft{
            text-align: left;
        }
        li{
            min-height: 4rem;
            border-bottom: 1px solid #838383;
            box-sizing: border-box;
            padding-left:1rem;
            line-height: 4rem;
            display:flex;
            flex-direction:column;
            div{
                display:flex;
            }
            span{
                flex:1;
                min-height: 4rem;
                line-height: 4rem;
            }
        }
        li span{
            flex:1;
            text-align: left;

        }
        span.sex{
            text-align: center;
        }
        ul{
            section p{
                text-indent: 2rem;
            }
        }
        div.dic{
            box-sizing: border-box;
            padding: 1rem;
            max-height: 20rem;
            overflow: scroll;
        }
        .empty{
            border: none;
            span{
              text-align: center;
            }
        }
        ul.detailInfo{
            font-size:1.8rem;
            li{
                flex-direction:row;
            }
            i{
                height: 4rem;
                display: inline-block;
                line-height: 4rem;
            }
        }
       
    }
</style>

<template>
    <div id='risreport'>
        <ul>
            <p class='TITLE'>检查记录</p>
            <li v-for='item in risreport' @click="showDetail(item)">
                <div>
                    <span><i>姓名:</i><i class='darkBlue'>{{item.nAME}}</i></span>
                    <span><i>科室:</i><i class='darkBlue'>{{item.lODGESECTION}}</i></span>
                </div>
                <div>
                    <span><i>检查日期:</i><i class='darkBlue'>{{item.lODGEDATE.substr(0,10)}}</i></span>
                </div>
            </li>
            <li v-if='risreport.length == 0' class='empty'><span>暂无数据！</span></li>
        </ul>
        <!--通用-->
        <my-dialog :show='showDialog' :cbClose='closeDialog'>
            <p slot="title" class='TITLE'>彩超检查报告</p>
            <ul slot='content' v-if='showItem' class='detailInfo'>
                <li>
                    <span><i>姓名:</i><i  class='darkBlue'>{{showItem.nAME}}</i></span>
                    <span class="sex"><i>性别:</i><i  class='darkBlue'>{{showItem.sEX}}</i></span>
                    <span><i>年龄:</i><i  class='darkBlue'>{{showItem.aGE}}岁</i></span>
                </li>
                <li>
                    <span class="flex2"><i>科室:</i><i  class='darkBlue'>{{showItem.lODGESECTION}}</i></span>
                    <span class="flex3"><i>住院号:</i><i  class='darkBlue'>{{showItem.iNPATIENTNO}}</i></span>
                </li>
                <li>
                    <span><i>项目:</i><i  class='darkBlue'>{{showItem.cLASSNAME}}</i></span>
                </li>
                <li>
                    <span><i>医生:</i><i  class='darkBlue'>{{showItem.lODGEDOCTOR}}</i></span>
                    <span><i>状态:</i><i  class='darkBlue'>{{showItem.sTATUS}}</i></span>
                </li>
                <li>
                    <span><i>检查部位:</i><i  class='darkBlue'>{{showItem.pARTOFCHECK}}</i></span>
                </li>
                <div class="dic">
                    <p class='textLeft'>描述:</p>
                    <section  class='darkBlue'><p>{{showItem.laybe1}}</p></section>
                    <p class="textLeft">提示:</p>
                    <section class='darkBlue'><p>{{showItem.laybe2}}</p></section>
                <div>
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
                showItem:'',
                showDialog:false,
                risreport:{}
            }
        },
        components:{
        },
        methods:{
            closeDialog(){
                this.showDialog = false;
            },
            showDetail(item){
                this.showItem = item;
                this.$nextTick(function(item){
                })
                this.showDialog = true;
            }
        },
        mounted(){
            this.risreport = this.$store.getters.risreport;
        }
    }   
</script>
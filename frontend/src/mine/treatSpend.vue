<style lang="scss" scoped>
    #treatSpend{
        height: 100%;
        font-size: 1.8rem;
        background: white;
        div{
            height:100%;
        }
        p{
            height: 5rem;
            line-height: 5rem;
            background: white;
            text-align: center;
            font-size:2.2rem;
        }
       
        .flex2{
            flex:2;
        }
        li.label i:first-child,
        li.title span{
            color: #171526;
        }

        i{
            font-style: normal;
        }
        ul{
            box-sizing: border-box;
        }
        li{
            text-indent: 1rem;
            display:flex;
            background: white;
            min-height:4rem;
            line-height: 4rem;    
            border-bottom: 1px solid #838383;        
            span{
                flex:1;
            }
        }

    }
</style>

<template>
    <div id='treatSpend'>
        <div v-if='step=="ONE"'>
            <patientList :doSomething='getPatient'/>
        </div>  
        <div v-else-if='step=="TWO"'>
            <patientDetailInfo :doSomething='getTreatSpend' :sfzh='sfzh' :ylkh='ylkh' :khStyle='01'/>
        </div>
        <ul v-else-if='step=="THREE"' class='stepThree'>
            <p>门诊费用清单</p>
            <li class='label'>
                <span><i>姓名:</i><i class='darkBlue'>{{treatSpend.brxm}}</i></span>
                <span><i>年龄:</i><i class='darkBlue'>{{treatSpend.brnl}}</i></span>
            </li>
            <li class='label'>
                <span class='flex2'><i>挂号日期:</i><i class='darkBlue'>{{treatSpend.ghrq}}</i></span>
                <span><i>金额:</i><i class='gold'>{{(+treatSpend.fyzj).toFixed(2)}}</i></span>
            </li>
            <li class='label'>
                <span><i>家庭住址:</i><i class='darkBlue'>{{treatSpend.jtzz}}</i></span>
            </li>
            <li class='title'>
                <span>项目名称</span><span>数量</span><span>单价</span><span>金额</span>
            </li>
            <li v-for='item in treatSpend.mzfymxBeans'>
                <span class='darkBlue'>{{item.ypmc}}</span><span class='darkBlue'>{{item.fysl}}</span><span class='darkBlue'>{{item.fydj}}</span><span class='gold'>{{item.fyje}}</span>
            </li>
            <li>
                <button class='GOBACK' @click='goBack'>返 回</button>
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
                treatSpend:{}
            }
        },

        methods:{
            getPatient(item){
                this.sfzh = item.sfzh;
                this.ylkh = item.ylkh;
                this.$nextTick(function(item){});
                this.step = 'TWO';
            },

            getTreatSpend(item){
                api.getTreatSpend(item.ghxh).then((data)=>{
                    this.treatSpend = JSON.parse(data);
                    this.step = 'THREE';
                })
            },
            goBack(){
                routerManager.goBack();
            }
        },

        mounted(){
        }
    }   
</script>
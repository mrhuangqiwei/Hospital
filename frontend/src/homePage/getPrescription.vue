<style lang="scss" scoped>
    #prescription{
        font-size: 1.8rem;
        height: 100%;
        background: white;
        .center{
            text-align: center;
        }
        .left{
            text-align: left;
        }
        .right{
            text-align: right;
        }
        .height100{
            height: 100%;
        }
        li{
            display:flex;
            text-indent: 1rem;
            span{
                flex:1;
            }
        }
        span{
            padding:0.5rem;
        }
        .page{
            border-bottom: 1px solid #838383;
            box-sizing: border-box;
        }
        span.double{
            display: flex;
            flex-direction: column;
            text-indent: 1rem;
            span{
                flex:1;
            }
        }
        ul.stepTwo,ul.stepOne{
            li{
                background:white;
                min-height: 4rem;
                line-height: 4rem;
                border-top:1px solid #838383;
            }
        }
        div.header{
            overflow-y: auto;
            font-size: 1.8rem;
            span{
                min-height: 3rem;
                line-height: 3rem;
                box-sizing: content-box;
                margin: 1px;
                background:white;
            }
            span.middle{
                line-height: initial;
            }
        }
        .empty{
            text-align: center;
            font-size: 2rem;
            margin-top: 2rem;
        }
    }
</style>

<template>
    <div id='prescription'>
        <div v-if='step=="ONE"' class='height100'>
            <patientList :doSomething='getPatient'/>
        </div>  
        <div v-else-if='step=="TWO"' class='height100'>
            <patientDetailInfo :doSomething='getPrescription' :sfzh='sfzh' :ylkh='ylkh' :khStyle='01'/>
        </div>
        <div v-else-if='step=="THREE"' class="pageContent height100">
            <p class='TITLE'>西药处方</p>
            <div v-if='loading'  class='height100'>
                <loader />
            </div>
            <div v-else  class='height100'>
               
                <div class='empty' v-if='prescription.length==0'>
                    暂无记录!
                </div> 
                <div>
                    <div class="header">
                        <ul v-for='script in prescription' class='page'>
                            <li>
                                <span>
                                    <i>临床诊断:</i><i class='darkBlue'>{{script.mzzd}}</i>
                                </span>
                            </li>
                            <li class='left'>
                                <span><i>姓名:</i><i class='darkBlue'>{{script.brxm}}</i></span>
                                <span><i>处方金额:</i><i class='gold'>{{script.cfje}}元</i></span>
                            </li>
                            <li v-for="bean in script.yppfmxBeans" class='darkBlue'>
                                <span>{{bean.ypmc}}</span>
                                <span class='double'>
                                    <span>{{bean.ypgg}}</span>
                                    <span>{{bean.fyjl+' '+bean.jldwmc}}</span>
                                </span>
                                <span class='double'>
                                    <span class='gold'>{{bean.zl+' '+bean.yfdwmc}}</span>
                                    <span class='middle'>{{bean.yyffmc+' '+bean.pcmc}}</span>
                                </span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import api from '../backend/api';
    import routerManager from '../routerManager';
    export default {
        data: function () {
            return {
                step:"ONE",
                commonPatient:[],
                patientInfo:{},
                prescription:[],
                loading:true
            }
        },
        components:{
        },
        methods:{
            getPatient(item){
                this.sfzh = item.sfzh;
                this.ylkh = item.ylkh;
                this.$nextTick(function(item){});
                this.step = 'TWO';
            },
            getPrescription(item){
                this.step = 'THREE';
                api.getPrescription('20160809000077').then((data)=>{
                    if(data==0){
                        this.prescription = [];
                    } else{
                        this.prescription = JSON.parse(data);
                        this.$nextTick(function(item){});
                    }
                    this.loading = false;
                })
            }
        },
        mounted(){
            this.commonPatient = this.$store.getters.commonPatient;
        }
    }   
</script>
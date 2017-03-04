<style lang="scss" > 
    #askOnline{
        overflow: auto;
        height: 100%;
        .header{
            height: 15rem;
        }
        .carousel{
            .one{
                background-size:cover !important; 
                background: url('../img/one.png') no-repeat center center;
            }
            .two{
                background-size:cover !important; 
                background: url('../img/two.png') no-repeat center center;
            }
        }
        .search{
            height: 5rem;
            line-height: 5rem;
            text-align: center;
            background: white;
            input{
                background:#EEE;
                height: 3rem;
                width: 15rem;
                padding-left: 1rem;
                border-radius: 0.5rem;
            }
        }
        .funcGroup{
            margin: 1rem 0;
            background: white;
            height: 16rem;
            .chipItem{
                width: 25%;
                float:left;
                height:8rem;
                display:inline-block;
                text-align: center;
                padding-top: 1rem;
                box-sizing: border-box;
                color: black;

                .more_icon,.chaosheng_icon,.chanke_icon,.fuke_icon,
                .ertongke_icon,.shengzhi_icon,.yousheng_icon,.erke_icon,.faremenzhen_icon,
                .tijianzhongxin_icon,.neiyike_icon,.yibaomenzhen_icon,.shoushushi_icon{
                    height: 4rem;
                    width: 4rem;
                    margin: 0 auto;
                    margin-bottom: 0.5rem;
                    background-size: cover !important;
                }
                .chaosheng_icon{
                    background: url('../img/chaosheng.png') no-repeat center center;
                }
                .chanke_icon{
                    background: url('../img/chanke.png') no-repeat center center;
                }
                .fuke_icon{
                    background: url('../img/fuke.png') no-repeat center center;
                }
                .ertongke_icon{
                    background: url('../img/ertongke.png') no-repeat center center;
                }
                .shengzhi_icon{
                    background: url('../img/shengzhi.png') no-repeat center center;
                }
                .yousheng_icon{
                    background: url('../img/yousheng.png') no-repeat center center;
                }
                .erke_icon{
                    background: url('../img/erke.png') no-repeat center center;
                }
                .more_icon{
                    background: url('../img/more.png') no-repeat center center;
                }
                .tijianzhongxin_icon{
                    background: url('../img/tijianzhongxin.png') no-repeat center center;
                }
                .neiyike_icon{
                    background: url('../img/neiyike.png') no-repeat center center;
                }
                .yibaomenzhen_icon{
                    background: url('../img/yibaomenzhen.png') no-repeat center center;
                }
                .shoushushi_icon{
                    background: url('../img/shoushushi.png') no-repeat center center;
                }
                .faremenzhen_icon{
                    background: url('../img/faremenzhen.png') no-repeat center center;
                }
            }
        }
        .doctors{
            background: white;
            .header{
                font-size: 2rem;
                position: relative;
                line-height: 3.5rem;
                height: 3.5rem;
                padding-left: 2rem;
                padding-top: 0; 
                border-bottom: 1px solid #CCC;
                .more{
                    float: right;
                    margin-right: 2rem;
                    color: #b5b5b5;
                }
            }
            li.docInfo{
                height: 8rem;
                line-height: 7rem;
                border-bottom: 1px solid #CCC;
                .photo{
                    height: 6rem;
                    width: 5rem;
                    display: block;
                    margin: 1rem;
                    box-sizing: border-box;
                    display:inline-block;
                    background:url('../img/photo.png') no-repeat center center;
                }
                .info{
                    vertical-align: top;
                    display:inline-block;
                    margin-top: 1rem;
                    font-size: 1.6rem;
                    .name{
                        font-size: 2rem;
                        color: black;
                    }
                    .title,.skilful{
                        color:#838383;
                        font-size:1.6rem;
                        padding-top:0;
                    }
                    li{
                        height: 2rem;
                        line-height: 2rem;
                    }
                }
            }
        }
        .dialog-content{
            section{
                padding: 0 1rem 1rem 1rem;
            }
            p{
                text-align: left;
                text-indent: 2rem;
            }
            p.title{
                line-height:5rem;
                text-align: center;
            }
            p.department_floor{
                margin-top:1rem;
            }
        }
    }
</style>

<template>
    <div id='askOnline'>
        <div class='header'>
            <mt-swipe :auto="5000" class='carousel'>
                <mt-swipe-item class='one'></mt-swipe-item>
                <mt-swipe-item class='two'></mt-swipe-item>
            </mt-swipe>
        </div>
        <div class='funcGroup'>
            <chipItem iconName='neiyike_icon' name='内一科' v-bind:doClick="showDepartmentInfo.bind(this,'0002')"/>
            <chipItem iconName='yibaomenzhen_icon' name='医保门诊' v-bind:doClick="showDepartmentInfo.bind(this,'0011')"/>
            <chipItem iconName='shoushushi_icon' name='手术室' v-bind:doClick="showDepartmentInfo.bind(this,'0015')"/>
            <chipItem iconName='ertongke_icon' name='计生门诊' v-bind:doClick="showDepartmentInfo.bind(this,'0031')"/>
            <chipItem iconName='faremenzhen_icon' name='发热门诊' v-bind:doClick="showDepartmentInfo.bind(this,'0039')"/>
            <chipItem iconName='chanke_icon' name='产科门诊' v-bind:doClick="showDepartmentInfo.bind(this,'0041')"/>
            <chipItem iconName='tijianzhongxin_icon' name='体检中心' v-bind:doClick="showDepartmentInfo.bind(this,'0056')"/>
            <chipItem iconName='more_icon' name='更多' v-bind:doClick="showMore"/>
        </div>
        <div class='doctors'>
            <div class='header'>
                科室 
                <p class='more'>更多</p>
            </div>
            <ul>
               <docInfo name='梁厚萍' title='主任医师' skilful='主任医师，教授，博士生导师'/>
               <docInfo name='吴胜松' title='主任医师' skilful='主任医师，教授，博士生导师'/>
            </ul>
        </div>
        <!--通用-->
        <my-dialog :show='showDialog' :cbClose='closeDialog'>
            <p slot="title" class='title'>{{departmentInfo.ksmc}}</p>
            <div slot='content' class='content'>
                <section>
                    <p class='department_introduce'>
                        介绍:{{departmentInfo.ksjj}}
                    </p>
                    <p class='department_floor'>
                        地址:{{departmentInfo.ksms}}
                    </p>
                </section>
            </div>
            <div slot='button' class='button'>
                <button @click='closeDialog'>确定</button>
            </div>
        </my-dialog>
    </div>
</template>

<script>
    import chipItem from '../component/chipItem';
    import api from '../backend/api';
    import { Toast } from 'mint-ui';
    import routerManager from '../routerManager';
    
    var docInfo = {
        props:['name','title','skilful'],
        template: "<li class='docInfo'>\
                     <span class='photo'></span>\
                     <ul class='info'>\
                        <li class='name'>{{name}}</li>\
                        <li class='title'>职称：{{title}}</li>\
                        <li class='skilful'>擅长：{{skilful}}</li>\
                     </ul>\
                   </li>"
    }
    export default { 
        components:{
            chipItem,
            docInfo
        },
        methods:{
            PopupTip(){
                Toast({
                    message: '开发中，敬请期待!',
                    duration: 2000,
                    className:'zIndex11000'
                });
            },
            closeDialog(){
                this.showDialog = false;
            },
            showDialogFunc(){
                this.showDialog = true;
            },
            showMore(){
                routerManager.routerTo('singel/departmentInfo');
            },
            getIntroduce(ksbm){
                var allInfo = this.$store.getters.departmentInfo;
                for(var i=0; i<allInfo.length;i++){
                    if(allInfo[i].ksbm == ksbm){
                        this.departmentInfo = allInfo[i];
                        // this.$nextTick(function(){});
                        this.showDialogFunc();
                        break;
                    }
                }
            },
            showDepartmentInfo(ksbm){
                if(this.$store.getters.departmentInfo && this.$store.getters.departmentInfo.length == 0){
                    api.getDepartmentInfo().then((data)=>{
                        var info = JSON.parse(data);
                        this.$store.commit('SET_DEPARTMENT_INFO',info);
                        this.getIntroduce(ksbm);
                    })
                } else{
                    this.getIntroduce(ksbm);
                }
            }
        },
        data: function (){
            return {
                showDialog:false,
                departmentInfo:{
                    ksmc:'',
                    ksjj:'',
                    ksms:''
                }
            }
        }
    }
</script>
<style lang="scss">
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
                .ertongke_icon,.shengzhi_icon,.yousheng_icon,.erke_icon{
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
            <chipItem iconName='chanke_icon' name='产科' v-bind:doClick="PopupTip"/>
            <chipItem iconName='fuke_icon' name='妇科' v-bind:doClick="PopupTip"/>
            <chipItem iconName='ertongke_icon' name='儿科' v-bind:doClick="PopupTip"/>
            <chipItem iconName='erke_icon' name='五官科' v-bind:doClick="PopupTip"/>
            <chipItem iconName='yousheng_icon' name='优生学科' v-bind:doClick="PopupTip"/>
            <chipItem iconName='shengzhi_icon' name='生殖医学' v-bind:doClick="PopupTip"/>
            <chipItem iconName='chaosheng_icon' name='超声科' v-bind:doClick="PopupTip"/>
            <chipItem iconName='more_icon' name='更多' v-bind:doClick="PopupTip"/>
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
    </div>
</template>

<script>
    import chipItem from '../component/chipItem';
    import { Toast } from 'mint-ui';
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
            }
        },
        data: function (){
            return {
            }
        }
    }
</script>
<style lang="scss" scoped>
    $bule : #0090CF;
    #bindCard{
        height: 100%;
        background: white;
        text-align: center;
        input{
            height: 3rem;
            width: 24rem;
            text-indent: 4.5rem;
            line-height:3rem;
            border-radius: 4px;
            border: 1px solid #dcdcdc;
        }
        .useCard{
            padding-top: 1rem;
            li{
                margin: 0 auto;
                height: 3rem;
                width: 24rem;
                position: relative;
            }
            div{
                height: 4rem;
            }
        }
        .useID{
            padding-top: 2rem;
            input{
                text-indent:1rem;
                padding: 0.5rem;
            }
            li{
                margin-bottom:1rem;
                input.sex{
                    height: 1.5rem;
                    width: 1.5rem;
                    position: relative; 
                    top: 0.2rem;
                }
                div{
                    background: white;
                    text-align: left;
                    font-size: 1.7rem;
                    line-height: 3rem;
                    margin: 0 auto;
                    height: 3rem;
                    width: 24rem;
                    position: relative;
                    label{
                        color:rgb(51, 51, 51);
;
                        margin-left:0.5rem;
                    }
                    input.sex + label{
                        margin-left: 0.5rem;
                    }
                }
            }
        }
        span.icon{
            position: absolute;
            height: 3rem;
            width: 3rem;
            background: url('../img/card.png') center center no-repeat;
            border-radius: 4px;
            margin-left: 0.5rem;
            margin-top: 0.5rem;
        }
        button.doBind{
            color: white;
            background-color: $bule;
            height:4rem;
            line-height:4rem;
        }
        button{
            height:4rem;
            line-height:4rem;
        }
        .bggray{
            color: white;
            background: #a9a9a9;
        }

        .dialog-content{
            button{
                margin-top:0;
            }
            p.title{
                line-height: 5rem;
            }
            .content{
                height: 8rem;
                line-height: 6rem;
            }
        }
    }
</style>

<template>
    <div id='bindCard'>
        <div v-if='useCard' class="useCard">
            <div>
                <li><span class=' card_icon'></span><input v-model='cardNumber' placeholder="请输入医疗卡号"/></li>
            </div>
            <button @click='bindWithCard' class='doBind'>立刻绑定</button>
            <button @click='useID' class='bggray'>还没有卡，去绑定身份证</button>
        </div>
        <div v-else class="useID">
            <ul>
                <li><span class=' name_icon'></span><input v-model='name' placeholder="请输入姓名"/></li>
                <li><span class=' sfzh_icon'></span><input  v-model='sfzh' placeholder="请输入身份证号"/></li>
                <li>
                    <div>
                        <label>请选择性别：</label>
                        <input class='sex' type='radio' v-model='sex' value='1'/><label>男</label>
                        <input class='sex' type='radio' v-model='sex' value='2'/><label>女</label>
                    </div>
                </li>
                <li><span class=' phone_icon'></span><input v-model='phone' placeholder="请输入手机号"/></li>
                <li><span class=' jtzz_icon'></span><input v-model='jtzz' placeholder="请输入家庭住址"/></li>
                <li><span class=' age_icon'></span><input v-model='age' type='number' min="0" max="199" placeholder="请输入年龄(周岁)"/></li>
            </ul>
            <button @click='bindWithID' class='doBind'>确 定</button>
        </div>
        <!--通用-->
        <my-dialog :show='showDialog' :cbClose='closeDialog'>
            <p slot="title" class='title'>系统提示</p>
            <p slot='content' class='content'>{{showResult}}</p>
            <div slot='button' class='button'>
                <button @click='closeDialog' >确定</button>
            </div>
        </my-dialog>
    </div>
</template>

<script>
    import api from '../backend/api';
    import routerManager from '../routerManager';

    export default {
        data: function () {
            return {
                showDialog:false,
                useCard: true,//false指用身份证
                cardNumber:'',
                name:'', 
                sfzh:'',
                sex:'',
                phone:'',
                jtzz:'',
                age:''
            }
        },
        components:{
        },
        methods:{
            useID(){
                this.useCard = false;
            },
            goBack(){
                this.useCard = true;
            },
            closeDialog(){
                this.showDialog = false;
            },
            showTip(tip){
                this.showResult = tip;
                this.showDialog = true;
            },
            bindWithCard(){
                if(this.cardNumber){
                    var param = {
                        openid: this.$store.getters.weChatInfo.openid,
                        ylkh: this.cardNumber
                    }
                    api.bindCard(param).then((data)=>{
                        this.showTip(data);
                    });
                } else{
                    this.showTip('请填写卡号！');
                }
            },

            bindWithID(){
                if(!this.name || !this.sex || !this.phone || !this.sfzh || !this.jtzz || !this.age ){
                    this.showTip('请填完整个人信息！');
                    return;
                }
                if(this.phone.length != 11 ){
                    this.showTip('请输入正确手机号！');
                    return;
                }
                if(this.sfzh.length != 18){
                    this.showTip('请输入正确二代身份证号！');
                    return;
                }
                var param = {
                    openid : this.$store.getters.weChatInfo.openid,
                    sfzh: this.sfzh,
                    brxm: this.name,
                    brxb: this.sex,
                    brjtzz: this.jtzz,
                    nl: this.age,
                    lxdh: this.phone
                }
                api.bindCard(param).then((data)=>{
                    this.showTip(data);
                });
            },

            goBack(){
                routerManager.goBack();
            }
        }
    }   
</script>
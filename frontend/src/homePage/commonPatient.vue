<style lang="scss" scoped>
    #commonPatient{
        height: 100%;
        .TITLE{
           border-radius: 4px;
        }
        div.dialog{
            .content{
                height:30rem;
            }
        }
        ul.editDialog{
            padding: 0 1rem;
        }
        ul.editDialog li{
            display: flex;
            text-align: left;
            height: 4rem;
            line-height: 4rem;
            input{
                width: 4rem;
            }
            input.sex {
                width: 2rem;   
                height: 1.5rem;
                box-shadow: none;
            }
            span{
                width: 8rem;
            }
            input{
                margin-top: 0.5rem;
                padding-left: 0.5rem;
                box-sizing: border-box;
                flex:1;
                box-shadow: inset 0px 0px 1px;
                border-radius: 4px;
                height: 3rem;
            }
        }
    }
</style>

<template>
    <div id='commonPatient'>
       <p class='TITLE'>常用就诊人</p>
       <mt-cell-swipe v-for="(item,index) in commonPatient"  
            :title='item.brxm'
            :right="[
            {
                content: 'Edit',
                style: { background: 'grey', color: '#fff', 'width':'3.5rem', 'text-align':'center'},
                handler: () => editDialog(index)
            },
            {
                content: 'Delete',
                style: { background: 'red', color: '#fff','width':'3.5rem', 'text-align':'center'},
                handler: () => deleteItem(index)
            }
        ]"></mt-cell-swipe>
        <!--通用-->
        <my-dialog :show='showDialog' :cbClose='closeDialog'>
            <p slot="title" class='TITLE'>修改常用人信息</p>
            <div slot='content' class='content'>
                <ul class="editDialog">
                    <li><span class='icon name_icon darkBlue'>姓名:</span><input v-model='editItem.brxm'/></li>
                    <li><span class='icon sfzh_icon darkBlue'>身份证号:</span><input  v-model='editItem.sfzh'/></li>
                    <li><span class='icon card_icon darkBlue'>医疗卡号:</span><span>{{editItem.ylkh}}</span></li>
                    <li>
                        <div>
                            <span class='icon sex_icon'></span>
                            <label class='darkBlue'>请选择性别：</label>
                            <input class='sex' type='radio' v-model='editItem.brxb' value='1'/><label>男</label>
                            <input class='sex' type='radio' v-model='editItem.brxb' value='2'/><label>女</label>
                        </div>
                    </li>
                    <li><span class='icon phone_icon darkBlue'>手机号码:</span><input v-model='editItem.brdh' placeholder="请输入手机号"/></li>
                    <li><span class='icon jtzz_icon darkBlue'>家庭住址:</span><input v-model='editItem.jtzz' placeholder="请输入家庭住址"/></li>
                    <li><span class='icon age_icon darkBlue'>年龄:</span><input v-model='editItem.brnl' type='number' min="0" max="199" placeholder="请输入年龄(周岁)"/></li>
                </ul>
            </div>
            <div slot='button' class='button'>
                <button @click='editItemFunc'>{{committing ? "提交中..." : "确定"}}</button>
                <button @click='closeDialog' >取消</button>
            </div>
        </my-dialog>
    </div>
</template>

<script>
    import chipItem from '../component/chipItem';
    import api from '../backend/api';
    import _ from 'underscore';
    import { Toast } from 'mint-ui';
    import routerManager from '../routerManager';
    export default {
        data: function () {
            return {
                commonPatient:[],
                committing:false,
                editItem:{
                    'brxm':'',
                    'brdh':'',
                    'brxb':'',
                    'brnl':'',
                    'jtzz':'',
                    'sfzh':'',
                    'sex':'',
                    'ylkh':''
                },
                showDialog:false
            }
        },
        components:{
            chipItem
        },
        methods:{
            deleteItem(index){
                var tmp = this.commonPatient[index];
                this.$store.commit('DELETE_COMMON_PATIENT',index);
                var sfzh = tmp.sfzh;
                var ylkh = tmp.ylkh; 
                api.deleteCommonPatient('owEWzwQKO7G_uy4C0X_Wn2boPVI4',sfzh,ylkh).then((data)=>{
                    console.log('提示删除成功');
                })
            },
            
            editDialog(index){
                this.index = index;
                this.editItem = _.extend({},this.editItem,this.commonPatient[index]);
                this.oldSfzh = this.commonPatient[index].sfzh;
                this.showDialog = true;
            },
            
            closeDialog(){
                this.showDialog = false;
                this.committing = false;
            },

            updataPatient(){
                this.$store.commit('UPDATE_COMMON_PATIENT',{
                    index:this.index,
                    load:this.editItem
                });
            },

            goBack(){
                routerManager.goBack();
            },

            editItemFunc(){
                var item = this.editItem;
                if(!item.brxm||(!item.sfzh && !item.ylkh)){
                   Toast({
                        message: '请填写完整信息!',
                        duration: 2000,
                        className:'zIndex11000'
                    });
                    return;
                }
                if(this.committing)
                    return;

                this.committing = true;
                api.editCommonPatient('owEWzwQKO7G_uy4C0X_Wn2boPVI4',item.sfzh,this.oldSfzh,item.ylkh,item.brxm,item.brxb,item.jtzz,item.brdh,item.nl)
                   .then((data)=>{
                       this.closeDialog();
                },(err)=>{
                       this.closeDialog();
                })
            }
        },
        mounted(){
            this.commonPatient = this.$store.getters.commonPatient;
        }
    }   
</script>
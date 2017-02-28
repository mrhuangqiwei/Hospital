<style lang="scss" scoped>
    #departmentNO{
        li{
            height: 4rem;
            line-height: 4rem;
            font-size: 1.7rem;
            text-indent: 3rem;
            border-bottom: 1px solid #838383;
            background-color: white;
            color: #336633;
            font-weight: 400;
        }
        span.arrow_icon{
            background: url('../img/arrow.png') no-repeat center center;
            height: 2rem;
            width: 2rem;
            background-size:cover !important;
            display: inline-block;
            float: right;
            top: 1rem;
            right: 1rem;
            position: relative;
        }
    }
</style>

<template>
    <div id='departmentNO'>
        <ul>
            <p class='TITLE'>科室名称</p>
            <li v-for='item in departmentInfo' :ksbm='item.ksbm' @click='getDoctorSchedule(item.ksbm)'>
                {{item.ksmc}}
                <span class="arrow_icon"></span>
            </li>
        </ul>
    </div>
</template>

<script>
    import chipItem from '../component/chipItem';
    import api from '../backend/api';
    import routerManager from '../routerManager';
    import { Toast } from 'mint-ui';
    var _hash_data = {};
    
    export default {
        data: function () {
            return {
                departmentInfo:[],
            }
        },
        components:{
            chipItem
        },
        methods:{
            addToHash(id,time,obj){
                if(id in _hash_data){
                    _hash_data[id][time] = obj;
                } else{
                    _hash_data[id] = {};
                    _hash_data[id][time] = obj;
                }
            },
            
            addToHashPM(id,time,obj){
                if(id in _hash_data){
                    // 找到了直接赋值 没找到插入后排序
                    if(_hash_data[id][time]){
                        _hash_data[id][time].pm = obj;
                    } else{
                       this.addToHash(swId,timeAM,{'pm':swData});
                    }
                } else{
                    this.addToHash(swId,timeAM,{'pm':swData});
                }
            },
            getHashData(){
                return _hash_data;
            },
            getDoctorSchedule(ksbm){
               _hash_data={};
               api.getDoctorSchedule(ksbm).then((data)=>{
                    var srcData = JSON.parse(data);
                    let swpb = srcData.swpb;
                    let xwpb = srcData.xwpb;
                    if(swpb.length==0){
                        Toast({
                            message: '暂无数据!',
                            duration: 2000,
                            className:'zIndex11000'
                        });
                        return;
                    }
                    let lenSW = swpb.length;
                    // 以上午为准，找下午排班
                    for(let i = 0; i < lenSW; i++){
                        let swData = swpb[i];
                        // 医生编码
                        let swId = swData.czybm;
                        // 做Key值
                        let dateAM = swData.yzrq.substring(0,10);
                        // 毫秒做key 方便排序
                        let timeAM = (new Date(dateAM)).getTime()+'';
                        this.addToHash(swId,timeAM,{'am':swData});
                    }

                    let lenXW = xwpb.length;
                    for(let i = 0; i < lenSW; i++){
                        let xwData = xwpb[i];
                        let xwId = xwData.czybm;
                        let datePM = xwData.yzrq.substring(0,10);
                        let timePM = (new Date(datePM)).getTime();
                        this.addToHashPM(xwId,timePM,xwData);
                    }
                    this.$store.commit('SET_DOCTORS_SCHEDULE',this.getHashData());
                    routerManager.routerTo('singel/doctorsSchedule');
               })
            }
        },
        mounted(){
            if(this.$store.getters.departmentNO.length == 0){
                api.getDepartmentNO().then((data)=>{
                    this.$store.commit('SET_DEPARTMENT_NO',JSON.parse(data));
                    this.departmentInfo = JSON.parse(data);
                })
            } else{
                this.departmentInfo = this.$store.getters.departmentNO;
            }
        }
    }   
</script>
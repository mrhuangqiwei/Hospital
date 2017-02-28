<style lang="scss">
    #homePage{
        background: white;
        height: 100%;
        display: flex;
        flex-direction: column;
        #header{
            min-height: 17rem;
        }

        #content{
            min-height: 16rem;
            display: block;
            .mint-swipe.carousel{
                height: 100%;
            }
            .mint-swipe-items-wrap{
                min-height: 16rem;
            }
        }

        #header{
            display: flex;
            .mainFunc{
                border-right: 1px solid #CCC;
                width: 10rem;
                .register{
                    height: 100%;
                    text-align: center;
                    .chipItem{
                        position: relative;
                        top: 1.5rem;
                        .name{
                            margin-top:0; 
                            font-size: 1.8rem;
                        }
                        .dic{
                            color: #838383;
                            margin-top: 0.5rem;
                            font-size:1.3rem;
                            span{
                                display:block;
                            }
                        }
                    }
                }
             }

            .secondlyFunc{
                flex:1;
                display: flex;
                flex-direction: column;
                .pay{
                    flex:1;
                    border-bottom: 1px solid #CCC;
                    box-sizing: border-box;
                    .name{
                        top: 2rem;
                        left: 1rem;
                        font-size: 1.8rem;
                        position: relative;
                    }
                    .dic{
                        left: 1rem;
                        top: 2rem;
                        position: relative;
                        color: #838383;
                    }
                }
                .secondlyFunc{
                    flex:1;
                }
                .bindCard{
                    border-right:1px solid #CCC;
                }
                .bindCard,.checkAppointment{
                    padding-top: 0.5rem;
                    box-sizing:border-box;
                    text-align: center;
                    height: 50%;
                    width: 50%;
                    float: left;
                    display: inline-block;
                    .name{
                        margin-top: 0.5rem;
                    }
                }
            }
        }
        .carousel{
            padding: 1rem 0;
            background: #EEE;
            box-sizing: border-box;
            .one{
                background-size:cover !important; 
                background: url('../img/one.png') no-repeat center center;
            }
            .two{
                background-size:cover !important; 
                background: url('../img/two.png') no-repeat center center;
            }
            .three{
                background-size:cover !important; 
                background: url('../img/three.png') no-repeat center center;
            }
        }
        ul{
            height: 100%;
        }
        li.funcItem{
            width: 25%;
            display: inline-block;
            float: left;
            box-sizing: border-box;
            border: 1px solid;
            height: 50%;
            text-align: center;
        }
        /* 挂号 */
        .register_icon{
            margin: 0 auto;
            height: 4.5rem;
            width: 4.5rem;
            display: block;
            background-size: cover !important;
            background: url('../img/resigter.png') no-repeat center center;
        }
        .pay_icon{
            float: right;
            position: relative;
            top: 1.2rem;
            right: 2rem;
            height: 4rem;
            width : 4rem;
            background-size: cover !important;
            background: url('../img/pay.png') no-repeat center center;
        }
        .bindCard_icon{
            margin-bottom: 0.5rem;
            background: url('../img/bindCard.png') no-repeat center center;
        }
        .checkAppointment_icon{
            background: url('../img/checkAppointment.png') no-repeat center center;
        }
        
        .lisreport_icon{
            background: url('../img/lisrepor.png') no-repeat center center;
        }
        .risReport_icon{
            background: url('../img/risReport.png') no-repeat center center;
        }
        .department_icon{
            background: url('../img/department.png') no-repeat center center;
        }
        .hospitalFee_icon{
            background: url('../img/hospitalFee.png') no-repeat center center;
        }
        .patient_icon{
            background: url('../img/patient.png') no-repeat center center;
        }
        .appointment_icon{
            background: url('../img/checkAppointment.png') no-repeat center center;
        }
        .prescription_icon{
            background: url('../img/prescription.png') no-repeat center center;
        }

        .pay_icon,.bindCard_icon,.checkAppointment_icon,.lisreport_icon,.prescription_icon,
        .risReport_icon,.department_icon,.hospitalFee_icon,.patient_icon,.appointment_icon{
            height: 4rem;
            width: 4rem;
            margin: 0 auto;
            background-size:cover !important;
        }

        /* funcGroup */
        .funcGroup{
            .chipItem{
                width: 25%;
                float:left;
                height:8rem;
                display:inline-block;
                text-align: center;
                padding-top: 1rem;
                box-sizing: border-box;
                color: black;
            }
        }
    }
</style>

<template>
    <div id='homePage'>
        <section id='header'>
            <div class="mainFunc">
                <div class='register'>
                    <chipItem iconName='register_icon' name='挂号' v-bind:doClick="getDepartmentNO">
                        <div class='dic' slot='dic'>
                            <span>足不出户快速挂</span>
                            <span>当日号和预约号</span>
                        </div>
                    </chipItem>
                </div>
            </div>
            <div class='secondlyFunc'>
                <div class="pay">
                    <chipItem iconName='pay_icon' name='缴费' v-bind:doClick="shouldPay">
                        <div class='dic' slot='dic'>
                            快速缴纳门诊费用
                        </div>
                    </chipItem>
                </div>
                <div class="down">
                    <div class='bindCard'>
                        <chipItem iconName='bindCard_icon' name='绑卡' v-bind:doClick="doBindCard"/>
                    </div>
                    <div class='checkAppointment'>
                        <chipItem iconName='checkAppointment_icon' v-bind:doClick='getLisreport' name='检查预约'/>
                    </div>
                </div>
            </div>
        </section>
        <section id='content'>
            <mt-swipe :auto="5000" class='carousel'>
                <mt-swipe-item class='one'></mt-swipe-item>
                <mt-swipe-item class='two'></mt-swipe-item>
                <mt-swipe-item class='three'></mt-swipe-item>
            </mt-swipe>
        </section>
        <section id='footer'>
            <div class="funcGroup">
                 <chipItem iconName='lisreport_icon' v-bind:doClick='getLisreport' name='检查查询'/>
                 <chipItem iconName='risReport_icon' v-bind:doClick='getRisReport' name='彩超查询'/>
                 <chipItem iconName='hospitalFee_icon' v-bind:doClick="getHospitalFee" name='住院费用'/>
                 <chipItem iconName='department_icon' v-bind:doClick="getDepartmentNO" name='科室信息'/>
                 <chipItem iconName='patient_icon' v-bind:doClick="getCommonPatient" name='管理就诊人'/>
                 <chipItem iconName='appointment_icon' v-bind:doClick="getPatientAppointmentInfo" name='检查预约'/>
                 <chipItem iconName='prescription_icon' v-bind:doClick="getPrescription" name='处方查询'/>
            </div>
        </section>
        <!--通用-->
        <my-dialog :show='showDialog' :cbClose='closeDialog'>
            <p slot="title" class='title'>系统提示</p>
            <p slot='content' class='content'>{{showResult}}</p>
            <div slot='button' class='button'>
                <button @click='closeDialog'>确定</button>
            </div>
        </my-dialog>
    </div>
</template>

<script>
    import chipItem from '../component/chipItem';
    import authen from '../authen';
    import api from '../backend/api';
    import routerManager from '../routerManager';

    export default {
        data: function () {
            return {
                showDialog:false,
                showResult:''
            }
        },
        components:{
            chipItem
        },
        methods:{
            unitCommit(type,data){
                this.$store.commit(type,JSON.parse(data));
            },

            doBindCard(){
                routerManager.routerTo('singel/bindCard');
            },

            closeDialog(){
                this.showDialog = false;
            },
           
            shouldPay(){
                api.getCommonPatient('owEWzwQKO7G_uy4C0X_Wn2boPVI4').then((data)=>{
                    this.unitCommit('SET_COMMON_PATIENT',data);
                    routerManager.routerTo('singel/shouldPay');
                });
            },
            getDoctorSchedule(){
                api.getDoctorSchedule('0004').then((data)=>{
                    this.unitCommit('SET_DOCTORS_SCHEDULE',data);
                    routerManager.routerTo('singel/doctorsSchedule');
                })
            },
            getDepartmentNO(){
                routerManager.routerTo('singel/departmentNO');
            },
            getHospitalFee(){
                api.getCommonPatient('owEWzwQKO7G_uy4C0X_Wn2boPVI4').then((data)=>{
                    this.unitCommit('SET_COMMON_PATIENT',data);
                    routerManager.routerTo('singel/hospitalizationFee');
                });
            },
            getCommonPatient(){
                api.getCommonPatient('owEWzwQKO7G_uy4C0X_Wn2boPVI4').then((data)=>{
                    this.unitCommit('SET_COMMON_PATIENT',data);
                    routerManager.routerTo('singel/commonPatient');
                });
            },
            getLisreport(){
                api.getLisreport('2016003850').then((data)=>{
                    this.unitCommit('SET_LISREPORT',data);
                    routerManager.routerTo('singel/lisreport');
                })
            },
            getRisReport(){
                api.getRisReport('2016022823').then((data)=>{
                    this.unitCommit('SET_RISREPORT',data);
                    routerManager.routerTo('singel/risreport');
                })
            },
            getPatientAppointmentInfo(){
                api.getCommonPatient('owEWzwQKO7G_uy4C0X_Wn2boPVI4').then((data)=>{
                    this.unitCommit('SET_COMMON_PATIENT',data);
                    routerManager.routerTo('singel/patientAppointmentInfo');
                });
            },
            getPrescription(){
                api.getCommonPatient('owEWzwQKO7G_uy4C0X_Wn2boPVI4').then((data)=>{
                    this.unitCommit('SET_COMMON_PATIENT',data);
                    routerManager.routerTo('singel/getPrescription');
                })
            }
        },
    }   
</script>
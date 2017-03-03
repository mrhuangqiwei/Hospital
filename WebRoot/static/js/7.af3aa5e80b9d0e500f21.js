webpackJsonp([7,20],{73:function(e,t,i){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var o=i(3),s=n(o),a=i(4),c=(n(a),i(11)),r=i(184),d=n(r),l={props:["schedule","title"],template:"<div class='scheduleItem' v-on:click='doShowDetail'>\n                    <div class='left' style='flex:1'>\n                        <p class='date'>{{schedule.yydjsj.substr(0,10)}}</p>\n                        <p class='time'>{{title}}</p>\n                    </div>\n                    <div class='middle' style='flex:1'>\n                        <p class='name'>医生:{{schedule.czyxm}}</p>\n                        <p class='apartment'>科室:{{schedule.ksmc}}</p>\n                    </div>\n                    <div class='right' style='flex:1'>\n                        <p class='remain'>余:{{schedule.xhzs}}</p>\n                        <p class='price'>单价:{{schedule.fydj}}</p>\n                    </div>\n                </div>",methods:{doShowDetail:function(){this.$emit("showDetail",this.schedule)}}};t.default={data:function(){return{showDialog:!1,scheduleAll:{},docIntroduce:[],chooseOne:{},showDialogPatient:!1,patient:[],selectedPatient:{},step:"ONE",docDetail:{},switchSide:!0,scheduleDetail:{}}},components:{scheduleItem:l},methods:{lookDetail:function(e){this.scheduleDetail=this.scheduleAll[e.czybm],this.docDetail=e,this.step="TWO"},lookSchedule:function(){this.switchSide=!0},lookIntroduce:function(){this.switchSide=!1},showDetailDig:function(e){var t=this;this.chooseOne=e,s.default.getCommonPatient(this.$store.getters.weChatInfo.openid).then(function(e){var i=JSON.parse(e);return 0==i.length?void(0,c.Toast)({message:"请先绑卡!",duration:2e3,className:"zIndex11000"}):(t.$store.commit("SET_COMMON_PATIENT",i),t.patient=i,void(t.showDialogPatient=!0))})},parseDocDate:function(e){var t=[],i=[];for(var n in e){t.push=n;for(var o in e[n]){var s=e[n][o].am||e[n][o].pm;i.push(s);break}}this.docIntroduce=i,this.$nextTick(function(e){})},closeDialog:function(){this.showDialog=!1},showPaient:function(){this.showDialogPatient=!0},closeDialogPatient:function(){this.showDialogPatient=!1},choosePatient:function(e){this.selectedPatient=e,this.closeDialogPatient(),this.showDialog=!0},initWeixin:function(){var e="/Hospital/homepage?from=singlemessage#/singel/doctorsSchedule";s.default.getWeChatPayParams(e).then(function(e){var t=JSON.parse(e);d.default.config({debug:!0,appId:t.appid,timestamp:t.timeStamp,nonceStr:t.nonceStr,signature:t.signature,jsApiList:["chooseWXPay"]}),alert(d.default)})},confirmToPay:function(){var e=this;this.showDialog=!1;var t=this.$store.getters.weChatInfo.openid;s.default.requestWechatOrder(t,this.chooseOne.ghfy).then(function(t){alert("支付 "+e.chooseOne.ghfy);var i=JSON.parse(t);d.default.chooseWXPay({timestamp:i.timeStamp,nonceStr:i.nonceStr,package:i.packageWithPrepayId,signType:i.signType,paySign:i.paySign,success:function(e){alert("支付成功！")}})})}},mounted:function(){this.scheduleAll=this.$store.getters.doctorSchedule,this.parseDocDate(this.scheduleAll),this.initWeixin()}}},104:function(e,t,i){t=e.exports=i(1)(),t.push([e.id,"#doctorsSchedule[data-v-cdd69598]{font-size:1.5rem;height:100%;background:#fff}#doctorsSchedule .flex1[data-v-cdd69598]{-ms-flex:1;flex:1}#doctorsSchedule .flex2[data-v-cdd69598]{-ms-flex:2;flex:2}#doctorsSchedule .flex4[data-v-cdd69598]{-ms-flex:4;flex:4}#doctorsSchedule span.icon[data-v-cdd69598]{width:6rem}#doctorsSchedule .TITLE[data-v-cdd69598]{font-size:2rem}#doctorsSchedule span.photo[data-v-cdd69598]{position:relative;top:1.5rem;left:1rem;border-radius:4px;height:4rem;width:4rem;display:inline-block;background-size:contain!important;background:url("+i(138)+") no-repeat 50%}#doctorsSchedule span.detailBtn[data-v-cdd69598]{width:6rem}#doctorsSchedule span.docDetail[data-v-cdd69598]{width:calc(100% - 12rem)}#doctorsSchedule li[data-v-cdd69598]{display:-ms-flexbox;display:flex;min-height:7rem;border-bottom:1px solid #dcd8d8}#doctorsSchedule .dot[data-v-cdd69598]{overflow:hidden;text-overflow:ellipsis;white-space:nowrap}#doctorsSchedule .btn[data-v-cdd69598]{height:2rem;border:1px solid #31b6aa;width:4rem;display:inline-block;text-align:center;border-radius:4px;line-height:2rem;background:#31b6aa;position:relative;top:2rem;left:.5rem;color:#fff}#doctorsSchedule .doctorList .name[data-v-cdd69598]{height:3rem;line-height:3rem;font-size:2rem}#doctorsSchedule div.header[data-v-cdd69598]{padding:1rem}#doctorsSchedule div.header p[data-v-cdd69598]{font-size:2rem}#doctorsSchedule div.header .name[data-v-cdd69598]{height:3rem;line-height:3rem;font-size:2.5rem;margin-bottom:5px}#doctorsSchedule div.middleBtn[data-v-cdd69598]{display:-ms-flexbox;display:flex;padding:0 3rem}#doctorsSchedule div.middleBtn span[data-v-cdd69598]{text-align:center;-ms-flex:1;flex:1;height:4rem;line-height:4rem;font-size:1.8rem}#doctorsSchedule div.middleBtn span.switchBtn[data-v-cdd69598]{color:#27a798;border-bottom:2px solid #27a798}#doctorsSchedule div.body[data-v-cdd69598]{border-top:1px solid #838383}#doctorsSchedule div.body li[data-v-cdd69598]{background:#fff;-ms-flex-direction:column;flex-direction:column}#doctorsSchedule div.body li div[data-v-cdd69598]{display:-ms-flexbox;display:flex;height:6rem;line-height:6rem;border-bottom:1px solid #dcd8d8;padding:1rem}#doctorsSchedule div.body li div span.double[data-v-cdd69598]{line-height:3rem}#doctorsSchedule div.body li span[data-v-cdd69598]{font-size:1.7rem;-ms-flex:1;flex:1}#doctorsSchedule div.body .introduce[data-v-cdd69598]{padding:1rem;height:100%;text-indent:2rem;font-size:1.7rem}#doctorsSchedule ul.patientList li[data-v-cdd69598]{min-height:4rem;line-height:4rem;text-align:center;font-size:1.8rem;padding-left:3rem}#doctorsSchedule div.showAppointInfo li[data-v-cdd69598]{min-height:4rem;line-height:4rem;padding-left:2rem;font-size:1.7rem;padding-right:2rem}#doctorsSchedule div.showAppointInfo li span[data-v-cdd69598]{-ms-flex:1;flex:1}#doctorsSchedule div.showAppointInfo li span[data-v-cdd69598]:first-child{text-align:left}#doctorsSchedule div.showAppointInfo li span[data-v-cdd69598]:last-child{text-align:right}",""])},130:function(e,t,i){var n=i(104);"string"==typeof n&&(n=[[e.id,n,""]]);i(2)(n,{});n.locals&&(e.exports=n.locals)},138:function(e,t,i){e.exports=i.p+"static/img/doc.8447a50.png"},145:function(e,t,i){var n,o;i(130),n=i(73);var s=i(177);o=n=n||{},"object"!=typeof n.default&&"function"!=typeof n.default||(o=n=n.default),"function"==typeof o&&(o=o.options),o.render=s.render,o.staticRenderFns=s.staticRenderFns,o._scopeId="data-v-cdd69598",e.exports=n},177:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{attrs:{id:"doctorsSchedule"}},["ONE"==e.step?i("ul",{staticClass:"doctorList"},[i("p",{staticClass:"TITLE"},[e._v("医生列表")]),e._v(" "),e._l(e.docIntroduce,function(t){return i("li",[e._m(0,!0),e._v(" "),i("span",{staticClass:"docDetail"},[i("p",{staticClass:"name"},[e._v(e._s(t.czyxm))]),e._v(" "),i("p",{staticClass:"fontGray"},[e._v("职称:"+e._s(t.zcmc))]),e._v(" "),i("p",{staticClass:"fontGray dot"},[e._v("介绍:"+e._s(t.czyjj.substring(0,22)))])]),e._v(" "),i("span",{staticClass:"detailBtn"},[i("i",{staticClass:"btn",on:{click:function(i){e.lookDetail(t)}}},[e._v("详情")])])])}),e._v(" "),0==e.docIntroduce.length?i("p",{staticClass:"center"},[e._v("暂无数据！")]):e._e()],2):"TWO"==e.step?i("div",[i("div",{staticClass:"header"},[i("p",{staticClass:"name"},[e._v(e._s(e.docDetail.czyxm))]),e._v(" "),i("p",{staticClass:"fontGray"},[e._v("职称:"+e._s(e.docDetail.zcmc))]),e._v(" "),i("p",{staticClass:"fontGray"},[e._v("科室:"+e._s(e.docDetail.ksmc))])]),e._v(" "),i("div",{staticClass:"middleBtn"},[i("span",{class:[{switchBtn:e.switchSide}],on:{click:e.lookSchedule}},[e._v("门诊排班")]),e._v(" "),i("span",{class:[{switchBtn:!e.switchSide}],on:{click:e.lookIntroduce}},[e._v("医生简介")])]),e._v(" "),i("div",{staticClass:"body"},[e.switchSide?i("ul",e._l(e.scheduleDetail,function(t){return i("li",[t.am?i("div",{on:{click:function(i){e.showDetailDig(t.am)}}},[i("span",{staticClass:"double"},[i("p",[e._v(e._s(t.am.yzrq.substring(0,10)))]),e._v(" "),i("i",[e._v(e._s(t.am.xq))]),e._v(" "),i("i",[e._v("上午")])]),e._v(" "),i("span",[i("i",{staticClass:"gold"},[e._v("¥"+e._s(t.am.ghfy))])]),e._v(" "),i("span",[i("i",{staticClass:"red"},[e._v("余"+e._s(t.am.xhzs))])])]):e._e(),e._v(" "),t.pm?i("div",{on:{click:function(i){e.showDetailDig(t.pm)}}},[i("span",{staticClass:"double"},[i("p",[e._v(e._s(t.pm.yzrq.substring(0,10)))]),e._v(" "),i("i",[e._v(e._s(t.pm.xq))]),e._v(" "),i("i",[e._v("下午")])]),e._v(" "),i("span",[i("i",{staticClass:"gold"},[e._v("¥"+e._s(t.pm.ghfy))])]),e._v(" "),i("span",[i("i",{staticClass:"red"},[e._v("余"+e._s(t.pm.xhzs))])])]):e._e()])})):i("div",{staticClass:"introduce"},[e._v("\n                "+e._s(e.docDetail.czyjj)+"\n            ")])])]):e._e(),e._v(" "),i("my-dialog",{attrs:{show:e.showDialogPatient,cbClose:e.closeDialogPatient}},[i("p",{staticClass:"TITLE",slot:"title"},[e._v("选择就诊人")]),e._v(" "),i("div",{staticClass:"content",slot:"content"},[i("ul",{staticClass:"patientList"},e._l(e.patient,function(t){return i("li",{on:{click:function(i){e.choosePatient(t)}}},[i("p",{staticClass:"center"},[e._v("姓名:")]),i("p",[e._v(e._s(t.brxm))])])}))])]),e._v(" "),i("my-dialog",{attrs:{show:e.showDialog,cbClose:e.closeDialog}},[i("p",{staticClass:"TITLE",slot:"title"},[e._v("挂号提醒")]),e._v(" "),i("div",{staticClass:"content showAppointInfo",slot:"content"},[i("li",[i("span",[e._v("就诊人:")]),i("span",{staticClass:"darkGreen",on:{click:e.showPaient}},[e._v(e._s(e.selectedPatient.brxm))])]),e._v(" "),i("li",[i("span",[e._v("就诊科室:")]),i("span",{staticClass:"darkGreen"},[e._v(e._s(e.chooseOne.ksmc))])]),e._v(" "),i("li",[i("span",[e._v("就诊医生:")]),i("span",{staticClass:"darkGreen"},[e._v(e._s(e.chooseOne.czyxm))])]),e._v(" "),i("li",[i("span",[e._v("费用:")]),i("span",{staticClass:"gold"},[e._v(e._s(e.chooseOne.ghfy))])]),e._v(" "),i("li",[i("span",[e._v("就诊日期:")]),i("span",{staticClass:"darkGreen"},[e._v(e._s(e.chooseOne.yydjsj&&e.chooseOne.yydjsj.substr(0,10)))])])]),e._v(" "),i("div",{staticClass:"button",slot:"button"},[i("button",{on:{click:e.confirmToPay}},[e._v("确定挂号")])])])],1)},staticRenderFns:[function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("span",{staticClass:"icon"},[i("span",{staticClass:"photo"})])}]}},184:function(e,t){!function(t,i){e.exports=i(t)}(window,function(e,t){function i(t,i,n){e.WeixinJSBridge?WeixinJSBridge.invoke(t,o(i),function(e){c(t,e,n)}):l(t,n)}function n(t,i,n){e.WeixinJSBridge?WeixinJSBridge.on(t,function(e){n&&n.trigger&&n.trigger(e),c(t,e,i)}):n?l(t,n):l(t,i)}function o(e){return e=e||{},e.appId=D.appId,e.verifyAppId=D.appId,e.verifySignType="sha1",e.verifyTimestamp=D.timestamp+"",e.verifyNonceStr=D.nonceStr,e.verifySignature=D.signature,e}function s(e){return{timeStamp:e.timestamp+"",nonceStr:e.nonceStr,package:e.package,paySign:e.paySign,signType:e.signType||"SHA1"}}function a(e){return e.postalCode=e.addressPostalCode,delete e.addressPostalCode,e.provinceName=e.proviceFirstStageName,delete e.proviceFirstStageName,e.cityName=e.addressCitySecondStageName,delete e.addressCitySecondStageName,e.countryName=e.addressCountiesThirdStageName,delete e.addressCountiesThirdStageName,e.detailInfo=e.addressDetailInfo,delete e.addressDetailInfo,e}function c(e,t,i){"openEnterpriseChat"==e&&(t.errCode=t.err_code),delete t.err_code,delete t.err_desc,delete t.err_detail;var n=t.errMsg;n||(n=t.err_msg,delete t.err_msg,n=r(e,n),t.errMsg=n),i=i||{},i._complete&&(i._complete(t),delete i._complete),n=t.errMsg||"",D.debug&&!i.isInnerInvoke&&alert(JSON.stringify(t));var o=n.indexOf(":"),s=n.substring(o+1);switch(s){case"ok":i.success&&i.success(t);break;case"cancel":i.cancel&&i.cancel(t);break;default:i.fail&&i.fail(t)}i.complete&&i.complete(t)}function r(e,t){var i=e,n=g[i];n&&(i=n);var o="ok";if(t){var s=t.indexOf(":");o=t.substring(s+1),"confirm"==o&&(o="ok"),"failed"==o&&(o="fail"),-1!=o.indexOf("failed_")&&(o=o.substring(7)),-1!=o.indexOf("fail_")&&(o=o.substring(5)),o=o.replace(/_/g," "),o=o.toLowerCase(),("access denied"==o||"no permission to execute"==o)&&(o="permission denied"),"config"==i&&"function not exist"==o&&(o="ok"),""==o&&(o="fail")}return t=i+":"+o}function d(e){if(e){for(var t=0,i=e.length;i>t;++t){var n=e[t],o=m[n];o&&(e[t]=o)}return e}}function l(e,t){if(!(!D.debug||t&&t.isInnerInvoke)){var i=g[e];i&&(e=i),t&&t._complete&&delete t._complete,console.log('"'+e+'",',t||"")}}function u(e){if(!(w||x||D.debug||"6.0.2">T||P.systemType<0)){var t=new Image;P.appId=D.appId,P.initTime=b.initEndTime-b.initStartTime,P.preVerifyTime=b.preVerifyEndTime-b.preVerifyStartTime,B.getNetworkType({isInnerInvoke:!0,success:function(e){P.networkType=e.networkType;var i="https://open.weixin.qq.com/sdk/report?v="+P.version+"&o="+P.isPreVerifyOk+"&s="+P.systemType+"&c="+P.clientVersion+"&a="+P.appId+"&n="+P.networkType+"&i="+P.initTime+"&p="+P.preVerifyTime+"&u="+P.url;t.src=i}})}}function p(){return(new Date).getTime()}function f(t){I&&(e.WeixinJSBridge?t():v.addEventListener&&v.addEventListener("WeixinJSBridgeReady",t,!1))}function h(){B.invoke||(B.invoke=function(t,i,n){e.WeixinJSBridge&&WeixinJSBridge.invoke(t,o(i),n)},B.on=function(t,i){e.WeixinJSBridge&&WeixinJSBridge.on(t,i)})}if(!e.jWeixin){var m={config:"preVerifyJSAPI",onMenuShareTimeline:"menu:share:timeline",onMenuShareAppMessage:"menu:share:appmessage",onMenuShareQQ:"menu:share:qq",onMenuShareWeibo:"menu:share:weiboApp",onMenuShareQZone:"menu:share:QZone",previewImage:"imagePreview",getLocation:"geoLocation",openProductSpecificView:"openProductViewWithPid",addCard:"batchAddCard",openCard:"batchViewCard",chooseWXPay:"getBrandWCPayRequest",openEnterpriseRedPacket:"getRecevieBizHongBaoRequest",startSearchBeacons:"startMonitoringBeacons",stopSearchBeacons:"stopMonitoringBeacons",onSearchBeacons:"onBeaconsInRange",consumeAndShareCard:"consumedShareCard",openAddress:"editAddress"},g=function(){var e={};for(var t in m)e[m[t]]=t;return e}(),v=e.document,_=v.title,S=navigator.userAgent.toLowerCase(),y=navigator.platform.toLowerCase(),w=!(!y.match("mac")&&!y.match("win")),x=-1!=S.indexOf("wxdebugger"),I=-1!=S.indexOf("micromessenger"),k=-1!=S.indexOf("android"),C=-1!=S.indexOf("iphone")||-1!=S.indexOf("ipad"),T=function(){var e=S.match(/micromessenger\/(\d+\.\d+\.\d+)/)||S.match(/micromessenger\/(\d+\.\d+)/);return e?e[1]:""}(),b={initStartTime:p(),initEndTime:0,preVerifyStartTime:0,preVerifyEndTime:0},P={version:1,appId:"",initTime:0,preVerifyTime:0,networkType:"",isPreVerifyOk:1,systemType:C?1:k?2:-1,clientVersion:T,url:encodeURIComponent(location.href)},D={},O={_completes:[]},M={state:0,data:{}};f(function(){b.initEndTime=p()});var A=!1,L=[],B={config:function(e){D=e,l("config",e);var t=D.check!==!1;f(function(){if(t)i(m.config,{verifyJsApiList:d(D.jsApiList)},function(){O._complete=function(e){b.preVerifyEndTime=p(),M.state=1,M.data=e},O.success=function(e){P.isPreVerifyOk=0},O.fail=function(e){O._fail?O._fail(e):M.state=-1};var e=O._completes;return e.push(function(){u()}),O.complete=function(t){for(var i=0,n=e.length;n>i;++i)e[i]();O._completes=[]},O}()),b.preVerifyStartTime=p();else{M.state=1;for(var e=O._completes,n=0,o=e.length;o>n;++n)e[n]();O._completes=[]}}),D.beta&&h()},ready:function(e){0!=M.state?e():(O._completes.push(e),!I&&D.debug&&e())},error:function(e){"6.0.2">T||(-1==M.state?e(M.data):O._fail=e)},checkJsApi:function(e){var t=function(e){var t=e.checkResult;for(var i in t){var n=g[i];n&&(t[n]=t[i],delete t[i])}return e};i("checkJsApi",{jsApiList:d(e.jsApiList)},function(){return e._complete=function(e){if(k){var i=e.checkResult;i&&(e.checkResult=JSON.parse(i))}e=t(e)},e}())},onMenuShareTimeline:function(e){n(m.onMenuShareTimeline,{complete:function(){i("shareTimeline",{title:e.title||_,desc:e.title||_,img_url:e.imgUrl||"",link:e.link||location.href,type:e.type||"link",data_url:e.dataUrl||""},e)}},e)},onMenuShareAppMessage:function(e){n(m.onMenuShareAppMessage,{complete:function(){i("sendAppMessage",{title:e.title||_,desc:e.desc||"",link:e.link||location.href,img_url:e.imgUrl||"",type:e.type||"link",data_url:e.dataUrl||""},e)}},e)},onMenuShareQQ:function(e){n(m.onMenuShareQQ,{complete:function(){i("shareQQ",{title:e.title||_,desc:e.desc||"",img_url:e.imgUrl||"",link:e.link||location.href},e)}},e)},onMenuShareWeibo:function(e){n(m.onMenuShareWeibo,{complete:function(){i("shareWeiboApp",{title:e.title||_,desc:e.desc||"",img_url:e.imgUrl||"",link:e.link||location.href},e)}},e)},onMenuShareQZone:function(e){n(m.onMenuShareQZone,{complete:function(){i("shareQZone",{title:e.title||_,desc:e.desc||"",img_url:e.imgUrl||"",link:e.link||location.href},e)}},e)},startRecord:function(e){i("startRecord",{},e)},stopRecord:function(e){i("stopRecord",{},e)},onVoiceRecordEnd:function(e){n("onVoiceRecordEnd",e)},playVoice:function(e){i("playVoice",{localId:e.localId},e)},pauseVoice:function(e){i("pauseVoice",{localId:e.localId},e)},stopVoice:function(e){i("stopVoice",{localId:e.localId},e)},onVoicePlayEnd:function(e){n("onVoicePlayEnd",e)},uploadVoice:function(e){i("uploadVoice",{localId:e.localId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},downloadVoice:function(e){i("downloadVoice",{serverId:e.serverId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},translateVoice:function(e){i("translateVoice",{localId:e.localId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},chooseImage:function(e){i("chooseImage",{scene:"1|2",count:e.count||9,sizeType:e.sizeType||["original","compressed"],sourceType:e.sourceType||["album","camera"]},function(){return e._complete=function(e){if(k){var t=e.localIds;t&&(e.localIds=JSON.parse(t))}},e}())},getLocation:function(e){},previewImage:function(e){i(m.previewImage,{current:e.current,urls:e.urls},e)},uploadImage:function(e){i("uploadImage",{localId:e.localId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},downloadImage:function(e){i("downloadImage",{serverId:e.serverId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},getLocalImgData:function(e){A===!1?(A=!0,i("getLocalImgData",{localId:e.localId},function(){return e._complete=function(e){if(A=!1,L.length>0){var t=L.shift();wx.getLocalImgData(t)}},e}())):L.push(e)},getNetworkType:function(e){var t=function(e){var t=e.errMsg;e.errMsg="getNetworkType:ok";var i=e.subtype;if(delete e.subtype,i)e.networkType=i;else{var n=t.indexOf(":"),o=t.substring(n+1);switch(o){case"wifi":case"edge":case"wwan":e.networkType=o;break;default:e.errMsg="getNetworkType:fail"}}return e};i("getNetworkType",{},function(){return e._complete=function(e){e=t(e)},e}())},openLocation:function(e){i("openLocation",{latitude:e.latitude,longitude:e.longitude,name:e.name||"",address:e.address||"",scale:e.scale||28,infoUrl:e.infoUrl||""},e)},getLocation:function(e){e=e||{},i(m.getLocation,{type:e.type||"wgs84"},function(){return e._complete=function(e){delete e.type},e}())},hideOptionMenu:function(e){i("hideOptionMenu",{},e)},showOptionMenu:function(e){i("showOptionMenu",{},e)},closeWindow:function(e){e=e||{},i("closeWindow",{},e)},hideMenuItems:function(e){i("hideMenuItems",{menuList:e.menuList},e)},showMenuItems:function(e){i("showMenuItems",{menuList:e.menuList},e)},hideAllNonBaseMenuItem:function(e){i("hideAllNonBaseMenuItem",{},e)},showAllNonBaseMenuItem:function(e){i("showAllNonBaseMenuItem",{},e)},scanQRCode:function(e){e=e||{},i("scanQRCode",{needResult:e.needResult||0,scanType:e.scanType||["qrCode","barCode"]},function(){return e._complete=function(e){if(C){var t=e.resultStr;if(t){var i=JSON.parse(t);e.resultStr=i&&i.scan_code&&i.scan_code.scan_result}}},e}())},openAddress:function(e){i(m.openAddress,{},function(){return e._complete=function(e){e=a(e)},e}())},openProductSpecificView:function(e){i(m.openProductSpecificView,{pid:e.productId,view_type:e.viewType||0,ext_info:e.extInfo},e)},addCard:function(e){for(var t=e.cardList,n=[],o=0,s=t.length;s>o;++o){var a=t[o],c={card_id:a.cardId,card_ext:a.cardExt};n.push(c)}i(m.addCard,{card_list:n},function(){return e._complete=function(e){var t=e.card_list;if(t){t=JSON.parse(t);for(var i=0,n=t.length;n>i;++i){var o=t[i];o.cardId=o.card_id,o.cardExt=o.card_ext,o.isSuccess=!!o.is_succ,delete o.card_id,delete o.card_ext,delete o.is_succ}e.cardList=t,delete e.card_list}},e}())},chooseCard:function(e){i("chooseCard",{app_id:D.appId,location_id:e.shopId||"",sign_type:e.signType||"SHA1",card_id:e.cardId||"",card_type:e.cardType||"",card_sign:e.cardSign,time_stamp:e.timestamp+"",nonce_str:e.nonceStr},function(){return e._complete=function(e){e.cardList=e.choose_card_info,delete e.choose_card_info},e}())},openCard:function(e){for(var t=e.cardList,n=[],o=0,s=t.length;s>o;++o){var a=t[o],c={card_id:a.cardId,code:a.code};n.push(c)}i(m.openCard,{card_list:n},e)},consumeAndShareCard:function(e){i(m.consumeAndShareCard,{consumedCardId:e.cardId,consumedCode:e.code},e)},chooseWXPay:function(e){i(m.chooseWXPay,s(e),e)},openEnterpriseRedPacket:function(e){i(m.openEnterpriseRedPacket,s(e),e)},startSearchBeacons:function(e){i(m.startSearchBeacons,{ticket:e.ticket},e)},stopSearchBeacons:function(e){i(m.stopSearchBeacons,{},e)},onSearchBeacons:function(e){n(m.onSearchBeacons,e)},openEnterpriseChat:function(e){i("openEnterpriseChat",{useridlist:e.userIds,chatname:e.groupName},e)}},V=1,E={};return v.addEventListener("error",function(e){if(!k){var t=e.target,i=t.tagName,n=t.src;if("IMG"==i||"VIDEO"==i||"AUDIO"==i||"SOURCE"==i){var o=-1!=n.indexOf("wxlocalresource://");if(o){e.preventDefault(),e.stopPropagation();var s=t["wx-id"];if(s||(s=V++,t["wx-id"]=s),E[s])return;E[s]=!0,wx.getLocalImgData({localId:n,success:function(e){t.src=e.localData}})}}}},!0),v.addEventListener("load",function(e){if(!k){var t=e.target,i=t.tagName;if(t.src,"IMG"==i||"VIDEO"==i||"AUDIO"==i||"SOURCE"==i){var n=t["wx-id"];n&&(E[n]=!1)}}},!0),t&&(e.wx=e.jWeixin=B),B}})}});
webpackJsonp([15,19],{68:function(t,e,s){"use strict";function i(t){return t&&t.__esModule?t:{default:t}}Object.defineProperty(e,"__esModule",{value:!0});var a=s(3);i(a);e.default={data:function(){return{showItem:"",showDialog:!1,risreport:{}}},components:{},methods:{closeDialog:function(){this.showDialog=!1},showDetail:function(t){this.showItem=t,this.$nextTick(function(t){}),this.showDialog=!0}},mounted:function(){this.risreport=this.$store.getters.risreport}}},82:function(t,e,s){e=t.exports=s(1)(),e.push([t.id,"#risreport[data-v-1a1e324e]{font-size:1.8rem;background:#fff;height:100%}#risreport .flex2[data-v-1a1e324e]{-ms-flex:2;flex:2}#risreport .flex3[data-v-1a1e324e]{-ms-flex:3;flex:3}#risreport .textLeft[data-v-1a1e324e]{text-align:left}#risreport li[data-v-1a1e324e]{min-height:4rem;border-bottom:1px solid #838383;box-sizing:border-box;padding-left:1rem;line-height:4rem;-ms-flex-direction:column;flex-direction:column}#risreport li[data-v-1a1e324e],#risreport li div[data-v-1a1e324e]{display:-ms-flexbox;display:flex}#risreport li span[data-v-1a1e324e]{-ms-flex:1;flex:1;min-height:4rem;line-height:4rem;text-align:left}#risreport span.sex[data-v-1a1e324e]{text-align:center}#risreport ul section p[data-v-1a1e324e]{text-indent:2rem}#risreport div.dic[data-v-1a1e324e]{box-sizing:border-box;padding:1rem;max-height:20rem;overflow:scroll}#risreport .empty[data-v-1a1e324e]{border:none}#risreport .empty span[data-v-1a1e324e]{text-align:center}#risreport ul.detailInfo[data-v-1a1e324e]{font-size:1.8rem}#risreport ul.detailInfo li[data-v-1a1e324e]{-ms-flex-direction:row;flex-direction:row}#risreport ul.detailInfo i[data-v-1a1e324e]{height:4rem;display:inline-block;line-height:4rem}",""])},101:function(t,e,s){var i=s(82);"string"==typeof i&&(i=[[t.id,i,""]]);s(2)(i,{});i.locals&&(t.exports=i.locals)},137:function(t,e,s){var i,a;s(101),i=s(68);var r=s(146);a=i=i||{},"object"!=typeof i.default&&"function"!=typeof i.default||(a=i=i.default),"function"==typeof a&&(a=a.options),a.render=r.render,a.staticRenderFns=r.staticRenderFns,a._scopeId="data-v-1a1e324e",t.exports=i},146:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{attrs:{id:"risreport"}},[s("ul",[s("p",{staticClass:"TITLE"},[t._v("检查记录")]),t._v(" "),t._l(t.risreport,function(e){return s("li",{on:{click:function(s){t.showDetail(e)}}},[s("div",[s("span",[s("i",[t._v("姓名:")]),s("i",{staticClass:"darkBlue"},[t._v(t._s(e.nAME))])]),t._v(" "),s("span",[s("i",[t._v("科室:")]),s("i",{staticClass:"darkBlue"},[t._v(t._s(e.lODGESECTION))])])]),t._v(" "),s("div",[s("span",[s("i",[t._v("检查日期:")]),s("i",{staticClass:"darkBlue"},[t._v(t._s(e.lODGEDATE.substr(0,10)))])])])])}),t._v(" "),0==t.risreport.length?s("li",{staticClass:"empty"},[s("span",[t._v("暂无数据！")])]):t._e()],2),t._v(" "),s("my-dialog",{attrs:{show:t.showDialog,cbClose:t.closeDialog}},[s("p",{staticClass:"TITLE",slot:"title"},[t._v("彩超检查报告")]),t._v(" "),t.showItem?s("ul",{staticClass:"detailInfo",slot:"content"},[s("li",[s("span",[s("i",[t._v("姓名:")]),s("i",{staticClass:"darkBlue"},[t._v(t._s(t.showItem.nAME))])]),t._v(" "),s("span",{staticClass:"sex"},[s("i",[t._v("性别:")]),s("i",{staticClass:"darkBlue"},[t._v(t._s(t.showItem.sEX))])]),t._v(" "),s("span",[s("i",[t._v("年龄:")]),s("i",{staticClass:"darkBlue"},[t._v(t._s(t.showItem.aGE)+"岁")])])]),t._v(" "),s("li",[s("span",{staticClass:"flex2"},[s("i",[t._v("科室:")]),s("i",{staticClass:"darkBlue"},[t._v(t._s(t.showItem.lODGESECTION))])]),t._v(" "),s("span",{staticClass:"flex3"},[s("i",[t._v("住院号:")]),s("i",{staticClass:"darkBlue"},[t._v(t._s(t.showItem.iNPATIENTNO))])])]),t._v(" "),s("li",[s("span",[s("i",[t._v("项目:")]),s("i",{staticClass:"darkBlue"},[t._v(t._s(t.showItem.cLASSNAME))])])]),t._v(" "),s("li",[s("span",[s("i",[t._v("医生:")]),s("i",{staticClass:"darkBlue"},[t._v(t._s(t.showItem.lODGEDOCTOR))])]),t._v(" "),s("span",[s("i",[t._v("状态:")]),s("i",{staticClass:"darkBlue"},[t._v(t._s(t.showItem.sTATUS))])])]),t._v(" "),s("li",[s("span",[s("i",[t._v("检查部位:")]),s("i",{staticClass:"darkBlue"},[t._v(t._s(t.showItem.pARTOFCHECK))])])]),t._v(" "),s("div",{staticClass:"dic"},[s("p",{staticClass:"textLeft"},[t._v("描述:")]),t._v(" "),s("section",{staticClass:"darkBlue"},[s("p",[t._v(t._s(t.showItem.laybe1))])]),t._v(" "),s("p",{staticClass:"textLeft"},[t._v("提示:")]),t._v(" "),s("section",{staticClass:"darkBlue"},[s("p",[t._v(t._s(t.showItem.laybe2))])]),t._v(" "),s("div")])]):t._e(),t._v(" "),s("div",{staticClass:"button",slot:"button"},[s("button",{on:{click:t.closeDialog}},[t._v("确定")])])])],1)},staticRenderFns:[]}}});
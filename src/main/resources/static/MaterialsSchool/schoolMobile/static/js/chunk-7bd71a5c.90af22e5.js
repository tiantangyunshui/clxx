(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-7bd71a5c"],{"60b5":function(e,t,a){"use strict";a.d(t,"a",(function(){return n}));a("27ae");function r(e,t,a,r,n,i,o){try{var s=e[i](o),l=s.value}catch(u){return void a(u)}s.done?t(l):Promise.resolve(l).then(r,n)}function n(e){return function(){var t=this,a=arguments;return new Promise((function(n,i){var o=e.apply(t,a);function s(e){r(o,n,i,s,l,"next",e)}function l(e){r(o,n,i,s,l,"throw",e)}s(void 0)}))}}},"8fd0":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"apply-out"},[a("div",{directives:[{name:"show",rawName:"v-show",value:e.applyMask,expression:"applyMask"}],staticClass:"apply-mask"}),a("van-nav-bar",{attrs:{title:e.title,"left-arrow":"",border:"",fixed:!0},on:{"click-left":e.onClickBack,"click-right":e.onClickExit},scopedSlots:e._u([{key:"right",fn:function(){return[a("van-icon",{attrs:{name:"cross",size:"20"}})]},proxy:!0}])}),a("van-form",{ref:"applyForm",on:{submit:e.applySubmit}},[a("div",{staticClass:"teacher-approve-info"},[a("div",{staticClass:"teacher-info"},[e._v("审批教师信息")]),a("van-field",{attrs:{maxlength:"11",readonly:!this.$route.query.hasOwnProperty("addName"),autocomplete:"off",name:"teacherTelephone",label:"手机号码:"},model:{value:e.applyForm.teacherTelephone,callback:function(t){e.$set(e.applyForm,"teacherTelephone",t)},expression:"applyForm.teacherTelephone"}}),a("van-field",{attrs:{readonly:"",placeholder:"请先输入手机号码",autocomplete:"off",name:"teacherName",label:"教师姓名:"},model:{value:e.applyForm.teacherName,callback:function(t){e.$set(e.applyForm,"teacherName",t)},expression:"applyForm.teacherName"}})],1),a("div",{staticClass:"student-approval-info"},[a("div",{staticClass:"student-info"},[e._v("学生信息")]),a("div",{staticClass:"picture"},[a("van-image",{attrs:{round:"",width:"3rem",height:"2.4rem",src:e.detailData.faceImg?e.detailData.faceImg:e.picUrl}}),a("div",{staticStyle:{width:"100%"}},[a("van-field",{attrs:{readonly:!this.$route.query.hasOwnProperty("addName"),autocomplete:"off",name:"studentNumber","label-width":"3em",label:"学号:"},model:{value:e.applyForm.studentNumber,callback:function(t){e.$set(e.applyForm,"studentNumber",t)},expression:"applyForm.studentNumber"}}),a("van-field",{attrs:{readonly:!this.$route.query.hasOwnProperty("addName"),autocomplete:"off",name:"studentName","label-width":"3em",label:"姓名:"},model:{value:e.applyForm.studentName,callback:function(t){e.$set(e.applyForm,"studentName",t)},expression:"applyForm.studentName"}})],1)],1),a("van-field",{attrs:{autocomplete:"off",name:"outReason",label:"outPage"===e.outName?"外出事由:":"返校事由:",readonly:!this.$route.query.hasOwnProperty("addName")},model:{value:e.applyForm.outReason,callback:function(t){e.$set(e.applyForm,"outReason",t)},expression:"applyForm.outReason"}}),a("div",{staticClass:"time"},[a("van-field",{attrs:{autocomplete:"off",name:"leavingTime",label:"outPage"===e.outName?"出校时间:":"返校时间:",placeholder:"outPage"===e.outName?"请选择出校时间":"请选择返校时间",readonly:""},on:{click:function(t){e.addNameStates&&e.leave()}},model:{value:e.applyForm.leavingTime,callback:function(t){e.$set(e.applyForm,"leavingTime",t)},expression:"applyForm.leavingTime"}}),a("van-field",{attrs:{autocomplete:"off",name:"returnTime",label:"outPage"===e.outName?"回校时间:":"离校时间:",placeholder:"outPage"===e.outName?"请选择回校时间":"请选择离校时间",readonly:""},on:{click:function(t){e.addNameStates&&e.backTime()}},model:{value:e.applyForm.returnTime,callback:function(t){e.$set(e.applyForm,"returnTime",t)},expression:"applyForm.returnTime"}})],1),a("van-popup",{attrs:{position:"bottom"},model:{value:e.leaveShow,callback:function(t){e.leaveShow=t},expression:"leaveShow"}},[a("van-datetime-picker",{attrs:{type:"datetime","min-date":e.minDate,"max-date":e.maxDate,formatter:e.formatter},on:{confirm:e.leaveConfirm,cancel:e.leaveCancel},model:{value:e.leaveCurrentDate,callback:function(t){e.leaveCurrentDate=t},expression:"leaveCurrentDate"}})],1),a("van-popup",{attrs:{position:"bottom"},model:{value:e.returnShow,callback:function(t){e.returnShow=t},expression:"returnShow"}},[a("van-datetime-picker",{attrs:{type:"datetime","min-date":e.minDate,"max-date":e.maxDate,formatter:e.formatter},on:{confirm:e.backConfirm,cancel:e.backCancel},model:{value:e.returnCurrentDate,callback:function(t){e.returnCurrentDate=t},expression:"returnCurrentDate"}})],1)],1),a("div",{staticClass:"health-promise"},[a("div",{staticClass:"health-promise-info"},[e._v("健康承诺")]),a("van-checkbox",{attrs:{disabled:!this.$route.query.hasOwnProperty("addName"),name:"promiseChecked",shape:"square","icon-size":"14px"},model:{value:e.applyForm.promiseChecked,callback:function(t){e.$set(e.applyForm,"promiseChecked",t)},expression:"applyForm.promiseChecked"}},[e._v("我承诺上述填写内容真实，准确，无误！")]),a("div",{staticClass:"checkbox-group"},[a("van-checkbox",{attrs:{disabled:!this.$route.query.hasOwnProperty("addName"),name:"remark2",shape:"square","icon-size":"14px"},model:{value:e.applyForm.remark2,callback:function(t){e.$set(e.applyForm,"remark2",t)},expression:"applyForm.remark2"}},[e._v("1.近14天内无境内外中高风险区域居住史")]),a("van-checkbox",{attrs:{disabled:!this.$route.query.hasOwnProperty("addName"),name:"remark3",shape:"square","icon-size":"14px"},model:{value:e.applyForm.remark3,callback:function(t){e.$set(e.applyForm,"remark3",t)},expression:"applyForm.remark3"}},[e._v("2.无确诊，疑似病历接触史")]),a("van-checkbox",{attrs:{disabled:!this.$route.query.hasOwnProperty("addName"),name:"remark4",shape:"square","icon-size":"14px"},model:{value:e.applyForm.remark4,callback:function(t){e.$set(e.applyForm,"remark4",t)},expression:"applyForm.remark4"}},[e._v("3.无发烧，咳嗽，胸闷，气促")])],1)],1),e.applyPageButton?a("div",{staticStyle:{margin:"16px"},style:e.addNameStates?"":"display:none"},[a("van-button",{staticClass:"info-submit",attrs:{round:"",block:"",loading:e.loading,"loading-text":"提交中...",type:"info","native-type":"submit",disabled:!e.applyForm.promiseChecked}},[e._v("提交")])],1):a("div",{staticClass:"pass",staticStyle:{margin:"16px"},style:"1"===e.detailData.state?"display:none":""},[a("van-button",{staticClass:"pass-submit",attrs:{round:"",block:"",type:"info","native-type":"button"},on:{click:function(t){return e.passClick(1)}}},[e._v("通过")]),a("van-button",{staticClass:"no-pass-submit",attrs:{round:"",block:"",type:"warning","native-type":"button"},on:{click:function(t){return e.passClick(2)}}},[e._v("不通过")])],1)])],1)},n=[],i=a("60b5"),o=(a("6a61"),a("27ae"),a("d447"),a("3337"),a("2c76"),a("24a8"),a("62f9"),{name:"Index",data:function(){return{applyMask:!1,picUrl:a("b31e"),detailData:{},loading:!1,teacherId:"",addNameStates:!1,addType:0,outName:"",roleType:"",title:"",leaveShow:!1,returnShow:!1,minHour:10,maxHour:20,minDate:new Date(2010,11,31),maxDate:new Date(2051,11,31),leaveCurrentDate:new Date,returnCurrentDate:new Date,leaveTimeStamp:"",returnTimeStamp:"",applyForm:{teacherTelephone:"",teacherName:"",studentNumber:"",studentName:"",outReason:"",leavingTime:"",returnTime:"",promiseChecked:!1,remark2:!1,remark3:!1,remark4:!1},applyPageButton:!0}},watch:{"applyForm.teacherTelephone":function(e){11===e.length?this.handleteacherName(e):this.applyForm.teacherName=""}},mounted:function(){console.log("store-----------",this.$store.getters),this.$route.query&&(this.outName=this.$route.query.outName,this.addType="addOutPage"===this.$route.query.addName?0:1),this.roleType=this.$store.getters.roleType,this.$route.query.hasOwnProperty("addName")?(this.applyForm.studentNumber=this.$store.getters.studentNum,this.applyForm.studentName=this.$store.getters.userName,this.picUrl=this.$store.getters.avatar,this.addNameStates=this.$route.query.hasOwnProperty("addName")):(this.detailData=JSON.parse(this.$route.query.detailData),console.log("detailData-----------",this.detailData),this.applyForm={teacherTelephone:this.detailData.phone,teacherName:this.detailData.teacherName,studentNumber:this.detailData.studentNo,studentName:this.detailData.studentName,outReason:this.detailData.reason,leavingTime:this.detailData.timeLeave,returnTime:this.detailData.timeBack,promiseChecked:this.detailData.remark1,remark2:this.detailData.remark2,remark3:this.detailData.remark3,remark4:this.detailData.remark4}),"1"===this.roleType?this.$route.query&&this.$route.query.outName?this.addNameStates?this.title="新建外出申请":this.title="外出申请详情":this.addNameStates?this.title="新建返校申请":this.title="返校申请详情":(this.applyPageButton=!1,this.detailData&&"0"===this.detailData.type?"0"===this.detailData.state?this.title="外出申批":this.title="外出申批详情":"0"===this.detailData.state?this.title="返校申批":this.title="返校申批详情")},methods:{passClick:function(e){var t=this;return Object(i["a"])(regeneratorRuntime.mark((function a(){var r,n;return regeneratorRuntime.wrap((function(a){while(1)switch(a.prev=a.next){case 0:return t.applyMask=!0,r={id:t.detailData.id.toString(),state:e.toString()},a.next=4,t.$api.apply.updApprovalData(r).catch((function(e){t.$toast("服务异常,请稍后再试！"),t.applyMask=!1}));case 4:n=a.sent,n?(t.$router.go(-1),t.applyMask=!1):(t.$toast("服务异常,请稍后再试！"),t.applyMask=!1);case 6:case"end":return a.stop()}}),a)})))()},checkTel:function(e){var t=/^[1][3,4,5,7,8][0-9]{9}$/;return t.test(e)},handleteacherName:function(e){var t=this;return Object(i["a"])(regeneratorRuntime.mark((function a(){var r,n,i;return regeneratorRuntime.wrap((function(a){while(1)switch(a.prev=a.next){case 0:if(r=t.checkTel(e),r){a.next=7;break}return t.$toast("手机号格式不正确"),t.applyForm.teacherName="",a.abrupt("return");case 7:return n={token:t.$store.getters.token,phone:e},a.next=10,t.$api.apply.teacherNameData(n).catch((function(e){t.$toast("服务异常，无法查询！")}));case 10:i=a.sent,i&&i.data&&0!==i.data.length?i.data.map((function(e){t.applyForm.teacherName=e.userName,t.teacherId=e.id})):(t.$toast("查询不到教师姓名"),t.applyForm.teacherName="");case 12:case"end":return a.stop()}}),a)})))()},onClickExit:function(){this.$store.dispatch("FedLogOut")},leave:function(){this.leaveShow=!0},backTime:function(){this.returnShow=!0},leaveConfirm:function(){this.leaveShow=!1,this.applyForm.leavingTime=this.leaveCurrentDate.getFullYear()+"-"+(Number(this.leaveCurrentDate.getMonth())+1).toString().padStart(2,"0")+"-"+this.leaveCurrentDate.getDate().toString().padStart(2,"0")+" "+this.leaveCurrentDate.getHours().toString().padStart(2,"0")+":"+this.leaveCurrentDate.getMinutes().toString().padStart(2,"0")+":00",this.leaveTimeStamp=new Date(this.leaveCurrentDate).getTime()/1e3},leaveCancel:function(){this.leaveShow=!1},backConfirm:function(){this.returnShow=!1,this.applyForm.returnTime=this.returnCurrentDate.getFullYear()+"-"+(Number(this.returnCurrentDate.getMonth())+1).toString().padStart(2,"0")+"-"+this.returnCurrentDate.getDate().toString().padStart(2,"0")+" "+this.returnCurrentDate.getHours().toString().padStart(2,"0")+":"+this.returnCurrentDate.getMinutes().toString().padStart(2,"0")+":00",this.returnTimeStamp=new Date(this.returnCurrentDate).getTime()/1e3},backCancel:function(){this.returnShow=!1},formatter:function(e,t){return"year"===e?"".concat(t,"年"):"month"===e?"".concat(t,"月"):"day"===e?"".concat(t,"日"):"hour"===e?"".concat(t,"时"):"minute"===e?"".concat(t,"分"):t},applySubmit:function(){var e=Object(i["a"])(regeneratorRuntime.mark((function e(t){var a,r,n,i,o=this;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:if(a=Object.keys(t).every((function(e){return t[e]})),!a){e.next=18;break}if(this.applyMask=!0,this.loading=!0,r=this.applyForm,!(new Date(t.leavingTime).getTime()>new Date(t.returnTime).getTime())){e.next=11;break}this.$toast("请选择正确的时间区间！"),this.loading=!1,this.applyMask=!1,e.next=16;break;case 11:return n={type:this.addType.toString(),teacherId:this.teacherId.toString(),studentId:this.$store.getters.roleId.toString(),reason:r.outReason,remark1:!0===r.promiseChecked?"1":"0",remark2:!0===r.remark2?"1":"0",remark3:!0===r.remark3?"1":"0",remark4:!0===r.remark4?"1":"0",timeBack:r.returnTime,timeLeave:r.leavingTime},e.next=14,this.$api.apply.addApplyData(n).catch((function(){o.$toast("服务异常，提交失败！"),o.applyMask=!1,o.loading=!1}));case 14:i=e.sent,i?(this.$router.go(-1),this.applyMask=!1,this.loading=!1):(this.applyForm.promiseChecked=!1,this.$toast("服务异常,请稍后再试！"),this.loading=!1,this.applyMask=!1,this.applyForm.promiseChecked=!1);case 16:e.next=20;break;case 18:this.$toast("请填写相应的值"),this.applyMask=!1;case 20:case"end":return e.stop()}}),e,this)})));function t(t){return e.apply(this,arguments)}return t}(),onClickBack:function(){this.$router.go(-1)}}}),s=o,l=(a("bc85"),a("5d22")),u=Object(l["a"])(s,r,n,!1,null,"225599a2",null);t["default"]=u.exports},"952c":function(e,t,a){},bc85:function(e,t,a){"use strict";a("952c")}}]);
import Vue from 'vue'
import Router from 'vue-router'
import login from '../components/login/login.vue'
import container from '../common/component/container.vue'
import shareCenter from '../components/sharecenter/sharecenter.vue'
import enterpriseInfo from '../components/enterpriseinfo/enterpriseinfo.vue'
import info from '../components/enterpriseinfo/pages/info.vue'
import user from '../components/enterpriseinfo/pages/user.vue'
import risk from '../components/enterpriseinfo/pages/risk.vue'
import material from '../components/enterpriseinfo/pages/material.vue'
import historyTask from '../components/enterpriseinfo/pages/historytask.vue'
import learn from '../components/enterpriseinfo/pages/learn.vue'
import emergency from '../components/enterpriseinfo/pages/emergency.vue'
import alarmManagement from '../components/alarmmanagement/alarmmanagement.vue'
import information from '../components/alarmmanagement/pages/information.vue'
import configure from '../components/alarmmanagement/pages/configure.vue'

Vue.use(Router)

export default new Router({
  routes: [{
    path: '/',
    redirect: '/login'
  }, {
    path: '/container',
    name: 'container',
    component: container,
    redirect: '/container/sharecenter',
    children: [{
      path: '/container/sharecenter',
      name: 'shareCenter',
      component: shareCenter,
    }, {
      path: '/container/enterpriseinfo',
      name: 'enterpriseInfo',
      component: enterpriseInfo,
      redirect: '/container/enterpriseinfo/info',
      children: [{
        path: '/container/enterpriseinfo/info',
        name: 'info',
        component: info,
        meta: { index: 0, name: '企业信息' }
      }, {
        path: '/container/enterpriseinfo/user',
        name: 'user',
        component: user,
        meta: { index: 1, name: '组织架构' }
      }, {
        path: '/container/enterpriseinfo/risk',
        name: 'risk',
        component: risk,
        meta: { index: 2, name: '风险识别' }
      }, {
        path: '/container/enterpriseinfo/material',
        name: 'material',
        component: material,
        meta: { index: 3, name: '器材配备' }
      }, {
        path: '/container/enterpriseinfo/historyTask',
        name: 'historyTask',
        component: historyTask,
        meta: { index: 4, name: '历史作业' }
      }, {
        path: '/container/enterpriseinfo/learn',
        name: 'learn',
        component: learn,
        meta: { index: 5, name: '安全培训' }
      }, {
        path: '/container/enterpriseinfo/emergency',
        name: 'emergency',
        component: emergency,
        meta: { index: 6, name: '应急管理' }
      }]
    }, {
      path: '/container/alarmmanagement',
      name: 'alarmManagement',
      component: alarmManagement,
      redirect: '/container/alarmmanagement/information',
      children: [{
        path: '/container/alarmmanagement/information',
        name: 'information',
        component: information,
        meta: { index: 0, name: '告警信息' }
      }, {
        path: '/container/alarmmanagement/configure',
        name: 'configure',
        component: configure,
        meta: { index: 1, name: '告警配置' }
      }]
    }]
  }, {
    path: '/login',
    name: 'login',
    component: login
  }]
})

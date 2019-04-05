<template>
  <div id="enterprise-info">
    <div class="left left-menu">
      <el-menu :default-active="routerIndex + ''" class="el-menu-vertical-demo" background-color="#fff" text-color="#202020" active-text-color="#fff" style="height: 100%" router>
        <el-menu-item :index="index+''" v-for="(menu, index) in menuList" :key="index" :route="menu.router" @click="selectMenu(index)">
          <img :src="menu.selImg" class="menu-item-icon" v-if="activeIndex == index">
          <img :src="menu.norImg" class="menu-item-icon" v-else>
          <span slot="title">{{menu.title}}</span>
        </el-menu-item>
      </el-menu>
    </div>
    <div class="module-view">
      <router-view></router-view>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      activeIndex: 0,
      menuList: [{
        title: '企业信息',
        router: '/container/enterpriseinfo/info',
        norImg: require('../../common/images/icon/icon_enterprise_nor.png'),
        selImg: require('../../common/images/icon/icon_enterprise_sel.png')
      }, {
        title: '组织架构',
        router: '/container/enterpriseinfo/user',
        norImg: require('../../common/images/icon/icon_organization_nor.png'),
        selImg: require('../../common/images/icon/icon_organization_sel.png')
      }, {
        title: '风险识别',
        router: '/container/enterpriseinfo/risk',
        norImg: require('../../common/images/icon/icon_risk_nor.png'),
        selImg: require('../../common/images/icon/icon_risk_sel.png')
      }, {
        title: '器材配备',
        router: '/container/enterpriseinfo/material',
        norImg: require('../../common/images/icon/icon_equipment_nor.png'),
        selImg: require('../../common/images/icon/icon_equipment_sel.png')
      }, {
        title: '历史作业',
        router: '/container/enterpriseinfo/historyTask',
        norImg: require('../../common/images/icon/icon_task_nor.png'),
        selImg: require('../../common/images/icon/icon_task_sel.png')
      }, {
        title: '安全培训',
        router: '/container/enterpriseinfo/learn',
        norImg: require('../../common/images/icon/icon_safe_nor.png'),
        selImg: require('../../common/images/icon/icon_safe_sel.png')
      }, {
        title: '应急管理',
        router: '/container/enterpriseinfo/emergency',
        norImg: require('../../common/images/icon/icon_emergency_nor.png'),
        selImg: require('../../common/images/icon/icon_emergency_sel.png')
      }]
    }
  },

  methods: {
    selectMenu(index) {
      this.activeIndex = index;
    }
  },

  computed: {
    routerIndex() {
      return this.activeIndex = this.$route.meta.index;
    }
  }
}

</script>
<style scoped>
#enterprise-info {
  position: absolute;
  display: flex;
  width: 100%;
  height: 93.8%;
}

.left-menu {
  width: 200px;
  height: 100%;
  box-shadow: 5px 1px 10px #e5e5e5;
}

.module-view {
  flex: 1;
  margin-left: 4px;
  padding-left: 11px;
  padding-top: 15px;
  padding-right: 15px;
  background-color: #F2F2F2
}

.menu-item-icon {
  width: 16px;
  height: 16px;
  margin-right: 6px;
}

.el-menu-item.is-active {
  background-color: #A0CDF9 !important;
  border-left: 3px solid #1B88F5;
}

.el-menu-item {
  height: 40px !important;
  line-height: 40px;
  border-left: 3px solid #fff;
}

.el-menu-item:hover {
  border-left: 3px solid #ccc;
}

</style>

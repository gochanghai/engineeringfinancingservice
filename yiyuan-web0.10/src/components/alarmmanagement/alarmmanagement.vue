<template>
  <div id="alarm-management">
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
        title: '告警信息',
        router: '/container/alarmmanagement/information',
        norImg: require('../../common/images/icon/icon_alarm_nor.png'),
        selImg: require('../../common/images/icon/icon_alarm_sel.png')
      }, {
        title: '告警配置',
        router: '/container/alarmmanagement/configure',
        norImg: require('../../common/images/icon/icoj_configure_nor.png'),
        selImg: require('../../common/images/icon/icoj_configure_sel.png')
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
#alarm-management {
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

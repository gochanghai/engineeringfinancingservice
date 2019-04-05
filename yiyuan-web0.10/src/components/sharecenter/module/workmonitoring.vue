<template>
  <div id="work-monitoring">
    <el-dialog title="有限空间作业监控" :visible.sync="workVisible" width="50%" :before-close="workClose">
      <div class="content work-content">
        <div class="media-info">
          <video style="width:55%;" controls>
            <source src="#" type="video/mp4">
            <source src="#" type="video/ogg">
            您的浏览器不支持 video。
          </video>
          <div class="list-info">
            <div class="list-info-one item1">
              <div class="more-item">
                <label>一氧化碳</label>
                <span style="color: #E90404;">0</span>
              </div>
              <div class="more-item">
                <label>硫化氢</label>
                <span style="color: #E90404;">0</span>
              </div>
              <div class="more-item">
                <label>氧气</label>
                <span style="color: #1B88F5;">19.5</span>
              </div>
              <div class="more-item">
                <label>人体活动检测</label>
                0
              </div>
              <div class="more-item">
                <label>喇叭</label>
                <el-button type="primary" size="mini" class='voice'>声音1</el-button>
                <el-button type="primary" size="mini" class='voice'>声音2</el-button>
                <el-button type="primary" size="mini" class='voice'>声音3</el-button>
              </div>
            </div>
            <div class="list-info-two item2">
              <h4>作业信息</h4>
              <div class="more-item">
                <label>作业名称</label>
                <span>有限空间</span>
              </div>
              <div class="more-item">
                <label>作业地点</label>
                <span>深圳市南山区</span>
              </div>
              <div class="more-item">
                <label>作业时间</label>
                <span>2019/03/15</span>
              </div>
              <div class="more-item">
                <label>作业人数</label>
                <span>2</span>
              </div>
              <div class="more-item">
                <label>作业人员</label>
                <span>2</span>
              </div>
              <div class="more-item">
                <label>安全管理员</label>
                <span>张飞</span>
              </div>
            </div>
          </div>
        </div>
        <div class="bottom-info nav-info">
          <span v-for="(item, index) in navList" :key="index" @click="selectType(index)" :style="{color: typeIndex == index ? '#1B88F5' : ''}">{{item}}
            <label class="active" v-show="typeIndex == index"></label>
          </span>
        </div>
        <div class="nav-view">
          <div v-show="typeIndex == 0">浓度：99%</div>
        </div>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  props: {
    workVisible: {
      type: Boolean,
      default: true
    }
  },

  data() {
    return {
      navList: ["一氧化碳", "硫化氢", "氧气", "人体活动检测"],
      typeIndex: 0
    }
  },

  methods: {
    selectType(index) {
      this.typeIndex = index;
    },

    workClose() {
      this.$emit('workClose', false);
    }
  }
}

</script>
<style scoped>
.media-info {
  display: flex;
}

.list-info {
  flex: 1;
  padding: 0 15px;
}

.list-info-one {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding-bottom: 15px;
  height: 150px;
  border-bottom: 1px solid #ccc;
}

.more-item {
  height: 30px;
}

.more-item label {
  display: inline-block;
  width: 95px;
  color: #555555;
  font-size: 13px;
  color: #555555;
}
.more-item span{
  color: #202020;
}

.voice {
  text-align: center;
}

.list-info-two {
  padding-top: 8px;
}

.list-info-two h4 {
  line-height: 30px;
}

.bottom-info {
  margin-top: 15px;
}

.bottom-info span {
  margin-right: 25px;
  display: inline-block;
}

.bottom-info span:hover {
  cursor: pointer;
}

.bottom-info span label.active {
  width: 70%;
  margin: 4px auto;
  display: block;
  height: 3px;
  background-color: #1B88F5;
  border-radius: 5px;
}

.nav-view {
  margin-top: 7px;
  height: 50px;
  font-size: 14px;
  color: #202020;
}

</style>

<template>
  <!-- 安全培训 -->
  <div id="learn">
    <div class="operation">
      <el-upload action="" :on-change="uploadFile" :before-remove="beforeRemove">
        <el-button type="text" icon="el-icon-upload2">上传</el-button>
        <!-- <div class="file-name">name</div> -->
      </el-upload>
      <el-button type="text" icon="el-icon-delete">删除</el-button>
    </div>
    <el-tabs v-model="activeName" style="margin-top: 12px;">
      <el-tab-pane label="有限空间作业培训" name="spaceWork">
        <div class="list" v-for="item in 8" :key="item">
          <video style="width:93%;" controls>
            <source src="#" type="video/mp4">
            <source src="#" type="video/ogg">
            您的浏览器不支持 video 标签。
          </video>
          <label>上传时间：2019/03/19 17:30:00 <br />文件大小：1Mb</label>
        </div>
      </el-tab-pane>
      <el-tab-pane label="有毒有害气体专项培训" name="harm">
        <div class="list" v-for="item in 2" :key="item">
          <video style="width:93%;" controls>
            <source src="#" type="video/mp4">
            <source src="#" type="video/ogg">
            您的浏览器不支持 video 标签。
          </video>
          <label>上传时间：2019/03/19 17:30:00 <br />文件大小：1Mb</label>
        </div>
      </el-tab-pane>
      <el-tab-pane label="安全生产警示教育培训" name="education">
        <div class="list" v-for="item in 5" :key="item">
          <video style="width:93%;" controls>
            <source src="#" type="video/mp4">
            <source src="#" type="video/ogg">
            您的浏览器不支持 video 标签。
          </video>
          <label>上传时间：2019/03/19 17:30:00 <br />文件大小：1Mb</label>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
const securityEducationService = require('../../../service/securityEducationService.js');
export default {
  data() {
    return {
      activeName: 'spaceWork'
    }
  },
  created() {
    this.getListData();
  },
  methods: {
    /**
     * 获取安全培训列表数据
     */
    getListData() {
      securityEducationService.list(res => {
          console.log(list);
      });      
    },

    /**
     * 上传附件函数
     */
    uploadFile(file) {
      console.log(file);
    },
    /**
     * 删除前处理函数
     */
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    }
  }
}

</script>
<style scoped>
#learn {
  position: relative;
  padding: 0 20px;
  height: 100%;
  display: flex;
  flex-direction: column;
  background-color: #fff;
}

.select {
  padding: 0 15px;
  display: flex;
  align-items: center;
}

.select:last-child {
  padding-right: 0;
}

.select:hover {
  cursor: pointer;
}

.operation {
  position: absolute;
  top: 16px;
  right: 20px;
  width: 130px;
  display: flex;
  justify-content: space-between;
  z-index: 999;
}

.operation span {
  color: #1B88F5;
  font-size: 14px;
}

.operation img {
  width: 17px;
  height: 17px;
  margin-right: 5px;
}

.list {
  float: left;
  width: 20%;
  margin-bottom: 16px;
}

.list label {
  padding-top: 4px;
  font-size: 14px;
  color: #AAA;
  display: block;
}

</style>
<style>
#learn /deep/.el-tabs__nav-wrap::after {
  background-color: #fff !important
}

</style>

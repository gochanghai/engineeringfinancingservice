  <template>
  <!-- 企业信息 -->
  <div id="info">
    <div class="form-center">
      <div class="form-item">
        <label>企业信用代码</label>
        <el-input size="small" v-model="infoList.code" clearable></el-input>
      </div>
      <div class="form-item">
        <label>企业名称</label>
        <el-input size="small" v-model="infoList.name" clearable></el-input>
      </div>
      <div class="form-item">
        <label>注册地址</label>
        <el-input size="small" v-model="infoList.address" clearable></el-input>
      </div>
      <div class="form-item">
        <label>联系电话</label>
        <el-input size="small" v-model="infoList.phone" clearable></el-input>
      </div>
      <div class="form-item">
        <label>传真</label>
        <el-input size="small" v-model="infoList.fax" clearable></el-input>
      </div>
      <div class="form-item">
        <label>法定代表人</label>
        <el-input size="small" v-model="infoList.representative" clearable></el-input>
      </div>
      <div class="form-item">
        <label>职工人数</label>
        <el-input size="small" v-model="infoList.workerCount" clearable></el-input>
      </div>
      <div class="form-item">
        <label>安全管理人数</label>
        <el-input size="small" v-model="infoList.managerCount" clearable></el-input>
      </div>
      <div class="form-item">
        <label>成立时间</label>
        <el-date-picker v-model="infoList.setUpAt" size="small" align="right" type="date" placeholder="选择日期">
        </el-date-picker>
      </div>
      <div class="form-item">
        <label>建筑面积</label>
        <el-input size="small" v-model="infoList.area" clearable></el-input>
      </div>
      <div class="form-item">
        <label>安全管理人员</label>
        <el-input size="small" v-model="infoList.managerPersonner" clearable></el-input>
      </div>
      <div class="form-item">
        <label>主要负责人</label>
        <el-input size="small" v-model="infoList.head" clearable></el-input>
      </div>
      <div class="form-item">
        <label>注册资本</label>
        <el-input size="small" v-model="infoList.setUpCapital" clearable></el-input>
      </div>
      <div class="form-item">
        <label>固定资产</label>
        <el-input size="small" v-model="infoList.fixedAssets" clearable></el-input>
      </div>
      <div class="form-item">
        <label>去年营业额</label>
        <el-input size="small" v-model="infoList.lastYearLines" clearable></el-input>
      </div>
      <div class="form-item">
        <label>主要产品</label>
        <el-input type="textarea" :rows="3" size="small" v-model="infoList.appInfo" clearable></el-input>
      </div>
      <div class="form-item bottons">
        <el-button type="info" plain size="medium">取消</el-button>
        <el-button type="primary" size="medium">确定</el-button>
      </div>
    </div>
    <div class="more">
      <img src="../../../common/images/erweima.png">
    </div>
  </div>
</template>
<script>

const enterpriseinfoService = require('../../../service/enterpriseinfoService.js');
export default {
  data() {
    return {
      infoList: {
        code: '91440300319595747J',
        name: '深圳易元安全技术有限公司',
        address: '深圳市龙岗区平湖街道凤凰大道371号平湖商会C区',
        phone: '0755-84658781',
        fax: '0755-84658780',
        representative: '谢小宝',
        workerCount: '7',
        managerCount: '10',
        setUpAt: '2019-09-10',
        area: '1000',
        managerPersonner: '19',
        head: '张丹峰',
        setUpCapital: '1200万',
        fixedAssets: '100万',
        lastYearLines: '5000万',
        appInfo: "消防设施检测维护保养；建筑消防设施检测、智能化设施竣工检测和专项检测、消防产品安全检测、消防检测的技术研发；电气系统安全检测；安全生产技术咨询；安全管理信息咨询；安全生产应急预案技术咨询；安全生产标准化技术咨询；安全生产评价与评估技术咨询；职业卫生咨询；摄影服务、影视制作；营业经营演出活动及经纪业务。安全、消防、应急技能培训；职业卫生培训。"
      }
    }
  },

  created(){
    this.getInfo();
  },

  methods: {
    
    /**
     * 获取企业信息
     */
    getInfo(){
        _than = this;
      enterpriseinfoService.getEnterpriseInfo( res=>{
        if(1 == res.code){
          _than.infoList = res.data
        }
      });
    }
  }
}

</script>
<style scoped>
#info {
  height: 100%;
  display: flex;
  background-color: #fff;
}

.form-center {
  padding: 20px;
  flex: 1;
}

.form-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.form-item label {
  display: inline-block;
  width: 100px;
  font-size: 14px;
  color: #555555;
}

.more {
  width: 240px;
}

.more img {
  display: block;
  margin: 0 auto;
  margin-top: 20px;
  width: 140px;
  height: 136px;
}

.el-date-editor {
  width: 100% !important;
}

.bottons{
	margin: 20px auto;
	width: 200px;
	display: flex;
	justify-content: space-between;
}

</style>

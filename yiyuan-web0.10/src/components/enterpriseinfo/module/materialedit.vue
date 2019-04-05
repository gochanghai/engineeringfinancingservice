<template>
  <div class="useredit">
    <el-dialog :title="materialeditTitle" :visible.sync="materialeditVisible" width="20%" :before-close="materialeditClose">
      <el-form label-width="80px" :model="materialeditData" size="small">
        <el-form-item label="器材名称">
          <el-input v-model="materialeditData.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="型号规格">
          <el-input v-model="materialeditData.model" clearable></el-input>
        </el-form-item>
        <el-form-item label="存放位置">
          <el-input v-model="materialeditData.deposit" clearable></el-input>
        </el-form-item>
        <el-form-item label="数量">
          <el-input v-model="materialeditData.count" clearable></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-input v-model="materialeditData.status" clearable></el-input>
        </el-form-item>
        <el-form-item label="责任人">
          <el-input v-model="materialeditData.personLiable" clearable></el-input>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="materialeditData.contact" clearable></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="small" @click="materialeditClose">取 消</el-button>
        <el-button size="small" type="primary" @click="save">提 交</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
const equipmentService = require('../../../service/equipmentService.js');
export default {
  data() {
    return {
      materialeditData: {
        id: '',
        name: '',
        model: '',
        deposit: '',
        count: '',
        status: '',
        personLiable: '',
        contact: ''
      }
    }
  },

  props: {
    materialeditTitle: {
      type: String,
      default: ''
    },
    materialeditVisible: {
      type: Boolean,
      default: false
    }
  },

  methods: {
    /**
     * 关闭弹窗
     */
    materialeditClose() {
      this.$emit('materialeditClose', false)
    },

    /**
     * 编辑则获取数据
     */
    getUserInfo(){
      if("修改设备器材" === this.materialeditTitle){
        equipmentService.getById(this.materialeditData.id, res => {
          console.log(res);
        });
      }
    },

    /**
     * 保存数据
     */
    save(){
      let materialedit = {
        id: this.materialeditData.id,
        name: this.materialeditData.name,
        model: this.materialeditData.model,
        deposit: this.materialeditData.deposit,
        count: this.materialeditData.count,
        status: this.materialeditData.status,
        personLiable: this.materialeditData.personLiable,
        contact: this.materialeditData.contact,
      };
      // 判断添加还是更新
      if("添加设备器材" === this.materialeditTitle){
        equipmentService.add(materialedit, res => {
          console.log(res);
        });
      }

      if("修改设备器材" === this.materialeditTitle){
        equipmentService.updateById(materialedit, res => {
          console.log(res);
        });
      }
      
    }
  }
}

</script>
<style scoped>
.dialog-footer {
  display: block;
  text-align: center;
}

</style>

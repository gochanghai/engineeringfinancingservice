<template>
  <div class="updatealarm">
    <el-dialog :title="updatealarmTitle" :visible.sync="updatealarmVisible" width="20%" :before-close="updateClose">
      <el-form :label-position="labelPosition" label-width="80px" :model="alarmConfig" size="small">
        <el-form-item label="警告名称">
          <el-input v-model="alarmConfig.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="警告类型">
          <el-select v-model="alarmConfig.alarmType" style="display: block;" clearable>
            <el-option v-for="item in alarmType" :key="item.id" :label="item.name" :value="item.name"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="最大值">
          <el-input v-model="alarmConfig.max_value" clearable></el-input>
        </el-form-item>
        <el-form-item label="最小值">
          <el-input v-model="alarmConfig.min_value" clearable></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
		    <el-button size="small" @click="updateClose">取 消</el-button>
		    <el-button size="small" type="primary" @click="save">提 交</el-button>
		  </span>
    </el-dialog>
  </div>
</template>
<script>

const alarmConfigService = require('../../../service/alarmConfigService.js');
export default {
  data() {
    return {
      labelPosition: 'left',
      alarmConfig: {
        id: '',
        name: '',
        alarmType: '',
        max_value: '',
        min_value: ''
      },
      alarmType: [{
        name: '一氧化碳',
        id: '0'
      }, {
        name: '硫化氢',
        id: '1'
      }, {
        name: '氧气',
        id: '2'
      }, {
        name: '人体感应',
        id: '3'
      }, {
        name: '摄像头',
        id: '4'
      }],

      rules: {
          name: [
            { required: true, message: '警告名称不能为空', trigger: 'blur' }
          ],
          alarmType: [
            { required: true, message: '警告类行不能为空', trigger: 'change' }
          ],
          max_value: [
            { type: 'date', required: true, message: '最大值不能为空', trigger: 'change' }
          ],
          min_value: [
            { type: 'date', required: true, message: '最小值不能为空', trigger: 'change' }
          ]
        }
    }
  },

  props: {
    updatealarmVisible: {
      type: Boolean,
      default: false
    },
    updatealarmTitle: {
      type: String,
      default: ''
    }
  },

  methods: {
    /**
     * 关闭弹窗
     */
    updateClose() {
      this.$emit('updateClose', false);
    },

    /**
     * 获取要更新的配置数据
     */
    getAlarmin_valuefo(id){
      let _than = this;
      if("更新警告配置" === this.updatealarmTitle){
        alarmConfigService.getById(id, res => {
            _than.alarmConfig = res;
        })
      }        
    },

    /**
     * 保存按钮处理函数
     */
    save(){
      let alarmConfig = this.alarmConfig;
      // 提交前检查是否填写正确
      this.$refs['alarmConfig'].validate((valid) => {
          if (valid) {
            if("添加警告配置" === this.updatealarmTitle){
                alarmConfigService.add(alarmConfig, res => {
                    console.log(res);
                })
            }
            if("更新警告配置" === this.updatealarmTitle){
              alarmConfigService.updateById(alarmConfig, res => {
                console.log(res);
              })
            }
          } else {
            console.log('error submit!!');
            return false;
          }
        });        
    }

  }
}

</script>
<style scoped>
.dialog-footer {
  display: block;
  text-align: center;
  /*border: 1px solid;*/
}

</style>

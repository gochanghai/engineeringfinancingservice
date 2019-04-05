<template>
  <div id="app">
    <router-view />
  </div>
</template>
<script>
export default {
  name: 'App'
}

</script>
<style>
.el-dialog__header {
  background-color: #007ED7;
  padding: 13px 20px;
}

.el-dialog__title {
  color: #fff;
  font-size: 16px;
}

.el-dialog__headerbtn .el-dialog__close {
  color: #fff;
}

.search-input /deep/.el-input__inner {
  width: 290px;
  background-color: #eee;
  border-radius: 30px;
  color: #555;
  border: none;
}

#app {
  /*font-family: 'NotoSansHans-Regular'*/
}

@font-face {
  font-family: 'NotoSansHans-Regular';
  src: url('./common/css/NotoSansHans-Regular.otf');
}

</style>

//异步：
// wx.getStorage({
//   key: 'ProjectList',
//   success: function (res) {
//     console.log("get callback data...");
//     _this.setData({
//       getProjectList: res.data
//     });
//     console.log(_this.data.getProjectList);
//   }
// })
//统一使用同步的方式


function getUser() {
  // let user = {
  //   account: "shangwjl",
  //   userName: "商务经理",
  // }
  var user = wx.getStorageSync('user');
  return user;
}

//登录时传入，程序在login.js，但只有account传入了，而name为空，待决定用户的姓名由何处获取
function setUser(userAccount,userName) {
  wx.removeStorageSync('user')
  let user = {
    account: userAccount,
    userName: userName
  }
  wx.setStorageSync("user", user)
}

function getProjectList() {
  var projectList = wx.getStorageSync('projectList');
  return projectList;
};

function setProjectList(projectList) {
  wx.removeStorageSync('projectList')
  wx.setStorageSync("projectList", projectList);
}

module.exports = {

  getUser: getUser,
  setUser: setUser,

  getProjectList: getProjectList,
  setProjectList: setProjectList,

}
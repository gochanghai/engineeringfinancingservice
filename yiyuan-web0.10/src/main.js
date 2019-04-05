import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import { get, post } from '@/common/js/http.js'

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.prototype.$post = post;
Vue.prototype.$get = get;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  ElementUI,
  components: { App },
  template: '<App/>'
})

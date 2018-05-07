import Vue from 'Vue';
import App from './components/App.vue';
import VueResource from 'vue-resource';
import Vuelidate from 'vuelidate';
import BootstrapVue from 'bootstrap-vue';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import {alert} from 'vue-strap';

Vue.use(VueResource);
Vue.use(Vuelidate);
Vue.use(BootstrapVue);

new Vue({
    components: {
        alert
    },
    el: '#app',
    render: h => h(App)
});
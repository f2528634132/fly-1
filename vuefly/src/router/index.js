import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import Home from '../components/Home'
import Admin from "../components/Admin";
import Test from "../components/Test";
import Emma from "../components/Emma";
import Content from "../components/Content";
import ExamEdit from "../components/ExamEdit";
import TopicEdit from "../components/TopicEdit";
import MyExam from "../components/MyExam";
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
      hidden:true
    },{
      path:'/Home',
      name:'主页',
      component:Home,
      hidden:true,
      meta:{
        requireAyth:true
      }
    },
    {
      path: '/Admin',
      name: '管理员',
      component: Admin,
      hidden: true,
      meta: {
        requireAyth: true
      }
    },
    {
      path: '/Test',
      name: 'Test',
      component: Test
    },
    {
      path: '/Emma',
      name: 'Emma',
      component: Emma
    },
    {
      path: '/Content',
      name: 'Content',
      component: Content
    },
    {
      path: '/ExamEdit',
      name: 'ExamEdit',
      component: ExamEdit
    },
    {
      path: '/TopicEdit',
      name: 'TopicEdit',
      component: TopicEdit
    },
    {
      path: '/MyExam',
      name: 'MyExam',
      component: MyExam
    }
  ]
})

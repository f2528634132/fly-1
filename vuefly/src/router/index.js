import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import Home from '../components/Home'
import Admin from "../components/Admin";
import Emma from "../components/Emma";
import Content from "../components/Content";
import ExamEdit from "../components/ExamEdit";
import TopicEdit from "../components/TopicEdit";
import MyExam from "../components/MyExam";
import MyExamDetails from "../components/MyExamDetails";
import AddExam from "../components/AddExam";
import ExamScores from "../components/ExamScores";
import MyCollectExam from "../components/MyCollectExam";
import Header from "../components/Header";
Vue.use(Router)

const router =  new Router({
  routes: [
    {
      path: '/',
      redirect: '/Login'
    },
    {
      path: '/Login',
      name: '登录',
      component: Login
    },
    {
      path: '/',
      name: 'Header',
      component: Header,
      children: [
        {
          path:'/Home',
          name:'主页',
          component:Home,
          // hidden:true,
          meta:{
            requireAyth:true
          }
        },
        {
          path: 'Admin',
          name: '管理员',
          component: Admin,
          // hidden: true,
          meta: {
            requireAyth: true
          }
        },
        {
          path: 'Emma',
          name: 'Emma',
          component: Emma
          // component: resolve => require(['../components/Emma'], resolve)
        },
        {
          path: 'Content',
          name: 'Content',
          component: Content
        },
        {
          path: 'ExamEdit',
          name: 'ExamEdit',
          component: ExamEdit
        },
        {
          path: 'ExamScores',
          name: 'ExamScores',
          component: ExamScores
        },
        {
          path: 'AddExam',
          name: 'AddExam',
          component: AddExam
        },
        {
          path: 'TopicEdit',
          name: 'TopicEdit',
          component: TopicEdit
        },
        {
          path: 'MyExam',
          name: 'MyExam',
          component: MyExam
        },
        {
          path: 'MyCollectExam',
          name: 'MyCollectExam',
          component: MyCollectExam
        },
        {
          path: 'MyExamDetails',
          name: 'MyExamDetails',
          component: MyExamDetails
        }
      ]
    }
  ]
});

// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
  if (to.path === '/Login') {
    next();
  } else {
    let token = localStorage.getItem('Authorization');

    if (token === null || token === '') {
      next('/Login');
    } else {
      next();
    }
  }
});
export default router;

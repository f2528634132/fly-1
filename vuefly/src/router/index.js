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
import ForumType from "../components/ForumType";
import Topic from "../components/Topic";
import PersonList from "../components/PersonList";
import SystemList from "../components/SystemList";
import ExamTopColl from "../components/ExamTopColl";
import AdInfo from "../components/AdInfo";
import ForumTopic from "../components/ForumTopic";
import MyExamNote from "../components/MyExamNote";
import PersonBasic from "../components/PersonBasic";
import JoinRate from "../components/JoinRate";
import AddExamScoreOpen from "../components/AddExamScoreOpen";
import Institution from "../components/Institution";
import Tutorial from "../components/Tutorial";
import MyCollectForum from "../components/MyCollectForum";
import MyCollectAd from "../components/MyCollectAd";
import Message from "element-ui/packages/message/src/main";
import MessageDetails from "../components/MessageDetails";
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
        { path:'/Home', component:Home, meta:{requireAuth:true}},
        { path: '/ForumType', component: ForumType},
        { path: '/Topic', component: Topic},
        { path: '/AddExam',component: AddExam},
        { path: '/Admin', component: Admin, meta: {requireAuth: true}},
        { path: '/Emma',  component: Emma},
        { path: '/Content', component: Content},
        { path: '/ExamEdit',  component: ExamEdit},
        {path: '/ExamScores',  component: ExamScores},
        {path: '/TopicEdit', component: TopicEdit},
        {path: '/MyExam', component: MyExam},
        {path: '/MyCollectExam', component: MyCollectExam},
        {path: '/MyExamDetails',  component: MyExamDetails},
        {path: '/PersonList',  component: PersonList},
        {path: '/SystemList',  component: SystemList},
        {path: '/ExamTopColl',  component: ExamTopColl},
        {path: '/AdInfo',  component: AdInfo},
        {path: '/ForumTopic',  component: ForumTopic},
        {path: '/MyExamNote',  component: MyExamNote},
        {path: '/PersonBasic',  component: PersonBasic},
        {path: '/JoinRate',  component: JoinRate},
        {path: '/AddExamScoreOpen',  component: AddExamScoreOpen},
        {path: '/Institution',  component: Institution},
        {path: '/Tutorial',  component: Tutorial},
        {path: '/MyCollectForum',  component: MyCollectForum},
        {path: '/MyCollectAd',  component: MyCollectAd},
        {path: '/MessageDetails',  component: MessageDetails},
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

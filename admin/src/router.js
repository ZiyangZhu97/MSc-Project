import Vue from "vue"
import Router from "vue-router"
import Login from "./views/login.vue"
import Admin from "./views/admin.vue"
import Welcome from "./views/admin/welcome.vue"
import Category from "./views/admin/category.vue"
import Program from "./views/admin/program.vue"
import Chapter from "./views/admin/chapter.vue"
import Episode from "./views/admin/episode.vue"
import File from "./views/admin/file.vue"
import User from "./views/admin/user.vue"


Vue.use(Router);

export default new Router({
    mode: "history",
    base: process.env.BASE_URL,
    routes: [{
        path: "*",
        redirect: "/login",
    }, {
        path: "",
        redirect: "/login",
    }, {
        path: "/login",
        component: Login
    }, {
        path: "/",
        name: "admin",
        component: Admin,
        meta: {
            loginRequire: true
        },
        children: [{
            path: "welcome",
            name: "welcome",
            component: Welcome,
        }, {
            path: "business/category",
            name: "business/category",
            component: Category,
        }, {
            path: "business/program",
            name: "business/program",
            component: Program,
        }, {
            path: "business/chapter",
            name: "business/chapter",
            component: Chapter,
        }, {
            path: "business/episode",
            name: "business/episode",
            component: Episode,
        }, {
            path: "file/file",
            name: "file/file",
            component: File,
        }, {
            path: "system/user",
            name: "system/user",
            component: User,

        }]
    }]
})

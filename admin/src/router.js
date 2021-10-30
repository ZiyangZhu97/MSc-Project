import Vue from "vue"
import Router from "vue-router"
import Login from "./views/login.vue"
import Admin from "./views/admin.vue"
import Welcome from "./views/admin/welcome.vue"
import Language from "./views/admin/language.vue"
import Genre from "./views/admin/genre.vue"
import Program from "./views/admin/program.vue"
import Chapter from "./views/admin/chapter.vue"
import Episode from "./views/admin/episode.vue"


Vue.use(Router);

export default new Router({
    mode: "history",
    base: process.env.BASE_URL,
    routes: [{
        path: "*",
        redirect: "/login",
    }, {
        path: "/login",
        component: Login
    }, {
        path: "/",
        name: "admin",
        component: Admin,
        children: [{
            path: "welcome",
            name: "welcome",
            component: Welcome,
        }, {
            path: "business/language",
            name: "business/language",
            component: Language,
        }, {
            path: "business/genre",
            name: "business/genre",
            component: Genre,
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
        }]
    }]
})

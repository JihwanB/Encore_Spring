// router config 파일
import { createRouter, createWebHistory } from 'vue-router';
import Home from '../pages/WorkIndexPage.vue';
import Works from '../pages/works/WorkIndex.vue';
import Work from '../pages/works/WorkView.vue';
import WorkCreate from '../pages/works/create/WorkCreate.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        // router 1개를 의미한다
        // localhost:8080/ -> WorkIndexPage.vue
        {
            path: '/',
            name: 'Home',
            component: Home
        },
        // localhost:8080/works -> WorkIndex.vue
        {
            path: '/works',
            name: 'Works',
            component: Works
        },
        // localhost:8080/works/:id -> Work.vue
        {
            path: '/works/:id',
            name: 'Work',
            component: Work
        },
        // localhost:8080/works/create -> WorkCreate.vue
        {
            path: '/works/create',
            name: 'WorkCreate',
            component: WorkCreate
        }
    ]

});

export default router;
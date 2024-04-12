<template>

    <!--
    요구사항
    ################################################
    Vue Router
    - 페이지 이동
    - 카드 목록에서 카드를 선택했을 때, 상세페이지로 이동
    - 설치 : npm install vue-router@4
    - src/router/index.js(config) 파일 생성
    - main.js 에서 router 등록

    - 페이지 트랜지션 발생하려면
    - <router-view/> 사용
    - 기존 href 속성을 사용해서는 안됨 (페이지가 reload 되기 때문)
    - to 속성으로 href 대체
    - to 속성을 쓰기 위해서는 <a> 태그 대신에 <router-link> 태그 사용
    - router.push() : script 영역에서 이벤트를 통해서 페이지 이동
    ///////////////// 이벤트 버블링
    - stop, prevent (script의 stopPropagation, preventDefault와 같은 기능)
        - e.g., click.stop, submit.prevent
    ################################################
    -->

    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container">
            <router-link class="navbar-brand" :to="{ name: 'Home' }">Hanwha SW Camp 5th</router-link>

            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <router-link class="nav-link active" aria-current="page"
                        :to="{ name: 'Works' }">To-Work</router-link>
                </li>
            </ul>
        </div>
    </nav>

    <router-view />

</template>

<script>
</script>

<style>
.error {
    color: red;
}

.work {
    color: green;
    text-decoration: line-through;
}
</style>

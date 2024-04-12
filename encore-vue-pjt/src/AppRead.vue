<template>

    <!--
    요구사항
    ################################################
    - 카드목록 선택시 상세페이지로 이동
    - 상세페이지에서 데이터 수정 및 상태정보 수정 발생하였을 떄, json-server 통신을 통해서 데이터 업데이트 하는 작업
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

    <div class="container">
        <router-view />
    </div>
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
